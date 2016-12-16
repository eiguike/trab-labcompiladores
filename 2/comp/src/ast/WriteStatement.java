// WriteStatement.java
// João Marcos Costa Salles RA: 489972
// Henrique Teruo Eihara RA: 490016
package ast;

import java.util.ArrayList;

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
	public void genC(PW pw, boolean putParenthesis, ArrayList<String[]> current, ArrayList<String[]> pai) {
		String linha = "";
		ArrayList<Expr> elem = this.exprList.getExpr();
		Integer i;
		
		pw.printIdent("printf(\"");
		for(i = 0; i < elem.size(); i++){
			if(i > 0){
				pw.print(",");
			}
			if(elem.get(i).getType().getCname().compareTo("int") == 0){
				pw.print("%d");
			}else if(elem.get(i).getType().getCname().compareTo("char *") == 0){
				pw.print("%s");
			}
		}
		if(this.ln != ""){
			pw.print("\\n");
		}
		pw.print("\"");
		for(i = 0; i < elem.size(); i++){
			if(i >= 0){
				pw.print(", ");
			}
			if(elem.get(i).getType().getCname().compareTo("char *") == 0){
				pw.print("\"");
				elem.get(i).genC(pw, false, null, null);
				pw.print("\"");
			}else{
				elem.get(i).genC(pw, false, null, null);
			}
		}
		pw.print("); \n");
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
