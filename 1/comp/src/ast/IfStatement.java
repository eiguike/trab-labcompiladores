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
public class IfStatement extends Statement{
	private Expr expr;
	private Statement stmt;
	private Statement stmtElse;
	
	public IfStatement(Expr expr, Statement stmt, Statement stmtElse){
		this.expr = expr;
		this.stmt = stmt;
		this.stmt = stmtElse;
	}

	public Expr getExpr() {
		return expr;
	}

	public Statement getStmt() {
		return stmt;
	}

	public Statement getStmtElse() {
		return stmtElse;
	}
	
	
	@Override
	public void genC(PW pw) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
	
}
