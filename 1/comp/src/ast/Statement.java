// Statement.java
// João Marcos Costa Salles RA: 489972
// Henrique Teruo Eihara RA: 490016
package ast;

abstract public class Statement {

	public Statement() {
		
	}

	abstract public void genC(PW pw);

        
        abstract public void genKra(PW pw);
}
