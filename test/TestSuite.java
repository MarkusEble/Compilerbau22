package test;


public class TestSuite extends TestSuiteIntf {
  
    public TestSuite(String input, TestCaseIntf testCase) {
    	super(input, testCase);
    }

	public void readAndExecuteTestSequence() throws Exception {
		while (m_inputReader.lookAheadChar() != 0) {
			readAndExecuteTestCase();
		}
	}
	
	public void readAndExecuteTestCase() throws Exception {
		readDollarIn();
		String input = readTestContent();
		readDollarOut();
		String output = readTestContent();
		executeTestCase(input, output);
	}
	
	public String readTestContent() throws Exception {
		String testContent = "";
		while (m_inputReader.lookAheadChar() != '$' && m_inputReader.lookAheadChar() != 0) {
			testContent += m_inputReader.lookAheadChar();
			m_inputReader.advance();
		}
		return testContent;
	}
	
	public void readDollarIn() throws Exception {
		m_inputReader.expect('$');
		m_inputReader.expect('I');
		m_inputReader.expect('N');
		m_inputReader.expect('\n');
	}
	
	public void readDollarOut() throws Exception {
		m_inputReader.expect('$');
		m_inputReader.expect('O');
		m_inputReader.expect('U');
		m_inputReader.expect('T');
		m_inputReader.expect('\n');
	}
}
