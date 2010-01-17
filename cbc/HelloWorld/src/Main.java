import cbccore.sensors.buttons.BlackButton;

public class Main {
	public static void main(String[] args) {
		Buttons test = new Buttons();
		test.run();
		System.out.println("Press Black to stop program!");
		BlackButton end = new BlackButton();
		while(end.isNotPushed());
	}
}
