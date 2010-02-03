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

package cbccore.movement;

import cbccore.motors.Motor;
import cbccore.InvalidValueException;

/**
 * A wheel class used by MotorDriveTrain
 * 
 * @author PiPeep
 */

public class Wheel extends Motor {
	
	protected double efficiency;
	private double maxRps;
	private double maxCmps;
	protected double circumference;
	private int currentTps;
	
	public Wheel(int port, double circumference, double efficiency) {
		super(port);
		this.circumference = circumference;
		this.efficiency = efficiency;
		this.maxRps = 1000./MotorDriveTrain.ticksPerRotation*efficiency;
		this.maxCmps = maxRps*circumference();
	}
	
	public double maxRps() {
		return maxRps;
	}
	
	public double maxCmps() {
		return maxCmps;
	}
	
	public int currentTps() {
	    return currentTps;
	}
	
	public double currentRps() {
		return currentTps/MotorDriveTrain.ticksPerRotation;
	}
	
	public double circumference() {
		return circumference;
	}
	
	protected void checkTpsRange(int tps) throws InvalidValueException {
		System.out.println(""+tps);
		if(Math.abs(tps) > (maxRps*MotorDriveTrain.ticksPerRotation)) {
			System.out.println("" + tps + ", " + maxRps*MotorDriveTrain.ticksPerRotation);
			throw new InvalidValueException();
		}
	}
	
	public void moveAtTps(int tps) throws InvalidValueException {
		checkTpsRange(tps);
		currentTps = tps;
		super.moveAtVelocity((int)(tps/efficiency));
	}
	
	public void moveAtRps(double rps) throws InvalidValueException {
		moveAtTps((int)(rps*MotorDriveTrain.ticksPerRotation));
	}
	
	public void moveAtCmps(double cmps) throws InvalidValueException {
		moveAtRps(cmps/circumference());
	}
	
	//calling this does not guarentee anything, but will attempt to move within a certain accuracy
	//uses recursive algorithm for fine tuning
	/*public void moveCm(double cm, double cmps, boolean fineTune) throws InvalidValueException {
		System.out.println("moveCm has been called - port #"+_port);
		//cmps is made to match cm's sign
		cmps = cm<0?-Math.abs(cmps):Math.abs(cmps);
		
		//we have an overhead, so in a case like this, it is better not to move at all
		if(Math.abs(cm) < .5) return;
		
		double destCmCounter = 0.;
		if(fineTune) {
			destCmCounter = getCmCounter()+cm;
		}
		moveAtCmps(cmps);
		long destTime = System.currentTimeMillis()+((long)((cm/cmps)*1000.));
		long sleepOverhead = 0;
		while(System.currentTimeMillis() < (destTime-sleepOverhead)) {
			//Thread.yield();
			long sleepTime = (destTime - System.currentTimeMillis() - sleepOverhead)/2;
			long sleepStart = System.currentTimeMillis();
			try {
				Thread.sleep(sleepTime);
			} catch(InterruptedException e) {
				moveAtTps(0);
				return;
			}
			sleepOverhead = System.currentTimeMillis() - sleepStart - sleepTime;
		}
		//while(System.currentTimeMillis() < destTime) {} //This code is questionable, may cause adverse multithreading effects
		
		//refine, and fineTune if necicary. Fine tuning moves slower.
		if(fineTune) { moveCm(destCmCounter-getCmCounter(), (Math.abs(cmps)/2)<1.5?1.5:(cmps/2) , fineTune); }
		moveAtTps(0);
	}*/
	
	public int getTickCounter() {
		return getPositionCounter();
	}
	
	public double getWheelRotationCounter() {
		return ((double)getTickCounter())/MotorDriveTrain.ticksPerRotation;
	}
	
	public double getCmCounter() {
		return getWheelRotationCounter()*circumference();
	}
	
	public int moveAtVelocity(int tps) {
		moveAtTps(tps);
		return 0;
	}
}
