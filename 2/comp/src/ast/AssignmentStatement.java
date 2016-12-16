// AssignmentStatement.java
// João Marcos Costa Salles RA: 489972
// Henrique Teruo Eihara RA: 490016
package ast;

import java.util.ArrayList;

/**
 *
 * @author floss
 */
public class AssignmentStatement extends Statement{
	
	private AssignmentExpr assignExpr;
	public AssignmentStatement(AssignmentExpr assignExpr){
		this.assignExpr = assignExpr;
	}

	public AssignmentExpr getAssignExpr() {
		return assignExpr;
	}
	
	@Override
	public void genC(PW pw, boolean putParenthesis, ArrayList<String[]> current, ArrayList<String[]> pai) {
		this.assignExpr.genC(pw, false, current , pai);
	}
        
        @Override
        public void genKra(PW pw) {
            this.assignExpr.genKra(pw, false);
        }
	
}
