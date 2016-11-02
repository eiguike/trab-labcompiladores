
package comp;

import ast.*;
import lexer.*;
import java.io.*;
import java.util.*;

public class Compiler {
	
	private Stack classeAtual;

	// compile must receive an input with an character less than
	// p_input.lenght
	public Program compile(char[] input, PrintWriter outError) {

		ArrayList<CompilationError> compilationErrorList = new ArrayList<>();
		signalError = new ErrorSignaller(outError, compilationErrorList);
		symbolTable = new SymbolTable();
		lexer = new Lexer(input, signalError);
		signalError.setLexer(lexer);

		Program program = null;
		lexer.nextToken();
		
		classeAtual = new Stack();
		program = program(compilationErrorList);
		return program;
	}

	private Program program(ArrayList<CompilationError> compilationErrorList) {
		// Program ::= KraClass { KraClass }
		ArrayList<MetaobjectCall> metaobjectCallList = new ArrayList<>();
		ArrayList<KraClass> kraClassList = new ArrayList<>();
		Program program = new Program(kraClassList, metaobjectCallList, compilationErrorList);
		try {
			// parte do texto em que ele simboliza onde deve ser exibido o erro 
			while ( lexer.token == Symbol.MOCall ) {
				metaobjectCallList.add(metaobjectCall());
			}
			kraClassList.add(classDec());
			while ( lexer.token == Symbol.CLASS )
				kraClassList.add(classDec());
			if ( lexer.token != Symbol.EOF ) {
				if( lexer.token == Symbol.IDENT ){
					signalError.showError("'class' expected");
				}else
					signalError.showError("End of file expected");
			}
		}
		catch( RuntimeException e) {
			System.out.println(e);
			// if there was an exception, there is a compilation signalError
		}
		return program;
	}


	/**  parses a metaobject call as <code>{@literal @}ce(...)</code> in <br>
     * <code>
     * @ce(5, "'class' expected") <br>
     * clas Program <br>
     *     public void run() { } <br>
     * end <br>
     * </code>
     * 
	   
	 */
	@SuppressWarnings("incomplete-switch")
	private MetaobjectCall metaobjectCall() {
		String name = lexer.getMetaobjectName();
		lexer.nextToken();
		ArrayList<Object> metaobjectParamList = new ArrayList<>();
		if ( lexer.token == Symbol.LEFTPAR ) {
			// metaobject call with parameters
			lexer.nextToken();
			while ( lexer.token == Symbol.LITERALINT || lexer.token == Symbol.LITERALSTRING ||
					lexer.token == Symbol.IDENT ) {
				switch ( lexer.token ) {
				case LITERALINT:
					metaobjectParamList.add(lexer.getNumberValue());
					break;
				case LITERALSTRING:
					metaobjectParamList.add(lexer.getLiteralStringValue());
					break;
				case IDENT:
					metaobjectParamList.add(lexer.getStringValue());
				}
				lexer.nextToken();
				if ( lexer.token == Symbol.COMMA ) 
					lexer.nextToken();
				else
					break;
			}
			if ( lexer.token != Symbol.RIGHTPAR ) 
				signalError.showError("')' expected after metaobject call with parameters");
			else
				lexer.nextToken();
		}
		if ( name.equals("nce") ) {
			if ( metaobjectParamList.size() != 0 )
				signalError.showError("Metaobject 'nce' does not take parameters");
		}
		else if ( name.equals("ce") ) {
			if ( metaobjectParamList.size() != 3 && metaobjectParamList.size() != 4 )
				signalError.showError("Metaobject 'ce' take three or four parameters");
			if ( !( metaobjectParamList.get(0) instanceof Integer)  )
				signalError.showError("The first parameter of metaobject 'ce' should be an integer number");
			if ( !( metaobjectParamList.get(1) instanceof String) ||  !( metaobjectParamList.get(2) instanceof String) )
				signalError.showError("The second and third parameters of metaobject 'ce' should be literal strings");
			if ( metaobjectParamList.size() >= 4 && !( metaobjectParamList.get(3) instanceof String) )  
				signalError.showError("The fourth parameter of metaobject 'ce' should be a literal string");
			
		}
			
		return new MetaobjectCall(name, metaobjectParamList);
	}

