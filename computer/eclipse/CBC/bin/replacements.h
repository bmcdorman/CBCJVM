#ifndef REPLACEMENTS_H_INCLUDED
#define REPLACEMENTS_H_INCLUDED

#define NUM_ENTRIES 51

const char* replacements[NUM_ENTRIES * 2] =
{
	// Preprocessor

	"#include",
	"//#include",

	"#define",
	"//#define",

	"#define C2J",
	"//#define C2J_UNUSED",

	"#if",
	"//#if",

	"#elif",
	"//#elif",

	"#endif",
	"//#endif",

	//Misc

	"C2J_ENTRY",
	"void main(String[] args) {\n\tCBC.init();\n",

	"C2J_PROGRAM_START",
	"import cbc.*;\npublic class Main {",

	"C2J_PROGRAM_END",
	"}",

	"C2J_MEMBER",
	"public static",

	//CBC.input
	"up_button(",
	"CBC.input.up_button(",

	"down_button(",
	"CBC.input.down_button(",

	"left_button(",
	"CBC.input.left_button(",

	"right_button(",
	"CBC.input.right_button(",

	"a_button(",
	"CBC.input.a_button(",

	"b_button(",
	"CBC.input.b_button(",

	"black_button(",
	"CBC.input.black_button(",

	"printf(",
	"//System.out.printf(",

	//CBC.sensor

	"digital(",
	"CBC.sensor.digital(",

	"set_digital_output_value(",
	"CBC.sensor.set_digital_output_value(",

	"analog10(",
	"CBC.sensor.analog10(",

	"analog(",
	"CBC.sensor.analog(",

	"accel_x(",
	"CBC.sensor.accel_x(",

	"accel_y(",
	"CBC.sensor.accel_y(",

	"accel_z(",
	"CBC.sensor.accel_z(",

	"sonar(",
	"CBC.sensor.sonar(",

	"sonar_inches(",
	"CBC.sensor.sonar_inches(",

	//CBC.motor
	"motor(",
	"CBC.motor.motor(",

	"clear_motor_position_counter(",
	"CBC.motor.clear_motor_position_counter(",

	"move_at_velocity(",
	"CBC.motor.move_at_velocity(",

	"mav(",
	"CBC.motor.mav(",

	"move_to_position(",
	"CBC.motor.move_to_position(",

	"mtp(",
	"CBC.motor.mtp(",

	"move_relative_position(",
	"CBC.motor.move_relative_position(",

	"mrp(",
	"CBC.motor.mrp(",

	"set_pid_gains(",
	"CBC.motor.set_pid_gains(",

	"freeze(",
	"CBC.motor.freeze(",

	"get_motor_done(",
	"CBC.motor.get_motor_done(",

	"get_motor_position_counter(",
	"CBC.motor.get_motor_position_counter(",

	"block_motor_done(",
	"CBC.motor.motor(",

	"bmd(",
	"CBC.motor.bmd(",

	"setpwm(",
	"CBC.motor.setpwm(",

	"getpwm(",
	"CBC.motor.getpwm(",

	"fd(",
	"CBC.motor.fd(",

	"bk(",
	"CBC.motor.bk(",

	"off(",
	"CBC.motor.off(",

	"ao(",
	"CBC.motor.ao(",

	//CBC.servo
	"enable_servos(",
	"CBC.servo.enable_servos(",

	"disable_servos(",
	"CBC.servo.disable_servos(",

	"set_servo_position(",
	"CBC.servo.set_servo_position(",

	"get_servo_position(",
	"CBC.servo.get_servo_position(",
};

#endif // REPLACEMENTS_H_INCLUDED
