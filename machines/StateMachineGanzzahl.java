package machines;

import compiler.StateMachine;
import compiler.TokenIntf;

public class StateMachineGanzzahl extends StateMachine {

	@Override
	public void initStateTable() {
		compiler.State start = new compiler.State("start");
		compiler.State end = new compiler.State("end");
		start.addTransition('0', "end");
		compiler.State numberZeroToNine = new compiler.State("numberZeroToNine");
		compiler.State negative = new compiler.State("negative");

    	numberZeroToNine.addTransitionRange('0', '9', "numberZeroToNine");
        start.addTransitionRange('0', '9', "numberZeroToNine");
        negative.addTransitionRange('0', '9', "numberZeroToNine");

		m_stateMap.put("start", start);

		m_stateMap.put("numberZeroToNine", numberZeroToNine);
		m_stateMap.put("end", end);
		m_stateMap.put("negative", negative);

	}

	@Override
	public String getStartState() {
		return "start";
	}

	@Override
	public boolean isFinalState() {
		return (m_state.equals("numberZeroToNine") || m_state.equals("end"));
	}

   @Override
   public TokenIntf.Type getType() {
       return TokenIntf.Type.INTEGER;
   }

}
