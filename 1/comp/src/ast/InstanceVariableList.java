package ast;

import java.util.*;

public class InstanceVariableList {

    public InstanceVariableList() {
       instanceVariableList = new ArrayList<InstanceVariable>();
    }

    public void addElement(InstanceVariable instanceVariable) {
       instanceVariableList.add( instanceVariable );
    }

    public Iterator<InstanceVariable> elements() {
    	return this.instanceVariableList.iterator();
    }

    public int getSize() {
        return instanceVariableList.size();
    }
    
     public void genKra(PW pw) {
         pw.add();
         for(InstanceVariable item : this.instanceVariableList){
             item.genKra(pw);
         }
         pw.sub();
     }

    private ArrayList<InstanceVariable> instanceVariableList;

}
