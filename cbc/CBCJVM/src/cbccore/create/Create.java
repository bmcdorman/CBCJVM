package cbccore.create;

import java.io.IOException;

import cbccore.Device;
import cbccore.create.commands.*;

public class Create {
	public class ConnectionException extends IOException {
		private static final long serialVersionUID = -7364867572058696574L;
	}
	
	enum Mode {
		Safe,
		Passive,
		Full,
		Off
	}
	
	private static cbccore.low.Create lowCreate = Device.getLowCreateController();
	private static LowSideDrivers lowSideDrivers = new LowSideDrivers(lowCreate);

	public Create() throws ConnectionException {
		connect();
	}
	
	@Override
	public void finalize() {
		disconnect();
	}
	
	public void sendScript(Script script) {
		Script s = (Script) script.clone();
		while(!s.isEmpty()) {
			Command c = s.pop();
			c.add(this);
		}
	}
	
	/**
	 * First step for connecting CBC to Create. This function puts the Create in
	 * the safe mode.
	 * 
	 * @see #disconnect
	 */
	public void connect() throws ConnectionException {
		int ret = lowCreate.create_connect();
		if(ret < 0) throw new ConnectionException();
	}

	/**
	 * Returns Create to proper state. Call this at the end of your program.
	 * 
	 * @see #connect
	 */
	public void disconnect() {
		lowCreate.create_disconnect();
	}

	/**
	 * Puts Create into passive mode (no motors)
	 * 
	 * @see #passive
	 * @see #safe
	 * @see #full
	 * @see #mode
	 */
	public void start() {
		lowCreate.create_start();
	}

	public void setMode(Mode m) {
		if(m == Mode.Safe) lowCreate.create_safe();
		else if(m == Mode.Passive) lowCreate.create_passive();
		else if(m == Mode.Full) lowCreate.create_full();
		else lowCreate.create_safe();
	}

	/**
	 * Simulates a Roomba doing a spot clean
	 * 
	 * @see #cover
	 * @see #demo
	 * @see #cover_dock
	 */
	public void spot() {
		lowCreate.create_spot();
	}

	/**
	 * Simulates a Roomba covering a room
	 * 
	 * @see #spot
	 * @see #demo
	 * @see #cover_dock
	 */
	public void cover() {
		lowCreate.create_cover();
	}

	/**
	 * Runs built in demos (see Create IO documentation)
	 * 
	 * @param d
	 *            See Create IO documentation. I would normally look this up,
	 *            but it seems so pointless...
	 * @see #spot
	 * @see #cover
	 * @see #cover_dock
	 */
	public void demo(int d) {
		lowCreate.create_demo(d);
	}

	/**
	 * Create roams around until it sees an IR dock and then attempts to dock
	 * 
	 * @see #spot
	 * @see #cover
	 * @see #demo
	 */
	public void coverDock() {
		lowCreate.create_cover_dock();
	}

	/**
	 * the Create's mode
	 * 
	 * @return 0 off; 1 passive; 2 safe; 3 full
	 * @see #passive
	 * @see #safe
	 * @see #full
	 */
	public Mode getMode() {
		int m = lowCreate.create_mode();
		if(m == 0) return Mode.Off;
		if(m == 1) return Mode.Passive;
		if(m == 2) return Mode.Safe;
		if(m == 3) return Mode.Full;
		return Mode.Off;
	}

	/**
	 * Stops the drive wheels
	 */
	public void stop() {
		lowCreate.create_stop();
	}

	/**
	 * Drives in an arc.
	 * 
	 * @param speed
	 *            range is 20-500mm/s
	 * @param radius
	 *            radius in mm/s.
	 *            <p>
	 *            A radius of 32767 will drive the robot straight.
	 *            <p>
	 *            A radius of 1 will spin the robot CCW
	 *            <p>
	 *            A radius of -1 will spin the robot CW
	 *            <p>
	 *            Negative radii will be right turns, positive radii left turns
	 * @see #drive_straight
	 */
	public void drive(int speed, int radius) {
		lowCreate.create_drive(speed, radius);
	}

	/**
	 * Drives straight at speed in mm/s
	 * 
	 * @param speed
	 *            20-500mm/s
	 */
	public void driveStraight(int speed) {
		lowCreate.create_drive_straight(speed);
	}

	/**
	 * Spins CW with edge speed of speed in mm/s
	 * 
	 * @param speed
	 *            20-500mm/s. Speed of edge (wheels) of bot.
	 * @see #spin_CCW
	 */
	public void spinCW(int speed) {
		lowCreate.create_spin_CW(speed);
	}