	private KraClass classDec() {
		// Note que os métodos desta classe não correspondem exatamente as
		// regras
		// da gramtica. Este mtodo classDec, por exemplo, implementa
		// a produção KraClass (veja abaixo) e partes de outras produções.

		/*
		 * KraClass ::= ``class'' Id [ ``extends'' Id ] "{" MemberList "}"
		 * MemberList ::= { Qualifier Member } 
		 * Member ::= InstVarDec | MethodDec
		 * InstVarDec ::= Type IdList ";" 
		 * MethodDec ::= Qualifier Type Id "("[ FormalParamDec ] ")" "{" StatementList "}" 
		 * Qualifier ::= [ "static" ]  ( "private" | "public" )
		 */

		KraClass superclass = null;
		InstanceVariableList variableList = new InstanceVariableList();
		ArrayList<Variable>  aux_member = new  ArrayList<Variable>();
		String superclassName = new String();
		String className = new String();
		
		if ( lexer.token != Symbol.CLASS ) signalError.showError("'class' expected");
		lexer.nextToken();
		if ( lexer.token != Symbol.IDENT )
			signalError.show(ErrorSignaller.ident_expected);
		className = lexer.getStringValue();

		lexer.nextToken();
		if ( lexer.token == Symbol.EXTENDS ) {
			lexer.nextToken();
			if ( lexer.token != Symbol.IDENT )
				signalError.show(ErrorSignaller.ident_expected);
			superclassName = lexer.getStringValue();

			// parte semântica
			superclass = (KraClass) symbolTable.get(superclassName);
			if(superclass == null){
				// se não houver a superclasse, então sinaliza erro
				signalError.showError("não ha classe "+ superclassName+" para herdar...");
			}	

			lexer.nextToken();
		}
		if ( lexer.token != Symbol.LEFTCURBRACKET )
			signalError.showError("{ expected", true);
		lexer.nextToken();
		
		KraClass aux = new KraClass(className, superclass, variableList, aux_member);		
		symbolTable.putInGlobal(className, aux);
		
		// semântica, pra identificar qual classe eu estou...
		classeAtual.add(aux);	
                
		while (lexer.token == Symbol.PRIVATE || lexer.token == Symbol.PUBLIC || lexer.token == Symbol.STATIC || lexer.token == Symbol.FINAL) {
                    

			Symbol qualifier = null;
                        Symbol quali_static = null;
                        Symbol quali_final = null;
                        
                        if (lexer.token == Symbol.FINAL){
                            quali_final = Symbol.FINAL;
                            lexer.nextToken();
                            if (lexer.token == Symbol.STATIC){
                                quali_static = Symbol.STATIC;
                                lexer.nextToken();
                            }
                        }
                        if (lexer.token == Symbol.STATIC){
                            quali_static = Symbol.STATIC;
                            lexer.nextToken();
                        }
                        
                        
			switch (lexer.token) {
			case PRIVATE:
				lexer.nextToken();
				qualifier = Symbol.PRIVATE;
				break;
			case PUBLIC:
				lexer.nextToken();
				qualifier = Symbol.PUBLIC;
				break;
                        
			default:
				signalError.showError("private, or public expected");
				qualifier = Symbol.PUBLIC;
			}
			Type t = type();
			if ( lexer.token != Symbol.IDENT )
				signalError.showError("Identifier expected");
			String name = lexer.getStringValue();
			lexer.nextToken();
			if ( lexer.token == Symbol.LEFTPAR ){
                            aux_member.add(methodDec(t, name, qualifier,quali_static,quali_final));
                        }else if ( qualifier != Symbol.PRIVATE )
				signalError.showError("Attempt to declare a public instance variable");
			else
				 instanceVarDec(t, name, variableList,qualifier,quali_static,quali_final );
		}
		if ( lexer.token != Symbol.RIGHTCURBRACKET )
			signalError.showError("public/private or \"}\" expected");
		lexer.nextToken();
		
		// remove a classe que esta sendo trabalhada
		classeAtual.pop();
		
		return (KraClass) symbolTable.get(className);

	}

//      returna array de instanceVarDec
	private void instanceVarDec(Type type, String name, InstanceVariableList variable_array,Symbol qualifier, Symbol quali_static, Symbol quali_final) {
		// InstVarDec ::= [ "static" ] "private" Type IdList ";"
//                ArrayList<InstanceVariable> variable_array = new ArrayList();
                InstanceVariable aux_variable = new InstanceVariable(name,type,qualifier,quali_static,quali_final);
//                pelo menos uma variavel vai existir
                variable_array.addElement(aux_variable); 
//                caso mais de uma existir
		while (lexer.token == Symbol.COMMA) {
			lexer.nextToken();
			if ( lexer.token != Symbol.IDENT )
				signalError.showError("Identifier expected");
			String variableName = lexer.getStringValue();
                        aux_variable = new InstanceVariable(lexer.getStringValue(),type);
//                        adiciona nova variavel na lista
                        variable_array.addElement(aux_variable);
			lexer.nextToken();
		}
		if ( lexer.token != Symbol.SEMICOLON )
			signalError.show(ErrorSignaller.semicolon_expected);
		lexer.nextToken();
	}

