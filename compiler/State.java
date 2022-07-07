package compiler;

import java.util.HashMap;
import java.util.Map;

/**
 * single state of a deterministic finite state machine with transitions
 */
public class State {
	private String m_name;
	// part of transition function
	private HashMap<String, String> m_transitionMap;

	public State(String name) {
		m_name = name;
		m_transitionMap = new HashMap<String, String>();
	}
	
	public void addTransition(char terminal, String targetState) {
		m_transitionMap.put(String.valueOf(terminal), targetState);
	}

    public void addTransitionRange(char first, char last, String targetState) {
        for (char c = first; c <= last; c++) {
            addTransition(c, targetState);
        }
    }

	public String getTransition(char terminal) {
		return m_transitionMap.get(String.valueOf(terminal));
	}

	public String getName() {
		return m_name;
	}

	public String transitionsAsDot(boolean collapse) {
		if (collapse) {
			return transitionsAsDot(getName(), Utils.collapse(m_transitionMap));
		} else {
			return transitionsAsDot(getName(), m_transitionMap);
		}
	}

	private static String transitionsAsDot(String origin, Map<String, String> transitions) {
		StringBuilder builder = new StringBuilder();
		for (Map.Entry<String, String> entry : transitions.entrySet()) {
			builder.append(
					String.format("  %s -> %s [ label = \"%s\" ];\n", origin, entry.getValue(), entry.getKey()));
		}
		return builder.toString();
	}

}