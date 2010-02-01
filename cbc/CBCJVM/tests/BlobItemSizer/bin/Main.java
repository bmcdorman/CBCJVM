import cbccore.movement.*;
import cbccore.sensors.vision.*;
import cbccore.sensors.buttons.*;
import cbccore.events.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
	public static void main(String [] args) {
		Camera c = new Camera();
		ButtonEmitter.get().start();
		
		System.out.println("Please move blob close (about 0.25 to 0.5 meters) to camera. Press hardware button to continue.");
		
		EventManager.get().connect(ButtonEmitter.BlackButtonReleased, new BlackButtonReleasedHandler());
	}
	
	public static class BlackButtonReleasedHandler implements IEventListener {
		public void event(Event type) {
			EventManager manager = EventManager.get();
			manager.disconnect(ButtonEmitter.BlackButtonReleased, this);
			System.out.println("Press up/down to choose channel. Press \"A\" to continue");
			
			AtomicInteger ai = new AtomicInteger(0);
			System.out.print("Using channel " + ai.get());
			
			UpDown up = new UpDown(ai, 1);
			UpDown down = new UpDown(ai, -1);
			manager.connect(ButtonEmitter.UpButtonReleased, up);
			manager.connect(ButtonEmitter.UpButtonReleased, down);
			manager.connect(ButtonEmitter.AButtonReleased, new LargestBlobFinder(ai, up, down));
		}
	};
	
	public static class UpDown implements IEventListener {
		
		int delta;
		AtomicInteger value;
		
		public UpDown(AtomicInteger value, int delta) {
			this.value = value;
			this.delta = delta;
		}
		
		public void event(Event e) {
			System.out.print("\b"+value.addAndGet(delta));
		}
	}
	
	private static class LargestBlobFinder implements IEventListener {
		
		private AtomicInteger ai;
		private UpDown up, down;
		
		public LargestBlobFinder(AtomicInteger ai, UpDown up, UpDown down) {
			this.up = up; this.down = down;
			this.ai = ai;
		}
		
		public void event(Event type) {
			EventManager manager = EventManager.get();
			manager.disconnect(ButtonEmitter.UpButtonReleased, up);
			manager.disconnect(ButtonEmitter.UpButtonReleased, down);
			manager.disconnect(ButtonEmitter.AButtonReleased, this);
			System.out.println();
			Camera c = new Camera();
			int largestSize = 0;
			while(c.getAvailableBlobLength(ai.get()) == 0) {
				while(!c.isNewDataAvailable());
				c.update();
			}
			Blob largestBlob = null;
			for(Blob i:new BlobList(c, ai.get())) {
				if(i.getSize() > largestSize) {
					largestBlob = i;
				}
			}
			System.out.println("Found largest blob. Please move blob 1 meter away (slowly). Press \"A\" to continue");
			while(!new AButton().getValue()) { //TODO: cleanup
				if(c.isNewDataAvailable()) {
					c.update();
					largestBlob.update();
				}
			}
			
			int total = 0;
			for(int i = 0; i < 5; ++i) {
				while(!c.isNewDataAvailable());
				c.update();
				largestBlob.update();
				total += largestBlob.getSize();
			}
			total /= 5;
			
			manager.connect(ButtonEmitter.AButtonReleased, new DistanceLister(new BlobItem(largestBlob, Math.sqrt(total))));
		}
	}
	
	private static class DistanceLister implements IEventListener {
		
		private BlobItem blob;
		
		public DistanceLister(BlobItem blob) {
			this.blob = blob;
		}
		
		public void event(Event type) {
			Camera c = new Camera();
			EventManager.get().disconnect(ButtonEmitter.AButtonReleased, this);
			cbccore.sensors.buttons.BButton bbutton = new cbccore.sensors.buttons.BButton();
			while(!bbutton.getValue()) {
				if(c.isNewDataAvailable()) {
					c.update();
					blob.getBlob().update();
					System.out.println(blob.getDistance() + "cm away");
				}
			}
		}
	};
}
