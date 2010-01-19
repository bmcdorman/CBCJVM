public class Main {
	public static void main(String[] a) {
		String[] args = new String[2];
		args[0] = "-f";
		args[1] = "test.js";
		org.mozilla.javascript.tools.shell.Main.main(args);
	}
}
 