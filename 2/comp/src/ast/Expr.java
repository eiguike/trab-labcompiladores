// Expr.java
// João Marcos Costa Salles RA: 489972
// Henrique Teruo Eihara RA: 490016
package ast;

import lexer.Symbol;

abstract public class Expr {
    abstract public void genC( PW pw, boolean putParenthesis );
      // new method: the type of the expression
    abstract public Type getType();
    
    abstract public void genKra( PW pw, boolean putParenthesis );
    
    public void genKra( PW pw, boolean putParenthesis,Symbol op ){
        throw new UnsupportedOperationException("Only CompositeExpr should enter in this method."); //To change body of generated methods, choose Tools | Templates.
    };
}