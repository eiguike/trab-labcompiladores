package ast;

import comp.ErrorSignaller;
import java.util.ArrayList;
import java.util.Iterator;

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
				return null;
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
			// queria ter feito recursivo, mas não passo pelo paarmetro a classe atual
			// vai ser assim mesmo, iterativo.
			aux_superclass = aux_superclass.getSuper();
			//if (aux_superclass == null) {
			//	errorSignal.showError("não há métodos no rolê... " + this.getCname());
			//}
		}

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
				if(item.getParameter().size() == message.getExprList().size())
					return (MethodDec_class) item;
			}
		}

		if(achouMethod == false){
			MethodDec_class aux = this.message2(new MessageSendToSuper(new ExprList(message.getExprList()),message.getString()) ,signalError);
			if(aux == null){
				signalError.showError("não tem o método no pai.");
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

	public void genKra(PW pw) {
		String linha;
		linha = "Class " + this.getCname() + " { \n";
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
