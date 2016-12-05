// LocalVariableList.java
// João Marcos Costa Salles RA: 489972
// Henrique Teruo Eihara RA: 490016
package ast;

import java.util.*;

public class LocalVariableList {

    public LocalVariableList() {
       localList = new ArrayList<Variable>();
    }

    public void addElement(Variable v) {
       localList.add(v);
    }

    public Iterator<Variable> elements() {
        return localList.iterator();
    }

    public int getSize() {
        return localList.size();
    }

    private ArrayList<Variable> localList;

}
