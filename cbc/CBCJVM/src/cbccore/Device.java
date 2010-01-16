package cbccore;

import cbccore.low.*;
import cbccore.low.CBCSimulator;

public class Device {
	
	public static void init() {
		
		boolean simulated = false;
		try {
			System.load("/mnt/user/jvm/cbc/CBC.so");
		}
		catch(UnsatisfiedLinkError e) {
			e.printStackTrace();
			System.out.println("Unable to load CBC.so! Assuming simulator mode.");
			simulated = true;
		}
		
		if(simulated) {
			simulator = new CBCSimulator();
			lowSound = simulator.sound;
			lowSensors = simulator.sensor;
			lowDevice = simulator.device;
			lowDisplay = simulator.display;
			lowInput = simulator.input;
			lowServos = simulator.servo;
			lowMotors = simulator.motor;
			lowCamera = simulator.camera;
			lowCreate = simulator.create;
		} else {
			lowSound = new Sound();
			lowSensors = new Sensor();
			lowDevice = new cbccore.low.Device();
			lowDisplay = new Display();
			lowInput = new Input();
			lowServos = new Servo();
			lowMotors = new Motor();
			lowCamera = new Camera();
			lowCreate = new Create();
			//System.out.println("Successfully initialized CBC");
		}
	}
	
	private static CBCSimulator simulator;
	private static Motor lowMotors;
	private static Display lowDisplay;
	private static Create lowCreate;
	private static Servo lowServos;
	private static Camera lowCamera;
	private static cbccore.low.Device lowDevice;
	private static Input lowInput;
	private static Sound lowSound;
	private static Sensor lowSensors;
	
	public static CBCSimulator getSimulatorController() {
		return simulator;
	}
	
	public static Motor getLowMotorController() {
		return lowMotors;
	}
	
	public static Create getLowCreateController() {
		return lowCreate;
	}
	
	public static Display getLowDisplayController() {
		return lowDisplay;
	}
	
	public static Servo getLowServoController() {
		return lowServos;
	}
	public static Camera getLowCameraController() {
		return lowCamera;
	}
	public static cbccore.low.Device getLowDeviceController() {
		return lowDevice;
	}
	public static Input getLowInputController() {
		return lowInput;
	}
	public static Sound getLowSoundController() {
		return lowSound;
	}
	public static Sensor getLowSensorController() {
		return lowSensors;
	}
}
