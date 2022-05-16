package compiler;

import java.io.*;

public class FileReader implements FileReaderIntf {
    private InputStream m_inputStream;
    private Reader m_inputStreamReader;
    private char m_nextChar;
    
	public FileReader(InputStream inputStream) throws Exception {
		m_inputStream       = inputStream;
		m_inputStreamReader = new InputStreamReader(m_inputStream);
		advance();
	}
	
	public char lookAheadChar() {
		return m_nextChar;
	}
	
	public void advance() throws Exception {
		int nextChar = m_inputStreamReader.read();
		if (nextChar == '\r') {
		    nextChar = m_inputStreamReader.read();
		}
		m_nextChar = (nextChar == -1) ? 0 : (char)nextChar;
	}
	
	public void expect(char c) throws Exception {
		if (m_nextChar != c) {
			String msg = new String("unexpected character: '");
			msg += m_nextChar;
			msg += "'";
			msg += " expected: '";
			msg += c;
			msg += "'";
			throw new Exception(msg);
		}
		advance();
	}
	
	public static FileReaderIntf fromFileName(String fileName) throws Exception {
		FileInputStream inputStream = new FileInputStream(fileName);
		compiler.FileReaderIntf fileReader = new compiler.FileReader(inputStream);
		return fileReader;
	}
}
