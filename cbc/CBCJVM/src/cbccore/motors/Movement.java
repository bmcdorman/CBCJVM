package cbccore.motors;

public class Movement {
	private Direction d;
	private int speed;
	private int distance;
	public Movement(Direction d, int speed, int distance) {
		this.d = d;
		this.speed = speed;
		this.distance = distance;
	}
	public int getDistance() {
		return distance;
	}
	public int getSpeed() {
		return speed;
	}
	public Direction getDirection() {
		return d;
	}
}
