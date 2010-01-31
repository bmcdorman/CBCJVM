import java.io.File;
import java.io.IOException;

import cbccore.display.*;
import cbccore.sensors.buttons.AbstractButton;
import cbccore.sensors.buttons.BlackButton;

public class Main {
	public static void main(String[] args) throws InterruptedException, IOException {
		System.out.println("Running!");
		Framebuffer fb0 = Display.getFramebuffers()[0];
		Pixmap g = new Pixmap(5, 80, Pixel.greenPixel);
		Pixmap r = new Pixmap(5, 80, Pixel.redPixel);
		Pixmap b = new Pixmap(5, 80, Pixel.bluePixel);
		AbstractButton button = new BlackButton();
		int x = 160 - r.getWidth() / 2;
		int y = 0; 
		int dir = 0;
		int dir2 = 0;
		Image image = new Image(new File("cbcdl.bin"));
		ImagePixmap cbc = new ImagePixmap(image);
		Drawer d = new Drawer(320, 1);
		d.drawHLine(10, 309, 0, Pixel.whitePixel);
		d.setClean(); 
		
		
		fb0.fastBlit(0, 0, cbc);
		fb0.setClean();
		while(button.isNotPushed()) {
			if(dir == 0) {
				x+=2;
				if(x + 5 >= 318) {
					dir = 1;
				}
			} else {
				x-=2;
				if(x <= 2) {
					dir = 0;
				}
			} 
			
			fb0.clean(); 
			
			fb0.fastBlit(0, dir2 == 0 ? y++ : y--, d);
			if(y > 239) {
				dir2 = 1;
			} else if(y < 0) {
				dir2 = 0;
			}
			fb0.fastBlit(x, 0, r);
			fb0.fastBlit(x, 80, g); 
			fb0.fastBlit(x, 160, b);
			
			fb0.sync();
		}
	}
}
