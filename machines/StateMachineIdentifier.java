package machines;

import compiler.TokenIntf;

/**
 * sample state machine
 * accept AB*
 */

public class StateMachineIdentifier extends compiler.StateMachine {

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
        start.addTransition('_', "end");
        start.addTransitionRange('A', 'Z', "end");
        start.addTransitionRange('a', 'z', "end");
        m_stateMap.put("start", start);

        compiler.State end = new compiler.State("end");
        end.addTransition('_', "end");
        end.addTransitionRange('A', 'Z', "end");
        end.addTransitionRange('a', 'z', "end");
        end.addTransitionRange('0', '9', "end");
        m_stateMap.put("end", end);
    }

    public TokenIntf.Type getType() {
        return TokenIntf.Type.IDENT;
    }


}
