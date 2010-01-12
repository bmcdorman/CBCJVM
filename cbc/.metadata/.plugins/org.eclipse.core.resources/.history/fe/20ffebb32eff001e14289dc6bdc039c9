package cbc;

public class Button {
	public Button(String name) {
		this.name = name.toLowerCase();
	}
	private int getCondition() {
		if(this.name == "a") {
			return CBC.input.a_button();
		}
		if(this.name == "b") {
			return CBC.input.b_button();
		}
		if(this.name == "up") {
			return CBC.input.up_button();
		}
		if(this.name == "down") {
			return CBC.input.down_button();
		}
		if(this.name == "left") {
			return CBC.input.left_button();
		}
		if(this.name == "right") {
			return CBC.input.right_button();
		}
		if(this.name == "black") {
			return CBC.input.black_button();
		}
		return -1;
	}
	private void waitUntilCondition(int condition) {
		while(getCondition() != condition) {
		}
	}
	public void waitUntilPressed() {
		waitUntilCondition(0);
	}
	public void waitUntilNotPressed() {
		waitUntilCondition(1);
	}
	public boolean getPressed() {
		return (getCondition() == 0) ? false : true;
	}
	private String name; 
}
