#include "C2J.h"

C2J_PROGRAM_START

C2J_MEMBER int forward_speed = 50;
C2J_MEMBER int backward_speed = -50;

C2J_MEMBER void doubleSpeed()
{
	forward_speed *= 2;
	backward_speed *= 2;
}

C2J_MEMBER C2J_ENTRY
	motor(0, forward_speed);
	int dir = 0;
	int pressed = 0;
	while(a_button() == 0)
	{
		if(digital(0) == 1 && pressed == 0)
		{
			if(dir == 0)
			{
				motor(0, backward_speed);
				dir = 1;
			}
			else
			{
				motor(0, forward_speed);
				dir = 0;
			}
			pressed = 1;
		}
		else
		{
			if(digital(0) == 0)
			{
				pressed = 0;
			}
		}
	}
}

C2J_PROGRAM_END
