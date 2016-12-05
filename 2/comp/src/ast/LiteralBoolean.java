// LiteralBoolean.java
// João Marcos Costa Salles RA: 489972
// Henrique Teruo Eihara RA: 490016
package ast;

import lexer.Symbol;

public class LiteralBoolean extends Expr {

    public LiteralBoolean( boolean value ) {
        this.value = value;
    }

    @Override
	public void genC( PW pw, boolean putParenthesis ) {
       pw.print( value ? "true" : "false" );
    }

    @Override
	public Type getType() {
        return Type.booleanType;
    }
        
     @Override
    public void genKra( PW pw, boolean putParenthesis ) {
       pw.print( value ? "true" : "false" );
    }
    
    @Override
    public void genKra( PW pw, boolean putParenthesis, Symbol op) {
      pw.print(op.toString());
       pw.print( value ? "true" : "false" );
    }   
        

    public static LiteralBoolean True  = new LiteralBoolean(true);
    public static LiteralBoolean False = new LiteralBoolean(false);

    private boolean value;
}
