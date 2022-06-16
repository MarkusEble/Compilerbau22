package compiler.ast;

import compiler.FunctionInfo;
import compiler.Instr;
import compiler.InstrIntf;

import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ASTFuncCallExprNode extends ASTExprNode {
    
    public String m_identifier;
    public List<ASTExprNode> m_args;
    
    public ASTFuncCallExprNode(String identifier, List<ASTExprNode> args) {
        m_identifier = identifier;
        m_args = args;
    }
    
    @Override
    public void print(OutputStreamWriter outStream, String indent) throws Exception {
        outStream.write(indent);
        outStream.write(String.format("CALL %s\n", m_identifier));
    }
    
    @Override
    public int eval() {
        return 0;
    }
    
    @Override
    public void codegen(compiler.CompileEnv env) throws Exception {
        FunctionInfo info = env.getFunctionTable().getFunction(m_identifier);
        
        // Check if function is even defined
        if (info == null) {
            throw new Exception(String.format("Function \"%s\" not defined.", m_identifier));
        }
        
        // Check if correct amount of expressions given
        if (info.varNames.size() != m_args.size()) {
            throw new Exception(String.format("Function call \"%s\" has invalid argument count.", info.m_name));
        }
        
        List<InstrIntf> instructions = new ArrayList<InstrIntf>();
        ListIterator<ASTExprNode> iter = m_args.listIterator();
        while (iter.hasNext()) {
            ASTExprNode arg = iter.next();
            arg.codegen(env);
            instructions.add(arg.getInstr());
        }
        
        // Generate this instruction and add to environment
        m_instr = new Instr.CallInstr(info, instructions);
        env.addInstr(m_instr);
    }
    
}
