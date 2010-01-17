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

#include "cbccore_low_Sound.h"
#include <stdio.h>
#include <cbc.h>

/*
 * cbccore_low_Sound JNI Wrapper Module
 * By: Braden McDorman
 * License: GNU GPLv3
 */

/*
 * Class:     Sound
 * Method:    tone
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_cbccore_low_Sound_tone(JNIEnv* env, jobject obj, jint frequency, jint duration)
{
#ifdef CBC
	tone(frequency, duration);
#else
	printf("Java_cbccore_low_Sound_tone stub\n");
	return 0;
#endif
}

/*
 * Class:     Sound
 * Method:    beep
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_cbccore_low_Sound_beep(JNIEnv* env, jobject obj)
{
#ifdef CBC
	beep();
#else
	printf("Java_cbccore_low_Sound_beep stub\n");
	return 0;
#endif
}

