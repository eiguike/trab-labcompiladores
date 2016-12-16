// LiteralString.java
// João Marcos Costa Salles RA: 489972
// Henrique Teruo Eihara RA: 490016
package ast;

import java.util.ArrayList;

public class LiteralString extends Expr {
    
    public LiteralString( String literalString ) { 
        this.literalString = literalString;
    }
    
    public void genC( PW pw, boolean putParenthesis, ArrayList<String[]> current, ArrayList<String[]> pai ) {
        pw.print(literalString);
    }
    
    public Type getType() {
        return Type.stringType;
    }
    
    
    @Override
    public void genKra( PW pw, boolean putParenthesis ) {
        pw.print("\"");
        pw.print(literalString);
        pw.print("\"");
    }

    private String literalString;
}
