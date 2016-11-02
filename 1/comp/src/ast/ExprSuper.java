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
public class ExprSuper extends Expr{
	
	private MethodDec_class variable; // é um metodo
	
	public ExprSuper(MethodDec_class variable){
		this.variable = variable;		
	}

	@Override
	public void genC(PW pw, boolean putParenthesis) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public Type getType() {
		return variable.getType();
	}
	
}
