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

	// recebe a mensagem referente a classe super
	// preciso receber o errorSignal para fazer os tratamentos
	public MethodDec_class receivingMessage(MessageSendToSuper msg, ErrorSignaller errorSignal) {
		KraClass aux_superclass = this.getSuper();
		while (true) {
			if (aux_superclass == null) {
				errorSignal.showError("Não há super classe nesta classe " + this.getCname());
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
        
        public KraClass getSuper(){
            return this.superclass;
        }
	
        public ArrayList<Variable> getMethodList() {
            return methodDecList;
        }
        
        public InstanceVariableList getIntance() {
            return instanceVariableList;
        }
        
        public ArrayList<Variable> parametros(String method){
             for(Variable item : this.methodDecList){//procura pelo metodos
                 if (item.getName().equals(method) ){
                    return item.getParameter();
                 }
             }
             return null;
        }
        
        public MethodDec_class message(MessageSendToSelf message, ErrorSignaller signalError) {
            boolean achouMethod = false;
            ArrayList<Variable> parametros = null;
            Variable metodo = null;
            for(Variable item : this.methodDecList){//procura pelo metodos
                if (item.getName().equals(message.getString()) ){
                    parametros = this.parametros(item.getName());
                    metodo = item;
                    achouMethod = true;
                    break;
                }
            }
            if (!achouMethod){
                signalError.showError("Method does not exist");
                return null;
            }
            if (parametros.size() != message.getExp().size()){
                signalError.showError("Parameter number different");
                return null;
            }
            int i = 0;
            ArrayList<Expr> message_pametros =  message.getExp();
            for(Variable item : parametros){
                if(item.getType() != message_pametros.get(i).getType()){
                    signalError.showError("Parameter value different");
                    return null;
                }
                
               i++;
            }
            return (MethodDec_class) metodo;
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
