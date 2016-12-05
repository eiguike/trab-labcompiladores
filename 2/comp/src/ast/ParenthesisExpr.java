// ParenthesisExpr.java
// João Marcos Costa Salles RA: 489972
// Henrique Teruo Eihara RA: 490016
package ast;

public class ParenthesisExpr extends Expr {
    
    public ParenthesisExpr( Expr expr ) {
        this.expr = expr;
    }
    
    public void genC( PW pw, boolean putParenthesis ) {
        pw.print("(");
        expr.genC(pw, false);
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