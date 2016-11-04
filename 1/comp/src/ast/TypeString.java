// TypeString.java
// João Marcos Costa Salles RA: 489972
// Henrique Teruo Eihara RA: 490016
package ast;

public class TypeString extends Type {
    
    public TypeString() {
        super("String");
    }
    
   public String getCname() {
      return "char *";
   }

}