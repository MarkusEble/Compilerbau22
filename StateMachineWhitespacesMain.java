import java.io.OutputStreamWriter;

import compiler.StateMachine;
import machines.StateMachineWhitespaces;

public class StateMachineWhitespacesMain {

	public static void main(String[] args) throws Exception {
		StateMachine whitespaceMachine = new StateMachineWhitespaces();
		OutputStreamWriter outStream = new OutputStreamWriter(System.out, "UTF-8");
		whitespaceMachine.process("	", outStream);
	}

}