/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
            
    private Variable variable;
    
}