	private MethodDec_class methodDec(Type type, String name, Symbol qualifier,Symbol quali_static, Symbol quali_final) {
		/*
		 * MethodDec ::= Qualifier Return Id "("[ FormalParamDec ] ")" "{"
		 *                StatementList "}"
		 */
                MethodDec_class aux_methodDec = new MethodDec_class(qualifier,name,type,quali_static, quali_final);
		lexer.nextToken();
		if ( lexer.token != Symbol.RIGHTPAR ) aux_methodDec.setParamList(formalParamDec());
		if ( lexer.token != Symbol.RIGHTPAR ) signalError.showError(") expected");

		lexer.nextToken();
		if ( lexer.token != Symbol.LEFTCURBRACKET ) signalError.showError("{ expected");

		lexer.nextToken();
		statementList();
		if ( lexer.token != Symbol.RIGHTCURBRACKET ) signalError.showError("} expected");

		lexer.nextToken();

                return aux_methodDec;

	}

	private Expr localDec() {
		// LocalDec ::= Type IdList ";"
		ArrayList<Variable> variableList = new ArrayList<Variable>();

		Type type = type();
		if ( lexer.token != Symbol.IDENT ) signalError.showError("Identifier expected");
		Variable v = new Variable(lexer.getStringValue(), type);
		variableList.add(v);
		lexer.nextToken();
		while (lexer.token == Symbol.COMMA) {
			lexer.nextToken();
			if ( lexer.token != Symbol.IDENT )
				signalError.showError("Missing identifier");
			v = new Variable(lexer.getStringValue(), type);
			variableList.add(v);
			lexer.nextToken();
		}
		if(lexer.token != Symbol.SEMICOLON){
                    //signalError.showError("Unknown character");
		    signalError.showError("Missing ';'", true);
                }
		lexer.nextToken();
		return new LocalDec(type, variableList);
	}

	private ParamList formalParamDec() {
		// FormalParamDec ::= ParamDec { "," ParamDec }
                ParamList para_list = new ParamList();
		para_list.addElement(paramDec());
		while (lexer.token == Symbol.COMMA) {
			lexer.nextToken();
			para_list.addElement(paramDec());
		}
                return para_list;
	}

	private Parameter paramDec() {
		// ParamDec ::= Type Id
		Type aux_type = type();
            	if ( lexer.token != Symbol.IDENT ) signalError.showError("Identifier expected");
                Parameter parametro = new Parameter(lexer.getStringValue(),aux_type);
		lexer.nextToken();
                return parametro;
	}

	private Type type() {
		// Type ::= BasicType | Id
		Type result;

		switch (lexer.token) {
		case VOID:
			result = Type.voidType;
			break;
		case INT:
			result = Type.intType;
			break;
		case BOOLEAN:
			result = Type.booleanType;
			break;
		case STRING:
			result = Type.stringType;
			break;
		case IDENT:
                        Type aux_ident = null;
                        Variable aux_variable;
			// # corrija: fa�a uma busca na TS para buscar a classe
			// IDENT deve ser uma classe.
                        
//                        procura no local
                        aux_variable = symbolTable.getInLocal(lexer.getStringValue()); 
                        if (aux_variable != null){
                            aux_ident = aux_variable.getType();
                        }else{
//                            nao achou no local procura no global
                             aux_ident = symbolTable.getInGlobal(lexer.getStringValue());
                        }
			result = aux_ident;
			break;
		default:
			signalError.showError("Type expected");
			result = Type.undefinedType;
		}
		lexer.nextToken();
		return result;
	}

	private CompositeStatement compositeStatement() {

		lexer.nextToken();
		ArrayList<Statement> stmtList = statementList();

		if ( lexer.token != Symbol.RIGHTCURBRACKET )
			signalError.showError("} expected");
		else
			lexer.nextToken();
		
		return new CompositeStatement(stmtList);
	}

	private ArrayList<Statement> statementList() {
		ArrayList<Statement> statementList = new ArrayList<Statement>();
		// qm chama essa função 'eo methodDec
		// CompStatement ::= "{" { Statement } "}"
		Symbol tk;
		Statement aux;
		// statements always begin with an identifier, if, read, write, ...
		while ((tk = lexer.token) != Symbol.RIGHTCURBRACKET && tk != Symbol.ELSE){
			statementList.add(statement());
		}
			
		return statementList;
	}

