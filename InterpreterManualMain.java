
import compiler.CompileEnv;

public class InterpreterManualMain {

	public static void main(String[] args) throws Exception {
		System.out.println("BEGIN");
        String program = compiler.InputReader.fileToString(args[0]);
		CompileEnv compiler = new CompileEnv(program, false);
		compiler.compile();
		//compiler.dumpAst(System.out);
		compiler.dump(System.out);
		compiler.execute(System.out);
		System.out.println("END");
	}

}
