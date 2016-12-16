// Statement.java
// João Marcos Costa Salles RA: 489972
// Henrique Teruo Eihara RA: 490016
package ast;

import java.util.ArrayList;

abstract public class Statement {

	public Statement() {
		
	}

	abstract public void genC(PW pw, boolean putParenthesis, ArrayList<String[]> current, ArrayList<String[]> pai);

        
        abstract public void genKra(PW pw);
}
