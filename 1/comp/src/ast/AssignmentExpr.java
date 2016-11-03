/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import java.util.ArrayList;

/**
 *
 * @author floss
 */
public class AssignmentExpr extends Expr{

	public void genC(PW pw, boolean putParenthesis) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
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
            pw.print(";");
            pw.println();
            pw.println();
        }
}
