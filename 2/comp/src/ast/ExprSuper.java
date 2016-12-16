// ExprSuper.java
// João Marcos Costa Salles RA: 489972
// Henrique Teruo Eihara RA: 490016
package ast;

/**
 *
 * @author floss
 */
public class ExprSuper extends Expr{
	
	private MethodDec_class variable; // é um metodo
        private ExprList exprList;
	
	public ExprSuper(MethodDec_class variable){
		this.variable = variable;		
	}
        
        public ExprSuper(MethodDec_class variable, ExprList expr){
		this.variable = variable;	
                this.exprList = expr;
	}

	@Override
	public void genC(PW pw, boolean putParenthesis) {
		String linha = "";
                pw.print("super." + this.variable.getName()+ "(");
                int i = 0;
                for(Expr item : this.exprList.getExpr()){
                    if(i == 0){
                        item.genC(pw, putParenthesis);
                    }else{
                        pw.print(", ");
                        item.genC(pw, putParenthesis);
                    }
                    i++;
                }
                pw.print(")");
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
