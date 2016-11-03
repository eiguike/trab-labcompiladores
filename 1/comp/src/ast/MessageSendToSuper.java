package ast;

public class MessageSendToSuper extends MessageSend {

	private ExprList exprList;
	private String nameMethod;
	
	public MessageSendToSuper(ExprList exprList, String nameMethod){
		this.exprList = exprList;
		this.nameMethod = nameMethod;
	}

	public ExprList getExprList() {
		return exprList;
	}

	public String getNameMethod() {
		return nameMethod;
	}
	
	public Type getType() {
		return null;
	}

        
                 
        @Override
        public void genKra(PW pw, boolean putParenthesis) {

	}
                 
                 
                 
	public void genC(PW pw, boolean putParenthesis) {

	}

}
