// KraClass.java
// João Marcos Costa Salles RA: 489972
// Henrique Teruo Eihara RA: 490016
package ast;

import comp.ErrorSignaller;
import java.util.ArrayList;
import java.util.Iterator;
import lexer.Symbol;

/*
* Krakatoa Class
 */
public class KraClass extends Type {

	public KraClass(String name, KraClass superclass, InstanceVariableList variableList) {
		super(name);
		this.superclass = superclass;
		this.instanceVariableList = variableList;
	}

	public KraClass(String name, KraClass superclass, InstanceVariableList variableList, ArrayList<Variable> methodDecList_entra) {
		super(name);
		this.superclass = superclass;
		this.instanceVariableList = variableList;
		this.methodDecList = methodDecList_entra;
	}

	public void addElement(MethodDec_class v) {
		methodDecList.add(v);
	}

	public Iterator<Variable> elements() {
		return methodDecList.iterator();
	}

        
        
        //para metodo em methodDec
        public MethodDec_class message2(MessageSendToSuper msg, ErrorSignaller errorSignal) {
		KraClass aux_superclass = this.getSuper();
		while (true) {
			if (aux_superclass == null) {
				break;
			}
			ArrayList<Variable> methodDecList_aux = aux_superclass.getMethodList();

			// tenho a superclasse, devo procurar o método
			for (Variable v : methodDecList_aux) {
				if (msg.getNameMethod().compareTo(v.getName()) == 0) {
					// encontrei o método, verificar possibildiade de utilização deles
					// pelo parâmetros enviados...

					//ESTAREI FAZENDO ESSA VERIFICAÇÃO DPS. POR ENQUANTO, VAI SER SÓ PELO NÚMERO DE EXPRESSÕES
					// PQ É UMA VERIFICAÇÃO DE SEMÃNTICA ESSE ROLE
					if (v instanceof MethodDec_class) { // devo verificar se não é uma variável, apesar que pelo fluxo não existira, mas creio que teremos esse problema
						if (msg.getExprList().getSizeExprList() == ((MethodDec_class) v).getParamList().getSize()) {
							// tudo limpo e tem tal função...
							// preparar mensagem de retorno...
							// NESSE MOMENTO RETORNAREI A CLASSE, PQ SÓ QUERO VER SE ESTA FUCNIOANDO.
							// O IDEAL É RETORNAR UMA MENSAGEM QUE TENHA O MÉTODO, A CLASSE, O TIPO DE RETORNO E ETC...
							return (MethodDec_class) v;
						}
					}
				}
			}
			aux_superclass = aux_superclass.getSuper();
		}
		return null;

	}
	// recebe a mensagem referente a classe super
	// preciso receber o errorSignal para fazer os tratamentos
	public MethodDec_class message(MessageSendToSuper msg, ErrorSignaller errorSignal) {
		KraClass aux_superclass = this.getSuper();
		if (aux_superclass == null) {
			errorSignal.showError("'super' used in class '"+this.getCname()+"' that does not have a superclass");
		}
		while (true) {
			if (aux_superclass == null){
				errorSignal.showError("Method '"+msg.getNameMethod()+"' was not found in superclass or its superclasses");
			}
			ArrayList<Variable> methodDecList_aux = aux_superclass.getMethodList();

			// tenho a superclasse, devo procurar o método
			for (Variable v : methodDecList_aux) {
				if (msg.getNameMethod().compareTo(v.getName()) == 0) {
					// encontrei o método, verificar possibildiade de utilização deles
					// pelo parâmetros enviados...

					//ESTAREI FAZENDO ESSA VERIFICAÇÃO DPS. POR ENQUANTO, VAI SER SÓ PELO NÚMERO DE EXPRESSÕES
					// PQ É UMA VERIFICAÇÃO DE SEMÃNTICA ESSE ROLE
					if (v instanceof MethodDec_class) { // devo verificar se não é uma variável, apesar que pelo fluxo não existira, mas creio que teremos esse problema
						if (msg.getExprList().getSizeExprList() == ((MethodDec_class) v).getParamList().getSize()) {
							// tudo limpo e tem tal função...
							// preparar mensagem de retorno...
							// NESSE MOMENTO RETORNAREI A CLASSE, PQ SÓ QUERO VER SE ESTA FUCNIOANDO.
							// O IDEAL É RETORNAR UMA MENSAGEM QUE TENHA O MÉTODO, A CLASSE, O TIPO DE RETORNO E ETC...
							if (((MethodDec_class) (v)).getQualifier() == Symbol.PRIVATE) {
								errorSignal.showError("Method 'p' was not found in the public interface of 'A' or its superclasses");
								}
							return (MethodDec_class) v;
						}
					}
				}
			}
			// queria ter feito recursivo, mas não passo pelo paarmetro a classe atual
			// vai ser assim mesmo, iterativo.
			aux_superclass = aux_superclass.getSuper();
			//if (aux_superclass == null) {
			//	errorSignal.showError("não há métodos no rolê... " + this.getCname());
			//}
		}

	}

