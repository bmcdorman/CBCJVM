package cbccore.create.commands;

import cbccore.create.Create;

public class Script implements Command {

	@Override
	public void add(Create create) {
		create.writeByte(152);
	    create.writeByte(5);
	}

}
