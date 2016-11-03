package comp;

import java.util.*;

import ast.*;

public class SymbolTable {

    public SymbolTable() {
        globalTable = new HashMap<String, KraClass>();
        localTable  = new HashMap<String, Variable>();
	methodLocalTable  = new HashMap<String, Variable>();
	
    }
    
    public Variable getInLocalAndMethod(String key){
	    Variable aux = this.getInMethod(key);
	    if(aux != null){
		    return aux;
	    }else
		    return this.getInLocal(key);
    }

    public Object putInGlobal( String key, KraClass value ) {
       return globalTable.put(key, value);
    }

    public KraClass getInGlobal( String key ) {
       return globalTable.get(key);
    }

    public Variable putInLocal( String key, Variable value ) {
       return localTable.put(key, value);
    }

    public Variable getInLocal( String key ) {
       return localTable.get(key);
    }
    
    public Variable putInMethod( String key, Variable value ) {
       return methodLocalTable.put(key, value);
    }

    public Variable getInMethod( String key ) {
       return methodLocalTable.get(key);
    }
    
    public Object get( String key ) {
        // returns the object corresponding to the key.
        Object result;
        if ( (result = localTable.get(key)) != null ) {
              // found local identifier
            return result;
        }
        else {
              // global identifier, if it is in globalTable
            return globalTable.get(key);
        }
    }

    public void removeLocalIdent() {
           // remove all local identifiers from the table
         localTable.clear();
    }
    public void removeMethodIdent() {
           // remove all local identifiers from the table
         methodLocalTable.clear();
    }


    private HashMap<String, KraClass> globalTable;
    private HashMap<String, Variable> localTable;
    private HashMap<String, Variable> methodLocalTable;
}
