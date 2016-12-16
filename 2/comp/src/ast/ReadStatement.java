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
		String linha = "";
		Integer i;
		
		pw.printIdent("scanf(\"");
		for(i = 0; i < this.variableList.size(); i++){
			if(i > 0){
				pw.print(" ");
			}
			if(this.variableList.get(i).getType().getCname().compareTo("int") == 0){
				pw.print("%d");
			}else if(this.variableList.get(i).getType().getCname().compareTo("char *") == 0){
				pw.print("%s");
			}
		}
		pw.print("\"");
		for(i = 0; i < this.variableList.size(); i++){
			if(i >= 0){
				pw.print(", ");
			}
			if(this.variableList.get(i).getType().getCname().compareTo("char *") == 0){
				this.variableList.get(i).genC(pw, null);
			}else{
				pw.print("&");
				this.variableList.get(i).genC(pw, null);
			}
		}
		pw.print("); \n");
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
