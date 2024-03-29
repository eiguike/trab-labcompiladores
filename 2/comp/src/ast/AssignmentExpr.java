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
public class AssignmentExpr extends Expr {

	public AssignmentExpr(Expr expr1, Expr expr2) {
		this.exprList = new ArrayList<Expr>();
		exprList.add(expr1);
		if (expr2 != null) {
			exprList.add(expr2);
		}
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
	public void genKra(PW pw, boolean putParenthesis) {
		int i = 0;
		if (this.exprList.size() == 1) {
			pw.printIdent("");
			this.exprList.get(0).genKra(pw, putParenthesis);
//                pw.print(")"); //chamda de metodo no assign
		} else {
			for (Expr item : this.exprList) {
				if (i == 0) {
					pw.printIdent("");
				}
				item.genKra(pw, putParenthesis);
				if (i == 0) {
					pw.print(" = ");
				}
				i++;
			}
		}
		pw.print(";");
		pw.println();
		pw.println();
	}

	@Override
	public void genC(PW pw, boolean putParenthesis, ArrayList<String[]> current, ArrayList<String[]> pai) {
		int i = 0;
		if (this.exprList.size() == 1) {
			pw.printIdent("");
			this.exprList.get(0).genC(pw, putParenthesis, current, pai);
//                pw.print(")"); //chamda de metodo no assign
		} else {
			pw.printIdent("");
			this.exprList.get(0).genC(pw, putParenthesis, current, pai);
			pw.print(" = ");
			if (this.exprList.get(0).getType() != this.exprList.get(1).getType()) {
				pw.print("(_class_"+this.exprList.get(0).getType().getName()+" *) ");
			}
			this.exprList.get(1).genC(pw, putParenthesis, current, pai);

//                for(Expr item : this.exprList){
//                    if(i == 0){
//                        pw.printIdent("");
//                    }
//                    item.genC(pw, putParenthesis, current, pai);
//                    if(i == 0){
//                        pw.print(" = ");
//                    }
//                    i++;
//                }
		}
		pw.print(";");
		pw.println();
		pw.println();
	}
}
