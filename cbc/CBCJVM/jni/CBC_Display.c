#include "cbc_Display.h"
#include <stdio.h>
#include <cbc.h>

/*
 * Class:     Display
 * Method:    display_clear
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_cbccore_low_Display_display_1clear(JNIEnv* env, jobject obj)
{
#ifdef CBC
	display_clear();
#else
	printf("Java_cbccore_low_Display_display_1clear stub\n");
#endif
}

/*
 * Class:     Display
 * Method:    cbc_display_clear
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_cbccore_low_Display_cbc_1display_1clear(JNIEnv* env, jobject obj)
{
#ifdef CBC
	cbc_display_clear();
#else
	printf("Java_cbccore_low_Display_cbc_1display_1clear stub\n");
#endif
}
