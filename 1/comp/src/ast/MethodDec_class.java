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
public class MethodDec_class {
    
    
    public MethodDec_class(String name_entra, Symbol symbol_entra, Type type_entra) {
       this.name = name_entra;
       this.type = type_entra;
       this.qualifier = symbol_entra;
    }
    
    
     public Type getType() {
        return type;
    }
     
    public void setQualifier(Symbol entra_quali) {
        this.qualifier = entra_quali;
    }
     
    public Symbol getQualifier() {
        return this.qualifier;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name_entra) {
        name = name_entra;
    }
    
    public void setParamList(ParamList paramList_entra) {
        this.paramList = paramList_entra;
    }
    
    private String name;
    private ParamList paramList;
    private Type type;
    private Symbol qualifier;
}
