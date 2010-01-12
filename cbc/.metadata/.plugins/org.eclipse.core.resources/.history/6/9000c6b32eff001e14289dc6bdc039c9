package cbc;

public class InteractionProtocol {
	public enum Command
	{
		STOP_PROGRAM,
	}
	public InteractionProtocol() {
		String data = "System.out.println(\"Hello World!\");";
		System.out.println(getMethod(data));
		String[] args = getArgs(data);
		for(int i = 0; i < args.length; ++i) {
			System.out.println(args[i]);
		}
	}
	public static void main(String[] args) {
		new InteractionProtocol();
	}
	public String getMethod(String data) {
		String method = data.substring(0, data.indexOf('('));
		return method;
	}
	public String[] getArgs(String data) {
		String args = data.substring(data.indexOf('(') + 1);
		String[] split = args.split(",");
		split[split.length - 1] = split[split.length - 1].substring(0, split[split.length - 1].indexOf(')'));
		return split;
	}
}
