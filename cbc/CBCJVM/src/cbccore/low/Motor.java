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
 * 
 * @author Braden McDorman
 *
 */

public class Motor {
	
	
	/**
	 * Set motor speed. It's recommended you use a Back-EMF based function instead.
	 * 
	 * @param  motor    Motor number to change percent of (0-3)
	 * @param  percent  percent % of full (-100 to 100)
	 * 
	 * @see #fd
	 * @see #bk
	 * @see #mav
	 */
	public native void motor (int motor, int percent);
	public native int clear_motor_position_counter(int motor); /* sets motor (0 to 3) counter to 0 */
	public native int move_at_velocity(int motor, int velocity); /* PID control of motor (0 to 3) at velocity tick per second */
	public native int mav(int motor, int velocity); /* PID control of motor (0 to 3) at velocity tick per second */
	public native int move_to_position(int motor, int speed, int goal_pos);/* move motor (0 to 3) at speed to goal_pos */
	public native int mtp(int motor, int speed, int goal_pos);/* move motor (0 to 3) at speed to goal_pos */
	public native int move_relative_position(int motor, int speed, int delta_pos);/* move motor (0 to 3) at speed by delta_pos */
	public native int mrp(int motor, int speed, int delta_pos);/* move motor (0 to 3) at speed by delta_pos */
	public native void set_pid_gains(int motor, int p, int i, int d, int pd, int id, int dd);/* set PID gains */
	public native int freeze(int motor);/* keep motor (0 to 3) at current position */
	public native int get_motor_done(int motor); /* returns 1 if motor (0 to 3) is moving to a goal and 0 otherwise */
	public native int get_motor_position_counter(int motor); /* returns int of motor (0 to 3) position +/-2147483647 */
	public native void block_motor_done(int motor); /* returns when motor (0 to 3) has reached goal */
	public native void bmd(int motor); /* returns when motor (0 to 3) has reached goal */
	public native int setpwm(int motor, int pwm); /* turns on motor (0 to 3) at pwm (-255 to 255)*/
	public native int getpwm(int motor);/* returns the current pwm setting for that motor (-255 to 255)*/
	public native void fd(int motor); /* motor (0 to 3) at full forward */
	public native void bk(int motor); /* motor (0 to 3) at full reverse */
	public native void off(int motor); /* turns motor (0 to 3) off */
	public native void ao(); /* turns all motors off */
}
