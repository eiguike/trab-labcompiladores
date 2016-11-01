/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

/**
 *
 * @author floss
 */
public class WhileStatement extends Statement {
	private Statement stmt;
	private Expr expr;
	
	public WhileStatement(Statement stmt, Expr expr){
		this.expr = expr;
		this.stmt = stmt;		
	}

	public Statement getStmt() {
		return stmt;
	}

	public Expr getExpr() {
		return expr;
	}
	
	public void genC(PW pw) {
		
	}	
}
