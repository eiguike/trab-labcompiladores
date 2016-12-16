// IfStatement.java
// João Marcos Costa Salles RA: 489972
// Henrique Teruo Eihara RA: 490016
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
		this.stmtElse = stmtElse;
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
            String linha;
            if(this.expr != null){
                pw.printIdent("if ( ");
                this.expr.genC(pw, false);
                pw.print(") {\n\n");
            }else{
                pw.print("if() {\n\n");
            }
            pw.add();
            if(this.stmt != null){
                this.stmt.genC(pw);
            }
            pw.sub();
            pw.printIdent("}");
            if(this.stmtElse != null){
                pw.print("else{\n");
                pw.add();
                this.stmtElse.genC(pw);
                pw.sub();
                pw.println();
                pw.printIdent("}\n");
            }else{
                pw.print("\n");
            }
	}
	
        
        @Override
        public void genKra(PW pw) {
            String linha;
            if(this.expr != null){
                pw.printIdent("if ( ");
                this.expr.genKra(pw, false);
                pw.print(") {\n\n");
            }else{
                pw.print("if() {\n\n");
            }
            pw.add();
            if(this.stmt != null){
                this.stmt.genKra(pw);
            }
            pw.sub();
            pw.printIdent("}");
            if(this.stmtElse != null){
                pw.print("else{\n");
                pw.add();
                this.stmtElse.genKra(pw);
                pw.sub();
                pw.println();
                pw.printIdent("}\n");
            }else{
                pw.print("\n");
            }
            
	}
	
}
