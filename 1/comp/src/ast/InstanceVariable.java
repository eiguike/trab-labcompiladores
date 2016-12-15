// InstanceVariable.java
// João Marcos Costa Salles RA: 489972
// Henrique Teruo Eihara RA: 490016
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

    public void genKra(PW pw) {
        String linha = "";
        if(this.quali_final != null){
            linha = this.quali_final.toString();
        }
        if(this.quali_static != null){
            linha += " " + this.quali_static.toString();
        }
        linha += " " + this.qualifier.toString() + " " + this.getType().getName() + " " + this.getName() +";\n";
        pw.printIdent(linha);
    }
    
    public void genC(PW pw) {
        String linha = "";
        if(this.quali_final != null){
            linha = this.quali_final.toString();
        }
        if(this.quali_static != null){
            linha += " " + this.quali_static.toString();
        }
        linha += " " + this.qualifier.toString() + " _" + this.getType().getName() + " " + this.getName() +";\n";
        pw.printIdent(linha);
    }
    
    
    private Symbol qualifier;
    private Symbol quali_static;
    private Symbol quali_final; 
}