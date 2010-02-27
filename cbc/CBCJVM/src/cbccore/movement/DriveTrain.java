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
import cbccore.easing.EasingEquation;

/**
 * Allows you to move the MotorDriveTrain or Create through high-level,
 * consistant abstracted commands. Centimeters and radians are used as the
 * default units, although for convenience, degree versions of the methods are
 * provided. Counter-clockwise is the default direction of rotation. This class
 * implements most of the hard things, so subclassing this abstract should be
 * <b>very</b> easy. You really only need to fill in a few things like
 * directDrive.
 * 
 * @author Benjamin Woodruff
 * @author Grady Bryson
 * @see	MotorDriveTrain
 * @see	CreateDriveTrain
 */

public abstract class DriveTrain {
	
	public static final int DISABLED = 0;
	public static final int HIGH_PRECISION = -1;
	public static final int YIELD = -2;
	public static final int MIN_SLEEP = -3;
	public static final int MIN_SLEEP_AND_YIELD = -4;
	public static final int PERCENT_DISTANCE = 1;
	public static final int PERCENT_TIME = 2;
	public static final int CONSTANT_DISTANCE = 3;
	public static final int CONSTANT_TIME = 4;
	
	public abstract double getTrainWidth();
	
	/**
	 * Utility to make cmps match the sign of cm.
	 */
	protected static double moveParser(double cm, double cmps) { //returns new cmps
		//cmps is made to match cm's sign
		return cm<0?0.-Math.abs(cmps):Math.abs(cmps);
	}
	
	/**
	 * Rotates the device a specified number of degrees Counter-Clockwise
	 * 
	 * @param  degrees   The desired change in degrees
	 * @see              #rotateRadians
	 */
	public void rotateDegrees(double degrees, double degreesPerSecond) {
		rotateDegrees(degrees, degreesPerSecond, DISABLED, DISABLED, DISABLED, DISABLED, DISABLED, 0., null);
	}
	
	public void rotateDegrees(double degrees, double degreesPerSecond,
		int stepMode, int inEaseMode, int outEaseMode,
		int easeTimeMode, int easeTimeConflictHandler, double easeTimeParam,
		EasingEquation ease) {
		
		rotateRadians(Math.toRadians(degrees), Math.toRadians(degreesPerSecond),
			stepMode, inEaseMode, outEaseMode, easeTimeMode,
			easeTimeConflictHandler, easeTimeParam, ease
		);
	}
	
	
	/**
	 * Rotates the device a specified number of radians Counter-Clockwise
	 * 
	 * @param  radians           The desired change in radians
	 * @param  radiansPerSecond  The number of radians to rotate every second
	 * @see                      #rotateDegrees
	 */
	public void rotateRadians(double radians, double radiansPerSecond) {
		rotateRadians(radians, radiansPerSecond, DISABLED, DISABLED, DISABLED, DISABLED, DISABLED, 0., null);
	}
	
	public void rotateRadians(double radians, double radiansPerSecond,
		int stepMode, int inEaseMode, int outEaseMode,
		int easeTimeMode, int easeTimeConflictHandler, double easeTimeParam,
		EasingEquation ease) {
		
		double dist = getTrainWidth()*radians*.5;
		double speed = radiansPerSecond*getTrainWidth();
		moveWheelCm(-dist, dist, -speed, speed, stepMode, inEaseMode,
			outEaseMode, easeTimeMode, easeTimeConflictHandler, easeTimeParam,
			ease
		);
	}
	
	
	/**
	 * Moves the robot in a piece of a circle.
	 * 
	 * @param  degrees  The piece of the circle defined as a change in the
	 *                      robot's rotation in degrees
	 * @param  radius   The radius of the circle.
	 * @param  cmps     The Centimeters-Per-Second of the center of the robot.
	 *                      The maximum value for this is less than maxCmps
	 * @see             #moveCurveRadians
	 */
	public void moveCurveDegrees(double degrees, double radius, double cmps) {
		moveCurveDegrees(degrees, radius, cmps, DISABLED, DISABLED, DISABLED, DISABLED, DISABLED, 0., null);
	}
	