	private Statement statement() {
		/*
		 * Statement ::= Assignment ``;'' | IfStat |WhileStat | MessageSend
		 *                ``;'' | ReturnStat ``;'' | ReadStat ``;'' | WriteStat ``;'' |
		 *               ``break'' ``;'' | ``;'' | CompStatement | LocalDec
		 */
		
		Statement stmt = null;

		switch (lexer.token) {
		case THIS:
		case IDENT:
		case SUPER:
		case INT:
		case BOOLEAN:
		case STRING:
			AssignmentExpr aux = (AssignmentExpr) assignExprLocalDec();
			if (aux == null){
				signalError.showError("Statement expected");
			}else
				stmt = new AssignmentStatement(aux);
			break;
		case ASSERT:
			assertStatement();
			break;
		case RETURN:
			stmt = returnStatement();
			break;
		case READ:
			stmt = readStatement();
			break;
		case WRITE:
			stmt = writeStatement();
			break;
		case WRITELN:
			stmt = writelnStatement();
			break;
		case IF:
			stmt = ifStatement();
			break;
		case BREAK:
			stmt = breakStatement();
			break;
		case WHILE:
			stmt = whileStatement();
			break;
		case SEMICOLON:
			stmt = nullStatement();
			break;
		case LEFTCURBRACKET:
			stmt = compositeStatement();
			break;
		case DO:
			stmt = doWhileStatement();
			break;
		default:
			signalError.showError("Statement expected");
		}
		
		return stmt;
	}

	private StatementAssert assertStatement() {
		lexer.nextToken();
		int lineNumber = lexer.getLineNumber();
		Expr e = expr();
		if ( e.getType() != Type.booleanType )
			signalError.showError("boolean expression expected");
		if ( lexer.token != Symbol.COMMA ) {
			this.signalError.showError("',' expected after the expression of the 'assert' statement");
		}
		lexer.nextToken();
		if ( lexer.token != Symbol.LITERALSTRING ) {
			this.signalError.showError("A literal string expected after the ',' of the 'assert' statement");
		}
		String message = lexer.getLiteralStringValue();
		lexer.nextToken();
		if ( lexer.token == Symbol.SEMICOLON )
			lexer.nextToken();
		
		return new StatementAssert(e, lineNumber, message);
	}

	/*
	 * retorne true se 'name' � uma classe declarada anteriormente. � necess�rio
	 * fazer uma busca na tabela de s�mbolos para isto.
	 */
	private boolean isType(String name) {
		return this.symbolTable.getInGlobal(name) != null;
	}

	/*
	 * AssignExprLocalDec ::= Expression [ ``$=$'' Expression ] | LocalDec
	 */
	private Expr assignExprLocalDec() {
		Expr expr1=null, expr2=null;
		if ( lexer.token == Symbol.INT || lexer.token == Symbol.BOOLEAN
				|| lexer.token == Symbol.STRING ||
				// token � uma classe declarada textualmente antes desta
				// instru��o
				(lexer.token == Symbol.IDENT && isType(lexer.getStringValue())) ) {
			/*
			 * uma declara��o de vari�vel. 'lexer.token' � o tipo da vari�vel
			 * 
			 * AssignExprLocalDec ::= Expression [ ``$=$'' Expression ] | LocalDec 
			 * LocalDec ::= Type IdList ``;''
			 */
			return new AssignmentExpr(localDec(), null);
		}
		else {
			/*
			 * AssignExprLocalDec ::= Expression [ ``$=$'' Expression ]
			 */
			expr1 = expr();
			if ( lexer.token == Symbol.ASSIGN ) {
				lexer.nextToken();
				expr2 = expr();
				if ( lexer.token != Symbol.SEMICOLON )
					signalError.showError("';' expected", true);
				else
					lexer.nextToken();
			}
			
			if(expr1 == null){
				return null;
			}else
				return new AssignmentExpr(expr1, expr2);
		}
	}

	
	private ExprList realParameters() {
		ExprList anExprList = null;

		if ( lexer.token != Symbol.LEFTPAR ) signalError.showError("( expected");
		lexer.nextToken();
		if ( startExpr(lexer.token) ) anExprList = exprList();
		if ( lexer.token != Symbol.RIGHTPAR ) signalError.showError(") expected");
		lexer.nextToken();
		return anExprList;
	}
	private DoWhileStatement doWhileStatement() {
		CompositeStatement cmpstmt = null;
		Expr expr = null;
		lexer.nextToken();
		
		if(lexer.token == Symbol.LEFTCURBRACKET){
			cmpstmt = compositeStatement();
		}else signalError.showError("Expected '{' after 'do'");
		
		if(lexer.token == Symbol.WHILE){
			lexer.nextToken();
			if(lexer.token == Symbol.LEFTPAR){
				lexer.nextToken();
				expr = expr();
				if(lexer.token == Symbol.RIGHTPAR){
					lexer.nextToken();
					return new DoWhileStatement(cmpstmt, expr);
				}else	signalError.showError("Expected a ')'");
			}else	signalError.showError("Expected a '('");
		}else	signalError.showError("Expected symbol 'while' but found "+lexer.token);

		return null;
	}

