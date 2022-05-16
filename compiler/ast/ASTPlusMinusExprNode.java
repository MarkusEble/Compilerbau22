package compiler.ast;

import java.io.OutputStreamWriter;

public class ASTPlusMinusExprNode extends ASTExprNode {
    public ASTExprNode m_lhs;
    public ASTExprNode m_rhs;
    public compiler.Token.Type m_type;
    
    public ASTPlusMinusExprNode(ASTExprNode lhs, ASTExprNode rhs, compiler.TokenIntf.Type type) {
        m_lhs = lhs;
        m_rhs = rhs;
        m_type = type;
    }
    
    @Override
    public void print(OutputStreamWriter outStream, String indent) throws Exception {
        outStream.write(indent);
        if (m_type == compiler.Token.Type.PLUS) {
            outStream.write("PLUS \n");
        } else {
            outStream.write("MINUS \n");
        }
        String childIndent = indent + "  ";
        m_lhs.print(outStream, childIndent);
        m_rhs.print(outStream, childIndent);
    }

    @Override
    public int eval() {
        if (m_type == compiler.Token.Type.PLUS) {
            return m_lhs.eval() + m_rhs.eval();
        } else {
            return m_lhs.eval() - m_rhs.eval();            
        }
    }

}
