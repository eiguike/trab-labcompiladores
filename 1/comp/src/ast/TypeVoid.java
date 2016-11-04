// TypeVoid.java
// João Marcos Costa Salles RA: 489972
// Henrique Teruo Eihara RA: 490016
package ast;

public class TypeVoid extends Type {
    
    public TypeVoid() {
        super("void");
    }
    
   public String getCname() {
      return "void";
   }

}