// PrimaryExpr.java
// João Marcos Costa Salles RA: 489972
// Henrique Teruo Eihara RA: 490016
package ast;

import java.util.ArrayList;
import lexer.Symbol;

/**
 *
 * @author joaosalles
 */
public class PrimaryExpr  extends Expr{
	
	public void genC( PW pw, ArrayList<String> current, ArrayList<String> parent ){
		String linha = "";
		if(this.valueThis){
			linha += "this.";
		}
		
		if(this.valueSuper){
			linha += "super.";
		}
		if(this.id1 != null){
			linha += "_"+this.id1;
		}
		if(this.id2  != null){
			linha += "."+ this.id2;
		}
		if(this.id3 != null){
			linha += "."+ this.id3;
		}
		if(this.expr != null ){
			linha += "(";
			pw.print(linha);
			this.expr.genKra(pw);
			pw.print(")");
		}else{
			pw.print(linha);
		}

	}
	
//	public void genC( PW pw, ArrayList<String> current, ArrayList<String> parent){
//
//		Integer i;
//		boolean flag= false;
//		if(this.id3 == null){
//			if(this.id2 == null){
//
//			}else{
//				// id2 é método, mas pode ser variável
//				if(this.expr != null){
//					//id2 é um método
//					// encontrando nós próprios métodos da classe
//					for(i=current.size()-1; i >= 0; i--){
//						if(parent.get(i).compareTo(this.id1) == 0){
//							flag = true;
//							break;
//						}
//					}
//					// procurando nos métodos p´ublicos existentes
//					for(i=parent.size()-1; i >= 0; i--){
//						if((parent.get(i).compareTo(this.id1) == 0)||(flag == true)){
//							flag = true;
//							break;		
//						}
//					}
//
//					pw.printIdent("(_"+id1+"->vt["+i+"])(_"+id1+", ");
//					expr.genC(pw);
//					pw.println(")");
//
//				}
//			}
//		}else{
//			// id3 é método
//			if(this.id1 != null){
//				for(i=parent.size()-1; i >= 0; i--){
//					if(parent.get(i).compareTo(this.id1) == 0){
//						
//					}
//				}
//				for(i=parent.size()-1; i >= 0; i--){
//				}
//			}
//		}
//	}
	
	@Override
	public void genKra( PW pw, boolean putParenthesis ){
		String linha = "";
		if(this.valueThis){
			linha += "this.";
		}
		
		if(this.valueSuper){
			linha += "super.";
		}
		if(this.id1 != null){
			linha += this.id1;
		}
		if(this.id2  != null){
			linha += "."+ this.id2;
		}
		if(this.id3 != null){
			linha += "."+ this.id3;
		}
		if(this.expr != null ){
			linha += "(";
			pw.print(linha);
			this.expr.genKra(pw);
			pw.print(")");
		}else{
			pw.print(linha);
		}
		
	}
	
	@Override
	public void genKra( PW pw, boolean putParenthesis ,Symbol op){
		String linha = "";
		linha+= op.toString();
		if(this.valueThis){
			linha += "this.";
		}
		if(this.valueSuper){
			linha += "super.";
		}
		if(this.id1 != null){
			linha += this.id1;
		}
		if(this.id2  != null){
			linha += "."+ this.id2;
		}
		if(this.id3 != null){
			linha += "."+ this.id3;
		}
		if(this.expr != null ){
			linha += "(";
			pw.print(linha);
			this.expr.genKra(pw);
			pw.print(")");
		}else{
			pw.print(linha);
		}
		
	}
	
	@Override
	public Type getType(){
		return this.type;
	}
	
	public PrimaryExpr(){
		this.valueThis = false;
		this.valueSuper = false;
		this.expr = null;
		this.id1 = null;
		this.id2 = null;
		this.id3 = null;
	}
	public void setType(Type type){
		this.type = type;
	}
	public void addID1(String type_entra){
		this.id1 = type_entra;
	}
	public void addID2(String type_entra){
		this.id2 = type_entra;
	}
	public void addID3(String type_entra){
		this.id3 = type_entra;
	}
	
	public void setExprList(ExprList expr_entra){
		this.expr = expr_entra;
	}
	
	public void setThis(boolean value){
		this.valueThis = value;
	}
	
	public ExprList getExpr(){
		return this.expr;
	}
	
	private boolean valueThis;
	private boolean valueSuper;
	private String id1;
	private String id2;
	private String id3;
	private ExprList expr;
	private Type type;

	@Override
	public void genC(PW pw, boolean putParenthesis, ArrayList<String[]> current, ArrayList<String[]> pai) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

}
