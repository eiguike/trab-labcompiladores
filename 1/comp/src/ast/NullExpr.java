// NullExpr.java
// João Marcos Costa Salles RA: 489972
// Henrique Teruo Eihara RA: 490016
package ast;

public class NullExpr extends Expr {
    
   public void genC( PW pw, boolean putParenthesis ) {
      pw.printIdent("NULL");
   }
   
   public void genKra( PW pw, boolean putParenthesis ) {
      pw.printIdent("NULL");
   }
   
   public Type getType() {
      return new TypeNull();
   }
}