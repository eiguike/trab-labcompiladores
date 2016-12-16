// AssignmentStatement.java
// João Marcos Costa Salles RA: 489972
// Henrique Teruo Eihara RA: 490016
package ast;

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
	public void genC(PW pw) {
		this.assignExpr.genC(pw, false);
	}
        
        @Override
        public void genKra(PW pw) {
            this.assignExpr.genKra(pw, false);
        }
	
}
