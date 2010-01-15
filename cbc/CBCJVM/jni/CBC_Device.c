#include "cbc_Device.h"
#include <stdio.h>
#include <cbc.h>

/*
 * CBC_Device JNI Wrapper Module
 * By: Braden McDorman
 * License: GNU GPLv3
 */

/*
 * Class:     Device
 * Method:    power_level
 * Signature: ()F
 */
JNIEXPORT jfloat JNICALL Java_cbc_Device_power_1level(JNIEnv* env, jobject obj)
{
#ifdef CBC
	return power_level();
#else
	printf("Java_cbc_Device_power_1level stub\n");
	return -1.0f;
#endif
}
