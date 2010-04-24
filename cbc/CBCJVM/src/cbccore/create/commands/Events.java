package cbccore.create.commands;

public class Events {
	public static final int EVENT_WHEEL_DROP = 1;
	public static final int EVENT_FRONT_WHEEL_DROP = 2;
	public static final int EVENT_LEFT_WHEEL_DROP = 3;
	public static final int EVENT_RIGHT_WHEEL_DROP = 4;
	public static final int EVENT_BUMP = 5;
	public static final int EVENT_LEFT_BUMP = 6;
	public static final int EVENT_RIGHT_BUMP = 7;
	public static final int EVENT_VIRTUAL_WALL = 8;
	public static final int EVENT_WALL = 9;
	public static final int EVENT_CLIFF = 10;
	public static final int EVENT_LEFT_CLIFF = 11;
	public static final int EVENT_FRONT_LEFT_CLIFF = 12;
	public static final int EVENT_FRONT_RIGHT_CLIFF = 13;
	public static final int EVENT_RIGHT_CLIFF = 14;
	public static final int EVENT_HOME_BASE = 15;
	public static final int EVENT_ADVANCE_BUTTON = 16;
	public static final int EVENT_PLAY_BUTTON = 17;
	public static final int EVENT_OI_MODE_PASSIVE = 22;
	public static int getDigitalInput(int port) {
		return 18 + port;
	}
}
