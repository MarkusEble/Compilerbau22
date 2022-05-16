package compiler;

import compiler.TokenIntf.Type;

public class Token extends TokenIntf {

	@Override
	public String toString() {
		String s = type2String(m_type);
    	s += ' ';
		s += m_value;
		return s;
	}

	static String type2String(Type type) {
		if (type == Type.EOF) {
			return "EOF";
		} else if (type == Type.IDENT) {
			return "IDENT";
		} else if (type == Type.INTEGER) {
			return "INTEGER";
		} else if (type == Type.DECIMAL) {
			return "DECIMAL";
		} else if (type == Type.CHAR) {
			return "CHAR";
		} else if (type == Type.STRING) {
			return "STRING";
        } else if (type == Type.LPAREN) {
            return "LPAREN";
        } else if (type == Type.RPAREN) {
            return "RPAREN";
        } else if (type == Type.MUL) {
            return "MUL";
        } else if (type == Type.DIV) {
            return "DIV";
        } else if (type == Type.PLUS) {
            return "PLUS";
        } else if (type == Type.MINUS) {
            return "MINUS";
        } else if (type == Type.BITAND) {
            return "BITAND";
        } else if (type == Type.BITOR) {
            return "BITOR";
        } else if (type == Type.SHIFTLEFT) {
            return "SHIFTLEFT";
        } else if (type == Type.SHIFTRIGHT) {
            return "SHIFTRIGHT";
        } else if (type == Type.NOT) {
            return "NOT";
        } else if (type == Type.AND) {
            return "AND";
        } else if (type == Type.OR) {
            return "OR";
        } else if (type == Type.QUESTIONMARK) {
            return "QUESTIONMARK";
        } else if (type == Type.DOUBLECOLON) {
            return "DOUBLECOLON";
		} else if (type == Type.LINECOMMENT) {
			return "LINECOMMENT";
		} else if (type == Type.MULTILINECOMMENT) {
			return "MULTILINECOMMENT";
        } else if (type == Type.WHITESPACE) {
            return "WHITESPACE";
        } else if (type == Type.ASSIGN) {
            return "ASSIGN";
        } else if (type == Type.PRINT) {
            return "PRINT";
        } else if (type == Type.IF) {
            return "IF";
        } else if (type == Type.ELSE) {
            return "ELSE";
		} else {
			return null;
		}
	}

}