	public boolean isSubType(KraClass p){
		KraClass aux;
		aux = p;
		while(aux != null && (this.getName().compareTo(aux.getName()) != 0)){
			aux = aux.getSuper();
		}

		if(aux == null){
			return false;
		}else{
			return true;
		}
	}

	public String getCname() {
		return getName();
	}

	public KraClass getSuper() {
		return this.superclass;
	}

	public ArrayList<Variable> getMethodList() {
		return methodDecList;
	}

	public InstanceVariableList getInstance() {
		return instanceVariableList;
	}

	public ArrayList<Variable> parametros(String method) {
		for (Variable item : this.methodDecList) {//procura pelo metodos
			if (item.getName().equals(method)) {
				return item.getParameter();
			}
		}
		return null;
	}

	public Variable message(MessageSendToVariable message, ErrorSignaller signalError) {
		for (Variable v : methodDecList) {
			if (v.getName().compareTo(message.getMethodName()) == 0) {
				// o método tem o mesmo nome...
				if (v.getParameter().size() == message.getExprList().getSizeExprList()) {
					// tem a mesma quantidade de parâmetros, ta ok, posso devolver o método que é...

					// verificar se o método é private, se for, tem q bugar
					if(((MethodDec_class)v).getQualifier() == Symbol.PRIVATE){
						signalError.showError("Method '"+v.getName()+"' was not found in the public interface of 'A' or its superclasses");
						
					}
					return v;
				}
			}
		}
		MessageSendToSuper msg = new MessageSendToSuper(message.getExprList(), message.getMethodName());
		Variable v = this.message2(msg, signalError);

		if(v != null){
			return v;
		}

		signalError.showError("Method '"+message.getMethodName()+"' was not found in class '"+this.getCname()+"' or its superclass");
		return null;
	}

	public MethodDec_class message(MessageSendToSelf message, ErrorSignaller signalError) {
		boolean achouMethod = false;
		ArrayList<Variable> parametros = null;
		Variable metodo = null;
		for (Variable item : this.methodDecList) {//procura pelo metodos
			if (item.getName().equals(message.getString())) {
				if((item.getParameter().size() == message.getExprList().size())&&(item.getParameter().size() > 0)){
					Integer i = 0;
						if(item.getParameter().get(i).getType().getName().compareTo(message.getExprList().get(i).getType().getName()) == 0){
							// variável é a mesma, nada faz
						}else{
							// ok não é do mesmo tipo, verificar se é do mesmo subtipo
							if((item.getType() instanceof KraClass)&&(message.getExprList().get(i).getType() instanceof KraClass)){
								KraClass aux = ((KraClass)item.getType());
								while((aux != null) && (aux != message.getExprList().get(i).getType())){
									aux = aux.getSuper();
								}

								if(aux == null){
									// não é do mesmo subtipo, não foi encontrado classe super
									signalError.showError("Type error: the type of the real parameter is not subclass of the type of the formal parameter");
								}
								
							}else{
								signalError.showError("Type error: the type of the real parameter is not subclass of the type of the formal parameter");
								// não é do mesmo subtipo pq não é classe
							}
						}
						i++;
					if((((MethodDec_class) item).getQualifier()) == Symbol.PRIVATE){
						signalError.showError("Method '"+item.getName()+"' was not found in the public interface of '"+((KraClass)item.getType()).getCname()+"' or its superclasses (comp.Compiler.factor())");
					}
					return (MethodDec_class) item;
				}else{
					// achou o metodo e não tem nada que possa impedir
					return (MethodDec_class) item;
				}
			}
		}

		if(achouMethod == false){
			MethodDec_class aux = this.message2(new MessageSendToSuper(new ExprList(message.getExprList()),message.getString()) ,signalError);
			if(aux == null){
				signalError.showError("Method '"+message.getString()+"' was not found in class 'A' or its superclasses");
			}
			if (aux.getParamList().getSize() != message.getExprList().size()) {
				signalError.showError("Parameter number different");
			}

			return aux;
		}

		return (MethodDec_class) metodo;
	}
        
        public Variable messageVariable(String procura) {
		boolean achouMethod = false;
		ArrayList<Variable> parametros = null;
		Variable metodo = null;
                for(Variable item   : this.instanceVariableList.getInstanceVariableList()){
                    if (item.getName().equals(procura)) {
                        return item;
                      }
                }
                    
		for (Variable item2 : this.methodDecList) {//procura pelo metodos
			if (item2.getName().equals(procura)) {
				parametros = this.parametros(item2.getName());
				return item2;
			}
		}
                
		int i = 0;
//		ArrayList<Expr> message_pametros = message.getExprList();
//		for (Variable item2 : parametros) {
//			if (item2.getType() != message_pametros.get(i).getType()) {
//				signalError.showError("Type error: the type of the real parameter is not subclass of the type of the formal parameter");
//				return null;
//			}
//
//			i++;
//		}
                KraClass aux_class = null;
                if(this.superclass != null){
                    aux_class = this.superclass;
                }
                while(aux_class != null){
                    aux_class.messageVariable(procura);
                }
                    
                    
		return  metodo;
	}
                
        

