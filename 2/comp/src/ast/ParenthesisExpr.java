// ParenthesisExpr.java
// João Marcos Costa Salles RA: 489972
// Henrique Teruo Eihara RA: 490016
package ast;

import java.util.ArrayList;

public class ParenthesisExpr extends Expr {
    
    public ParenthesisExpr( Expr expr ) {
        this.expr = expr;
    }
    
    public void genC( PW pw, boolean putParenthesis, ArrayList<String[]> current, ArrayList<String[]> pai ) {
        pw.print("(");
        expr.genC(pw, false, null, null);
        pw.printIdent(")");
    }
    
    public Type getType() {
        return expr.getType();
    }
    
    public void genKra( PW pw, boolean putParenthesis ) {
//        pw.print("( ");
        this.expr.genKra(pw, putParenthesis);
//        pw.print(") ");
    }
    private Expr expr;
}