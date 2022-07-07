package machines;

public class StateMachineStringLiteral extends compiler.StateMachine {
	
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
		start.addTransition('"', "inStringLiteral");
		m_stateMap.put("start", start);

		compiler.State inStringLiteral = new compiler.State("inStringLiteral");
		inStringLiteral.addTransitionRange(' ', '~', "inStringLiteral");
		inStringLiteral.addTransition('"', "end");
		m_stateMap.put("inStringLiteral", inStringLiteral);

		compiler.State end = new compiler.State("end");
		m_stateMap.put("end", end);

	}

	public String getName() {
		return "String";
	}

}
