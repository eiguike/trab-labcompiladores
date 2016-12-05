// MessageSendStatement.java
// João Marcos Costa Salles RA: 489972
// Henrique Teruo Eihara RA: 490016
package ast;

public class MessageSendStatement extends Statement { 


   public void genC( PW pw ) {
      pw.printIdent("");
      // messageSend.genC(pw);
      pw.println(";");
   }

   private MessageSend  messageSend;
   
   @Override
   public void genKra(PW pw) {

	}

}


