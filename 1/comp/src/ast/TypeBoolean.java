// TypeBoolean.java
// João Marcos Costa Salles RA: 489972
// Henrique Teruo Eihara RA: 490016
package ast;

public class TypeBoolean extends Type {

   public TypeBoolean() { super("boolean"); }

   @Override
   public String getCname() {
      return "int";
   }

}
