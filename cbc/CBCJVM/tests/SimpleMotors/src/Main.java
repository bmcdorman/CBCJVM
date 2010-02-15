import cbccore.motors.Motor;

public class Main {
	public static void main(String [] args) {
		Motor m = new Motor(0);
		m.moveAtVelocity(100);
		m = new Motor(1);
		m.moveAtVelocity(200);
		m = new Motor(2);
		m.moveAtVelocity(300);
		m = new Motor(3);
		m.moveAtVelocity(400);
		try { Thread.sleep(2000l); } catch (Exception e) {}
	}
}
