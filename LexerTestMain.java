public class LexerTestMain {

	public static void main(String[] args) throws Exception {
		System.out.println("BEGIN");
		test.TestSuiteIntf test = new test.TestSuite(compiler.InputReader.fileToString(args[0]), new LexerTest());
		test.testRun();
		System.out.println("END");
	}

}

