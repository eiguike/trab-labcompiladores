// LocalDec.java
// João Marcos Costa Salles RA: 489972
// Henrique Teruo Eihara RA: 490016
package ast;

import java.util.ArrayList;

/**
 *
 * @author floss
 */
public class LocalDec extends Expr{

	public void genC(PW pw, boolean putParenthesis) {
	}

	public LocalDec(Type type, ArrayList<Variable> variableList){
		this.type = type;
		this.variableList = variableList;
	}
	
	public Type getType() {
		return type;	
	}

	public ArrayList<Variable> getVariableList() {
		return variableList;
	}
	
        @Override
        public void genKra(PW pw, boolean putParenthesis) {
           String linha ="";
//           pw.print(this.type.getName();
            pw.print(this.type.getName());
            int aux = 0;
           for (Variable item : variableList){
               if (aux == 0){
                    pw.print(" " + item.getName());
               }else{
                   pw.print(", " + item.getName());
               }
           }
	}
        
	
	private Type type;
	private ArrayList<Variable> variableList;

}
