package ast;

import lexer.Symbol;

public class InstanceVariable extends Variable {

    public InstanceVariable( String name, Type type ) {
        super(name, type);
    }
    
    public InstanceVariable( String name, Type type, Symbol sym_entra , Symbol sym_entra1, Symbol sym_entra2) {
        super(name, type);
        this.qualifier = sym_entra;
        this.quali_static = sym_entra1;
        this.quali_final = sym_entra2;
    }
    
    public Symbol getQualifier(){
        return this.qualifier;
    }
    
    public Symbol getQuali_static() {
        return this.quali_static;
    }
    
    public Symbol getQuali_final() {
        return this.quali_final;
    }

    private Symbol qualifier;
    private Symbol quali_static;
    private Symbol quali_final; 
}