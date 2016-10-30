package ast;

import java.util.ArrayList;

/*
* Krakatoa Class
*/
public class KraClass extends Type {
	
	public KraClass( String name , KraClass superclass, InstanceVariableList variableList) {
		super(name);
		this.superclass = superclass;
		this.instanceVariableList = variableList;
	}
	
	public String getCname() {
		return getName();
	}
	
	private String name; //id
	private KraClass superclass; // extends
	private InstanceVariableList instanceVariableList;
	// private MethodList publicMethodList, privateMethodList;
	// métodos públicos get e set para obter e iniciar as variáveis acima,
	// entre outros métodos
}
