/*
// Comp.java
// João Marcos Costa Salles RA: 489972
// Henrique Teruo Eihara RA: 490016
 */
package ast;

import java.util.ArrayList;

/**
 *
 * @author floss
 */
public class AssignmentExpr extends Expr{

	public AssignmentExpr(Expr expr1, Expr expr2){
		this.exprList = new ArrayList<Expr>();
		exprList.add(expr1);
		if(expr2 != null)
			exprList.add(expr2);
	}

	public ArrayList<Expr> getExprList() {
		return exprList;
	}
		
	private ArrayList<Expr> exprList;
	
        @Override
	public Type getType() {
		// implementar idk como fazer
		return null;
	}
        
        @Override
        public void genKra(PW pw,  boolean putParenthesis) {
            int i = 0;
            if(this.exprList.size() == 1){
                pw.printIdent("");
                this.exprList.get(0).genKra(pw, putParenthesis);
//                pw.print(")"); //chamda de metodo no assign
            }else{
                for(Expr item : this.exprList){
                    if(i == 0){
                        pw.printIdent("");
                    }
                    item.genKra(pw, putParenthesis);
                    if(i == 0){
                        pw.print(" = ");
                    }
                    i++;
                }
            }
            pw.print(";");
            pw.println();
            pw.println();
        }
        
        public void genC(PW pw, boolean putParenthesis) {
		int i = 0;
            if(this.exprList.size() == 1){
                pw.printIdent("");
                this.exprList.get(0).genC(pw, putParenthesis);
//                pw.print(")"); //chamda de metodo no assign
            }else{
                for(Expr item : this.exprList){
                    if(i == 0){
                        pw.printIdent("");
                    }
                    item.genKra(pw, putParenthesis);
                    if(i == 0){
                        pw.print(" = ");
                    }
                    i++;
                }
            }
            pw.print(";");
            pw.println();
            pw.println();
	}
}
