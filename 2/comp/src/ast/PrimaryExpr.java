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
public class PrimaryExpr extends Expr {

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
	public void genKra(PW pw, boolean putParenthesis) {
		String linha = "";
		if (this.valueThis != null) {
			linha += "this.";
		}

		if (this.valueSuper) {
			linha += "super.";
		}
		if (this.id1 != null) {
			linha += this.id1;
		}
		if (this.id2 != null) {
			linha += "." + this.id2;
		}
		if (this.id3 != null) {
			linha += "." + this.id3;
		}
		if (this.expr != null) {
			linha += "(";
			pw.print(linha);
			this.expr.genKra(pw);
			pw.print(")");
		} else {
			pw.print(linha);
		}

	}

	@Override
	public void genKra(PW pw, boolean putParenthesis, Symbol op) {
		String linha = "";
		linha += op.toString();
		if (this.valueThis != null) {
			linha += "this.";
		}
		if (this.valueSuper) {
			linha += "super.";
		}
		if (this.id1 != null) {
			linha += this.id1;
		}
		if (this.id2 != null) {
			linha += "." + this.id2;
		}
		if (this.id3 != null) {
			linha += "." + this.id3;
		}
		if (this.expr != null) {
			linha += "(";
			pw.print(linha);
			this.expr.genKra(pw);
			pw.print(")");
		} else {
			pw.print(linha);
		}

	}

	@Override
	public Type getType() {
		return this.type;
	}

	public PrimaryExpr() {
		this.valueThis = null;
		this.valueSuper = false;
		this.expr = null;
		this.id1 = null;
		this.id2 = null;
		this.id3 = null;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public void addID1(Variable type_entra) {
		this.id1 = type_entra;
	}

	public void addID2(Variable type_entra) {
		this.id2 = type_entra;
	}

	public void addID3(Variable type_entra) {
		this.id3 = type_entra;
	}

	public void setExprList(ExprList expr_entra) {
		this.expr = expr_entra;
	}

	public void setThis(Type value) {
		this.valueThis = value;
	}

	public ExprList getExpr() {
		return this.expr;
	}

	private Type valueThis;
	private boolean valueSuper;
	private Variable id1;
	private Variable id2;
	private Variable id3;
	private ExprList expr;
	private Type type;

	@Override
	public void genC(PW pw, boolean putParenthesis, ArrayList<String[]> current, ArrayList<String[]> pai) {
		String linha = "";
		Integer i;
		if (this.valueThis != null) {
			if(this.id1 instanceof InstanceVariable){
				// acessando variável de instância
				linha += "this->_"+this.valueThis.getName();
			}else if(this.id1 instanceof MethodDec_class){
				linha += "_"+this.valueThis.getName()+"_"+this.id1.getName()+"(";
				linha += "this";
				for(Expr expr_ : this.expr.getExpr()){
					if(expr_ instanceof LiteralInt){
						linha += ", ";
						linha += ((LiteralInt) expr_).getValue();
					}
				}
				linha += ")";
			}
		}else if ((this.id1 != null)&&(this.id2 == null)) {
			linha += "_" + this.id1.getName();
		}
		
		Integer i_aux = 0;
		if (this.id2 != null) {
			if(this.id1.getType() instanceof KraClass){
				KraClass aux = (KraClass) this.id1.getType();
				Variable auxv = null;
				
				////////////////////////////
				////////////////////////////
//				for (String[] s : pai){
//					if(s[0].compareTo(this.id2.getName()) == 0){
//						auxv = new Variable(null,null);
//						break;
//					}
//					i_aux += 1; 
//				}
				if(auxv == null){
					for (String[] s : current){
						if(s[0].compareTo(this.id2.getName()) == 0){
							auxv = null;
							break;
						}
						i_aux += 1;
					}	
				}
				
				////////////////////////////
				////////////////////////////
//				for(Variable m : aux.getMethodList()){;
//					if(m.getName().compareTo(this.id2.getName())== 0){
//						break;
//					}
//					auxv = m;
//					i_aux +=1;
//				}
				////////////////////////////
				////////////////////////////
				////////////////////////////////////////////////////////
				
				linha += "( (";
				
				if(this.id2.getType() != null){
					linha+= " "+ this.id2.getType().getName() +" ";
				}
				linha	+= " (*)(_class_"+aux.getCname();
				linha += "* ";
				if(this.expr.getSizeExprList() > 0){
					for(Expr t : this.expr.getExpr()){
						linha += ",";
						linha += t.getType().getCname();
					}
				}
				
				linha += ")) _" + this.id1.getName()+"->vt["+ i_aux +"])(_"+this.id1.getName()+"";
				if(this.expr.getSizeExprList() > 0){
					pw.print(linha+", ");
					this.expr.genC(pw, putParenthesis, current, pai);
				}else{
					pw.print(linha);
				}
				linha = "";
			}
			
//			for (i = current.size() - 1; i >= 0; i--) {
//				if (current.get(i)[0].compareTo(this.id2) == 0) {
//					linha += current.get(i)[1];
//					break;
//				}
//			}
			
			linha += ")";
//			linha += "." + this.id2;
		}
		if (this.id3 != null) {
			linha += "." + this.id3;
		}

			pw.print(linha);
	}
	
	public void genC(PW pw, boolean putParenthesis, ArrayList<String[]> current, ArrayList<String[]> pai, Symbol op) {
		String linha = "";
		Integer i;
		if (this.valueThis != null) {
			linha += "this.";
		}

		if (this.valueSuper) {
			linha += "super.";
		}
		if ((this.id1 != null)&&(this.id2 == null)) {
			linha += "_" + this.id1.getName();

		}
		Integer i_aux = 0;
		if (this.id2 != null) {
				KraClass aux = (KraClass) this.getType();
				
				for(Variable m : aux.getMethodList()){
					if(m.getName().compareTo(this.id2.getName())== 0){
						break;
					}
					i_aux +=1;
				}
				linha += "( (void (*)(_class_"+aux.getCname();
				linha += "*) ";
				if(this.expr.getSizeExprList() > 0){
					for(Expr t : this.expr.getExpr()){
						pw.print(",");
						pw.print(t.getType().getCname());
					}
				}
				linha += ") _" + this.id1+"->vt["+ i_aux +"])(_"+this.id1+"";
				if(this.expr.getSizeExprList() > 0){
					for(Expr t : this.expr.getExpr()){
						pw.print(",");
					}
				}
			
//			for (i = current.size() - 1; i >= 0; i--) {
//				if (current.get(i)[0].compareTo(this.id2) == 0) {
//					linha += current.get(i)[1];
//					break;
//				}
//			}
			
			if(expr != null){
				linha+= "";
				this.expr.genC(pw, putParenthesis, current, pai);
			}
			
			linha += ")";
//			linha += "." + this.id2;
		}
		if (this.id3 != null) {
			linha += "." + this.id3;
		}

			pw.print(linha);
	}	

}
