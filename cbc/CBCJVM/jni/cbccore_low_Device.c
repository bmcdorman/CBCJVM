/* This file is part of CBCJVM.
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

#include "cbccore_low_Device.h"
#include <stdio.h>
#include <cbc.h>

/*
 * cbccore_low_Device JNI Wrapper Module
 * By: Braden McDorman
 * License: GNU GPLv3
 */

/*
 * Class:     Device
 * Method:    power_level
 * Signature: ()F
 */
JNIEXPORT jfloat JNICALL Java_cbccore_low_Device_power_1level(JNIEnv* env, jobject obj)
{
#ifdef CBC
	return power_level();
#else
	printf("Java_cbccore_low_Device_power_1level stub\n");
	return -1.0f;
#endif
}

