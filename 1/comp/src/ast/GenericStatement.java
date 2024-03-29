// GenericStatement.java
// João Marcos Costa Salles RA: 489972
// Henrique Teruo Eihara RA: 490016
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
        
        @Override
        public void genKra(PW pw) {
            pw.printlnIdent(this.stmt);
	}
	
}
