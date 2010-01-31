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

import cbccore.low.simulator.*;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.io.PrintStream;

/**
 * 
 * @author Benjamin Woodruff
 *
 */

public class CBCSimulator {
	
	public SimulatedSound sound;
	public SimulatedSensor sensor;
	public SimulatedDevice device;
	public SimulatedDisplay display;
	public SimulatedInput input;
	public SimulatedServo servo;
	public SimulatedMotor motor;
	public SimulatedCamera camera;
	public SimulatedCreate create;
	
	public MotorSpeed[] m;
	
	public static PrintStream stdOut;
	
	public CBCSimulator() {
		try {
		      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		    } catch(Exception e) {
		      System.out.println("Error setting native LAF: " + e);
		    }
		sound = new SimulatedSound(this);
		sensor = new SimulatedSensor(this);
		device = new SimulatedDevice(this);
		display = new SimulatedDisplay(this);
		input = new SimulatedInput(this);
		servo = new SimulatedServo(this);
		motor = new SimulatedMotor(this);
		camera = new SimulatedCamera(this);
		create = new SimulatedCreate(this);
		
		m = new MotorSpeed[4];
		for(int i = 0; i < m.length; ++i) {
			m[i] = new MotorSpeed(0, false);
		}
		
		if(stdOut == null) { stdOut = System.out; }
		System.out.println("Welcome to CBCJava");
		JFrame frame = new JFrame("CBCSimulator");
		
		frame.getContentPane().add(new JScrollPane(display.getTextBox()), BorderLayout.CENTER);
		
		FlowLayout buttonLayout = new FlowLayout();
		Container buttonContainer = new Container();
		buttonContainer.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		//buttonContainer.add(input.ub, BorderLayout.SOUTH);
		//buttonContainer.add(input.db, BorderLayout.SOUTH);
		buttonContainer.setLayout(buttonLayout);
		buttonContainer.add(input.ub);
		buttonContainer.add(input.db);
		buttonContainer.add(input.lb);
		buttonContainer.add(input.rb);
		buttonContainer.add(input.ab);
		buttonContainer.add(input.bb);
		buttonContainer.add(input.blb);
		frame.getContentPane().add(buttonContainer, BorderLayout.SOUTH);
		
		System.setOut(display.getPrintStream());
		
		frame.setSize(320, 240);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
