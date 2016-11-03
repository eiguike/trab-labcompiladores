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
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
