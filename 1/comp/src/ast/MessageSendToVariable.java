package ast;

public class MessageSendToVariable extends MessageSend {

	public MessageSendToVariable(String methodName, ExprList exprList){
		this.methodName = methodName;
		this.exprList = exprList;
	}

	public String getMethodName() {
		return methodName;
	}

	public ExprList getExprList() {
		return exprList;
	}

	// nome do método
	private String methodName;
	// parametros
	private ExprList exprList;

	public Type getType() {
		return null;
	}

	@Override
	public void genC(PW pw, boolean putParenthesis) {

	}

	@Override
	public void genKra(PW pw, boolean putParenthesis) {

	}

}
