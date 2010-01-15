#include "cbc_Input.h"
#include <stdio.h>
#include <cbc.h>

/*
 * CBC_Input JNI Wrapper Module
 * By: Braden McDorman
 * License: GNU GPLv3
 */

/*
 * Class:     CBC_Input
 * Method:    up_button
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_cbccore_low_Input_up_1button(JNIEnv* env, jobject obj)
{
#ifdef CBC
    return up_button();
#else
    printf("Java_cbccore_low_Input_up_1button stub\n");
    return -1;
#endif
}

/*
 * Class:     CBC_Input
 * Method:    down_button
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_cbccore_low_Input_down_1button(JNIEnv* env, jobject obj)
{
#ifdef CBC
    return down_button();
#else
    printf("Java_cbccore_low_Input_down_1button stub\n");
    return -1;
#endif
}

/*
 * Class:     CBC_Input
 * Method:    left_button
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_cbccore_low_Input_left_1button(JNIEnv* env, jobject obj)
{
#ifdef CBC
    return left_button();
#else
    printf("Java_cbccore_low_Input_left_1button stub\n");
    return -1;
#endif
}

/*
 * Class:     CBC_Input
 * Method:    right_button
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_cbccore_low_Input_right_1button(JNIEnv* env, jobject obj)
{
#ifdef CBC
    return right_button();
#else
    printf("Java_cbccore_low_Input_right_1button stub\n");
    return -1;
#endif
}

/*
 * Class:     CBC_Input
 * Method:    a_button
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_cbccore_low_Input_a_1button(JNIEnv* env, jobject obj)
{
#ifdef CBC
    return a_button();
#else
    printf("Java_cbccore_low_Input_a_1button stub\n");
    return -1;
#endif
}

/*
 * Class:     CBC_Input
 * Method:    b_button
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_cbccore_low_Input_b_1button(JNIEnv* env, jobject obj)
{
#ifdef CBC
    return b_button();
#else
    printf("Java_cbccore_low_Input_b_1button stub\n");
    return -1;
#endif
}

/*
 * Class:     CBC_Input
 * Method:    black_button
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_cbccore_low_Input_black_1button(JNIEnv* env, jobject obj)
{
#ifdef CBC
    return black_button();
#else
    printf("Java_cbccore_low_Input_black_1button stub\n");
    return -1;
#endif
}
