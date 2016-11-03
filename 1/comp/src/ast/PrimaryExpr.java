/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import java.util.ArrayList;

/**
 *
 * @author joaosalles
 */
public class PrimaryExpr  extends Expr{
    
    @Override
    public void genC( PW pw, boolean putParenthesis ){}
    
    @Override
    public void genKra( PW pw, boolean putParenthesis ){}

    @Override
    public Type getType(){
       return this.type;
    }
    
    public PrimaryExpr(boolean value){
        this.valueThis = value;
    }
    
    public PrimaryExpr(){
        this.valueThis = false;
        this.valueSuper = false;
        this.expr = null;
        this.id1 = null;
        this.id2 = null;
        this.id3 = null;
    }
    public void setType(Type type){
	    this.type = type;
    }
    public void addID1(String type_entra){
        this.id1 = type_entra;
    }
    public void addID2(String type_entra){
        this.id2 = type_entra;
    }
    public void addID3(String type_entra){
        this.id3 = type_entra;
    }
    
    public void setExpr(ExprList expr_entra){
        this.expr = expr_entra;
    }
    
    public void setThis(boolean value){
        this.valueThis = value;
    }
    
    public ExprList getExpr(){
        return this.expr;
    }
    
    private boolean valueThis;
    private boolean valueSuper;
    private String id1;
    private String id2;
    private String id3;
    private ExprList expr;
    private Type type;
}
