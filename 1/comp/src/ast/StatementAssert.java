// StatementAssert.java
// João Marcos Costa Salles RA: 489972
// Henrique Teruo Eihara RA: 490016
package ast;

public class StatementAssert extends Statement {
	public StatementAssert(Expr expr, int lineNumber, String message) {
		this.expr = expr;
		this.lineNumber = lineNumber;
		this.message = message;
	}
	@Override
	public void genC(PW pw) {
		pw.printIdent("if ( !( ");
		expr.genC(pw, false);
		pw.println(" ) ) {");
		pw.add();
		pw.printlnIdent("puts(\"" + message +  "\");");
		pw.sub();
		pw.printlnIdent("}");

	}

	public Expr getExpr() {
		return expr;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public String getMessage() {
		return message;
	}
        
        @Override
        public void genKra(PW pw) {

	}

	private Expr expr;
	private int lineNumber;
	private String message;
}
