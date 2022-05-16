package compiler;
import compiler.ast.*;

public class Parser {
    private Lexer m_lexer;
    
    public Parser(Lexer lexer) {
        m_lexer = lexer;
    }
    
    public ASTExprNode parseExpression(String val) throws Exception {
        m_lexer.init(val);
        return getQuestionMarkExpr();
    }
    
    ASTExprNode getParantheseExpr() throws Exception {
        Token curToken = m_lexer.lookAhead();
        m_lexer.expect(Token.Type.INTEGER);
        return new ASTIntegerLiteralNode(curToken.m_value);
    }
    
    ASTExprNode getUnaryExpr() throws Exception {
        return getParantheseExpr();
    }
    
    ASTExprNode getMulDivExpr() throws Exception {
        return getUnaryExpr();
    }
    
    ASTExprNode getPlusMinusExpr() throws Exception {
        ASTExprNode result = getMulDivExpr();
        Token nextToken = m_lexer.lookAhead();
        while (nextToken.m_type == Token.Type.PLUS || nextToken.m_type == Token.Type.MINUS) {
            if (nextToken.m_type == Token.Type.PLUS) {
                m_lexer.advance();
                result = new ASTPlusMinusExprNode(result, getMulDivExpr(), Token.Type.PLUS);
            } else {
                m_lexer.advance();
                result = new ASTPlusMinusExprNode(result, getMulDivExpr(), Token.Type.MINUS);
            }
            nextToken = m_lexer.lookAhead();
        }
        return result;
    }

    ASTExprNode getBitAndOrExpr() throws Exception {
        return getPlusMinusExpr();
    }

    ASTExprNode getShiftExpr() throws Exception {
        return getBitAndOrExpr();
    }

    ASTExprNode getCompareExpr() throws Exception {
        return getShiftExpr();
    }

    ASTExprNode getAndOrExpr() throws Exception {
        return getCompareExpr();
    }

    ASTExprNode getQuestionMarkExpr() throws Exception {
        return getAndOrExpr();
    }
}
