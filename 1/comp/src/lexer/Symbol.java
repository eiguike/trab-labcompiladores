// Symbol.java
// João Marcos Costa Salles RA: 489972
// Henrique Teruo Eihara RA: 490016
package lexer;

public enum Symbol {

    AND("&&"),
    ASSERT("assert"),
    ASSIGN("="),
    BOOLEAN("boolean"),
    BREAK("break"),
    CLASS("class"),
    COLON(":"),
    COMMA(","),
    DIV("/"),
    DOT("."),
    ELSE("else"),
    EOF("~eof"),
    EQ("=="),
    EXTENDS("extends"),
    FALSE("false"),
    FINAL("final"),
    GE(">="),
    GT(">"),
    IDENT("~ident"),
    IF("if"),
    INT("int"),
    LE("<="),
    LEFTCURBRACKET("{"),
    LEFTPAR("("),
    LITERALINT("~number"),
    LITERALSTRING("~literalString"),
    LT("<"),
    MINUS("-"),
    MOCall("~metaobjectCall"),
    MULT("*"),
    NEQ("!="),
    NEW("new"),
    NOT("!"),
    NULL("null"),
    OR("||"),
    PLUS("+"),
    PRIVATE("private"),
    PUBLIC("public"),
    READ("read"),
    RETURN("return"),
    RIGHTCURBRACKET("}"),
    RIGHTPAR(")"),
    SEMICOLON(";"),
    STRING("String"),
    SUPER("super"),
    STATIC("static"),
    THIS("this"),
    TRUE("true"),
    VOID("void"),
    DO("do"),
    WHILE("while"),
    WRITE("write"),
    WRITELN("writeln");

	Symbol(String name) {
		this.name = name;
	}

	@Override public String toString() {
		return name;
	}
	private String name;
}