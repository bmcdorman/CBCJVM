package cbccore.motors;


public class ServoMotor {
	private Servo servo = null;
	
	public ServoMotor(Servo servo) {
		this.servo = servo;
	}
	
	public void moveTo(int ms, int newPos) {
		int curPos = servo.getPosition();
		int delta = newPos - curPos;
		double tpms = (double)delta / ms;
		double pos = curPos;
		for(;;) {
			pos += tpms;
			servo.setPosition((int)pos);
			if(tpms < 0 && pos <= newPos) {
				return;
			} else if(tpms > 0 && pos >= newPos) {
				return;
			}
		}
	}
	
	public Servo getServo() {
		return servo;
	}
}