	public void genKra(PW pw, boolean putParenthesis) {
		String linha = "";
		linha = "new " + this.getCname() + "()";
		pw.print(linha);

	}
        
        public void genC(PW pw, boolean putParenthesis) {
		String linha = "";
		linha = "new " + this.getCname() + "()";
		pw.print(linha);

	}

        
	public ArrayList<String[]> currentMethod(){
		ArrayList<String[]> current = new ArrayList<String[]>();
		String[] aux = new String[2];
		for (Variable item : this.methodDecList) {
			aux[0] = item.getName();
			aux[1] = this.getName();
			current.add(aux);
		}
		return current;
	}
	
        public ArrayList<String[]> parentMethod(KraClass pai){
            ArrayList<String[]> current = new ArrayList<String[]>();
	    String[] aux = new String[2];
            if (pai != null){
                if(pai.getSuper() != null){
                    current = parentMethod(pai.getSuper());
                }
                for (Variable item : pai.methodDecList) {
			aux[0] = item.getName();
			aux[1] = pai.getName();
			current.add(aux);
                }
            }
            return current;
        }
        
        public String VTcreation(KraClass currentClass, PW pw, String line){
            
            if(currentClass.getSuper() != null){
               line = this.VTcreation(currentClass.getSuper(), pw, line);
            }
           boolean primeiro = true;
           for (Variable item : currentClass.methodDecList) {
                if(item.getQualifier() == Symbol.PUBLIC){
                    
                    if(line.isEmpty()){
                        line = "( void (*)() ) _" + currentClass.getCname() + "_" + item.getName();
                    }else{
                        line += ", \n";
                        line += "\t( void (*)() ) _" + currentClass.getCname() + "_" + item.getName();
                    }
//                    if(primeiro){
//                        primeiro = false;
//                        pw.printIdent("( void (*)() ) _" + currentClass.getCname() + "_" + item.getName() + ";");
//			pw.println();
//                    }else{
//                        pw.printlnIdent("( void (*)() ) _" + currentClass.getCname() + "_" + item.getName() + ";");
//			pw.println();
//                    }
                }
            }
           return line;
        }
        
        public void genC(PW pw) {
		String linha;
                ArrayList<String[]> thisMethod = null;
                ArrayList<String[]> parentMethod = null;
		linha = "\ntypedef \n   struct __St_" + this.getCname() + "{";
                
//                if(this.superclass != null){
//                    linha += " extends " + this.superclass.getCname();
//                }
		pw.printIdent(linha);
		pw.printIdent("\n");
		this.instanceVariableList.genC(pw);
		pw.printIdent("\n");
                if (!this.methodDecList.isEmpty()){
                    pw.add();
                    pw.printIdent("Func *vt;\n");
                    pw.sub();
                    
                }
                thisMethod =  this.currentMethod();
                parentMethod = this.parentMethod(this.superclass);
                pw.print("} _class_"+ this.getCname() + ";\n\n");
                pw.printlnIdent("_class_" + this.getCname() + " *new_" + this.getCname() + "(void);\n");
		for (Variable item : this.methodDecList) {
                    item.genC(pw, this.getCname(),thisMethod, parentMethod);
		}
                pw.println("Func VTclass_" + this.getCname() + "[] = {");
                Boolean primeiro = true;
                pw.add();
                pw.printIdent(this.VTcreation(this, pw, ""));
                pw.sub();
                pw.print("\n};\n\n");
                
                
                 pw.println("_class_" + this.getCname() + " *new_" + this.getCname() + "()" +"{");
                 pw.add();
                 pw.printlnIdent("_class_" + this.getCname() + " *t;");
                 pw.printlnIdent("if ( ( t = malloc(sizeof(_class_" + this.getCname() + "))) != NULL )");
                 pw.add();
                 pw.printlnIdent("t->vt = VTclass_" + this.getCname() + ";");
                 pw.sub();
                 pw.printlnIdent("return t;");
                 pw.sub();
	}
        

	public void genKra(PW pw) {
		String linha;
		linha = "Class " + this.getCname();
                if(this.superclass != null){
                    linha += " extends " + this.superclass.getCname();
                }
                linha += " {\n";
		pw.printIdent(linha);
		pw.printIdent("\n");
		this.instanceVariableList.genKra(pw);
		pw.printIdent("\n");

		for (Variable item : this.methodDecList) {
			item.genKra(pw);
		}
                pw.print("}");
	}

	private String name; //id
	private KraClass superclass; // extends
	private InstanceVariableList instanceVariableList;
	private ArrayList<Variable> methodDecList;
	private MessageSendToSelf self;

	// private MethodList publicMethodList, privateMethodList;
	// métodos públicos get e set para obter e iniciar as variáveis acima,
	// entre outros métodos
}
