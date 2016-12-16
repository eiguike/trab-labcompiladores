// UnaryExpr.java
// João Marcos Costa Salles RA: 489972
// Henrique Teruo Eihara RA: 490016
package ast;

import java.util.ArrayList;
import lexer.*;

public class UnaryExpr extends Expr {

	public UnaryExpr(Expr expr, Symbol op) {
		this.expr = expr;
		this.op = op;
	}

	@Override
	public void genC(PW pw, boolean putParenthesis, ArrayList<String[]> current, ArrayList<String[]> pai) {
		switch (op) {
		case PLUS:
//                        expr.genC(pw, putParenthesis,op);
			expr.genC(pw, putParenthesis,current, pai, op);
//			pw.print("+");
			break;
		case MINUS:
			expr.genC(pw, putParenthesis,current, pai, op);
//                        expr.genC(pw,putParenthesis,op);
//			pw.print("-");
			break;
		case NOT:
			pw.print("!");
			expr.genC(pw, putParenthesis,current, pai, op);
//                        expr.genC(pw, putParenthesis, op);
			
			break;
		default:
			pw.print(" internal error at UnaryExpr::genKra");

		}
//		expr.genKra(pw, putParenthesis);
	}

	@Override
	public Type getType() {
		return expr.getType();
	}
        
        
        @Override
	public void genKra(PW pw, boolean putParenthesis) {
		switch (op) {
		case PLUS:
                        expr.genKra(pw, putParenthesis,op);
//			pw.print("+");
			break;
		case MINUS:
                        expr.genKra(pw,putParenthesis,op);
//			pw.print("-");
			break;
		case NOT:
                        expr.genKra(pw, putParenthesis, op);
//			pw.print("!");
			break;
		default:
			pw.print(" internal error at UnaryExpr::genKra");

		}
//		expr.genKra(pw, putParenthesis);
	}
                
	private Expr	expr;
	private Symbol	op;
}
