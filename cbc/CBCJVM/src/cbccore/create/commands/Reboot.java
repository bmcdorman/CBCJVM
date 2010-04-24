package cbccore.create.commands;

import cbccore.create.Create;

public class Reboot implements Command {

	@Override
	public void add(Create create) {
		create.writeByte(7);
	}

}
