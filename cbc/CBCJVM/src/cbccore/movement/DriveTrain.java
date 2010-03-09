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
import cbccore.movement.plugins.MovementPlugin;

/**
 * A pluggable movement library. Fill in a few low-level fields by extending
 * the abstract MovementPlugin class, and this class will do the rest. You can
 * extend this class to more advanced things, and thanks to the pluggable
 * architecture, people will be able to use your version with their own plugins.
 * 
 * @author Benjamin Woodruff
 * @author Grady Bryson
 * @see	cbccore.movement.plugins
 * @see	cbccore.movement.plugins.MovementPlugin
 */

public class DriveTrain {
	
	public static final int DISABLED = 0;
	public static final int PERCENT_DISTANCE = 1;
	public static final int PERCENT_TIME = 2;
	public static final int CONSTANT_DISTANCE = 3;
	public static final int CONSTANT_TIME = 4;
	
	private MovementPlugin plugin;
	private double oldAngle;
	private double oldX;
	private double oldY;
	private double leftCmps;
	private double rightCmps;
	private long oldTime;
	
	public DriveTrain(MovementPlugin plugin) {
		this.plugin = plugin;
	}
	
	
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
		
		double dist = plugin.getTrainWidth()*radians*.5;
		double speed = radiansPerSecond*plugin.getTrainWidth();
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
		
		//method start
		double halfOffset = plugin.getTrainWidth()*radians*.5;
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
		long travelTime = (long)(leftCm/ease.easeArea(easeMode, 1., startLeftCmps, endLeftCmps-startLeftCmps, 1.)*1.0e3);
		
		//System.out.println("goalCm: " + leftCm);
		//System.out.println("travelTime: " + travelTime*1.0e-3 + " seconds");
		
		long startTime = System.currentTimeMillis();
		long destTime = travelTime+startTime;
		long currentTime = 0;
		
		double currentLeftCm = 0.;
		double speedMultiplier;
		double desiredCurrentLeftCm;
		double multipliedLeftCmps = 0.; double multipliedRightCmps = 0.;
		long prevTime = currentTime;
		long timeDiff;
		
