package cbccore.movement.beta;

public interface Mover {
	public void move(int speed, double cm);

	public void moveNonBlocking(int speed, double cm);
}
