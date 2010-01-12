//#include "C2J.h"

import cbc.*;
public class Main {

public static int forward_speed = 50;
public static int backward_speed = -50;

public static void doubleSpeed()
{
	forward_speed *= 2;
	backward_speed *= 2;
}

public static void main(String[] args) {
	CBC.init();

	CBC.motor.motor(0, forward_speed);
	int dir = 0;
	int pressed = 0;
	while(CBC.input.a_button() == 0)
	{
		if(CBC.sensor.digital(0) == 1 && pressed == 0)
		{
			if(dir == 0)
			{
				CBC.motor.motor(0, backward_speed);
				dir = 1;
			}
			else
			{
				CBC.motor.motor(0, forward_speed);
				dir = 0;
			}
			pressed = 1;
		}
		else
		{
			if(CBC.sensor.digital(0) == 0)
			{
				pressed = 0;
			}
		}
	}
}

}

