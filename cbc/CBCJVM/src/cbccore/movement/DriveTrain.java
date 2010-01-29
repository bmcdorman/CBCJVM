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

import cbccore.InvalidValueException;

/**
 * 
 * @author Benjamin Woodruff
 *
 */

public abstract class DriveTrain {
	
	public abstract void moveAtCmps(double cmps) throws InvalidValueException;
	
	public abstract double getTrainWidth();
	
	protected double moveParser(double cm, double cmps) { //returns new cmps
		//cmps is made to match cm's sign
		return cm<0?-Math.abs(cmps):Math.abs(cmps);
	}
	
	//Clockwise
	public void rotateDegrees(double degrees, double degreesPerSecond) {
		rotateRadians(Math.toRadians(degrees), Math.toRadians(degreesPerSecond));
	}
	
	//Clockwise
	public void rotateRadians(double radians, double radiansPerSecond) {
		double dist = (getTrainWidth()*Math.PI)*(radians/(2*Math.PI));//, simplified
		double speed = radiansPerSecond/radians*dist;//, simplified
		moveWheelCm(dist, -dist, speed, -speed);
	}
	
	//TODO: we need a function to move in a curve (w00t!)
	
	public void moveCurveRadians(double radians, double radius, double cmps) {
		double leftCm = radians/(2*Math.PI) * radius*Math.PI + getTrainWidth()*2*radians/(2*Math.PI);
		double rightCm = radians/(2*Math.PI) * radius*Math.PI - getTrainWidth()*2*radians/(2*Math.PI);
		double leftCmps = cmps + radians/(2*Math.PI) * getTrainWidth()*2.;
		double rightCmps = cmps - radians/(2*Math.PI) * getTrainWidth()*2.;
		moveWheelCm(leftCm, rightCm, leftCmps, rightCmps);
	}
	
	//remember, interruption is non-instantanious
	public void moveCm(double cm, double cmps) throws InvalidValueException {
		moveWheelCm(cm, cm, cmps, cmps);
	}
	
	//leftCm/leftCmps must be equal to rightCm/rightCmps
	protected void moveWheelCm(double leftCm, double rightCm, double leftCmps, double rightCmps) {
		//System.out.println("moving left: "+ leftCm + " and right " + rightCm + " for " + leftCm/leftCmps + "seconds");
		
		leftCmps = leftCm<0?-Math.abs(leftCmps):Math.abs(leftCmps);
		rightCmps = rightCm<0?-Math.abs(rightCmps):Math.abs(rightCmps);
		
		//we have an overhead, so in a case like this, it is better not to move at all
		if(Math.abs(leftCm) < .5) leftCm = 0.;
		if(Math.abs(rightCm) < .5) { if(leftCm == 0) { return; } rightCm = 0.; }
		
		//double destCmCounter = 0.;
		moveLeftCmps(leftCmps);
		moveRightCmps(rightCmps);
		long destTime = System.currentTimeMillis()+((long)((leftCm/leftCmps)*1000.));
		long sleepOverhead = 0l;
		while(System.currentTimeMillis() < (destTime-sleepOverhead)) {
			//Thread.yield();
			long sleepTime = (destTime - System.currentTimeMillis() - sleepOverhead)>>1;
			long sleepStart = System.currentTimeMillis();
			try {
				Thread.sleep(sleepTime);
			} catch(InterruptedException e) {
				stop();
				return;
			}
			sleepOverhead = System.currentTimeMillis() - sleepStart - sleepTime;
		}
		while(System.currentTimeMillis() < destTime) {} //This code is questionable, may cause adverse multithreading effects
		
		stop();
	}
	
	protected abstract void moveLeftCmps(double cmps);
	protected abstract void moveRightCmps(double cmps);
	public abstract void stop();
	public abstract void freeze();
	public abstract void kill();
	public abstract double maxCmps();
}
