// ReturnStatement.java
// João Marcos Costa Salles RA: 489972
// Henrique Teruo Eihara RA: 490016
package ast;

/**
 *
 * @author floss
 */
public class ReturnStatement extends Statement{
	private Expr expr;
	
	public ReturnStatement(Expr expr) {
		this.expr = expr;
	}
	
	public Expr getExpr(){
		return this.expr;
	}

	@Override
	public void genC(PW pw) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
        
        @Override
        public void genKra(PW pw) {
            pw.printIdent("return ");
            this.expr.genKra(pw, false);
            pw.print(";\n");
            pw.println();
	}
}
