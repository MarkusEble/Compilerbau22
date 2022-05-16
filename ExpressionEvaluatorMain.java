public class ExpressionEvaluatorMain {

    public static void main(String[] args) throws Exception {
        compiler.Lexer lexer = new compiler.Lexer();
        compiler.ExpressionEvaluator exprEvaluator = new compiler.ExpressionEvaluator(lexer);
        int result = exprEvaluator.eval("423 + 15 - 9 ");
        System.out.println(result);
    }

}