	private WhileStatement whileStatement() {

		lexer.nextToken();
		if ( lexer.token != Symbol.LEFTPAR ) signalError.showError("( expected");
		lexer.nextToken();
		Expr expr = expr();
		if ( lexer.token != Symbol.RIGHTPAR ) signalError.showError(") expected");
		lexer.nextToken();
		Statement stmt = statement();
		
		return new WhileStatement(stmt, expr);
	}

	private IfStatement ifStatement() {
		Statement stmtElse = null;

		lexer.nextToken();
		if ( lexer.token != Symbol.LEFTPAR ) signalError.showError("( expected");
		lexer.nextToken();
		Expr expr = expr();
		if ( lexer.token != Symbol.RIGHTPAR ) signalError.showError(") expected");
		lexer.nextToken();
		Statement stmt = statement();
		if ( lexer.token == Symbol.ELSE ) {
			lexer.nextToken();
			stmtElse = statement();
		}
		
		return new IfStatement(expr, stmt, stmtElse);
	}

	private ReturnStatement returnStatement() {

		lexer.nextToken();
		Expr expr = expr();
		if ( lexer.token != Symbol.SEMICOLON )
			signalError.show(ErrorSignaller.semicolon_expected);
		lexer.nextToken();
		
		return new ReturnStatement(expr);
	}

	private ReadStatement readStatement() {
		ArrayList<Variable> variableList = new ArrayList<Variable>();
		lexer.nextToken();
		if ( lexer.token != Symbol.LEFTPAR ) signalError.showError("'(' expected after 'read' command");
		lexer.nextToken();
                if ( lexer.token == Symbol.RIGHTPAR ) signalError.showError("Command 'read' without arguments");
		while (true) {
			if ( lexer.token == Symbol.THIS ) {
				lexer.nextToken();
				if ( lexer.token != Symbol.DOT ) signalError.showError(". expected");
				lexer.nextToken();
			}
			if ( lexer.token != Symbol.IDENT )
				signalError.showError("Expression expected");

			String name = lexer.getStringValue();
			// AQUI TEM QUE FAZER A VERIFICAÇAÕ DA EXISTÊNCIA DA VARIÁVEL
			// NÃO ESQUECER.................
			variableList.add(new Variable(name, null));
			lexer.nextToken();
			if ( lexer.token == Symbol.COMMA )
				lexer.nextToken();
			else
				break;
			
		}

		if ( lexer.token != Symbol.RIGHTPAR ) signalError.showError(") expected");
		lexer.nextToken();
		if ( lexer.token != Symbol.SEMICOLON )
			signalError.show(ErrorSignaller.semicolon_expected);
		lexer.nextToken();
		
		return new ReadStatement(variableList);
	}

	private WriteStatement writeStatement() {

		lexer.nextToken();
		if ( lexer.token != Symbol.LEFTPAR ) signalError.showError("Missing '('");
		lexer.nextToken();
                if ( lexer.token == Symbol.RIGHTPAR ) signalError.showError("Command 'write' without arguments");
		ExprList exprList = exprList();
		if ( lexer.token != Symbol.RIGHTPAR ) signalError.showError(") expected");
		lexer.nextToken();
		if ( lexer.token != Symbol.SEMICOLON )
			signalError.show(ErrorSignaller.semicolon_expected);
		lexer.nextToken();
		
		return new WriteStatement(exprList, "");
	}

