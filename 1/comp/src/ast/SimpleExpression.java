// SimpleExpression.java
// João Marcos Costa Salles RA: 489972
// Henrique Teruo Eihara RA: 490016
package ast;

import lexer.Symbol;

/**
 *
 * @author joaosalles
 */
public class SimpleExpression extends Expr {
    
    
     @Override
	public void genC( PW pw, boolean putParenthesis ) {}
    
    @Override
    public Type getType() {
        return this.variable.getType();
    }
    
    public String getName(){
        return this.variable.getName();
    }
            
    public SimpleExpression(String name, Type type){
        this.variable = new Variable(name,type);
    }
            
    
    @Override
	public void genKra( PW pw, boolean putParenthesis ) {}
    
    private Variable variable;
    
}
