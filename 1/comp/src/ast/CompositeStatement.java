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
public class CompositeStatement extends Statement {
	
	private ArrayList<Statement> stmtList;
	
	public CompositeStatement(ArrayList<Statement> stmtList){
		this.stmtList = stmtList;		
	}

	public ArrayList<Statement> getStmtList() {
		return stmtList;
	}

	public void genC(PW pw) {

	}
	
}
