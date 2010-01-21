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

package cbccore.low;

/**
 * Direct access to the CBC-C Create Libraries<p>
 * Documentation stolen from the KISS-C documentation
 * 
 * @author  Braden McDorman
 * @see     cbccore.movement.CreateDriveTrain
 */

public class Create {
	
	/**
	 * First step for connecting CBC to Create. This function puts the Create in the create_safe mode.
	 * 
	 * @return        0 if sucessful and a negative number if not
	 * @see           #create_disconnect
	 */
	public native int create_connect();
	
	
	
	/**
	 * Returns Create to proper state. Call this at the end of your program.
	 * 
	 * @see    #create_connect
	 */
	public native void create_disconnect();
	
	
	
	/**
	 * Puts Create into passive mode (no motors)
	 * 
	 * @see    #create_passive
	 * @see    #create_safe
	 * @see    #create_full
	 * @see    #create_mode
	 */
	public native void create_start();
	
	
	
	/**
	 * Puts Create into passive mode (no motors)
	 * 
	 * @see    #create_start
	 * @see    #create_safe
	 * @see    #create_full
	 * @see    #create_mode
	 */
	public native void create_passive();
	
	
	
	/**
	 * Puts Create into safe mode. Create will execute all commands, but will
	 * disconnect and stop if drop or cliff sensors fire. This is recommended
	 * for practice, but not at a tournament.
	 * 
	 * @see    #create_passive
	 * @see    #create_full
	 * @see    #create_mode
	 */
	public native void create_safe();
	
	
	
	/**
	 * Puts Create into full mode. Create will move however you tell it -- even
	 * if that is a bad thing. In particular, the Create will not stop and
	 * disconnect, even if it is picked up or the cliff sensors fire. This is
	 * recommended for tournaments, but not for practice, due to its dangerous
	 * nature.
	 * 
	 * @see    #create_passive
	 * @see    #create_safe
	 * @see    #create_mode
	 */
	public native void create_full();
	
	
	
	/**
	 * Simulates a Roomba doing a spot clean
	 * 
	 * @see    #create_cover
	 * @see    #create_demo
	 * @see    #create_cover_dock
	 */
	public native void create_spot();
	
	
	
	/**
	 * Simulates a Roomba covering a room
	 * 
	 * @see    #create_spot
	 * @see    #create_demo
	 * @see    #create_cover_dock
	 */
	public native void create_cover();
	
	
	
	/**
	 * Runs built in demos (see Create IO documentation)
	 * 
	 * @param  d  See Create IO documentation. I would normally look this up, but it seems so pointless...
	 * @see       #create_spot
	 * @see       #create_cover
	 * @see       #create_cover_dock
	 */
	public native void create_demo(int d) ;
	
	
	
	/**
	 * Create roams around until it sees an IR dock and then attempts to dock
	 * 
	 * @see    #create_spot
	 * @see    #create_cover
	 * @see    #create_demo
	 */
	public native void create_cover_dock();
	
	
	
	/**
	 * the Create's mode
	 * 
	 * @return   0 off; 1 passive; 2 safe; 3 full
	 * @see      #create_passive
	 * @see      #create_safe
	 * @see      #create_full
	 */
	public native int create_mode();
	public native int create_sensor_update();
	public native int create_wall();
	public native int create_buttons() ;
	public native int create_bumpdrop();
	public native int create_cliffs() ;
	public native int create_angle() ;
	public native int create_distance();
	public native int create_velocity();
	public native int create_read_IR();
	public native int create_overcurrents();
	public native int create_battery_charge();
	public native int create_cargo_bay_inputs();
	public native void create_stop();
	public native void create_drive (int speed, int radius);
	public native void create_drive_straight (int speed);
	public native void create_spin_CW (int speed);
	public native void create_spin_CCW (int speed);
	public native void create_drive_direct(int r_speed, int l_speed);
	public native int create_spin_block(int speed, int angle);
	// public native int _create_get_raw_encoders(long* lenc, long* renc);
	public native void create_advance_led(int on) ;
	public native void create_play_led(int on) ;
	public native void create_power_led(int color, int brightness) ;
	public native void create_digital_output(int bits);
	public native void create_pwm_low_side_drivers(int pwm2, int pwm1, int pwm0);
	public native void create_low_side_drivers(int pwm2, int pwm1, int pwm0);
	public native void create_load_song(int num);
	public native void create_play_song(int num);
	// public native int create_read_block(char* data, int count);
	public native void create_write_byte(char write_byte);
	public native void create_clear_serial_buffer();
}
