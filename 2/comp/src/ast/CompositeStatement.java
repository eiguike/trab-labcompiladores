// CompositeStatement.java
// João Marcos Costa Salles RA: 489972
// Henrique Teruo Eihara RA: 490016
package ast;

import java.util.ArrayList;

/**
 *
 * @author floss
 */
public class CompositeStatement extends Statement {

	private ArrayList<Statement> stmtList;

	public CompositeStatement(ArrayList<Statement> stmtList) {
		this.stmtList = stmtList;
	}

	public ArrayList<Statement> getStmtList() {
		return stmtList;
	}

	@Override
	public void genC(PW pw, boolean putParenthesis, ArrayList<String[]> current, ArrayList<String[]> pai) {
		if (this.stmtList == null) {
			return;
		}
		for (Statement item : this.stmtList) {
			item.genC(pw, false, null, null);
		}

	}

	@Override
	public void genKra(PW pw) {
		if (this.stmtList == null) {
			return;
		}
		for (Statement item : this.stmtList) {
			item.genKra(pw);
		}
	}

}
