package cbccore.motors;

import cbccore.motors.Servo;

public class ServoMotor {
	private long begin = 0;
	private int ms = 0;
	private int delta = 0;
	private int curPos = 0;
	private boolean moving = false;
	private Servo servo = null;

	public ServoMotor(Servo servo) {
		this.servo = servo;
	}

	public boolean isMoving() {
		return moving;
	}

	public void moveTo(int ms, int newPos) {
		curPos = servo.getPosition();
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
		servo.setPosition(y);
	}
	
	public Servo getServo() {
		return servo;
	}
}

/*
 * 
 * y = ((y2-y1)/(x2-x1)) * x + y1
 */