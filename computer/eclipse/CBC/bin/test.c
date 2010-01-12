#define C2J_PROGRAM_START
#define C2J_PROGRAM_END
#define C2J_MEMBER
#define C2J_ENTRY int main() {

C2J_PROGRAM_START

C2J_MEMBER int value = 1;

C2J_MEMBER C2J_ENTRY
	printf("Hello, kissc2cbcjava! value = %d\n", value);
	motor(1, 100);
	while(a_button() == 0)
	{
	}
	ao();
}

C2J_PROGRAM_END
