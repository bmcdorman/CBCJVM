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

package cbccore.sensors.analog;

/**
 * Calculates a distance in centimeters based of the value given by a floating
 * analog sensor port. The ETSensor's values are approximately inversely
 * proportional to the actual distance, and with some calibration, this class
 * can use that relationship to solve for the actual distance. See the
 * ETCalibrator test, which will give you k, power, and b required by the
 * constructor. The class also contains calibration information collected by the
 * programmer for his own sensor. This should only be used for prototyping, and
 * you should do the calibration process yourself, as values could vary greatly
 * from sensor to sensor.
 * <p>
 * The formula used is: <code>distance=k/Math.pow(analog10Value, power)+b</code>
 * 
 * @author Benjamin Woodruff
 *
 */

//Version 0.1: First stable version, had to add floating sensor support to
//                 CBCJVM
//Version 0.2: Added second getCm method that averages readings
//Version 0.3: New calibration values
//Version 1.0: Public CBCJVM release, added documentation.

//Sorry for excessive commenting, I think I'm going to use this for my
    //documented code example

public class ETSensor extends Analog {
	
	//calibration data
	private double k; //multiplication factor
	private double b; //fudge factor
	private double power; //negitive power of analog value
	
	//port number is handled by superclass
	
	/**
	 * Basic constructor for fast tesing/prototyping, uses preset calibration
	 * values, which probably would differ slightly from your sensor. You should
	 * run the calibration utility yourself.
	 * 
	 * @param  port  The port that the ETSensor is plugged into.
	 */
	public ETSensor(int port) {
		this(port, 29470, 1.23, -.00448);
	}
	
	
	/**
	 * A constructor that you can plug your calibration data into. This is what
	 * you should use if you really want accuracy. 
	 * 
	 * @param  port   The port that the ETSensor is plugged into.
	 * @param  k      The calibration k value. A multiplication factor.
	 * @param  power  The calibration pow value. The negitive power of the
	 *                    analog value
	 * @param  b      The calibration b value. An offset, "fudge factor".
	 */
	public ETSensor(int port, double k, double power, double b) {
		super(port);
		setFloating(true);
		this.k = k;
		this.power = power;
		this.b = b;
	}
	
	/**
	 * Conducts muliple readings and averages them together. Much more accurate,
	 * but takes much more time. It is recommended that you stop before calling
	 * this function so that it is not averaging positions from muliple
	 * positions.
	 * 
	 * @param  readings    The number of readings to take that will be averaged
	 * @param  millisWait  The milliseconds to wait before each reading. 0
	 *                         should work in most cases. Don't make this too
	 *                         big, as the time it takes the function to return
	 *                         equals (millisWait+timeForSensorProbe)*readings
	 *                         with timeForSensorProbe being about 5-8 millis on
	 *                         a stock CBCv2. The main point of this is to allow
	 *                         a lower CPU usage, although with Matt's CBCv2 mod
	 *                         this shouldn't be too much of a problem, as
	 *                         in the mod, sensor probes are sleeping.
	 * @return             The average distance in cm
	 */
	public double getCm(int readings, int millisWait) {
		int sum = 0;
		for(int i = 0; i < readings; ++i) {
			sum += getValueHigh();
			if(millisWait != 0) {
				try { Thread.sleep(millisWait); } catch(Exception e) { return -1.; }
			}
		}
		double avg = ((double)(sum))/((double)(readings));
		return k/Math.pow(avg, power);
	}
	
	/**
	 * Calls getCm(1, 0), essentially giving you one quick reading. Accuracy is
	 * iffy, and it is recommended that you only use this function if you have
	 * your own averaging method, because CBC sensor readings have been known to
	 * fluctate from reading to reading. Otherwise, you should at least call
	 * getCm(3, 0) or something like that.
	 * 
	 * @return     a quick-and-dirty approximation for the distance.
	 */
	public double getCm() {
		return getCm(1, 0);
	}
}
