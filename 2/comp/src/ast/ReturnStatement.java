// ReturnStatement.java
// João Marcos Costa Salles RA: 489972
// Henrique Teruo Eihara RA: 490016
package ast;

import java.util.ArrayList;

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
	public void genC(PW pw, boolean putParenthesis, ArrayList<String[]> current, ArrayList<String[]> pai) {
		pw.printIdent("return ");
		expr.genC(pw, false, null , null);
		pw.print(";");
		pw.println();
	}
	
        
        @Override
        public void genKra(PW pw) {
            pw.printIdent("return ");
            this.expr.genKra(pw, false);
            pw.print(";\n");
            pw.println();
	}
}
