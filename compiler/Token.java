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
        if(type == null) return null;

		return type.toString();
	}

}

