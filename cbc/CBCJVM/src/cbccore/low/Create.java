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
 * Documentation stolen from the KISS-C documentation and 
 * <a href="http://github.com/kipr/cbc/blob/master/userlib/libcbc/src/create.c"> the cbc source code </a>
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
	
	
	
	/**
	 * Don't use. Updates an inaccessable variable.
	 */
	public native int create_sensor_update();
	
	
	
	/**
	 * Don't use. Updates an inaccessable variable.
	 */
	public native int create_wall();
	
	
	
	/**
	 * Don't use. Updates an inaccessable variable.
	 */
	public native int create_buttons();
	
	
	
	/**
	 * Don't use. Updates an inaccessable variable.
	 */
	public native int create_bumpdrop();
	
	
	
	/**
	 * Don't use. Updates an inaccessable variable.
	 */
	public native int create_cliffs();
	
	
	
	/**
	 * Don't use. Updates an inaccessable variable.
	 */
	public native int create_angle();
	
	
	
	/**
	 * Don't use. Updates an inaccessable variable.
	 */
	public native int create_distance();
	
	
	
	/**
	 * Don't use. Updates an inaccessable variable.
	 */
	public native int create_velocity();
	
	
	
	/**
	 * Don't use. Updates an inaccessable variable.
	 */
	public native int create_read_IR();
	
	
	
	/**
	 * Don't use. Updates an inaccessable variable.
	 */
	public native int create_overcurrents();
	
	
	
	/**
	 * Don't use. Updates an inaccessable variable.
	 */
	public native int create_battery_charge();
	
	
	
	/**
	 * Don't use. Updates an inaccessable variable.
	 */
	public native int create_cargo_bay_inputs();
	
	
	
	/**
	 * Stops the drive wheels
	 */
	public native void create_stop();
	
	
	
	/**
	 * Drives in an arc.
	 * 
	 * @param  speed   range is 20-500mm/s
	 * @param  radius  radius in mm/s.<p>A radius of 32767 will drive the robot straight.
	 *                    <p>A radius of 1 will spin the robot CCW
	 *                    <p>A radius of -1 will spin the robot CW
	 *                    <p>Negative radii will be right turns, positive radii left turns
	 * @see            #create_drive_straight
	 */
	public native void create_drive (int speed, int radius);
	
	
	
	/**
	 * Drives straight at speed in mm/s
	 * 
	 * @param  speed  20-500mm/s
	 */
	public native void create_drive_straight (int speed);
	
	
	
	/**
	 * Spins CW with edge speed of speed in mm/s
	 * 
	 * @param  speed  20-500mm/s. Speed of edge (wheels) of bot.
	 * @see           #create_spin_CCW
	 */
	public native void create_spin_CW (int speed);
	
	
	
	/**
	 * Spins CCW with edge speed of speed in mm/s
	 * 
	 * @param  speed  20-500mm/s. Speed of edge (wheels) of bot.
	 * @see           #create_spin_CW
	 */
	public native void create_spin_CCW (int speed);
	
	
	
	/**
	 * Specifies individual left and right speeds in mm/s
	 * 
	 * @param  r_speed  20-500mm/s. Speed of right wheel.
	 * @param  l_speed  20-500mm/s. Speed of left wheel.
	 * @see            #create_drive
	 */
	public native void create_drive_direct(int r_speed, int l_speed);
	
	
	
	/**
	 * This function blocks and does a pretty accurate spin. Note that the
	 * function will not return until the spin is complete
	 * CAUTION: requesting the robot to spin more than about 3600 degrees may
	 *                                                           never terminate
	 * 
	 * @param  speed  20-500mm/s. Speed of edge (wheels) of bot.
	 * @param  angle  Angle in degrees to turn before returning. <p>
	 *                   <b>CAUTION: requesting the robot to spin more than
	 *                                about 3600 degrees may never terminate</b>
	 * @return        -1 if error
	 * @see           #create_spin_CW
	 * @see           #create_spin_CCW
	 */
	public native int create_spin_block(int speed, int angle);
	
	
	
	// public native int _create_get_raw_encoders(long* lenc, long* renc);
	
	
	/**
	 * Turn on/off the advance LED
	 * 
	 * @param  on   1 to turn on light and 0 to turn it off
	 * @see         #create_play_led
	 * @see         #create_power_led
	 */
	public native void create_advance_led(int on);
	
	
	
	/**
	 * Turn on/off the play LED
	 * 
	 * @param  on   1 to turn on light and 0 to turn it off
	 * @see         #create_advance_led
	 * @see         #create_power_led
	 */
	public native void create_play_led(int on);
	
	
	
	/**
	 * Control the color and the brightness of the power LED
	 * 
	 * @param  color       0 is red and 255 green
	 * @param  brightness  0 is off and 255 is full brightness
	 * @see                #create_advance_led
	 * @see                #create_play_led
	 */
	public native void create_power_led(int color, int brightness);
	
	
	
	/**
	 * This function sets the three digital out put pins 20,7,19 where 20 is the
	 * high bit and 19 is the low. You probably don't care about this function.
	 * 
	 * @param  bits  Should have a value 0 to 7.
	 */
	public native void create_digital_output(int bits);
	
	
	
	/**
	 * Sets the PWM signal for the three low side drivers (128 = 100%). You
	 * probably don't care about this function.
	 * 
	 * @param  pwm0   pin 22
	 * @param  pwm1   pin 23
	 * @param  pwm2   pin 24
	 */
	public native void create_pwm_low_side_drivers(int pwm2, int pwm1, int pwm0);
	
	
	
	/**
	 * Turns on and off the signal for the three low side drivers (128 = 100%).
	 * A 0 or 1 should be given for each of the drivers to turn them off or on.
	 * You probably don't care about this function.
	 * 
	 * @param  pwm0   pin 22
	 * @param  pwm1   pin 23
	 * @param  pwm2   pin 24
	 */
	public native void create_low_side_drivers(int pwm2, int pwm1, int pwm0);
	
	
	
	/**
	 * This loads a song into the robot's memory. Song can be numbered 0 to 15.
	 * The first element in each row of the array should be the number of notes
	 * (1-16) the subsequent pairs of bytes should be tone and duration see the
	 * roomba SCI manual for note codes. User's program should load the song
	 * data into the array before calling this routine. Sets gc_song_array, an
	 * inaccessable variable. <b>DO NOT USE THIS FUNCTION UNTIL THE ISSUE IS
	 * RESOLVED</b>
	 * 
	 * @param  num   Song can be numbered 0 to 15
	 * @see          #create_play_song
	 */
	public native void create_load_song(int num);
	
	
	
	
	/**
	 * See the roomba SCI manual for note codes. Uses gc_song_array, an
	 * inaccessable variable. <b>DO NOT USE THIS FUNCTION UNTIL THE ISSUE IS
	 * RESOLVED</b>
	 * 
	 * @param  num   Song can be numbered 0 to 15
	 * @see          #create_load_song
	 */
	public native void create_play_song(int num);
	
	
	
	// public native int create_read_block(char* data, int count);
	
	
	
	/**
	 * See Create IO Documentation. You probably don't care about this function.
	 * 
	 * @param  write_byte  the byte to write
	 * @see                #create_clear_serial_buffer
	 */
	public native void create_write_byte(char write_byte);
	
	
	
	
	public native void create_clear_serial_buffer();
}