	private WriteStatement writelnStatement() {

		lexer.nextToken();
		if ( lexer.token != Symbol.LEFTPAR ) signalError.showError("( expected");
		lexer.nextToken();
		ExprList exprList = exprList();
		if ( lexer.token != Symbol.RIGHTPAR ) signalError.showError(") expected");
		lexer.nextToken();
		if ( lexer.token != Symbol.SEMICOLON )
			signalError.show(ErrorSignaller.semicolon_expected);
		lexer.nextToken();
		
		return new WriteStatement(exprList, "\n");
	}

	private GenericStatement breakStatement() {
		lexer.nextToken();
		if ( lexer.token != Symbol.SEMICOLON )
			signalError.show(ErrorSignaller.semicolon_expected);
		lexer.nextToken();
		
		return new GenericStatement("break;");
	}

	private Statement nullStatement() {
		lexer.nextToken();
		return new GenericStatement(";");
	}

	private ExprList exprList() {
		// ExpressionList ::= Expression { "," Expression }

		ExprList anExprList = new ExprList();
		anExprList.addElement(expr());
		while (lexer.token == Symbol.COMMA) {
			lexer.nextToken();
			anExprList.addElement(expr());
		}
		return anExprList;
	}

	private Expr expr() {

		Expr left = simpleExpr();
		Symbol op = lexer.token;
		if ( op == Symbol.EQ || op == Symbol.NEQ || op == Symbol.LE
				|| op == Symbol.LT || op == Symbol.GE || op == Symbol.GT ) {
			lexer.nextToken();
			Expr right = simpleExpr();
			left = new CompositeExpr(left, op, right);
		}
		return left;
	}

	private Expr simpleExpr() {
		Symbol op;

		Expr left = term();
		while ((op = lexer.token) == Symbol.MINUS || op == Symbol.PLUS
				|| op == Symbol.OR) {
			lexer.nextToken();
			Expr right = term();
			left = new CompositeExpr(left, op, right);
		}
		return left;
	}

	private Expr term() {
		Symbol op;

		Expr left = signalFactor();
		while ((op = lexer.token) == Symbol.DIV || op == Symbol.MULT
				|| op == Symbol.AND) {
			lexer.nextToken();
			Expr right = signalFactor();
			left = new CompositeExpr(left, op, right);
		}
		return left;
	}

	private Expr signalFactor() {
		Symbol op;
		if ( (op = lexer.token) == Symbol.PLUS || op == Symbol.MINUS ) {
			lexer.nextToken();
			return new SignalExpr(op, factor());
		}
		else
			return factor();
	}

