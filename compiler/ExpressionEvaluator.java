package compiler;

public class ExpressionEvaluator {
    private Lexer m_lexer;
    
    public ExpressionEvaluator(Lexer lexer) {
        m_lexer = lexer;
    }
    
    public int eval(String val) throws Exception {
        m_lexer.init(val);
        return getQuestionMarkExpr();
    }
    
    int getParantheseExpr() throws Exception {
        Token curToken = m_lexer.lookAhead();
        m_lexer.expect(Token.Type.INTEGER);
        return Integer.valueOf(curToken.m_value);
    }
    
    int getUnaryExpr() throws Exception {
        return getParantheseExpr();
    }
    
    int getMulDivExpr() throws Exception {
        return getUnaryExpr();
    }
    
    int getPlusMinusExpr() throws Exception {
        int result = getMulDivExpr();
        Token nextToken = m_lexer.lookAhead();
        while (nextToken.m_type == Token.Type.PLUS || nextToken.m_type == Token.Type.MINUS) {
            if (nextToken.m_type == Token.Type.PLUS) {
                m_lexer.advance();
                result += getMulDivExpr();
            } else {
                m_lexer.advance();
                result -= getMulDivExpr();                
            }
            nextToken = m_lexer.lookAhead();
        }
        return getMulDivExpr();
    }

    int getBitAndOrExpr() throws Exception {
        return getPlusMinusExpr();
    }

    int getShiftExpr() throws Exception {
        return getBitAndOrExpr();
    }

    int getCompareExpr() throws Exception {
        return getShiftExpr();
    }

    int getAndOrExpr() throws Exception {
        return getCompareExpr();
    }

    int getQuestionMarkExpr() throws Exception {
        return getAndOrExpr();
    }
}
