import java.io.File;
import java.io.IOException;

import cbccore.display.*;
import cbccore.sensors.buttons.AbstractButton;
import cbccore.sensors.buttons.BlackButton;

public class Main {
	public static void main(String[] args) throws InterruptedException, IOException {
		AbstractButton button = new BlackButton();
		Framebuffer fb0 = Display.getFramebuffer();
		System.out.println("Running!");
		int dir = 0;
		Image image1 = new Image(new File("skull1.bin"));
		ImagePixmap skull1 = new ImagePixmap(image1);
		
		Image image2 = new Image(new File("skull2.bin"));
		ImagePixmap skull2 = new ImagePixmap(image2);
		
		fb0.setClean();
		while(button.isNotPushed()) {
			fb0.clean();
			//System.out.println(skull1.getWidth());
			if(dir == 0) {
				fb0.fastBlit(160 - skull1.getWidth() / 2, 120 - skull1.getHeight() / 2, skull1);
				dir = 1;
			} else {
				fb0.fastBlit(160 - skull2.getWidth() / 2, 120 - skull2.getHeight() / 2, skull2);
				dir = 0;
			}
			fb0.sync();
			//Thread.sleep(750);
		}
	}
}
