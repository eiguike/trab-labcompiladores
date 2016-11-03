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

        @Override
	public void genC(PW pw) {

	}
        
        @Override
        public void genKra(PW pw) {
            if (this.stmtList == null){
                return ;
            }
            for(Statement item : this.stmtList){
                item.genKra(pw);
            }
	}
	
}
