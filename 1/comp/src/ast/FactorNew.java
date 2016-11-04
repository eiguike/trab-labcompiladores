// FactorNew.java
// João Marcos Costa Salles RA: 489972
// Henrique Teruo Eihara RA: 490016
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
            this.kraclass.genKra(pw, false);
	}
	@Override
	public Type getType() {
		return kraclass;
	}
	
}
