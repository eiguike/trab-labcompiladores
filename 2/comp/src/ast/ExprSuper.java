// ExprSuper.java
// João Marcos Costa Salles RA: 489972
// Henrique Teruo Eihara RA: 490016
package ast;

import java.util.ArrayList;

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

	@Override
	public void genC(PW pw, boolean putParenthesis, ArrayList<String[]> current, ArrayList<String[]> pai) {
		pw.printIdent("_"+this.classeAtual.getSuper().getCname()+"_"+this.variable.getName());
		pw.print("((_class_"+this.classeAtual.getSuper().getCname()+" * ) this ");
		
		if(exprList.getSizeExprList() > 0 ){
			pw.print(",");
			for(Expr a : exprList.getExpr()){
				if(a.getType() == Type.intType){
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
