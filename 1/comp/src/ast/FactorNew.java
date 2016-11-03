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
public class FactorNew extends Expr{
	public FactorNew(KraClass kraclass){
		this.kraclass = kraclass;
	}
	
	private KraClass kraclass;

	public KraClass getKraclass() {
		return kraclass;
	}
	
	@Override
	public void genC(PW pw, boolean putParenthesis) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

        @Override
	public void genKra(PW pw, boolean putParenthesis) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	@Override
	public Type getType() {
		return kraclass;
	}
	
}
