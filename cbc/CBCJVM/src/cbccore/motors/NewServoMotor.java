import cbccore.motors.Servo;
import cbccore.motors.ServoMotor;

public class NewServoMotor extends ServoMotor {
	private long begin = 0;
	private int ms = 0;
	private int delta = 0;
	private int curPos = 0;
	private boolean moving = false;

	public NewServoMotor(Servo servo) {
		super(servo);
	}

	public boolean isMoving() {
		return moving;
	}

	@Override
	public void moveTo(int ms, int newPos) {
		curPos = getServo().getPosition();
		delta = newPos - curPos;
		begin = System.currentTimeMillis();
		moving = true;
		this.ms = ms;
		ServoMotorThread.get().addServoMotor(this);
	}

	public void update() {
		if (!moving)
			return;

		if (System.currentTimeMillis() > begin + ms) {
			getServo().setPosition(curPos + delta);
			moving = false;
		}

		double frac = ((double) delta / (double) ms);
		int y = (int) (frac * (System.currentTimeMillis() - begin) + curPos);
		if (y > curPos + delta) {
			y = curPos + delta;
			moving = false;
		}
		getServo().setPosition(y);
	}
}

/*
 * 
 * y = ((y2-y1)/(x2-x1)) * x + y1
 */