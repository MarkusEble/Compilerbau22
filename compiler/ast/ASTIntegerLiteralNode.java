package compiler.ast;

import java.io.OutputStreamWriter;

public class ASTIntegerLiteralNode extends ASTExprNode {
    public String m_value;
    
    public ASTIntegerLiteralNode(String value) {
        m_value = value;
    }

    @Override
    public void print(OutputStreamWriter outStream, String indent) throws Exception {
        outStream.write(indent);
        outStream.write("INTEGER ");
        outStream.write(m_value);
        outStream.write("\n");
    }

    @Override
    public int eval() {
        return Integer.valueOf(m_value);
    }

}
