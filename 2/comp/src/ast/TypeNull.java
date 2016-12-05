// TypeNull.java
// João Marcos Costa Salles RA: 489972
// Henrique Teruo Eihara RA: 490016
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
