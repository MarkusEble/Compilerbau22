package compiler;

public class MultiLineInputReader {
    private String[] m_lines;
    private int m_line = 0;
    private int m_col = 0;
    
    MultiLineInputReader(String input) {
        // split input into lines
        m_lines = input.split("\\r?\\n|\\r");
        // at \n at end of lines
        for (int i = 0; i != m_lines.length; i++) {
            m_lines[i] += "\n";
        }        
    }

    int getLine() {
        return m_line;
    }

    int getCol() {
        return m_col;
    }
    
    boolean isEmpty() {
        return m_line == m_lines.length;
    }

    public char currentChar() {
        // check end of file
        if (isEmpty()) {
            return 0;
        }

        // return current char
        return m_lines[m_line].charAt(m_col);
    }

    public String currentLine() {
        if (isEmpty()) {
            return "";
        }
        return m_lines[m_line];
    }

    public void advance() {
        if (isEmpty()) {
            return;
        }
        m_col++;
        // check end of line
        if (m_col == currentLine().length()) {
            m_line++;
            m_col = 0;
        }
    }
    
    String getRemaining() {
        String remaining = new String();
        // first line
        if (m_line < m_lines.length) {
            remaining += m_lines[m_line].substring(m_col);
        }
        for (int curLine = m_line+1; curLine < m_lines.length; curLine++) {
            remaining += m_lines[curLine];
        }
        return remaining;
    }

    String advanceAndGet(int cnt) {
        String result = new String();
        for (int i = 0; i < cnt; i++) {
            result += currentChar();
            advance();
        }
        return result;
    }

    String getMarkedCodeSnippetCurrentPos() {
        return getMarkedCodeSnippet(m_line, m_col, m_line, m_col+1);
    }

    String getMarkedCodeSnippet(int startLine, int startCol, int endLine, int endCol) {
        String codeSnippet = new String();
        // for each line
        for (int curLine = startLine; curLine <= endLine; curLine++) {
            String line = m_lines[curLine];
            codeSnippet += line;
            // for each col
            for (int curCol = 0; curCol < line.length(); curCol++) {
                boolean beforeBegin = (curLine == startLine && curCol < startCol);
                boolean afterEnd = (curLine == endLine && curCol >= endCol);
                if (beforeBegin || afterEnd) {
                    codeSnippet += ' ';
                } else {
                    // mark in snippet
                    codeSnippet += '^';
                }
            }
            codeSnippet += '\n';            
        }
        return codeSnippet;
    }
}
