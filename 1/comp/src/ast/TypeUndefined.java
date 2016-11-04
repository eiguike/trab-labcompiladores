// TypeUndefined.java
// João Marcos Costa Salles RA: 489972
// Henrique Teruo Eihara RA: 490016
package ast;

public class TypeUndefined extends Type {
    // variables that are not declared have this type
    
   public TypeUndefined() { super("undefined"); }
   
   public String getCname() {
      return "int";
   }
   
}
