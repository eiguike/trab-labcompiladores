// WriteStatement.java
// João Marcos Costa Salles RA: 489972
// Henrique Teruo Eihara RA: 490016
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
            if(this.ln == ""){
               pw.printIdent("write( ");
            }else{
                pw.printIdent("writeln( ");
            }
            this.exprList.genKra(pw);
            pw.print(" ); \n");
	}
}
