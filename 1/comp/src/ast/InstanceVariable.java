package ast;

import lexer.Symbol;

public class InstanceVariable extends Variable {

    public InstanceVariable( String name, Type type ) {
        super(name, type);
    }
    
    public InstanceVariable( String name, Type type, Symbol sym_entra , Symbol sym_entra1 ) {
        super(name, type);
        this.qualifier = sym_entra;
        this.qualifier1 = sym_entra1;
    }
    
    public Symbol getQualifier(){
        return this.qualifier;
    }
    
    public Symbol getQualifier1(){
        return this.qualifier1;
    }

    private Symbol qualifier;
    private Symbol qualifier1;
}