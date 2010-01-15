package cbccore;

import cbccore.simulator.*;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.io.PrintStream;

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
		
		System.setOut(display.getPrintStream());
		
		frame.setSize(320, 240);
		frame.setVisible(true);
	}
}
