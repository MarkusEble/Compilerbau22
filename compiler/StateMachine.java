package compiler;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * deterministic finish state machine with transition table
 */
public abstract class StateMachine extends StateMachineBase {
	// set of states
	protected HashMap<String, State> m_stateMap;

	public StateMachine() {
		super();
		m_stateMap = new HashMap<String, State>();
		initStateTable();
	}

	/**
	 * initialize state transition table
	 */
	public abstract void initStateTable();

	@Override
	public void step() {
		// look for transition on (current state, current input)
		char curChar = m_input.currentChar();
		State curState = m_stateMap.get(m_state);
		String nextStateString = curState.getTransition(curChar);
        if (nextStateString == null) {
        	// no transition => error
        	m_state = "error";
        } else {
        	// execute transition
        	State nextState = m_stateMap.get(nextStateString);
        	m_state = nextState.getName();
        }
        m_input.advance();
	}

	public String asDot() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("digraph StateMachine {" + System.lineSeparator());
		stringBuilder.append("  rankdir=LR;" + System.lineSeparator());
		stringBuilder.append("  size=\"8,5\";" + System.lineSeparator());
		stringBuilder.append("  node [shape = doublecircle];");
		for (String finalState : getFinalStates()) {
			stringBuilder.append(" " + finalState + ";");
		}
		stringBuilder.append(System.lineSeparator());
		stringBuilder.append("  node [shape = circle];" + System.lineSeparator());
		for (State state : m_stateMap.values()) {
			stringBuilder.append(state.transitionsAsDot(true));
		}
		stringBuilder.append("}" + System.lineSeparator());
		return stringBuilder.toString();
	}

	private Set<String> getFinalStates() {
		/*
		 * FIXME: Since no proper data structure is used for the final states, but an
		 * abstract function, we have to change the objects inner state. That's quite
		 * hacky. We should think about storing the final states directly in a set. This
		 * would make this function no longer necessary.
		 */
		Set<String> result = new HashSet<>();
		String previousState = m_state;
		for (String state : m_stateMap.keySet()) {
			m_state = state;
			if (isFinalState()) {
				result.add(state);
			}
		}
		m_state = previousState;
		return result;
	}
}
