// VariableExpr.java
// João Marcos Costa Salles RA: 489972
// Henrique Teruo Eihara RA: 490016
package ast;

public class VariableExpr extends Expr {
    
    public VariableExpr( Variable v ) {
        this.v = v;
    }
    
    public void genC( PW pw, boolean putParenthesis ) {
        pw.print( v.getName() );
    }
    
    public Type getType() {
        return v.getType();
    }
    
    @Override
    public void genKra(PW pw,  boolean putParenthesis) {
         pw.print( v.getName() );
        }
    
    
    
    private Variable v;
}