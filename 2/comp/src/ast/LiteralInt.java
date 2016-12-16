// LiteralInt.java
// João Marcos Costa Salles RA: 489972
// Henrique Teruo Eihara RA: 490016
package ast;

import java.util.ArrayList;

public class LiteralInt extends Expr {
    
    public LiteralInt( int value ) { 
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
    public void genC( PW pw, boolean putParenthesis, ArrayList<String[]> current, ArrayList<String[]> pai ) {
        pw.printIdent("" + value);
    }
    
    public Type getType() {
        return Type.intType;
    }
    public void genKra( PW pw, boolean putParenthesis ) {
        pw.print("" + value);
    }
    
    
    private int value;
}
