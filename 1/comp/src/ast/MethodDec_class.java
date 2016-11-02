/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import java.util.ArrayList;
import java.util.Iterator;
import lexer.Symbol;

/**
 *
 * // João Marcos Costa Salles RA: 489972 // Henrique Teruo Eihara RA: 490016
 */
public class MethodDec_class extends Variable{
    
    
    public MethodDec_class(Symbol symbol_entra, String name, Type type) {
        super(name, type);
        this.qualifier = symbol_entra;
        this.quali_static = null;
        this.quali_final = null;
    }
    
    public MethodDec_class(Symbol symbol_entra, String name, Type type, Symbol symbol_entra1, Symbol symbol_entra2) {
        super(name, type);
        this.qualifier = symbol_entra;
        this.quali_static = symbol_entra1;
        this.quali_final = symbol_entra2;
    }
    
    
    @Override
     public Type getType() {
        return super.getType();
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

	private ParamList paramList;
	private Symbol qualifier;
	private Symbol quali_static;
	private Symbol quali_final;  //final
}
