// ReadStatement.java
// João Marcos Costa Salles RA: 489972
// Henrique Teruo Eihara RA: 490016
package ast;

import java.util.ArrayList;

/**
 *
 * @author floss
 */
public class ReadStatement extends Statement{
	public ReadStatement(ArrayList<Variable> variableList){
		this.variableList = variableList;
	}

	public ArrayList<Variable> getVariableList() {
		return variableList;
	}
		
	private ArrayList<Variable> variableList;

	@Override
	public void genC(PW pw) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
        
        @Override
        public void genKra(PW pw) {
            String linha = "";
            pw.printIdent("read( ");
            int i = 0;
            for(Variable item : this.variableList){
                if (i == 0){
                     item.genKra(pw);
                }else{
                    pw.print(", ");
                    item.genKra(pw);
                }
                i++;
            }
            pw.print(");\n");
	}
}
