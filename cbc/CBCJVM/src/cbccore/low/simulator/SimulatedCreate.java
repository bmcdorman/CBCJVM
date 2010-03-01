/*
 * This file is part of CBCJVM.
 * CBCJVM is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * CBCJVM is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with CBCJVM.  If not, see <http://www.gnu.org/licenses/>.
 */

package cbccore.low.simulator;

import cbccore.low.CBCSimulator;
import cbccore.low.Create;
import cbccore.NotImplemented;

/**
 * 
 * @author Braden McDorman / Benjamin Woodruff
 * 
 */

public class SimulatedCreate extends Create {

	protected CBCSimulator cbc;

	protected boolean connected;

	public double radiansPerSecond;
	public double forwardSpeed;
	public double width;

	public SimulatedCreate(CBCSimulator c) {
		cbc = c;
	}

	public int create_connect() {
		return 0;
	}

	public void create_disconnect() {
		connected = false;
	}

	@NotImplemented
	public void create_start() {
		// stub
	}

	@NotImplemented
	public void create_passive() {
		// stub
	}

	@NotImplemented
	public void create_safe() {
		// stub
	}

	@NotImplemented
	public void create_full() {
		// stub
	}

	@NotImplemented
	public void create_spot() {
		// stub
	}

	@NotImplemented
	public void create_cover() {
		// stub
	}

	@NotImplemented
	public void create_demo(int d) {
		// stub
	}

	@NotImplemented
	public void create_cover_dock() {
		// stub
	}

	public int create_mode() {
		return 3;
	}

	// Don't use these, they update inaccessable variables
	public int create_sensor_update() {
		return 0;
	}

	public int create_wall() {
		return 0;
	}

	public int create_buttons() {
		return 0;
	}

	public int create_bumpdrop() {
		return 0;
	}

	public int create_cliffs() {
		return 0;
	}

	public int create_angle() {
		return 0;
	}

	public int create_distance() {
		return 0;
	}

	public int create_velocity() {
		return 0;
	}

	public int create_read_IR() {
		return 0;
	}

	public int create_overcurrents() {
		return 0;
	}

	public int create_battery_charge() {
		return 0;
	}

	public int create_cargo_bay_inputs() {
		return 0;
	}

	// */

	public void create_stop() {
		create_drive_straight(0);
	}

	@NotImplemented
	public void create_drive(int speed, int radius) {

	}

	public void create_drive_straight(int speed) {
		radiansPerSecond = 0;
		forwardSpeed = speed / 10.;
	}

	@NotImplemented
	public void create_spin_CW(int speed) {
		// stub
	}

	@NotImplemented
	public void create_spin_CCW(int speed) {
		// stub
	}

	@NotImplemented
	public void create_drive_direct(int r_speed, int l_speed) {
		// stub
	}

	@NotImplemented
	public int create_spin_block(int speed, int angle) {
		return 0; // stub
	}

	// public int _create_get_raw_encoders(long* lenc, long* renc) {
	@NotImplemented
	public void create_advance_led(int on) {
		// stub
	}

	@NotImplemented
	public void create_play_led(int on) {
		// stub
	}

	@NotImplemented
	public void create_power_led(int color, int brightness) {
		// stub
	}

	@NotImplemented
	public void create_digital_output(int bits) {
		// stub
	}

	@NotImplemented
	public void create_pwm_low_side_drivers(int pwm2, int pwm1, int pwm0) {
		// stub
	}

	@NotImplemented
	public void create_low_side_drivers(int pwm2, int pwm1, int pwm0) {
		// stub
	}

	@NotImplemented
	public void create_load_song(int num) {
		// stub
	}

	@NotImplemented
	public void create_play_song(int num) {
		// stub
	}

	// public int create_read_block(char* data, int count) {
	@NotImplemented
	public void create_write_byte(char write_byte) {
		System.out.println((int)write_byte);
	}

	@NotImplemented
	public void create_clear_serial_buffer() {
		// stub
	}

	private String getBits(char value) {
		int displayMask = 1 << 7;
		StringBuffer buf = new StringBuffer(35);

		for (int c = 1; c <= 8; c++) {
			buf.append((value & displayMask) == 0 ? '0' : '1');
			value <<= 1;

			if (c % 8 == 0)
				buf.append(' ');
		}

		return buf.toString();
	}
}
