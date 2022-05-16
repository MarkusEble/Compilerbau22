package compiler;

public class CompilerException extends Exception {
	private static final long serialVersionUID = 1L;

	public CompilerException(String msg, int lineNumber, String line, String expected) {
		super(buildMsg(msg, lineNumber, line, expected));
	}

	private static String buildMsg(String msg, int lineNumber, String line, String expected) {
		String fullMsg = msg + "\nat line " + Integer.toString(lineNumber) + "\n" + line;
		if (expected != null) {
			fullMsg += "\nExpected: " + expected + "\n";
		}
		return fullMsg;
	}
}

