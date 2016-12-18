// ExprSuper.java
// João Marcos Costa Salles RA: 489972
// Henrique Teruo Eihara RA: 490016
package ast;

import java.util.ArrayList;
import lexer.Symbol;

/**
 *
 * @author floss
 */
public class ExprSuper extends Expr{
	
	private KraClass classeAtual;	
	private MethodDec_class variable; // é um metodo
        private ExprList exprList;
	
	public ExprSuper(MethodDec_class variable){
		this.variable = variable;		
	}
        
        public ExprSuper(MethodDec_class variable, ExprList expr, KraClass classeAtual){
		this.variable = variable;	
                this.exprList = expr;
		this.classeAtual = classeAtual;
	}
	
	// MÉTODO ADICIONADO DEPOIS DA PROVA
	public String classSuperFindingMethod(KraClass super_class, String name){
		boolean flag = false;
		for (Variable v : super_class.getMethodList()){
			if(v.getName().compareTo(name) == 0){
				flag = true;
				break;
			}
		}
		
		if(flag == false){
			return this.classSuperFindingMethod(super_class.getSuper(), name);
		}
		return super_class.getName();
	}

	@Override
	public void genC(PW pw, boolean putParenthesis, ArrayList<String[]> current, ArrayList<String[]> pai) {
		pw.printIdent("_"+this.classSuperFindingMethod(classeAtual.getSuper(), this.variable.getName())+"_"+this.variable.getName());
		pw.print("((_class_"+this.classSuperFindingMethod(classeAtual.getSuper(), this.variable.getName())+" * ) this ");
		
		if(exprList.getSizeExprList() > 0 ){
			pw.print(",");
			for(Expr a : exprList.getExpr()){
				if(a instanceof CompositeExpr){
					
					((CompositeExpr)a).genC(pw, putParenthesis, current, pai, Symbol.OR);
				}else if(a.getType() == Type.intType){
					Integer aux = ((LiteralInt)a).getValue();
					pw.print(aux.toString());
				}else{
					pw.print("_"+a.getType().getCname());
				}
				
	
			}			
		}
		pw.print(");");

	}

	@Override
	public void genKra(PW pw, boolean putParenthesis) {
                String linha = "";
                pw.print("super." + this.variable.getName()+ "(");
                int i = 0;
                for(Expr item : this.exprList.getExpr()){
                    if(i == 0){
                        item.genKra(pw, putParenthesis);
                    }else{
                        pw.print(", ");
                        item.genKra(pw, putParenthesis);
                    }
                    i++;
                }
                pw.print(")");
	}

	@Override
	public Type getType() {
		return variable.getType();
	}
	
}
