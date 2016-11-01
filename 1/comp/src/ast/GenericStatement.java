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
	public void genC(PW pw) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
}
