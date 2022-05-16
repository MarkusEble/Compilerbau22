import java.io.OutputStreamWriter;

public class LexerMain {

    public static void main(String[] args) throws Exception {
        compiler.Lexer lexer = new compiler.Lexer();
        OutputStreamWriter outStream = new OutputStreamWriter(System.out, "UTF-8");

        lexer.processInput("if abc /*abc\ndef*/5 else \"hello\" def 'u' //comment\n 5.5", outStream);
    }

}
