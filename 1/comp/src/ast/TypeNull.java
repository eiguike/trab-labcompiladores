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
public class TypeNull extends Type{

	public TypeNull() {
		super("null");
	}

	@Override
	public String getCname() {
		return this.getName();
	}
	
}
