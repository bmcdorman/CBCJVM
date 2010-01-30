package cbctools.image;

import java.awt.image.BufferedImage;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

import javax.imageio.*;

import cbccore.display.Pixel;

public class ConvertImage {
	public static void main(String[] args) throws IOException {
		System.out.println("CBCJVM Image Converter Tool - Version 0.0.1");
		if(args.length < 2) {
			System.out.println("Usage: ");
			System.out.println("\tjava cbctools.image.ConvertImage <source> <destination>");
			return;
		}
		OutputStream out = new FileOutputStream(new File(args[1]));
		DataOutputStream dOut = new DataOutputStream(out);
		BufferedImage image = ImageIO.read(new File(args[0]));
		dOut.writeInt(image.getWidth());
	
		for(int iy = 0; iy < image.getHeight(); ++iy) {
			for(int ix = 0; ix < image.getWidth(); ++ix) {
				dOut.writeInt(image.getRGB(ix, iy));
			}
		}
		System.out.println("Success! Wrote the image " + args[0] + " (" + image.getWidth() + ", " + image.getHeight() + ")");
		System.out.println("to " + args[1]);
	}
}
