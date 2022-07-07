
import machines.StateMachineMultiLineComment;

import java.io.OutputStreamWriter;

public class StateMachineMultiLineCommentMain {

	public static void main(String[] args) throws Exception {
		compiler.StateMachine commentMachine = new StateMachineMultiLineComment();
		OutputStreamWriter outStream = new OutputStreamWriter(System.out, "UTF-8");
		commentMachine.process("/*abc ()*/", outStream);

        System.out.println();
        System.out.println(commentMachine.asDot());
		System.out.println();
	}

}
