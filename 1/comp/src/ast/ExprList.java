package ast;

import java.util.*;

public class ExprList {

    public ExprList() {
        exprList = new ArrayList<Expr>();
    }
    
    // utilizando no kraclass
    public Integer getSizeExprList(){
	    return exprList.size();
    }

    public void addElement( Expr expr ) {
        exprList.add(expr);
    }

    public void genC( PW pw ) {

        int size = exprList.size();
        for ( Expr e : exprList ) {
        	e.genC(pw, false);
            if ( --size > 0 )
                pw.print(", ");
        }
    }
    
    public void genKra( PW pw ){
        int size = exprList.size();
        for ( Expr e : exprList ) {
                e.genKra(pw, false);
            if ( --size > 0 )
                pw.print(", ");
        }
    
    }
    
    public ArrayList<Expr> getExpr(){
        return this.exprList;
    }

    private ArrayList<Expr> exprList;

}
