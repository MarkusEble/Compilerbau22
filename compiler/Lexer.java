package compiler;

import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Lexer {

    static class MachineInfo {

        public StateMachineBase m_machine;
        public int m_acceptPos;

        public MachineInfo(StateMachineBase machine) {
            m_machine = machine;
            m_acceptPos = 0;
        }

        public void init(String input) {
            m_acceptPos = 0;
            m_machine.init(input);
        }
    }

    protected Vector<MachineInfo> m_machineList;
    protected String m_input;
    protected int lineCount;

    int curLine = 0;

    public Lexer() {
        m_machineList = new Vector<MachineInfo>();
        this.lineCount = 0;
    }

    public void addMachine(StateMachineBase machine) {
        m_machineList.add(new MachineInfo(machine));
    }

    public void init(String input) {
        m_input = input;
    }

    public void initMachines(String input) {
        for (MachineInfo machine : m_machineList) {
            machine.init(input);
        }
    }

    public Token nextWord() throws Exception {
        int curPos = 0;
        // initialize machines
        initMachines(m_input);
        // while some machine are in process
        boolean machineActive;
        do {
            machineActive = false;
            // for each machine in process
            for (MachineInfo machine : m_machineList) {
                if (machine.m_machine.isFinished()) {
                    continue;
                }
                machineActive = true;
                // next step
                machine.m_machine.step();
                // if possible final state
                if (machine.m_machine.isFinalState()) {
                    // update last position machine would accept
                    machine.m_acceptPos = curPos + 1;
                }
            } // end for each machine in process
            curPos++;
        } while (machineActive); // end while some machine in process
        // select first machine with largest final pos (greedy)
        MachineInfo bestMatch = new MachineInfo(null);
        for (MachineInfo machine : m_machineList) {
            if (machine.m_acceptPos > bestMatch.m_acceptPos) {
                bestMatch = machine;
            }
        }



        // set next word [start pos, final pos)
        String nextWord = m_input.substring(0, bestMatch.m_acceptPos);
        m_input = m_input.substring(bestMatch.m_acceptPos);

        curLine += nextWord.length() - nextWord.replace("\n", "").length();
        Token token = new Token();
        token.m_type = bestMatch.m_machine.getType();
        token.m_value = nextWord;
        return token;
    }

    boolean isWhitespace(char c) {
        if (c == ' ' || c == '\t' || c == '\n') {
            return true;
        } else {
            return false;
        }
    }

    void skipWhiteSpace() {
        int i = 0;
        while (i < m_input.length() && isWhitespace(m_input.charAt(i))) {
            i++;
        }
        m_input = m_input.substring(i);
    }

    private List<Token> tokenListe = new ArrayList<>();

    public void processInput(String input, OutputStreamWriter outStream) throws Exception {
        m_input = input;
        // while input available
        while (!m_input.isEmpty()) {
            // get next word
            Token curWord = nextWord();
            // break on failure
            if (curWord.m_type == Token.Type.EOF) {
                outStream.write("ERROR\n");
                break;
            }
            // print word
            //outStream.write(curWord.toString());
            //outStream.write("\n");
            //outStream.flush();
            tokenListe.add(curWord);
        }
    }

    public Token getCurToken() {
        return tokenListe.get(0);
    }

    public Token lookAhead() {
        return tokenListe.get(1);
    }

    public void advance() {
        tokenListe.remove(0);
    }

    public void expect(Token token) throws Exception {
        if (token.equals(tokenListe.get(0))) {
            advance();
        }
        throw new Exception("Token not expected");

    }

    public boolean accept(Token token) {
        if (token.equals(tokenListe.get(0))) {
            advance();
            return true;
        }
        return false;
    }
}
