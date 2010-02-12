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

import cbccore.display.SimulatedFramebuffer;
import cbccore.low.simulator.*;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import java.io.PrintStream;

/**
 * Do not instanciate this class. It will automatically be created the first
 * time a CBCJVM API method and/or class is used. It is handled by
 * cbccore.Device
 * 
 * @author Benjamin Woodruff, Braden McDorman
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
	
	
	//sidebar things
	public MotorSpeed[] m;
	Container sidebar = new Container();
	
	public static PrintStream stdOut;
	
	private JFrame frame = new JFrame("CBCSimulator");
	
	private boolean framebuffersAdded = false;
	
	public CBCSimulator() {
		
		//Blend in with Native look
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e) {
			System.out.println("Error setting native LAF: " + e);
		}
		
		//create simulated devices, to replace CBC methods
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
		
		//stdOut: redirect System.out.println to the builtin console
		if(stdOut == null) { stdOut = System.out; }
		System.out.println("Welcome to CBCJava");
		frame.getContentPane().add(new JScrollPane(display.getTextBox()), BorderLayout.CENTER);
		System.setOut(display.getPrintStream());
		
		//Create sidebar
		addSidebar();
		
		//buttons
		addButtons();
		
		//Setup visibility settings for root pane
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		//Make sure the window isn't too big for the person's screen
		frame.setSize(Math.min(1024, (int)(screenSize.width*.7)), Math.min(768, (int)(screenSize.height*.7)));
		frame.setVisible(true);
	}
	
	private void addSidebar() {
		GridLayout sidebarLayout = new GridLayout(2, 1, 0, 5);
		sidebar.setLayout(sidebarLayout);
		frame.getContentPane().add(sidebar, BorderLayout.EAST);
	}
	
	private void addButtons() {
		FlowLayout buttonLayout = new FlowLayout();
		Container buttonContainer = new Container();
		buttonContainer.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		buttonContainer.setLayout(buttonLayout);
		buttonContainer.add(input.ub);
		buttonContainer.add(input.db);
		buttonContainer.add(input.lb);
		buttonContainer.add(input.rb);
		buttonContainer.add(input.ab);
		buttonContainer.add(input.bb);
		buttonContainer.add(input.blb);
		frame.getContentPane().add(buttonContainer, BorderLayout.SOUTH);
	}
	
	/**
	 * Checks to see if the framebuffer has already been added. If not, it adds
	 * it.
	 */
	public void addFramebuffers() {
		if(framebuffersAdded) return;
		framebuffersAdded = true;
		sidebar.add(((SimulatedFramebuffer)cbccore.display.Display.getFramebuffer()).getPanel(), 0);
	}
}
