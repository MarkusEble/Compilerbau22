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
		LBRACE,
		RBRACE,
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
		SEMICOLON,
		COMMA,
		DECLARE,
		ASSIGN,
		PRINT,
		IF,
		ELSE,
		WHILE,
		DO,
		FOR,
		LOOP,
		BREAK,
		SWITCH,
		CASE,
		EXECUTE,
		TIMES,
        FUNCTION,
        CALL,
        RETURN,
        BLOCK,
		DEFAULT,
	}

	public Type m_type;
	public String m_value;
	public int m_firstLine;
	public int m_lastLine;
	public int m_firstCol;
	public int m_lastCol;

	/**
	 *  returns a string representation of the current token
	 */
	public abstract String toString();		
}
