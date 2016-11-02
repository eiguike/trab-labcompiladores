package ast;

import comp.ErrorSignaller;
import java.util.ArrayList;
import java.util.Iterator;

/*
* Krakatoa Class
*/
public class KraClass extends Type {
	
	public KraClass( String name , KraClass superclass, InstanceVariableList variableList) {
		super(name);
		this.superclass = superclass;
		this.instanceVariableList = variableList;
	}
        public KraClass( String name , KraClass superclass, InstanceVariableList variableList,  ArrayList<Variable> methodDecList_entra) {
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
        
        public boolean message(MessageSendToSelf message, ErrorSignaller signalError) {
            boolean achouMethod = false;
            for(Variable item : this.methodDecList){//procura pelo metodos
                if (item.getName().equals(message.getString()) ){
                    ArrayList<Variable> parametros = this.parametros(item.getName());
                    achouMethod = true;
                    break;
                }
            }
            if (!achouMethod){
                signalError.showError("Method does not exist");
            }
//           ExprList 
            for(Expr item : message)
            return true;
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
