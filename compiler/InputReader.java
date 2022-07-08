package compiler;

import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * provide input string as stream
 */
public class InputReader implements InputReaderIntf, Cloneable {
	private String m_input;
	private int m_pos;
    
	public InputReader(String input) {
		m_input = input;
		m_pos = 0;
	}
	
	public char currentChar() {
		if (m_pos != m_input.length()) {
			return m_input.charAt(m_pos);
		} else {
			return 0;
		}
	}
	
	public char lookAheadChar() {
	    return currentChar();
	}

	public void advance() {
		if (m_pos != m_input.length()) {
			m_pos++;
		}
		// skip \r in \r\n
		if (m_pos+1 < m_input.length() && m_input.charAt(m_pos) == '\r' && m_input.charAt(m_pos+1) == '\n') {		    
		    m_pos++;
		}
	}
	
    public void expect(char c) throws Exception {
        if (currentChar() != c) {
            String msg = new String("unexpected character: '");
            msg += currentChar();
            msg += "'";
            msg += " expected: '";
            msg += c;
            msg += "'";
            throw new Exception(msg);
        }
        advance();
    }
    
    public void traceState(OutputStreamWriter outStream) throws Exception {
		for (int i = 0; i != m_input.length(); i++) {
			if (i < m_pos) {
				// blank already processed characters
			    outStream.write(' ');
			} else {
				// print remaining characters
				outStream.write(m_input.charAt(i));
			}
		}
	}
	
	public void traceBlank(OutputStreamWriter outStream) throws Exception {
		for (int i = 0; i != m_input.length(); i++) {
 	        outStream.write(' ');
		}
	}

	public void traceHead(OutputStreamWriter outStream) throws Exception {
		outStream.write("IN");
		for (int i = 2; i < m_input.length(); i++) {
 	        outStream.write(' ');
		}		
	}

	public Object clone() throws CloneNotSupportedException {
		InputReader theClone = (InputReader)super.clone();
		theClone.m_input = new String(m_input);
		theClone.m_pos = m_pos;
		return theClone;
	}

    public static String fileToString(String fileName) throws Exception {
	   Path filePath = Path.of(fileName);
	   String content = Files.readString(filePath);
	   return content;
	}
}

