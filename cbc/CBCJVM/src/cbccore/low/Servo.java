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

public class Servo {
	public native void enable_servos(); /* powers up the servos */
	public native void disable_servos(); /* powers down the servos */
	public native int set_servo_position(int servo, int pos); /* sets servo (1 to 4) to pos (0 to 2047) */
	public native int get_servo_position(int servo); /* returns int of last setting for servo (1 to 4) */
}
