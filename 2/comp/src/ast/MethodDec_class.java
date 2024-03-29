// MethodDec_class.java
// João Marcos Costa Salles RA: 489972
// Henrique Teruo Eihara RA: 490016
package ast;

import java.util.ArrayList;
import java.util.Iterator;
import lexer.Symbol;

/**
 *
 * // João Marcos Costa Salles RA: 489972 // Henrique Teruo Eihara RA: 490016
 */
public class MethodDec_class extends Variable {

	public MethodDec_class(Symbol symbol_entra, String name, Type type) {
		super(name, type);
		this.qualifier = symbol_entra;
		this.quali_static = null;
		this.quali_final = null;
		this.returned = false;
	}

	public MethodDec_class(Symbol symbol_entra, String name, Type type, Symbol symbol_entra1, Symbol symbol_entra2, KraClass classeQuePertenco) {
		super(name, type);
		this.qualifier = symbol_entra;
		this.quali_static = symbol_entra1;
		Symbol quali_final;  //final
		this.quali_final = symbol_entra2;
		this.returned = false;
		this.classeQuePertenco = classeQuePertenco;
	}

	@Override
	public Type getType() {
		return super.getType();
	}

	public KraClass getClasseQuePertenco() {
		return classeQuePertenco;
	}

	public void setQualifier(Symbol entra_quali) {
		this.qualifier = entra_quali;
	}

	public void setQualifier1(Symbol entra_quali) {
		this.quali_static = entra_quali;
	}

	public Symbol getQualifier() {
		return this.qualifier;
	}

	public Symbol getQuali_static() {
		return this.quali_static;
	}

	public Symbol getQuali_final() {
		return this.quali_final;
	}

	public String getName() {
		return super.getName();
	}

	@Override
	public void genKra(PW pw) {
		String linha = "";
		pw.add();
		if (this.quali_final != null) {
			linha = this.quali_final.toString();
		}
		if (this.quali_static != null) {
			linha += " " + this.quali_static.toString();
		}
		linha += " " + this.qualifier.toString() + " " + this.getType().getName() + " " + this.getName() + "( ";
		//+" { \n"
		pw.printIdent(linha);
		if (this.paramList != null) {
			this.paramList.genKra(pw);
		}
		pw.print("){ \n\n");
		pw.add();
		for (Statement item : this.statementList) {
			item.genKra(pw);
		}
		pw.sub();
		pw.printlnIdent("}\n");
		pw.sub();

	}

	public void setStament(ArrayList<Statement> state_entra) {
		this.statementList = state_entra;
	}

	public ArrayList<Statement> getStament() {
		return this.statementList;
	}

	public void setParamList(ParamList paramList_entra) {
		this.paramList = paramList_entra;
	}

	@Override
	public ArrayList<Variable> getParameter() {
		return this.paramList.getPara();
	}

//    public ParamList getParameter() {
//        return this.paramList;
//    }
	public ParamList getParamList() {
		return paramList;
	}

	public void genC(PW pw, String class_name) {
		String linha = "";
		if (this.quali_final != null) {
			linha = this.quali_final.toString();
		}
		if (this.quali_static != null) {
			linha += " " + this.quali_static.toString();
		}
		linha += " " + this.getType().getCname() + " " + "_" + class_name + "_" + this.getName() + "( _class_" + class_name + " *this";
		//+" { \n"
		pw.printIdent(linha);
		if (this.paramList != null) {

			this.paramList.genC(pw);
		}
		pw.print("){ \n\n");
		pw.add();
		for (Statement item : this.statementList) {
			item.genC(pw, false, null, null);
		}
		pw.sub();
		pw.printlnIdent("}\n");

	}

	public void genC(PW pw, String class_name, ArrayList<String[]> current, ArrayList<String[]> parent) {
		String linha = "";
		if (this.quali_final != null) {
			linha = this.quali_final.toString();
		}
		if (this.quali_static != null) {
			linha += " " + this.quali_static.toString();
		}
		linha += " " + this.getType().getCname() + " " + "_" + class_name + "_" + this.getName() + "( _class_" + class_name + " *this";
		//+" { \n"
		pw.printIdent(linha);
		if (this.paramList != null) {
			this.paramList.genC(pw);
		}
		pw.print("){ \n\n");
		pw.add();
		for (Statement item : this.statementList) {
			item.genC(pw, false, current, parent);
		}
		pw.sub();
		pw.printlnIdent("}\n");

	}

	private ArrayList<Statement> statementList;
	private ParamList paramList;
	private Symbol qualifier;
	private Symbol quali_static;
	private Symbol quali_final;  //final
	private Boolean returned;
	private KraClass classeQuePertenco;

	public ArrayList<Statement> getStatementList() {
		return statementList;
	}

	public void setStatementList(ArrayList<Statement> statementList) {
		this.statementList = statementList;
	}

	public Boolean getReturned() {
		return returned;
	}

	public void setReturned(Boolean returned) {
		this.returned = returned;
	}

}
