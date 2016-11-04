/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
