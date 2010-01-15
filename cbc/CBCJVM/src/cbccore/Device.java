package cbccore;

import cbccore.low.*;

public class Device {
	private static Motor lowMotors = new Motor();
	private static Servo lowServos = new Servo();
	private static Camera lowCamera = new Camera();
	private static Device lowDevice = new Device();
	private static Input lowInput = new Input();
	private static Sound lowSound = new Sound();
	private static Sensor lowSensor = new Sensor();
	public static Motor getLowMotorController() {
		return lowMotors;
	}
	public static Servo getLowServoController() {
		return lowServos;
	}
	public static Camera getLowCameraController() {
		return lowCamera;
	}
	public static Device getLowDeviceController() {
		return lowDevice;
	}
	public static Input getLowInputController() {
		return lowInput;
	}
	public static Sound getLowSoundController() {
		return lowSound;
	}
	public static Sensor getLowSensorController() {
		return lowSensor;
	}
}