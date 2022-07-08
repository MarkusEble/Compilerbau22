package test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public abstract class TestSuiteIntf {
    compiler.InputReaderIntf m_inputReader;
    TestCaseIntf m_testCase;
	
    // read and execute a sequence of test cases
	abstract void readAndExecuteTestSequence() throws Exception;
	
	// read and execute one test case
	abstract void readAndExecuteTestCase() throws Exception;
	
	// read the content of a test section (after $IN or $OUT)
	abstract String readTestContent() throws Exception;
	
	// check that input is $IN and consume it
	abstract void readDollarIn() throws Exception;
	
	// check that output is $OUT and consume it
	abstract void readDollarOut() throws Exception;
	
    // creates a test object from an input file
    TestSuiteIntf(String input, TestCaseIntf testCase) {
        m_inputReader = new compiler.InputReader(input);
        m_testCase = testCase;
    }

	// execute a test case with the given input and compare result with expected output
	void executeTestCase(String input, String expectedOutput) throws Exception {
		String result;
		try {
			result = m_testCase.executeTest(input);
		} catch (Exception e) {
			result = "exception: \"";
			result += e.getMessage();
			result += "\"\n";
		}
		if (result.equals(expectedOutput)) {
			System.out.println("TEST SUCCEEDED");
			System.out.print(input);
			System.out.println("ACTUAL OUTPUT");			
			System.out.print(result);
		} else {
			System.out.println("TEST FAILED");
			System.out.print(input);
			System.out.println("EXPECTED OUTPUT");			
			System.out.print(expectedOutput);
			System.out.println("ACTUAL OUTPUT");			
			System.out.print(result);
			throw new Exception("TestFailure");
		}
	}
	
	// public interface to execute all tests
	public void testRun() throws Exception {
		readAndExecuteTestSequence();
	}

}
