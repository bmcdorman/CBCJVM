/*
 * This file is part of CBCJVM.
 * CBCJVM is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * CBCJVM is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with CBCJVM.  If not, see <http://www.gnu.org/licenses/>.
 */

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
