package machines;

import compiler.TokenIntf;

/**
 * sample state machine
 * accept Line Comment
 */

public class StateMachineLineComment extends compiler.StateMachine {
	
	@Override
	public String getStartState() {
		return "start";
	}

	@Override
	public boolean isFinalState() {
		return (m_state.equals("end"));
	}

	@Override
	public void initStateTable() {
		compiler.State start = new compiler.State("start");
		start.addTransition('/', "afterSlash");
		m_stateMap.put("start", start);

		compiler.State afterSlash = new compiler.State("afterSlash");
		afterSlash.addTransition('/', "inComment");
		m_stateMap.put("afterSlash", afterSlash);

		compiler.State inComment = new compiler.State("inComment");
		inComment.addTransitionRange(' ', '~', "inComment");
		m_stateMap.put("inComment", inComment);
        inComment.addTransition('\r', "inComment");
		inComment.addTransition('\n', "end");

		compiler.State end = new compiler.State("end");
		m_stateMap.put("end", end);
	}

    public TokenIntf.Type getType() {
        return TokenIntf.Type.LINECOMMENT;
    }

}
