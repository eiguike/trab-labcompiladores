/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import java.util.ArrayList;

/**
 *
 * @author floss
 */
public class LocalDec extends Expr{

	public void genC(PW pw, boolean putParenthesis) {
	}

	public LocalDec(Type type, ArrayList<Variable> variableList){
		this.type = type;
		this.variableList = variableList;
	}
	
	public Type getType() {
		return type;	
	}

	public ArrayList<Variable> getVariableList() {
		return variableList;
	}
	
	
	private Type type;
	private ArrayList<Variable> variableList;

}
