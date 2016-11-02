package ast;


public class MessageSendToSelf extends MessageSend {
    
    public Type getType() { 
        return null;
    }
    
    public void genC( PW pw, boolean putParenthesis ) {
    }
    
    public MessageSendToSelf(String string_entra, ExprList exp_entra) { 
        this.method = string_entra;
        this.exprList = exp_entra;
    }
    
    
    public String getString(){
        return this.method;
    }
    
    
    public ExprList getExp(){
        return this.exprList;
    }
    
          
    private String method;
    private ExprList exprList;
    
}