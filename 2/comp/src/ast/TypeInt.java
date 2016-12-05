// TypeInt.java
// João Marcos Costa Salles RA: 489972
// Henrique Teruo Eihara RA: 490016
package ast;

public class TypeInt extends Type {
    
    public TypeInt() {
        super("int");
    }
    
   public String getCname() {
      return "int";
   }

}