	public void moveCurveDegrees(double degrees, double radius, double cmps,
		int stepMode, int inEaseMode, int outEaseMode,
		int easeTimeMode, int easeTimeConflictHandler, double easeTimeParam,
		EasingEquation ease) {
		
		moveCurveRadians(Math.toRadians(degrees), radius, cmps, stepMode,
			inEaseMode, outEaseMode, easeTimeMode, easeTimeConflictHandler,
			easeTimeParam, ease
		);
	}
	
	
	/**
	 * Moves the robot in a piece of a circle.
	 * 
	 * @param  radians  The piece of the circle defined as a change in the
	 *                      robot's rotation in radians
	 * @param  radius   The radius of the circle.
	 * @param  cmps     The Centimeters-Per-Second of the center of the robot.
	 *                      The maximum value for this is less than maxCmps
	 * @see             #moveCurveDegrees
	 */
	public void moveCurveRadians(double radians, double radius, double cmps) {
		moveCurveRadians(radians, radius, cmps, DISABLED, DISABLED, DISABLED, DISABLED, DISABLED, 0., null);
	}
	
	
	
	public void moveCurveRadians(double radians, double radius, double cmps,
		int stepMode, int inEaseMode, int outEaseMode,
		int easeTimeMode, int easeTimeConflictHandler, double easeTimeParam,
		EasingEquation ease) {
		
		double halfOffset = getTrainWidth()*radians*.5;
		double cm = radians*radius;
		double leftCm = cm - halfOffset;
		double rightCm = cm + halfOffset;
		double timeOfTrip = cm/cmps;
		double leftCmps = leftCm/timeOfTrip;
		double rightCmps = rightCm/timeOfTrip;
		moveWheelCm(leftCm, rightCm, leftCmps, rightCmps, stepMode, inEaseMode,
			outEaseMode, easeTimeMode, easeTimeConflictHandler, easeTimeParam,
			ease
		);
	}
	
	
	
	/**
	 * Moves robot forward a certain number of centimeters at a set speed. If
	 * you specify a negitive number for cm, the robot will move backwards that
	 * many centimeters.
	 * 
	 * @param  cm     Desired distance in centimeters
	 * @param  cmps   Desired speed in centimeters-per-second
	 */
	public void moveCm(double cm, double cmps) {
		moveCm(cm, cmps, DISABLED, DISABLED, DISABLED, DISABLED, DISABLED, 0., null);
	}
	
	
	
