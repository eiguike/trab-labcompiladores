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
public class BreakStatement extends Statement{

	@Override
	public void genC(PW pw) {
		System.out.println("break;");
	}	
}
