// DoWhileStatement.java
// João Marcos Costa Salles RA: 489972
// Henrique Teruo Eihara RA: 490016
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
        
        @Override
        public void genKra(PW pw) {

	}
	
}
