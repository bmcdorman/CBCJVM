#include "cbc_Sound.h"
#include <stdio.h>
#include <cbc.h>

/*
 * CBC_Sound JNI Wrapper Module
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

