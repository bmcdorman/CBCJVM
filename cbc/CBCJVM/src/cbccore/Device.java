package cbccore;

import cbccore.low.*;
import cbccore.low.CBCSimulator;

public class Device {
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
	
	static {
		try {
			System.load("/mnt/user/jvm/cbc/CBC.so");
			if(System.getenv().get("ON_CBC").equals("1")) {
				lowSound = new Sound();
				lowSensors = new Sensor();
				lowDevice = new cbccore.low.Device();
				lowDisplay = new Display();
				lowInput = new Input();
				lowServos = new Servo();
				lowMotors = new Motor();
				lowCamera = new Camera();
				lowCreate = new Create();
			} else {
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
			}
		}
		catch(UnsatisfiedLinkError e) {
			e.printStackTrace();
		}
	}
	
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
