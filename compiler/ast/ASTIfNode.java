package compiler.ast;

import compiler.Instr;

import java.io.OutputStreamWriter;

public class ASTIfNode extends ASTStmtNode {

    private final ASTExprNode m_ifCondition;
    private final ASTStmtNode m_ifBody;
    private final ASTStmtNode m_elseBlock;
    private static int m_index = 0;

    public ASTIfNode(ASTExprNode m_ifCondition, ASTStmtNode m_ifBody, ASTStmtNode m_elseBlock) {
        this.m_ifCondition = m_ifCondition;
        this.m_ifBody = m_ifBody;
        this.m_elseBlock = m_elseBlock;
    }

    @Override
    public void print(OutputStreamWriter outStream, String indent) throws Exception {
        outStream.append(indent);
        outStream.append("IF\n");

        String childIndent = indent + "  ";
        m_ifCondition.print(outStream, childIndent);
        m_ifBody.print(outStream, childIndent);
        if (m_elseBlock instanceof ASTIfNode || m_elseBlock instanceof ASTElseNode)
            if (m_elseBlock instanceof ASTElseNode) {
                m_elseBlock.print(outStream, childIndent);
            } else {
                outStream.append("ELSE ");
                m_elseBlock.print(outStream, childIndent);
            }
    }

    @Override
    public void execute() {
        if (m_ifCondition.eval() != 0) {
            m_ifBody.execute();
        } else if (m_elseBlock instanceof ASTIfNode || m_elseBlock instanceof ASTElseNode) {
            m_elseBlock.execute();
        }
    }

    @Override
    public void codegen(compiler.CompileEnv env) throws Exception {
        // create code blocks needed for control structure
        compiler.InstrBlock condition = env.createBlock("if_condition_" + m_index);
        compiler.InstrBlock body = env.createBlock("if_body_" + m_index);
        compiler.InstrBlock elseHead = env.createBlock("if_elseHead_" + m_index);
        compiler.InstrBlock exit = env.createBlock("if_exit_" + m_index);
        m_index++;

        // current block of CompileEnv is our entry block
        // terminate entry block with jump/conditional jump
        // into block of control structure

        compiler.InstrIntf jmpIntoCondition = new compiler.Instr.JumpInstr(condition);
        env.addInstr(jmpIntoCondition);

        // for each block of control structure
        // switch CompileEnv to the corresponding block
        // trigger codegen of statements that
        // belong into this block
        env.setCurrentBlock(condition);
        m_ifCondition.codegen(env);

        // terminate current block with jump
        // Instr to check if condition is true
        var conditionInstr = m_ifCondition.getInstr();

        var jmpToBodyIfValueNotZero = new Instr.JumpCondInstr(conditionInstr, body, elseHead);
        env.addInstr(jmpToBodyIfValueNotZero);

        env.setCurrentBlock(body);
        m_ifBody.codegen(env);
        var jmpToExit = new compiler.Instr.JumpInstr(exit);
        env.addInstr(jmpToExit);

        env.setCurrentBlock(elseHead);
        if (m_elseBlock instanceof ASTIfNode || m_elseBlock instanceof ASTElseNode) {
            m_elseBlock.codegen(env);
        }
        env.addInstr(jmpToExit);

        // switch CompileEnv to exit block
        env.setCurrentBlock(exit);
    }
}