	public void moveCm(double cm, double cmps,
		int stepMode, int inEaseMode, int outEaseMode,
		int easeTimeMode, int easeTimeConflictHandler, double easeTimeParam,
		EasingEquation ease) throws InvalidValueException {
		
		moveWheelCm(cm, cm, cmps, cmps, stepMode, inEaseMode, outEaseMode,
			easeTimeMode, easeTimeConflictHandler, easeTimeParam, ease
		);
	}
	
	
	//left and right cmps must be proper before calling this function
	private void moveWheelCmEasedHelper(double leftCm, double rightCm,
	                                        double startLeftCmps, double endLeftCmps,
	                                        double startRightCmps, double endRightCmps,
	                                        int easeMode, int stepMode, EasingEquation ease) throws InterruptedException {
		
		double deltaLeftCmps = endLeftCmps-startLeftCmps;
		double deltaRightCmps = endRightCmps-startRightCmps;
		
		//System.out.println("avgCmps: " + ease.easeArea(easeMode, 1., startLeftCmps, endLeftCmps-startLeftCmps, 1.));
		
		//t is time, b is beginning position, c is the total change in position, and d is the duration of the tween.
		//when time is 1 second, easeInArea will return the average velocity (d = t*avgVel)
		long travelTime = (long)(leftCm/ease.easeArea(easeMode, 1., startLeftCmps, endLeftCmps-startLeftCmps, 1.)*1.0e9);
		
		System.out.println("goalCm: " + leftCm);
		System.out.println("travelTime: " + travelTime*1.0e-9 + " seconds");
		
		long startTime = System.nanoTime();
		long destTime = travelTime+startTime;
		long currentTime = 0;
		
		double currentLeftCm = 0.;
		double speedMultiplier;
		double desiredCurrentLeftCm;
		double multipliedLeftCmps = 0.; double multipliedRightCmps = 0.;
		long prevTime = currentTime;
		long timeDiff;
		
		for(; currentTime < travelTime; currentTime = System.nanoTime()-startTime) {
			timeDiff = currentTime - prevTime;
			prevTime = currentTime;
			currentLeftCm += multipliedLeftCmps*timeDiff*1.0e-9;
			
			desiredCurrentLeftCm = ease.easeArea(easeMode, currentTime*1.0e-9, startLeftCmps, deltaLeftCmps, travelTime*1.0e-9);
			speedMultiplier = 1.+((desiredCurrentLeftCm-currentLeftCm)/leftCm);
			double leftCmps = ease.ease(easeMode, (double)currentTime, startLeftCmps, deltaLeftCmps, (double)travelTime);
			double rightCmps = ease.ease(easeMode, (double)currentTime, startRightCmps, deltaRightCmps, (double)travelTime);
			multipliedLeftCmps = leftCmps*speedMultiplier;
			//System.out.println(speedMultiplier);
			if(Math.abs(multipliedLeftCmps) > getLeftMaxCmps()) {
				speedMultiplier = Math.abs(getLeftMaxCmps()/leftCmps);
				multipliedLeftCmps = moveParser(leftCmps, getLeftMaxCmps());
			}
			multipliedRightCmps = rightCmps*speedMultiplier;
			if(Math.abs(multipliedRightCmps) > getRightMaxCmps()) {
				speedMultiplier = Math.abs(getRightMaxCmps()/rightCmps);
				multipliedRightCmps = moveParser(rightCmps, getRightMaxCmps());
				multipliedLeftCmps = leftCmps*speedMultiplier;
			}
			
			directDrive(multipliedLeftCmps, multipliedRightCmps);
			
			switch(stepMode) {
				case DISABLED:
				case HIGH_PRECISION:
					break;
				case MIN_SLEEP_AND_YIELD:
					Thread.yield();
				case MIN_SLEEP:
					Thread.sleep(0l, 1);
					break;
				case YIELD:
					Thread.yield();
					break;
				default:
					Thread.sleep(stepMode);
			}
		}
		//stop();
		System.out.println("currLeftCm: "+currentLeftCm);
		System.out.println("finished moveWheelCmEasedHelper");
	}
	
	private void moveWheelCmSimpleHelper(double leftCm, double rightCm,
	                                     double leftCmps, double rightCmps) throws InterruptedException {
		
		//do all calculations at front to prevent delays
		leftCmps = moveParser(leftCm, leftCmps);
		rightCmps = moveParser(rightCm, rightCmps);
		long travelTime = ((long)((leftCm/leftCmps)*1.0e9));
		long millis = (long)(travelTime*1e-6);
		int nanos = (int)(travelTime%1e6);
		//actually call the support functions
		directDrive(leftCmps, rightCmps);
		Thread.sleep(millis, nanos); //let our parent handle interrupted exceptions
		stop();
	}
	
