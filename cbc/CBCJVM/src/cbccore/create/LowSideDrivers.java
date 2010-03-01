package cbccore.create;

public class LowSideDrivers {
	private cbccore.low.Create create = null;
	public LowSideDrivers(cbccore.low.Create create) {
		this.create = create;
	}
	/**
	 * Sets the PWM signal for the three low side drivers (128 = 100%). You
	 * probably don't care about this function.
	 * 
	 * @param pwm0
	 *            pin 22
	 * @param pwm1
	 *            pin 23
	 * @param pwm2
	 *            pin 24
	 */
	public void setPWM(int pwm2, int pwm1, int pwm0) {
		create.create_pwm_low_side_drivers(pwm2, pwm1, pwm0);
	}
	public void setState(boolean pwm2, boolean pwm1, boolean pwm0) {
		create.create_low_side_drivers(pwm2 ? 1 : 0, pwm1 ? 1 : 0, pwm0 ? 1 : 0);
	}
}
