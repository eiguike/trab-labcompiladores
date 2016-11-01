package ast;

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
        
	private String name; //id
	private KraClass superclass; // extends
	private InstanceVariableList instanceVariableList;
        private ArrayList<Variable> methodDecList;
	// private MethodList publicMethodList, privateMethodList;
	// métodos públicos get e set para obter e iniciar as variáveis acima,
	// entre outros métodos
}
