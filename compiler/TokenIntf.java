package compiler;

public abstract class TokenIntf {
	public enum Type {
		EOF,
		IDENT,
		INTEGER,
		DECIMAL,
		STRING,
		CHAR,
        LPAREN,
        RPAREN,
        MUL,
        DIV,
        PLUS,
        MINUS,
        BITAND,
        BITOR,
        SHIFTLEFT,
        SHIFTRIGHT,
        EQUAL,
        LESS,
        GREATER,
        NOT,
        AND,
        OR,
        QUESTIONMARK,
        DOUBLECOLON,
		LINECOMMENT,
		MULTILINECOMMENT,
		WHITESPACE,
		ASSIGN,
		PRINT,
		IF,
		ELSE,
	}

	public Type m_type;
	public String m_value;

	/**
	 *  returns a string representation of the current token
	 */
	public abstract String toString();
	
	/**
	 * returns a string representation of the given token type
	 */
	// static String type2String(Type type);		
}
