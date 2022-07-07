package machines;

import compiler.TokenIntf;

/**
 * sample state machine
 * accept AB*
 */

public class StateMachineMultiLineComment extends compiler.StateMachine {
	
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
        afterSlash.addTransition('*', "inComment");
        m_stateMap.put("afterSlash", afterSlash);
        compiler.State inComment = new compiler.State("inComment");
        inComment.addTransitionRange(' ', '~', "inComment");
        inComment.addTransition('\n', "inComment");
        inComment.addTransition('\t', "inComment");
        inComment.addTransition('\r', "inComment");
        inComment.addTransition('/', "inComment");
        inComment.addTransition('*', "inCommentAfterStar");
        m_stateMap.put("inComment", inComment);
        compiler.State inCommentAfterStar = new compiler.State("inCommentAfterStar");
        inCommentAfterStar.addTransitionRange(' ', '~', "inComment");
        inCommentAfterStar.addTransition('*', "inCommentAfterStar");
        inCommentAfterStar.addTransition(' ', "inComment");
        inCommentAfterStar.addTransition('\n', "inComment");
        inCommentAfterStar.addTransition('\t', "inComment");
        inCommentAfterStar.addTransition('\r', "inComment");
        inCommentAfterStar.addTransition('/', "end");
        m_stateMap.put("inCommentAfterStar", inCommentAfterStar);
        compiler.State end = new compiler.State("end");
        m_stateMap.put("end", end);
	}

    public TokenIntf.Type getType() {
        return TokenIntf.Type.MULTILINECOMMENT;
    }

}
