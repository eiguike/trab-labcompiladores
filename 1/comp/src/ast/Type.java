// Type.java
// João Marcos Costa Salles RA: 489972
// Henrique Teruo Eihara RA: 490016
package ast;

abstract public class Type {

    public Type( String name ) {
        this.name = name;
    }

    public static Type booleanType = new TypeBoolean();
    public static Type intType = new TypeInt();
    public static Type stringType = new TypeString();
    public static Type voidType = new TypeVoid();
    public static Type undefinedType = new TypeUndefined();

    public String getName() {
        return name;
    }

    abstract public String getCname();

    private String name;
}
