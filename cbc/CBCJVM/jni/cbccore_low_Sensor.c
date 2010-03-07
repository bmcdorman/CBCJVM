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

#include "cbccore_low_Sensor.h"
#include <stdio.h>
#include <cbc.h>

/*
 * cbccore_low_Sensor JNI Wrapper Module
 * By: Braden McDorman
 * License: GNU GPLv3
 */

/*
 * Class:     cbccore_low_Sensor
 * Method:    digital
 * Signature: (I)I
 */
JNIEXPORT jint Java_cbccore_low_Sensor_digital(JNIEnv* env, jobject obj, jint port)
{
#ifdef CBC
	return digital(port);
#else
	printf("Java_cbccore_low_Sensor_digital stub\n");
	return -1;
#endif
}

/*
 * Class:     cbccore_low_Sensor
 * Method:    set_digital_output_value
 * Signature: (II)I
 */
JNIEXPORT jint Java_cbccore_low_Sensor_set_1digital_1output_1value(JNIEnv* env, jobject obj, jint port, jint value)
{
#ifdef CBC
	set_digital_output_value(port, value);
#else
	printf("Java_cbccore_low_Sensor_set_1digital_1output_1value stub\n");
	return 0;
#endif
}

/*
 * Class:     cbccore_low_Sensor
 * Method:    set_analog_floats
 * Signature: (I)I
 */
JNIEXPORT void Java_cbccore_low_Sensor_set_1analog_1floats(JNIEnv* env, jobject obj, jint mask)
{
#ifdef CBC
	set_analog_floats(mask);
#else
	printf("Java_cbccore_low_Sensor_set_1analog_1floats stub\n");
	return 0;
#endif
}

/*
 * Class:     cbccore_low_Sensor
 * Method:    set_each_analog_state
 * Signature: (I)I
 */
JNIEXPORT void Java_cbccore_low_Sensor_set_1each_1analog_1state(JNIEnv* env, jobject obj, jint a0, jint a1, jint a2, jint a3, jint a4, jint a5, jint a6, jint a7)
{
#ifdef CBC
	set_each_analog_state(a0, a1, a2, a3, a4, a5, a6, a7);
#else
	printf("Java_cbccore_low_Sensor_set_1each_1analog_1state stub\n");
	return 0;
#endif
}

/*
 * Class:     cbccore_low_Sensor
 * Method:    analog10
 * Signature: (I)I
 */
JNIEXPORT jint Java_cbccore_low_Sensor_analog10(JNIEnv* env, jobject obj, jint port)
{
#ifdef CBC
	return analog10(port);
#else
	printf("Java_cbccore_low_Sensor_analog10 stub\n");
	return 0;
#endif
}

/*
 * Class:     cbccore_low_Sensor
 * Method:    analog
 * Signature: (I)I
 */
JNIEXPORT jint Java_cbccore_low_Sensor_analog(JNIEnv* env, jobject obj, jint port)
{
#ifdef CBC
	return analog(port);
#else
	printf("Java_cbccore_low_Sensor_analog stub\n");
	return 0;
#endif
}

/*
 * Class:     cbccore_low_Sensor
 * Method:    accel_x
 * Signature: ()I
 */
JNIEXPORT jint Java_cbccore_low_Sensor_accel_1x(JNIEnv* env, jobject obj)
{
#ifdef CBC
	return accel_x();
#else
	printf("Java_cbccore_low_Sensor_accel_1x stub\n");
	return 0;
#endif
}

/*
 * Class:     cbccore_low_Sensor
 * Method:    accel_y
 * Signature: ()I
 */
JNIEXPORT jint Java_cbccore_low_Sensor_accel_1y(JNIEnv* env, jobject obj)
{
#ifdef CBC
	return accel_y();
#else
	printf("Java_cbccore_low_Sensor_accel_1y stub\n");
	return 0;
#endif
}

/*
 * Class:     cbccore_low_Sensor
 * Method:    accel_z
 * Signature: ()I
 */
JNIEXPORT jint Java_cbccore_low_Sensor_accel_1z(JNIEnv* env, jobject obj)
{
#ifdef CBC
	return accel_z();
#else
	printf("Java_cbccore_low_Sensor_accel_1z stub\n");
	return 0;
#endif
}

/*
 * Class:     cbccore_low_Sensor
 * Method:    sonar
 * Signature: (I)I
 */
JNIEXPORT jint Java_cbccore_low_Sensor_sonar(JNIEnv* env, jobject obj, jint port)
{
#ifdef CBC
	return sonar(port);
#else
	printf("Java_cbccore_low_Sensor_sonar stub\n");
	return 0;
#endif
}

/*
 * Class:     cbccore_low_Sensor
 * Method:    sonar_inches
 * Signature: (I)I
 */
JNIEXPORT jint Java_cbccore_low_Sensor_sonar_1inches(JNIEnv* env, jobject obj, jint port)
{
#ifdef CBC
	return sonar_inches(port);
#else
	printf("Java_cbccore_low_Sensor_sonar_1inches stub\n");
	return 0;
#endif
}

