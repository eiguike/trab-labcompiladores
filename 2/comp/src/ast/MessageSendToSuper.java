// MessageSendToSuper.java
// João Marcos Costa Salles RA: 489972
// Henrique Teruo Eihara RA: 490016
package ast;

import java.util.ArrayList;

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
                 
                 
                 
	public void genC(PW pw, boolean putParenthesis, ArrayList<String[]> current, ArrayList<String[]> pai) {

	}

}
