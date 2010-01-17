import cbccore.sensors.buttons.AButton;
import cbccore.sensors.buttons.AbstractButton;

public class Main {
	public static void main(String[] args) {
		AbstractButton abutton = new AButton();
		while(abutton.isNotPushed());
		System.out.println("Pushed A Button!");
	}
}
