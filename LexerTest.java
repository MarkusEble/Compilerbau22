import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;

public class LexerTest implements test.TestCaseIntf {

	public String executeTest(String input) throws Exception {
        compiler.Lexer lexer = new compiler.Lexer();
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        OutputStreamWriter outStreamWriter = new OutputStreamWriter(outStream, "UTF-8");
        lexer.processInput(input, outStreamWriter);
		return new String(outStream.toByteArray(), "UTF-8");
	}
}
