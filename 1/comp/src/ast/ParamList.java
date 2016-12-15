// ParamList.java
// João Marcos Costa Salles RA: 489972
// Henrique Teruo Eihara RA: 490016
package ast;

import java.util.*;

public class ParamList {

    public ParamList() {
       paramList = new ArrayList<Variable>();
    }

    public void addElement(Variable v) {
       paramList.add(v);
    }

    public Iterator<Variable> elements() {
        return paramList.iterator();
    }

    public int getSize() {
        return paramList.size();
    }
    
    public ArrayList<Variable> getPara(){
        return this.paramList;
    }
    
    
    public void genKra(PW pw) {
        String linha = "";
        boolean primeiro = true;
        for(Variable item : this.paramList){
            if(primeiro){
                primeiro = false;
                linha = item.getType().getName() + " " + item.getName();
            }
            else{
                linha += ", " + item.getType().getName() + " " + item.getName();
            }
        }
        pw.print(linha);
    }
    
    public void genC(PW pw) {
        String linha = "";
        boolean primeiro = true;
        for(Variable item : this.paramList){
            if(primeiro){
                primeiro = false;
                linha = item.getType().getName() + " _" + item.getName();
            }
            else{
                linha += ", " + item.getType().getName() + " " + item.getName();
            }
        }
        pw.print(linha);
    }

    private ArrayList<Variable> paramList;

}
