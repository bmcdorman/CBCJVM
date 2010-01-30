import cbccore.movement.*;
import cbccore.sensors.vision.*;

public class Main {
	public static void main(String [] args) {
		Camera c = new Camera();
		
		System.out.println("Please move blob close (about 0.25 to 0.5 meters) to camera. Press hardware button to continue.");
		
		System.out.println("Press up/down to choose channel. Press \"A\" to continue");
		int i = 0;
		System.out.print("Using channel " + i);
		//buttons
		System.out.println();
		
		System.out.println("Found largest blob. Please move blob 1 meter away (slowly). Press \"A\" to continue");
		while(true) {
		
		}
		
		BlobList bList = new BlobList(c, i);
		System.out.println("--------------\nI see " + bList.size() + " blobs on channel " + i);
		
		//individual blobs
		for(Blob k : bList) {
			System.out.println("I see a blob with " + k.getConfindence() +
				"% confidence at position (" + k.getX() + ", " + k.getY() + ").\n\n");
		}
	}
}
