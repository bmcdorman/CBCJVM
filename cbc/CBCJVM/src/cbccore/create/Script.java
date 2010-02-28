package cbccore.create;

import java.util.Stack;

import cbccore.create.commands.Command;

public class Script extends Stack<Command> {
	private static final long serialVersionUID = 6974010113183225899L;
	public Script() {
		add(new cbccore.create.commands.Script());
	}
}
