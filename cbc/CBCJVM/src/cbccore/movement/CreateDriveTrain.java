package cbccore.movement;
/*
 *      CreateDriveTrain.java
 *      
 *      Version 0.9 r0000
 *      
 *      Copyright 2009 PiPeep
 *      
 *      This program is free software; you can redistribute it and/or modify
 *      it under the terms of the GNU General Public License as published by
 *      the Free Software Foundation; either version 2 of the License, or
 *      (at your option) any later version.
 *      
 *      This program is distributed in the hope that it will be useful,
 *      but WITHOUT ANY WARRANTY; without even the implied warranty of
 *      MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *      GNU General Public License for more details.
 *      
 *      You should have received a copy of the GNU General Public License
 *      along with this program; if not, write to the Free Software
 *      Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 *      MA 02110-1301, USA.
 */



import cbccore.Device;
import cbccore.InvalidValueException;

public class CreateDriveTrain extends DriveTrain {
	
	private static final double width = 27.0;
	private static final double wheelCircumference = 10.;
	private double efficiency;
	private cbccore.low.Create create  = Device.getLowCreateController();
	
	public CreateDriveTrain(double efficiency, boolean fullMode) {
		Device.getLowCreateController().create_connect();
		this.efficiency = efficiency;
		// FIXME: This should be moved to it's own method
		if(fullMode) {
			create.create_full(); 
		} else {
			create.create_safe(); 
		}
	}
	
	public void moveAtRps(double rps) throws InvalidValueException {
		moveAtCmps(rps/wheelCircumference);
	}
	
	public void moveAtCmps(double cmps) throws InvalidValueException {
		create.create_drive_straight((int)(cmps*10./efficiency));
	}
	
	public void rotateDegrees(double degrees, double degreesPerSecond) {
		rotateRadians(Math.toRadians(degrees), Math.toRadians(degreesPerSecond));
	}
	
	public void rotateRadians(double radians, double radiansPerSecond) {
		//time
		//System.out.println((radiansPerSecond/(2*Math.PI))*(_width*Math.PI)*10.);
		//System.out.println((int)((radiansPerSecond/(2*Math.PI))*(_width*Math.PI)*10.+.5));
		//System.out.println((int)(radians/radiansPerSecond*1000.));
		create.create_spin_CW((int)((radiansPerSecond/(2*Math.PI))*(width*Math.PI)*10./efficiency+.5));
		try { Thread.sleep((int)(radians/radiansPerSecond*1000.)); } catch (Exception e) {}
		kill();
	}
	
	public void moveCm(double cm, double cmps) throws InvalidValueException {
		moveAtCmps(cmps);
		try { Thread.sleep((int)(cm/cmps*1000.)); } catch (Exception e) {}
	}
	
	public void stop() {
		kill();
	}
	
	public void freeze() {
		kill(); //no such api
	}
	
	public void kill() {
		create.create_stop();
	}
	
	public double maxCmps() { //min is 0.2, but the api design...
		return 50.0 * efficiency;
	}
	
	public double maxRps() {
		return maxCmps() / wheelCircumference;
	}
}
