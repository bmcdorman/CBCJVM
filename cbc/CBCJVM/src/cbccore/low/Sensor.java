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

public class Sensor {
	public native int digital(int port); /* returns a 1 or 0 reflecting the state of port (0 to 7) */     
	public native int set_digital_output_value(int port, int value); /*sets port (0 to 7)to value (0 or 1) */
	public native void set_analog_floats(int mask);
	public native void set_each_analog_state(int a0, int a1, int a2, int a3, int a4, int a5, int a6, int a7);
	public native int analog10(int port); /* returns 10-bit value from analog port (ports 8 to 15) */        
	public native int analog(int port); /* returns 8-bit value from analog port (ports 8 to 15) */           
	public native int accel_x(); /* returns x acceleration (-2047 to 2047, +/- 1.5 gee) */                  
	public native int accel_y(); /* returns y acceleration (-2047 to 2047, +/- 1.5 gee) */                  
	public native int accel_z(); /* returns z acceleration (-2047 to 2047, +/- 1.5 gee) */                  
	public native int sonar(int port); /* returns range in mm for sonar plugged into port (13-15)*/          
	public native int sonar_inches(int port); /* returns range in whole inches for sonar plugged into port (13-15)*/
}
