// GenericStatement.java
// João Marcos Costa Salles RA: 489972
// Henrique Teruo Eihara RA: 490016
package ast;

import java.util.ArrayList;

/**
 *
 * @author floss
 */
// utilziado para o nullstatement e break
public class GenericStatement extends Statement{
	private String stmt;

	public String getStmt() {
		return stmt;
	}
	
	public GenericStatement(String stmt){
		this.stmt = stmt;
	}

	@Override
	public void genC(PW pw, boolean putParenthesis, ArrayList<String[]> current, ArrayList<String[]> pai) {
            pw.printIdent(this.stmt);
	}
        
        @Override
        public void genKra(PW pw) {
            pw.printlnIdent(this.stmt);
	}
	
}
