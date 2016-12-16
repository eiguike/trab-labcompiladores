// WhileStatement.java
// João Marcos Costa Salles RA: 489972
// Henrique Teruo Eihara RA: 490016
package ast;

import java.util.ArrayList;

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
	
	@Override
	public void genC(PW pw, boolean putParenthesis, ArrayList<String[]> current, ArrayList<String[]> pai) {
            String linha = "";
//            pw.add();
            pw.printIdent("while(");
            this.expr.genC(pw, false, null, null);
             pw.print(") {\n");
            pw.add();
            this.stmt.genC(pw, false, null, null);
	    pw.print("\n");
            pw.sub();
            pw.printIdent("}\n\n");
	}	
        
        @Override
        public void genKra(PW pw) {
            String linha = "";
//            pw.add();
            pw.printIdent("while(");
            this.expr.genKra(pw, false);
             pw.print(") {\n");
            pw.add();
            this.stmt.genKra(pw);
            pw.sub();
            pw.printIdent("}\n\n");
//            pw.sub();
	}
}
