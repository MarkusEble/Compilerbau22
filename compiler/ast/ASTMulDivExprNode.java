package compiler.ast;

import java.io.OutputStreamWriter;

public class ASTMulDivExprNode extends ASTExprNode {

	public ASTExprNode m_lhs;
	public ASTExprNode m_rhs;
	public compiler.Token.Type m_type;

	public ASTMulDivExprNode(ASTExprNode lhs, ASTExprNode rhs, compiler.TokenIntf.Type type) {
		m_lhs = lhs;
		m_rhs = rhs;
		m_type = type;
	}

	@Override
	public void print(OutputStreamWriter outStream, String indent) throws Exception {
		if (m_type == compiler.Token.Type.MUL) {
			outStream.write("MUL \n");
		} else {
			outStream.write("DIV \n");
		}
		String childIndent = indent + "  ";
		m_lhs.print(outStream, childIndent);
		m_rhs.print(outStream, childIndent);
	}

	@Override
	public int eval() {
		if (m_type == compiler.Token.Type.MUL) {
			return m_lhs.eval() * m_rhs.eval();
		} else {
			return m_lhs.eval() / m_rhs.eval();
		}
	}

}
