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
 // João Marcos Costa Salles RA: 489972
// Henrique Teruo Eihara RA: 490016
 */
public class MethodDec_class extends Variable{
    
    
    public MethodDec_class(Symbol symbol_entra, String name, Type type) {
        super(name, type);
        this.qualifier = symbol_entra;
    }
    
    
     public Type getType() {
        return super.getType();
    }
     
    public void setQualifier(Symbol entra_quali) {
        this.qualifier = entra_quali;
    }
     
    public Symbol getQualifier() {
        return this.qualifier;
    }
    
    public String getName() {
        return super.getName();
    }
    
    
    public void setParamList(ParamList paramList_entra) {
        this.paramList = paramList_entra;
    }
    

    private ParamList paramList;
    private Symbol qualifier;
}
