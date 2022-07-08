package compiler;

import java.io.OutputStreamWriter;

/**
 * interface for stream input
 */
public interface InputReaderIntf {
	/**
	 * constructs InputReader reading from string
	 */
	// public InputReader(String input);

	/**
	 * look at the current character without
	 * consuming it. 0 means end of input.
	 */
	public char currentChar();
    public char lookAheadChar();
	
	/**
	 * consume current char and
	 * advance to next character
	 */
	public void advance();
	
    /**
     * check if current char is the expected char
     * consume if yes, throw exception otherwise
     */
    public void expect(char c) throws Exception;

    /**
	 * print current state of input to stream
	 */
	public void traceState(OutputStreamWriter outStream) throws Exception;

	/**
	 * print blanks for current state of input stream
	 */
	public void traceBlank(OutputStreamWriter outStream) throws Exception;

	/**
	 * print headline to stream
	 */
	public void traceHead(OutputStreamWriter outStream) throws Exception;

}
