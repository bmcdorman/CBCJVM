////#define C2J_UNUSED_PROGRAM_START
////#define C2J_UNUSED_PROGRAM_END
////#define C2J_UNUSED_MEMBER
////#define C2J_UNUSED_ENTRY int main() {

public class Main {

public static int value = 1;

public static void main(String[] args) {
	CBC.init();

	System.out.print("Hello, kissc2cbcjava! value = %d\n", value);
	CBC.motor.motor(1, 100);
	while(CBC.input.a_button() == 0)
	{
	}
	CBC.motor.ao();
}

}

