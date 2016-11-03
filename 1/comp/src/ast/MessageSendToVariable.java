package ast;


public class MessageSendToVariable extends MessageSend { 

    public Type getType() { 
        return null;
    }
    
    @Override
    public void genC( PW pw, boolean putParenthesis ) {
        
    }
    
    @Override
    public void genKra( PW pw, boolean putParenthesis ) {
        
    }
    
    
}    