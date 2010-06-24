package cbccore.create;

public class CliffState {
	
	int rightCliff;
	int rightFrontCliff;
	int leftCliff;
	int leftFrontCliff;
	int rightCliffAmount;
	int rightFrontCliffAmount;
	int leftCliffAmount;
	int leftFrontCliffAmount;
	
	public CliffState(int rightCliff, int rightFrontCliff, int leftCliff,
			int leftFrontCliff, int rightCliffAmount,
			int rightFrontCliffAmount, int leftCliffAmount,
			int leftFrontCliffAmount) {
		super();
		
		this.rightCliff = rightCliff;
		this.rightFrontCliff = rightFrontCliff;
		this.leftCliff = leftCliff;
		this.leftFrontCliff = leftFrontCliff;
		this.rightCliffAmount = rightCliffAmount;
		this.rightFrontCliffAmount = rightFrontCliffAmount;
		this.leftCliffAmount = leftCliffAmount;
		this.leftFrontCliffAmount = leftFrontCliffAmount;
	}

	public int getRightCliff() {
		return rightCliff;
	}
	
	public int getRightFrontCliff() {
		return rightFrontCliff;
	}
	
	public int getLeftCliff() {
		return leftCliff;
	}
	
	public int getLeftFrontCliff() {
		return leftFrontCliff;
	}
	
	public int getRightCliffAmount() {
		return rightCliffAmount;
	}
	
	public int getRightFrontCliffAmount() {
		return rightFrontCliffAmount;
	}
	
	public int getLeftCliffAmount() {
		return leftCliffAmount;
	}
	
	public int getLeftFrontCliffAmount() {
		return leftFrontCliffAmount;
	}

}
