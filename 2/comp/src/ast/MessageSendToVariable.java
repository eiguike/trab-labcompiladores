// MessageSendToVariable.java
// João Marcos Costa Salles RA: 489972
// Henrique Teruo Eihara RA: 490016
package ast;

import java.util.ArrayList;

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
	public void genC(PW pw, boolean putParenthesis, ArrayList<String[]> current, ArrayList<String[]> pai) {

	}

	@Override
	public void genKra(PW pw, boolean putParenthesis) {

	}

}
