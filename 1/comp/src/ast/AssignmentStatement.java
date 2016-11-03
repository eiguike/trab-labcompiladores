/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
	}
        
  
        
        
        public void genKra(PW pw) {
//            this.assignExpr.genKra(pw);
        }
	
}
