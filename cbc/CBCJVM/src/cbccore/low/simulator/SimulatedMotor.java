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
import cbccore.low.Motor;
import cbccore.NotImplemented;

/**
 * 
 * @author Braden McDorman, Benjamin Woodruff
 *
 */

public class SimulatedMotor extends Motor {
	
	protected CBCSimulator cbc;
	protected SimulatedCBOB cbob;
	
	public SimulatedMotor(CBCSimulator c) {
		cbc = c;
		cbob = c.cbob;
	}
	
	public void motor (int motor, int percent) {/* motor (0 to 3) at percent % of full (-100 to 100)*/
		cbob.setMotorSpeed(motor, new MotorSpeed(percent, false));
	}
	
	@NotImplemented public int clear_motor_position_counter(int motor) { /* sets motor (0 to 3) counter to 0 */
		return 0; //stub
	}
	
	public int move_at_velocity(int motor, int velocity) { /* PID control of motor (0 to 3) at velocity tick per second */
		cbob.setMotorSpeed(motor, new MotorSpeed(velocity, true));
		return 0;
	}
	
	public int mav(int motor, int velocity) { /* PID control of motor (0 to 3) at velocity tick per second */
		return move_at_velocity(motor, velocity);
	}
	
	@NotImplemented public int move_to_position(int motor, int speed, int goal_pos) {/* move motor (0 to 3) at speed to goal_pos */
		return 0; //stub - Threading?
	}
	
	@NotImplemented public int mtp(int motor, int speed, int goal_pos) {/* move motor (0 to 3) at speed to goal_pos */
		return move_to_position(motor, speed, goal_pos);
	}
	
	@NotImplemented public int move_relative_position(int motor, int speed, int delta_pos) {/* move motor (0 to 3) at speed by delta_pos */
		return move_to_position(motor, speed, delta_pos+get_motor_position_counter(motor));
	}
	
	@NotImplemented public int mrp(int motor, int speed, int delta_pos) {/* move motor (0 to 3) at speed by delta_pos */
		return move_relative_position(motor, speed, delta_pos);
	}
	
	@NotImplemented public void set_pid_gains(int motor, int p, int i, int d, int pd, int id, int dd) {/* set PID gains */
		//stub
	}
	
	public int freeze(int motor) {/* keep motor (0 to 3) at current position */
		cbob.setMotorSpeed(motor, new MotorSpeed(0, true));
		return 0;
	}
	
	@NotImplemented public int get_motor_done(int motor) { /* returns 1 if motor (0 to 3) is moving to a goal and 0 otherwise */
		return 0;
	}
	
	@NotImplemented public int get_motor_position_counter(int motor) { /* returns int of motor (0 to 3) position +/-2147483647 */
		return 0;
	}
	
	@NotImplemented public void block_motor_done(int motor) { /* returns when motor (0 to 3) has reached goal */
		//stub- need mav first
	}
	
	public void bmd(int motor) { /* returns when motor (0 to 3) has reached goal */
		block_motor_done(motor);
	}
	
	public int setpwm(int motor, int pwm) { /* turns on motor (0 to 3) at pwm (-255 to 255)*/
		cbob.setMotorSpeed(motor, new MotorSpeed(pwm*100/255, false)); //it appears as if the manual and this info above don't match, just guessing what it does
		return 0;
	}
	
	public int getpwm(int motor) {/* returns the current pwm setting for that motor (-255 to 255)*/
		return cbob.getMotorSpeed(motor).bemf ? cbob.getMotorSpeed(motor).speed*255/1000 : cbob.getMotorSpeed(motor).speed*255/100; //not even documented, just guessing at what it does
	}
	
	public void fd(int motor) { /* motor (0 to 3) at full forward */
		cbob.setMotorSpeed(motor, new MotorSpeed(100, false));
	}
	
	public void bk(int motor) { /* motor (0 to 3) at full reverse */
		cbob.setMotorSpeed(motor, new MotorSpeed(-100, false));
	}
	
	public void off(int motor) { /* turns motor (0 to 3) off */
		cbob.setMotorSpeed(motor, new MotorSpeed(0, false));
	}
	
	@NotImplemented public void ao() { /* turns all motors off */
		
	}
}