	//leftCm/leftCmps must be equal to rightCm/rightCmps
	private void moveWheelCm(double leftCm, double rightCm, //positions
	                         double leftCmps, double rightCmps, //speed
	                         //finetuning, answer 0 and false for all if you don't
	                         int stepMode, int inEaseMode, int outEaseMode,
	                         int easeTimeMode, int easeTimeConflictHandler, double easeTimeParam,
	                         EasingEquation ease) {
		
		leftCmps = moveParser(leftCm, leftCmps);
		rightCmps = moveParser(rightCm, rightCmps);
		
		long part1Time = 0; long part2Time = (long)(leftCm*1.0e9/leftCmps); long part3Time = 0;
		
		int steps;
		if(inEaseMode != DISABLED || outEaseMode != DISABLED) {
			switch(easeTimeMode) {
				case PERCENT_DISTANCE:
					//when time is 1 second, easeInArea will return the average velocity (d = t*avgVel)
					steps = 0;
					if(inEaseMode != DISABLED) {
						part1Time = (long)(leftCm*easeTimeParam/ease.easeArea(inEaseMode, 1., 0., leftCmps, 1.)*1.0e9);
						++steps;
					} if(outEaseMode != DISABLED) {
						part3Time = (long)(leftCm*easeTimeParam/ease.easeArea(outEaseMode, 1., 0., leftCmps, 1.)*1.0e9);
						++steps;
					}
					part2Time = (long)((1.-steps*easeTimeParam)*leftCm/leftCmps * 1.0e9);
					break;
				case PERCENT_TIME:
					double part1Cmps = 0.;
					double part2Cmps = leftCmps;
					double part3Cmps = 0.;
					if(inEaseMode != DISABLED) {
						ease.easeArea(inEaseMode, 1., 0., leftCmps, 1.);
					}
					if(outEaseMode != DISABLED) {
						ease.easeArea(outEaseMode, 1., 0., leftCmps, 1.);
					}
					double part1Percent, part2Percent, part3Percent;
					double sum = part1Cmps+part2Cmps+part3Cmps;
					
					part1Percent = part1Cmps/sum;
					part2Percent = part2Cmps/sum;
					part3Percent = part3Cmps/sum;
					
					double avgCmps = part1Cmps*part1Percent + part2Cmps*part2Percent + part3Cmps*part3Percent;
					double totalSeconds = leftCm/avgCmps;
					part1Time = (long)(part1Percent*totalSeconds*1.0e9);
					part3Time = (long)(part3Percent*totalSeconds*1.0e9);
					part2Time = (long)((totalSeconds-part1Time-part3Time)*1.0e9); //faster
					break;
				case CONSTANT_DISTANCE:
					steps = 0;
					if(inEaseMode != DISABLED) {
						part1Time = (long)(easeTimeParam/ease.easeArea(inEaseMode, 1., 0., leftCmps, 1.)*1.0e9);
						++steps;
					} if(outEaseMode != DISABLED) {
						part3Time = (long)(easeTimeParam/ease.easeArea(outEaseMode, 1., 0., leftCmps, 1.)*1.0e9);
						++steps;
					}
					part2Time = (long)((leftCm-steps*easeTimeParam)/leftCmps * 1.0e9);
					break;
				case CONSTANT_TIME:
					if(inEaseMode != DISABLED) {
						part1Time = (long)(easeTimeParam*1.0e9);
					} if(outEaseMode != DISABLED) {
						part3Time = (long)(easeTimeParam*1.0e9);
					}
					part2Time = (long)(1.0e9*(leftCm -
						ease.easeArea(inEaseMode, ((double)(part1Time))*1.0e-9, 0., leftCmps, ((double)(part1Time))*1.0e-9) -
						ease.easeArea(outEaseMode, ((double)(part2Time))*1.0e-9, 0., leftCmps, ((double)(part2Time))*1.0e-9)
					)/leftCmps);
					
			}
		}
		
		if(inEaseMode != DISABLED) {
			double partLeftCm = ease.easeArea(inEaseMode, part1Time*1.0e-9, 0., leftCmps, part1Time*1.0e-9);
			double partRightCm = ease.easeArea(inEaseMode, part1Time*1.0e-9, 0., rightCmps, part1Time*1.0e-9);
			try {
				moveWheelCmEasedHelper(partLeftCm, partRightCm,
					0, leftCmps,
					0, rightCmps,
					inEaseMode, stepMode, ease
				);
			} catch (InterruptedException ex) {
				stop(); return;
			}
		}
		
		try {
			moveWheelCmSimpleHelper(part2Time*leftCmps*1.0e-9, 
				part2Time*rightCmps*1.0e-9, leftCmps, rightCmps
			);
		} catch (InterruptedException ex) {
			stop(); return;
		}
		
		if(outEaseMode != DISABLED) {
			double partLeftCm = ease.easeArea(outEaseMode, part3Time*1.0e-9, 0., leftCmps, part3Time*1.0e-9);
			double partRightCm = ease.easeArea(outEaseMode, part3Time*1.0e-9, 0., rightCmps, part3Time*1.0e-9);
			try {
				moveWheelCmEasedHelper(partLeftCm, partRightCm,
					leftCmps, 0,
					rightCmps, 0,
					outEaseMode, stepMode, ease
				);
			} catch (InterruptedException ex) {
				stop(); return;
			}
		}
		
		stop();
	}
	
	
	
