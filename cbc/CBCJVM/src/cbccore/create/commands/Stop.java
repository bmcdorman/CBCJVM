package cbccore.create.commands;

import cbccore.create.Create;

public class Stop implements Command {
	public void add(Create create) {
		create.stop();
	}
}
