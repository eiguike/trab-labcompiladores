// MessageSendToSelf.java
// João Marcos Costa Salles RA: 489972
// Henrique Teruo Eihara RA: 490016
package ast;

import java.util.ArrayList;


public class MessageSendToSelf extends MessageSend {
    
    public Type getType() { 
        return null;
    }
    
    public void genC( PW pw, boolean putParenthesis, ArrayList<String[]> current, ArrayList<String[]> pai ) {
    }
    
    @Override
    public void genKra( PW pw, boolean putParenthesis ) {
    }

    public MessageSendToSelf(String string_entra, ExprList exp_entra) { 
        this.method = string_entra;
        this.exprList = exp_entra;
    }
    
    
    public String getString(){
        return this.method;
    }
    
    
    public ArrayList<Expr> getExprList(){
        return this.exprList.getExpr();
    }
    
          
    private String method;
    private ExprList exprList;
    
}