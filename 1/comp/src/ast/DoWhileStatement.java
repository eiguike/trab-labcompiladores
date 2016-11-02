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
public class DoWhileStatement extends Statement {
	
	public DoWhileStatement(CompositeStatement compStatement, Expr expr){
		this.compStatement = compStatement;
		this.expr = expr;
	}

	public CompositeStatement getCompStatement() {
		return compStatement;
	}

	public Expr getExpr() {
		return expr;
	}
	
	private CompositeStatement compStatement;
	private Expr expr;
	

	@Override
	public void genC(PW pw) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
}
