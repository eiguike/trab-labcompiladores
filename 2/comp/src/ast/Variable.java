// Variable.java
// João Marcos Costa Salles RA: 489972
// Henrique Teruo Eihara RA: 490016
package ast;

import java.util.ArrayList;
import lexer.Symbol;

public class Variable {

    public Variable( String name, Type type ) {
        this.name = name;
        this.type = type;
    }

    public String getName() { return name; }

    public Type getType() {
        return type;
    }

    public void genKra(PW pw) {
        pw.print(this.name);
    }
    public Symbol getQualifier() {
        return null;
    }
    
    public void genC(PW pw, String class_name) {
        pw.print("_" + this.name);
    }
    
    public void genC(PW pw, String class_name, ArrayList<String> current, ArrayList<String> parent) {
        pw.print("_" + this.name);
    }
    
    private String name;
    private Type type;

    
    ArrayList<Variable> getParameter() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}