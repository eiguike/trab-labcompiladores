package ast;

abstract public class Statement {

	public Statement() {
		
	}

	abstract public void genC(PW pw);

}