	/**
	 * It's always handy to have a method like this. Just moves the robot at a
	 * set speed, so say you wanted to back into some PVC to line yourself up,
	 * this could help.
	 * 
	 * @param  cmps   The speed for the robot to move in centimeters-per-second
	 * @see           #directDrive
	 */
	public void moveAtCmps(double cmps) {
		directDrive(cmps, cmps);
	}
	
	
	
	/**
	 * Directly move the robot at said speed, called by DriveTrain. You _must_
	 * fill this in. Otherwise the robot will not be able to move.
	 * 
	 * @param  leftCmps  The desired speed of the left wheel in
	 *                       centimeters-per-second
	 */
	protected abstract void directDrive(double leftCmps, double rightCmps);
	
	
	
	/**
	 * Just mirrors (calls) <code>freeze()</code>
	 * 
	 * @see	#stop
	 * @see	#kill	
	 */
	public void stop() {
		freeze();
	}
	
	/**
	 * Should lock both the robot's motors if possible, otherwise it should call
	 * <code>kill()</code>
	 * 
	 * @see	#stop
	 * @see	#kill
	 */
	public abstract void freeze();
	
	
	/**
	 * Stops the robot by setting the speed to zero. If a different mechanism is
	 * needed for stopping, you can override this method.
	 * 
	 * @see	#stop
	 * @see	#freeze
	 */
	public void kill() {
		directDrive(0, 0);
	}
	
	/**
	 * Should get the maximum speed in centimeters-per-second for the left wheel
	 * of the robot.
	 * 
	 * @return    The speed in cmps
	 * @see       #getRightMaxCmps
	 */
	protected abstract double getLeftMaxCmps();
	
	/**
	 * System is not yet implemented yet. However, to future-proof your plugins,
	 * you should override this.
	 */
	public double getMinLeftCmps() {
		return 0.;
	}
	
	/**
	 * System is not yet implemented yet. However, to future-proof your plugins,
	 * you should override this.
	 */
	public double getMinRightCmps() {
		return 0.;
	}
	
	/**
	 * Should get the maximum speed in centimeters-per-second for the right
	 * wheel of the robot.
	 * 
	 * @return       The speed in cmps
	 * @see          #getLeftMaxCmps
	 */
	protected abstract double getRightMaxCmps();
	
	
	/**
	 * Gets the maximum speed in centimeters-per-second for the robot.
	 * 
	 * @return      The maximum speed in centimeters-per-second for the robot.
	 * @see         #getMaxRadiansPerSec
	 * @see         #getLeftMaxCmps
	 * @see         #getRightMaxCmps
	 */
	public double getMaxCmps() {
		return Math.min(getLeftMaxCmps(), getRightMaxCmps());
	}
	
	
	/**
	 * Gets the maximum speed that the robot could turn in place
	 * 
	 * @return    The maximum speed in radians-per-second
	 * @see       #getMaxDegreesPerSec
	 * @see       #getMaxCmps
	 * @see       #getLeftMaxCmps
	 * @see       #getRightMaxCmps
	 */
	public double getMaxRadiansPerSec() {
		return Math.min(getLeftMaxCmps(), getRightMaxCmps())/getTrainWidth();
	}
	
	
	/**
	 * Gets the maximum speed that the robot could turn in place
	 * 
	 * @return     The maximum speed in degrees-per-second
	 * @see        #getMaxRadiansPerSec
	 * @see        #getMaxCmps
	 * @see        #getLeftMaxCmps
	 * @see        #getRightMaxCmps
	 */
	public double getMaxDegreesPerSec() {
		return Math.toDegrees(getMaxRadiansPerSec());
	}
	
	
	/**
	 * Gets the maximum speed when moving in a curve
	 * DOES NOT YET WORK
	 * 
	 * @param   radius  paraminfo
	 * @return  The maximum speed of the center of the robot in
	 *              centimeters-per-second
	 * @see      #moveCurveRadians
	 * @see      #moveCurveDegrees
	 */
	public double getMaxCmps(double radius) {
		double outerMaxSpeed = (radius>0 ? getRightMaxCmps() : getLeftMaxCmps()) - (radius+getTrainWidth()*.5)/radius;
		double innerMaxSpeed = (radius>0 ? getLeftMaxCmps() : getRightMaxCmps()) + (radius+getTrainWidth()*.5)/radius;
		return Math.min(outerMaxSpeed, innerMaxSpeed);
	}
}