		for(; currentTime < travelTime; currentTime = System.currentTimeMillis()-startTime) {
			timeDiff = currentTime - prevTime;
			prevTime = currentTime;
			currentLeftCm += multipliedLeftCmps*timeDiff*1.0e-3;
			
			desiredCurrentLeftCm = ease.easeArea(easeMode, currentTime*1.0e-3, startLeftCmps, deltaLeftCmps, travelTime*1.0e-3);
			speedMultiplier = 1.+((desiredCurrentLeftCm-currentLeftCm)/leftCm);
			double leftCmps = ease.ease(easeMode, (double)currentTime, startLeftCmps, deltaLeftCmps, (double)travelTime);
			double rightCmps = ease.ease(easeMode, (double)currentTime, startRightCmps, deltaRightCmps, (double)travelTime);
			multipliedLeftCmps = leftCmps*speedMultiplier;
			//System.out.println(speedMultiplier);
			if(Math.abs(multipliedLeftCmps) > plugin.getLeftMaxCmps()) {
				speedMultiplier = Math.abs(plugin.getLeftMaxCmps()/leftCmps);
				multipliedLeftCmps = moveParser(leftCmps, plugin.getLeftMaxCmps());
			}
			multipliedRightCmps = rightCmps*speedMultiplier;
			if(Math.abs(multipliedRightCmps) > plugin.getRightMaxCmps()) {
				speedMultiplier = Math.abs(plugin.getRightMaxCmps()/rightCmps);
				multipliedRightCmps = moveParser(rightCmps, plugin.getRightMaxCmps());
				multipliedLeftCmps = leftCmps*speedMultiplier;
			}
			
			directDrive(multipliedLeftCmps, multipliedRightCmps);
			
			switch(stepMode) {
				case DISABLED:
				case DriveTrainStepModes.HIGH_PRECISION:
					break;
				case DriveTrainStepModes.MIN_SLEEP_AND_YIELD:
					Thread.yield();
				case DriveTrainStepModes.MIN_SLEEP:
					Thread.sleep(0l, 1);
					break;
				case DriveTrainStepModes.YIELD:
					Thread.yield();
					break;
				default:
					Thread.sleep(stepMode);
			}
		}
		//stop();
		//System.out.println("currLeftCm: "+currentLeftCm);
		//System.out.println("finished moveWheelCmEasedHelper");
	}
	
	private void moveWheelCmSimpleHelper(double leftCm, double rightCm,
	                                     double leftCmps, double rightCmps) throws InterruptedException {
		
		//do all calculations at front to prevent delays
		leftCmps = moveParser(leftCm, leftCmps);
		rightCmps = moveParser(rightCm, rightCmps);
		long travelTime = ((long)((leftCm/leftCmps)*1.0e3));
		//actually call the support functions
		directDrive(leftCmps, rightCmps);
		Thread.sleep(travelTime); //let our parent handle interrupted exceptions
		stop();
	}
	
	//leftCm/leftCmps must be equal to rightCm/rightCmps
	protected void moveWheelCm(double leftCm, double rightCm, //positions
	                         double leftCmps, double rightCmps, //speed
	                         //finetuning, answer 0 and false for all if you don't
	                         int stepMode, int inEaseMode, int outEaseMode,
	                         int easeTimeMode, int easeTimeConflictHandler, double easeTimeParam,
	                         EasingEquation ease) {
		
		leftCmps = moveParser(leftCm, leftCmps);
		rightCmps = moveParser(rightCm, rightCmps);
		
		//if I were to re-write this again, I would use part?Cm instead of times
		long part1Time = 0; long part2Time = (long)(leftCm*1.0e3/leftCmps); long part3Time = 0;
		//   easein              const speed                                     ease out
		
		int steps; //must be defined up here otherwise java freaks that it has
			//been defined twice, even though that would be impossible
		
		if(inEaseMode != DISABLED || outEaseMode != DISABLED) {
			switch(easeTimeMode) {
				case PERCENT_DISTANCE:
					steps = 0;
					if(inEaseMode != DISABLED) {
						part1Time = (long)(leftCm*easeTimeParam/ease.easeArea(inEaseMode, 1., 0., leftCmps, 1.)*1.0e3);
						++steps;
					} if(outEaseMode != DISABLED) {
						part3Time = (long)(leftCm*easeTimeParam/ease.easeArea(outEaseMode, 1., 0., leftCmps, 1.)*1.0e3);
						++steps;
					}
					part2Time = (long)((1.-steps*easeTimeParam)*leftCm/leftCmps * 1.0e3);
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
					part1Time = (long)(part1Percent*totalSeconds*1.0e3);
					part3Time = (long)(part3Percent*totalSeconds*1.0e3);
					part2Time = (long)((totalSeconds-part1Time-part3Time)*1.0e3); //faster
					break;
				case CONSTANT_DISTANCE:
					steps = 0;
					if(inEaseMode != DISABLED) {
						part1Time = (long)(easeTimeParam/ease.easeArea(inEaseMode, 1., 0., leftCmps, 1.)*1.0e3);
						++steps;
					} if(outEaseMode != DISABLED) {
						part3Time = (long)(easeTimeParam/ease.easeArea(outEaseMode, 1., 0., leftCmps, 1.)*1.0e3);
						++steps;
					}
					part2Time = (long)((leftCm-steps*easeTimeParam)/leftCmps * 1.0e3);
					break;
				case CONSTANT_TIME:
					if(inEaseMode != DISABLED) {
						part1Time = (long)(easeTimeParam*1.0e3);
					} if(outEaseMode != DISABLED) {
						part3Time = (long)(easeTimeParam*1.0e3);
					}
					part2Time = (long)(1.0e3*(leftCm -
						ease.easeArea(inEaseMode, ((double)(part1Time))*1.0e-3, 0., leftCmps, ((double)(part1Time))*1.0e-3) -
						ease.easeArea(outEaseMode, ((double)(part2Time))*1.0e-3, 0., leftCmps, ((double)(part2Time))*1.0e-3)
					)/leftCmps);
					
			}
		}
		
		//this seems kinda verbose, could be shortened
		
		if(inEaseMode != DISABLED) {
			double partLeftCm = ease.easeArea(inEaseMode, part1Time*1.0e-3, 0., leftCmps, part1Time*1.0e-3);
			double partRightCm = ease.easeArea(inEaseMode, part1Time*1.0e-3, 0., rightCmps, part1Time*1.0e-3);
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
			moveWheelCmSimpleHelper(part2Time*leftCmps*1.0e-3, 
				part2Time*rightCmps*1.0e-3, leftCmps, rightCmps
			);
		} catch (InterruptedException ex) {
			stop(); return;
		}
		
		if(outEaseMode != DISABLED) {
			double partLeftCm = ease.easeArea(outEaseMode, part3Time*1.0e-3, 0., leftCmps, part3Time*1.0e-3);
			double partRightCm = ease.easeArea(outEaseMode, part3Time*1.0e-3, 0., rightCmps, part3Time*1.0e-3);
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
	
	
	public void directDrive(double leftCmps, double rightCmps) {
		plugin.directDrive(leftCmps, rightCmps);
		updateOldPos();
		this.leftCmps = leftCmps; this.rightCmps = rightCmps;
	}
	
	
	//By putting this all in one function, we can minimize duplicate calculations
	//adapted from http://rossum.sourceforge.net/papers/DiffSteer/DiffSteer.html
	//Thanks for directing me to it Jeremy!
	public DriveTrainPosition getPosition() {
		double newX, newY, newAngle;
		double seconds = (double)(System.currentTimeMillis()-oldTime)*1e-3;
		newAngle = seconds*(rightCmps-leftCmps)/plugin.getTrainWidth();
		double centerDist = seconds*(rightCmps+leftCmps)*.5; //get rid of duplicate calculations
		newX = oldX + centerDist*Math.cos(newAngle);
		newY = oldY + centerDist*Math.sin(newAngle);
		newAngle += oldAngle;
		
		return new DriveTrainPosition(newX, newY, newAngle);
	}
	
	
	protected void updateOldPos() {
		DriveTrainPosition pos = getPosition();
		oldX = pos.getX();
		oldY = pos.getY();
		oldAngle = pos.getRawAngleRadians();
		oldTime = System.currentTimeMillis();
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
	
	
	public void stop() {
		plugin.stop();
		updateOldPos();
		leftCmps = 0; rightCmps = 0;
		//we don't need to change oldTime, cause speed is 0 :-)
	}
	
	
	public void freeze() {
		plugin.freeze();
		updateOldPos();
		leftCmps = 0; rightCmps = 0;
	}
	
	
	public void kill() {
		plugin.kill();
		updateOldPos();
		leftCmps = 0; rightCmps = 0;
	}
	
	
	/**
	 * Gets the maximum speed in centimeters-per-second for the robot.
	 * 
	 * @return      The maximum speed in centimeters-per-second for the robot.
	 * @see         #getMaxRadiansPerSec
	 * @see         cbccore.movement.plugins.MovementPlugin#getLeftMaxCmps
	 * @see         cbccore.movement.plugins.MovementPlugin#getRightMaxCmps
	 */
	public double getMaxCmps() {
		return Math.min(plugin.getLeftMaxCmps(), plugin.getRightMaxCmps());
	}
	
	
	/**
	 * Gets the maximum speed that the robot could turn in place
	 * 
	 * @return    The maximum speed in radians-per-second
	 * @see       #getMaxDegreesPerSec
	 * @see       #getMaxCmps
	 * @see       cbccore.movement.plugins.MovementPlugin#getLeftMaxCmps
	 * @see       cbccore.movement.plugins.MovementPlugin#getRightMaxCmps
	 */
	public double getMaxRadiansPerSec() {
		return Math.min(plugin.getLeftMaxCmps(), plugin.getRightMaxCmps())/plugin.getTrainWidth();
	}
	
	
	/**
	 * Gets the maximum speed that the robot could turn in place
	 * 
	 * @return     The maximum speed in degrees-per-second
	 * @see        #getMaxRadiansPerSec
	 * @see        #getMaxCmps
	 * @see        cbccore.movement.plugins.MovementPlugin#getLeftMaxCmps
	 * @see        cbccore.movement.plugins.MovementPlugin#getRightMaxCmps
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
		double outerMaxSpeed = (radius>0 ? plugin.getRightMaxCmps() : plugin.getLeftMaxCmps()) - (radius+plugin.getTrainWidth()*.5)/radius;
		double innerMaxSpeed = (radius>0 ? plugin.getLeftMaxCmps() : plugin.getRightMaxCmps()) + (radius+plugin.getTrainWidth()*.5)/radius;
		return Math.min(outerMaxSpeed, innerMaxSpeed);
	}
}
