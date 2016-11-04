package ast;

import java.util.ArrayList;

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
    
    private String name;
    private Type type;

    
    ArrayList<Variable> getParameter() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}