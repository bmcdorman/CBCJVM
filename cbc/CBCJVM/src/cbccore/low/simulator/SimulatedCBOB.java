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

/**
 * Simulates the actions of the CBOB, called on by the simulated CBC for stuff
 * like motor info. Helps to divide up the tasks, might even be able to speed
 * things up with multithreading at some point.
 * 
 * @author Benjamin Woodruff
 */

public class SimulatedCBOB {
	
	private MotorSpeed[] motorSpeeds = new MotorSpeed[4];
	private int[] motorPositions = new int[4]; //straight zeros are fine
	private long[] startTimes = new long[4]; //straight zeros are fine
	
	public SimulatedCBOB(CBCSimulator parent) {
		for(int i = 0; i < motorSpeeds.length; ++i) {
			motorSpeeds[i] = new MotorSpeed(0, false);
			motorPositions[i] = 0;
			startTimes[i] = 0l;
		}
	}
	
	public MotorSpeed getMotorSpeed(int port) {
		//System.out.println("getMotorSpeed");
		return motorSpeeds[port];
	}
	
	public void setMotorSpeed(int port, MotorSpeed speed) {
		motorPositions[port] = getMotorPosition(port);
		startTimes[port] = System.currentTimeMillis();
		motorSpeeds[port] = speed;
		//System.out.println("setMotorSpeed");
	}
	
	public int getMotorPosition(int port) {
		//System.out.println("getMotorPosition");
		return motorPositions[port] + motorSpeeds[port].getTpsSpeed()*((int)(System.currentTimeMillis()-startTimes[port]))/1000;
	}
	
	public boolean inMotion() {
		for(MotorSpeed ms : motorSpeeds) {
			if(ms.speed != 0) {
				return true;
			}
		} return false;
	}
}
