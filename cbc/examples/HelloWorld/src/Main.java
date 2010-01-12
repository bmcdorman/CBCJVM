import cbc.*;

public class Main {
	public Main() {
	}
	public void motor(int port, int speed) {
		CBC.motor.motor(port, speed);
	} 
	public void run(String[] args) {
		CBC.init(this, true);
		while(CBC.interaction.isActive) {
		}
		System.out.println("Exiting...");
	}
	public static void main(String[] args) {
		Main main = new Main();
		main.run(args);
	}
}
