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

package cbccore;

import cbccore.config.ChoiceConfigurator;
import cbccore.config.Choices;
import cbccore.sensors.IBooleanSensor;
import cbccore.sensors.analog.Analog;
import cbccore.sensors.buttons.AButton;
import cbccore.sensors.buttons.BButton;
import cbccore.sensors.buttons.LeftButton;
import cbccore.motors.Servo;
import cbccore.motors.Motor;

public class Botball {
	Thread shutDownThread = null;
	private class ShutDownIn implements Runnable {
		double secs = 0.0;
		public ShutDownIn(double secs) {
			this.secs = secs;		
		}	
		@Override
		public void run() {
			try {
				Thread.sleep((long)(secs * 1000));			
			} catch(InterruptedException e) {
				e.printStackTrace();			
			}	
			Motor.allOff();
			Servo.disable();
			System.exit(0);	
		}
	}
	public static void waitForLight(Analog light) {
		Choices choices = new Choices();
		choices.put(0, "Calibrate Light ON");
		choices.put(1, "Calibrate Light OFF");
		choices.put(2, "Wait...");
		int lightOn = 0;
		int lightOff = 0;
		IBooleanSensor[] sensors = {new AButton(), new BButton(), new LeftButton()};
		ChoiceConfigurator config = new ChoiceConfigurator(sensors, choices);
		int cho = -1;
		while(cho != 2) {
			cho = config.ask();
			if(cho == 0) {
				lightOn = light.getValueHigh();
				System.out.println("Set ON value to " + lightOn);
			} else if(cho == 1) {
				lightOff = light.getValueHigh();
				System.out.println("Set OFF value to " + lightOff);
			}
		}
		System.out.println("Waiting...");
		int delta = lightOff - lightOn;
		if(delta < 200) {
			System.out.println("Bad Calibration.");
			System.exit(1);
		}
		while(light.getValueHigh() > lightOn + (delta / 2));
	}
	public void shutDownIn(double secs) {
		shutDownThread = new Thread(new ShutDownIn(secs));
		shutDownThread.start();
	}
}
