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
public class WriteStatement extends Statement {
	
	public WriteStatement(ExprList exprList, String ln){
		this.exprList = exprList;
		this.ln = ln;
	}
	
	private String ln;

	public String getLn() {
		return ln;
	}
	
	public ExprList getExprList(){
		return this.exprList;
	}

	@Override
	public void genC(PW pw) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
	private ExprList exprList;
	
        @Override
	public void genKra(PW pw) {
            String linha = "";
            pw.printIdent("write( ");
            this.exprList.genKra(pw);
            pw.print(" ); \n");
	}
}
