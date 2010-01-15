package cbc;


public class CBC {
    	
    	public static void init() {
    		init(null, false);
    	}
    	
    	public static void init(boolean simulated) {
			init(null, false, simulated);
		}
    	
    	public static void init(Object main, boolean runInteraction) {
    		init(main, runInteraction, false);
    	}
    	
		public static void init(Object main, boolean runInteraction, boolean simulated)
		{
			if(simulated) {
				simulator = new CBCSimulator();
				sound = simulator.sound;
				sensor = simulator.sensor;
				device = simulator.device;
				display = simulator.display;
				input = simulator.input;
				servo = simulator.servo;
				motor = simulator.motor;
				camera = simulator.camera;
				create = simulator.create;
			} else {
				System.load("/mnt/user/java/cbc/CBC.so");
				sound = new Sound();
				sensor = new Sensor();
				device = new Device();
				display = new Display();
				input = new Input();
				servo = new Servo();
				motor = new Motor();
				camera = new Camera();
				create = new Create();
				if(runInteraction) {
					interaction = new Interaction(main);
					new Thread(interaction).start();
				}
				System.out.println("Successfully initialized CBC");
			}
		}
		
		private static CBCSimulator simulator = null;
		public static Sound sound = null;
		public static Sensor sensor = null;
		public static Device device = null;
		public static Display display = null;
		public static Input input = null;
		public static Servo servo = null;
		public static Motor motor = null;
		public static Camera camera = null;
		public static Interaction interaction = null;
		public static Create create = null;
}