	/**
	 * Spins CCW with edge speed of speed in mm/s
	 * 
	 * @param speed
	 *            20-500mm/s. Speed of edge (wheels) of bot.
	 * @see #spin_CW
	 */
	public void spinCCW(int speed) {
		lowCreate.create_spin_CCW(speed);
	}

	/**
	 * Specifies individual left and right speeds in mm/s
	 * 
	 * @param r_speed
	 *            20-500mm/s. Speed of right wheel.
	 * @param l_speed
	 *            20-500mm/s. Speed of left wheel.
	 * @see #drive
	 */
	public void driveDirect(int r_speed, int l_speed) {
		lowCreate.create_drive_direct(r_speed, l_speed);
	}

	/**
	 * This function blocks and does a pretty accurate spin. Note that the
	 * function will not return until the spin is complete CAUTION: requesting
	 * the robot to spin more than about 3600 degrees may never terminate
	 * 
	 * @param speed
	 *            20-500mm/s. Speed of edge (wheels) of bot.
	 * @param angle
	 *            Angle in degrees to turn before returning.
	 *            <p>
	 *            <b>CAUTION: requesting the robot to spin more than about 3600
	 *            degrees may never terminate</b>
	 * @return -1 if error
	 * @see #spin_CW
	 * @see #spin_CCW
	 */
	public int spinBlock(int speed, int angle) {
		return lowCreate.create_spin_block(speed, angle);
	}
	
	// public int _get_raw_encoders(long* lenc, long* renc);

	/**
	 * Turn on/off the advance LED
	 * 
	 * @param on
	 *            1 to turn on light and 0 to turn it off
	 * @see #play_led
	 * @see #power_led
	 */
	public void advanceLed(int on) {
		lowCreate.create_advance_led(on);
	}

	/**
	 * Turn on/off the play LED
	 * 
	 * @param on
	 *            1 to turn on light and 0 to turn it off
	 * @see #advance_led
	 * @see #power_led
	 */
	public void playLed(int on) {
		lowCreate.create_play_led(on);
	}

	/**
	 * Control the color and the brightness of the power LED
	 * 
	 * @param color
	 *            0 is red and 255 green
	 * @param brightness
	 *            0 is off and 255 is full brightness
	 * @see #advance_led
	 * @see #play_led
	 */
	public void powerLed(int color, int brightness) {
		lowCreate.create_power_led(color, brightness);
	}

	/**
	 * This function sets the three digital out put pins 20,7,19 where 20 is the
	 * high bit and 19 is the low. You probably don't care about this function.
	 * 
	 * @param bits
	 *            Should have a value 0 to 7.
	 */
	public void digitalOutput(int bits) {
		lowCreate.create_digital_output(bits);
	}

	/**
	 * Turns on and off the signal for the three low side drivers (128 = 100%).
	 * A 0 or 1 should be given for each of the drivers to turn them off or on.
	 * You probably don't care about this function.
	 * 
	 * @param pwm0
	 *            pin 22
	 * @param pwm1
	 *            pin 23
	 * @param pwm2
	 *            pin 24
	 */
	public LowSideDrivers getLowSideDrivers() {
		return lowSideDrivers;
	}

	/**
	 * This loads a song into the robot's memory. Song can be numbered 0 to 15.
	 * The first element in each row of the array should be the number of notes
	 * (1-16) the subsequent pairs of bytes should be tone and duration see the
	 * roomba SCI manual for note codes. User's program should load the song
	 * data into the array before calling this routine. Sets gc_song_array, an
	 * inaccessable variable. <b>DO NOT USE THIS FUNCTION UNTIL THE ISSUE IS
	 * RESOLVED</b>
	 * 
	 * @param num
	 *            Song can be numbered 0 to 15
	 * @see #play_song
	 */
	public void loadSong(int num) {
		lowCreate.create_load_song(num);
	}

	/**
	 * See the roomba SCI manual for note codes. Uses gc_song_array, an
	 * inaccessable variable. <b>DO NOT USE THIS FUNCTION UNTIL THE ISSUE IS
	 * RESOLVED</b>
	 * 
	 * @param num
	 *            Song can be numbered 0 to 15
	 * @see #load_song
	 */
	public void playSong(int num) {
		lowCreate.create_play_song(num);
	}

	// public int read_block(char* data, int count);

	/**
	 * See Create IO Documentation. You probably don't care about this function.
	 * 
	 * @param write_byte
	 *            the byte to write
	 * @see #clear_serial_buffer
	 */
	public void writeByte(int write_byte) {
		char c = (char)write_byte;
		lowCreate.create_write_byte(c);
	}

	public void clearSerialBuffer() {
		lowCreate.create_clear_serial_buffer();
	}
	
	public static byte fromUnsigned(int c) {
		return (byte) ((c > 127) ? c - 256 : c);
	}
}
