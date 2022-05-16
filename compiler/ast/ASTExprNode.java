package compiler.ast;

import java.io.OutputStreamWriter;

public abstract class ASTExprNode extends ASTNode {
    public abstract void print(OutputStreamWriter outStream, String indent) throws Exception;
    public abstract int eval();
}
