#include "cbc_Servo.h"
#include <stdio.h>
#include <cbc.h>

/*
 * CBC_Servo JNI Wrapper Module
 * By: Braden McDorman
 * License: GNU GPLv3
 */

/*
 * Class:     Servo
 * Method:    enable_servos
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_cbc_Servo_enable_1servos(JNIEnv* env, jobject obj)
{
#ifdef CBC
	enable_servos();
#else
	printf("Java_cbc_Servo_enable_1servos stub\n");
#endif
}

/*
 * Class:     Servo
 * Method:    disable_servos
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_cbc_Servo_disable_1servos(JNIEnv* env, jobject obj)
{
#ifdef CBC
	disable_servos();
#else
	printf("Java_cbc_Servo_disable_1servos stub\n");
#endif
}

/*
 * Class:     Servo
 * Method:    set_servo_position
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_cbc_Servo_set_1servo_1position(JNIEnv* env, jobject obj, jint port, jint pos)
{
#ifdef CBC
	return set_servo_position(port, pos);
#else
	printf("Java_cbc_Servo_set_1servo_1position stub\n");
	return -1;
#endif
}

/*
 * Class:     Servo
 * Method:    get_servo_position
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_cbc_Servo_get_1servo_1position(JNIEnv* env, jobject obj, jint port)
{
#ifdef CBC
	return get_servo_position(port);
#else
	printf("Java_cbc_Servo_get_1servo_1positio stub\n");
	return -1;
#endif
}
