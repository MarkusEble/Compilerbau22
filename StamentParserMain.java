import java.io.OutputStreamWriter;

public class StamentParserMain {

    public static void main(String[] args) throws Exception {
        String program = compiler.InputReader.fileToString(args[0]);
        compiler.CompileEnv compileEnv = new compiler.CompileEnv(program, false);
        compiler.Lexer lexer = new compiler.Lexer();
        compiler.Parser parser = new compiler.Parser(compileEnv, lexer);
        compiler.ast.ASTStmtNode stmt = parser.parseStmt(program);
        stmt.execute();
        OutputStreamWriter outStream = new OutputStreamWriter(System.out, "UTF-8");
        stmt.print(outStream, "");
        outStream.flush();
    }

}