	/*
	 * Factor ::= BasicValue | "(" Expression ")" | "!" Factor | "null" |
	 *      ObjectCreation | PrimaryExpr
	 * 
	 * BasicValue ::= IntValue | BooleanValue | StringValue 
	 * BooleanValue ::=  "true" | "false" 
	 * ObjectCreation ::= "new" Id "(" ")" 
	 * PrimaryExpr ::= "super" "." Id "(" [ ExpressionList ] ")"  | 
	 *                 Id  |
	 *                 Id "." Id | 
	 *                 Id "." Id "(" [ ExpressionList ] ")" |
	 *                 Id "." Id "." Id "(" [ ExpressionList ] ")" |
	 *                 "this" | 
	 *                 "this" "." Id | 
	 *                 "this" "." Id "(" [ ExpressionList ] ")"  | 
	 *                 "this" "." Id "." Id "(" [ ExpressionList ] ")"
	 */
        @SuppressWarnings("UnusedAssignment")
	private Expr factor() {

		Expr anExpr;
		ExprList exprList;
		String messageName, id;
                KraClass aux_class = (KraClass) classeAtual.get(classeAtual.size()-1); //recebe ultima classe
//                MethodDec_class aux_method = (MethodDec_class) methodAtual.get(classeAtual.size()-1);
//                String quali = aux_method.getQualifier().toString();
//                String aux_name;
		InstanceVariableList aux_instanceVariable = aux_class.getIntance();
                ArrayList<Variable> aux_method = aux_class.getMethodList();
                switch (lexer.token) {
		// IntValue
		case LITERALINT:
			return literalInt();
			// BooleanValue
		case FALSE:
			lexer.nextToken();
			return LiteralBoolean.False;
			// BooleanValue
		case TRUE:
			lexer.nextToken();
			return LiteralBoolean.True;
			// StringValue
		case LITERALSTRING:
			String literalString = lexer.getLiteralStringValue();
			lexer.nextToken();
			return new LiteralString(literalString);
			// "(" Expression ")" |
		case LEFTPAR:
			lexer.nextToken();
			anExpr = expr();
			if ( lexer.token != Symbol.RIGHTPAR ) signalError.showError(") expected");
			lexer.nextToken();
			return new ParenthesisExpr(anExpr);

			// "null"
		case NULL:
			lexer.nextToken();
			return new NullExpr();
			// "!" Factor
		case NOT:
			lexer.nextToken();
			anExpr = expr();
			return new UnaryExpr(anExpr, Symbol.NOT);
			// ObjectCreation ::= "new" Id "(" ")"
		case NEW:
			lexer.nextToken();
			if ( lexer.token != Symbol.IDENT )
				signalError.showError("Identifier expected");

			String className = lexer.getStringValue();
                        KraClass aClass;
                        
			/*
			 * // encontre a classe className in symbol table KraClass 
			 *      aClass = symbolTable.getInGlobal(className); 
			 *      if ( aClass == null ) ...
			 */
                                                
                        aClass =  symbolTable.getInGlobal(className);
			if (aClass == null){
				signalError.showError("não existe tal classe. não complica rapaz");
			}
                        
			lexer.nextToken();
			if ( lexer.token != Symbol.LEFTPAR ) signalError.showError("( expected");
			lexer.nextToken();
			if ( lexer.token != Symbol.RIGHTPAR ) signalError.showError(") expected");
			lexer.nextToken();
			/*
			 * return an object representing the creation of an object
			 */
                        if (aClass != null){
                            return new FactorNew(aClass);
                        }
			/*
          	 * PrimaryExpr ::= "super" "." Id "(" [ ExpressionList ] ")"  | 
          	 *                 Id  |
          	 *                 Id "." Id | 
          	 *                 Id "." Id "(" [ ExpressionList ] ")" |
          	 *                 Id "." Id "." Id "(" [ ExpressionList ] ")" |
          	 *                 "this" | 
          	 *                 "this" "." Id | 
          	 *                 "this" "." Id "(" [ ExpressionList ] ")"  | 
          	 *                 "this" "." Id "." Id "(" [ ExpressionList ] ")"
			 */
		case SUPER:
			// "super" "." Id "(" [ ExpressionList ] ")"
			lexer.nextToken();
			if ( lexer.token != Symbol.DOT ) {
				signalError.showError("'.' expected");
			}
			else
				lexer.nextToken();
			if ( lexer.token != Symbol.IDENT )
				signalError.showError("Identifier expected");
			messageName = lexer.getStringValue();
			
			/*
			 * para fazer as confer�ncias sem�nticas, procure por 'messageName'
			 * na superclasse/superclasse da superclasse etc
			 */
			
			KraClass aux = (KraClass) classeAtual.lastElement();
						
			lexer.nextToken();
			exprList = realParameters();
			
			return new ExprSuper (aux.receivingMessage(new MessageSendToSuper(exprList, messageName), signalError));
			//break;
		case IDENT:
			/*
          	 * PrimaryExpr ::=  
          	 *                 Id  |
          	 *                 Id "." Id | 
          	 *                 Id "." Id "(" [ ExpressionList ] ")" |
          	 *                 Id "." Id "." Id "(" [ ExpressionList ] ")" |
			 */

			String firstId = lexer.getStringValue();
                        PrimaryExpr prim_expr = new PrimaryExpr();
			lexer.nextToken();
                        prim_expr.addID1(firstId);
			if ( lexer.token != Symbol.DOT && lexer.token != Symbol.SEMICOLON) {
				// Id
				// retorne um objeto da ASA que representa um identificador
				return prim_expr;
			}
			else if(lexer.token != Symbol.SEMICOLON){ // Id "."
				lexer.nextToken(); // coma o "."
				if ( lexer.token != Symbol.IDENT) {
					signalError.showError("Identifier expected");
				}
				else{
					// Id "." Id
                                        String secondId = lexer.getStringValue();
                                        prim_expr.addID2(secondId);
					lexer.nextToken();
					id = lexer.getStringValue();
					if ( lexer.token == Symbol.DOT ) {
						// Id "." Id "." Id "(" [ ExpressionList ] ")"
						/*
						 * se o compilador permite vari�veis est�ticas, � poss�vel
						 * ter esta op��o, como
						 *     Clock.currentDay.setDay(12);
						 * Contudo, se vari�veis est�ticas n�o estiver nas especifica��es,
						 * sinalize um erro neste ponto.
						 */
						lexer.nextToken();
						if ( lexer.token != Symbol.IDENT )
							signalError.showError("Identifier expected");
						messageName = lexer.getStringValue();
                                                String thirdId = lexer.getStringValue();
                                                prim_expr.addID3(thirdId);
						lexer.nextToken();
//						exprList = this.realParameters();
                                                prim_expr.setExpr(this.realParameters());
                                                return prim_expr;
					}
					else if ( lexer.token == Symbol.LEFTPAR ) {
						// Id "." Id "(" [ ExpressionList ] ")"
//						exprList = this.realParameters();
                                                prim_expr.setExpr(this.realParameters());
                                                return prim_expr;
						/*
						 * para fazer as confer�ncias sem�nticas, procure por
						 * m�todo 'ident' na classe de 'firstId'
						 */
					}
					else {
                                            return prim_expr;
						// retorne o objeto da ASA que representa Id "." Id
					}
				}
			}
			break;
		case THIS:
                     PrimaryExpr this_expr = new PrimaryExpr();
			/*
			 * Este 'case THIS:' trata os seguintes casos: 
          	 * PrimaryExpr ::= 
          	 *                 "this" | 
          	 *                 "this" "." Id | 
          	 *                 "this" "." Id "(" [ ExpressionList ] ")"  | 
          	 *                 "this" "." Id "." Id "(" [ ExpressionList ] ")"
			 */
			lexer.nextToken();
                        this_expr.setThis(true);
			if ( lexer.token != Symbol.DOT ) {
				// only 'this'
				// retorne um objeto da ASA que representa 'this'
				// confira se n�o estamos em um m�todo est�tico
//                                for(Variable item : aux_method){
//                                    aux_name = item.getName();
//                                    if (quali.equals()){
//                                        System.out.print('c');
//                                    }
//                                }
				return this_expr;
			}
			else {
				lexer.nextToken();
				if ( lexer.token != Symbol.IDENT )
					signalError.showError("Identifier expected");
				id = lexer.getStringValue();
                                this_expr.addID1(id);
				lexer.nextToken();
				// j� analisou "this" "." Id
				if ( lexer.token == Symbol.LEFTPAR ) {
					// "this" "." Id "(" [ ExpressionList ] ")"
					/*
					 * Confira se a classe corrente possui um m�todo cujo nome �
					 * 'ident' e que pode tomar os par�metros de ExpressionList
					 */
                                        boolean possui_metodo = false;
                                        for(Variable item : aux_method){
                                            if(item.getName().equals(id)){
                                                possui_metodo = true;
                                            }
                                        }
                                        
                                        if(!possui_metodo){
                                            signalError.showError("Method does not exist");
                                        }
                                        
                                        this_expr.setExpr(this.realParameters());
                                        MessageSendToSelf message = new MessageSendToSelf(id,this_expr.getExpr());
                                        aux_class.message(message, signalError);
                                        return this_expr;
//					exprList = this.realParameters();
				}
				else if ( lexer.token == Symbol.DOT ) {
					// "this" "." Id "." Id "(" [ ExpressionList ] ")"
					lexer.nextToken();
					if ( lexer.token != Symbol.IDENT )
						signalError.showError("Identifier expected");
                                        this_expr.addID2(lexer.getStringValue());
					lexer.nextToken();
                                        if ( lexer.token == Symbol.LEFTPAR ){
                                            this_expr.setExpr(this.realParameters());
                                            return this_expr;
                                        }else{
                                            signalError.showError("( expected");
                                        }
//					exprList = this.realParameters();
				}
				else {
                                        id = lexer.getStringValue();
                                        this_expr.addID1(id);
					// retorne o objeto da ASA que representa "this" "." Id
					/*
					 * confira se a classe corrente realmente possui uma
					 * vari�vel de inst�ncia 'ident'
					 */
					return this_expr;
				}
			}
			break;
		default:
			signalError.showError("Expression expected");
		}
		return null;
	}

	private LiteralInt literalInt() {

		LiteralInt e = null;

		// the number value is stored in lexer.getToken().value as an object of
		// Integer.
		// Method intValue returns that value as an value of type int.
		int value = lexer.getNumberValue();
		lexer.nextToken();
		return new LiteralInt(value);
	}

	private static boolean startExpr(Symbol token) {

		return token == Symbol.FALSE || token == Symbol.TRUE
				|| token == Symbol.NOT || token == Symbol.THIS
				|| token == Symbol.LITERALINT || token == Symbol.SUPER
				|| token == Symbol.LEFTPAR || token == Symbol.NULL
				|| token == Symbol.IDENT || token == Symbol.LITERALSTRING;

	}

	private SymbolTable		symbolTable;
	private Lexer			lexer;
	private ErrorSignaller	signalError;

}
