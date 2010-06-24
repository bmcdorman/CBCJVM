package cbcdownloader;

public class CommunicationException extends Exception {
	private static final long serialVersionUID = -4718464287815374459L;
	
	public CommunicationException() {
		super();
	}
	
	public CommunicationException(String message) {
		super(message);
	}
}
