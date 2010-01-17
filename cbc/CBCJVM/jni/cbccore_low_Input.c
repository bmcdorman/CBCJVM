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

#include "cbccore_low_Input.h"
#include <stdio.h>
#include <cbc.h>

/*
 * cbccore_low_Input JNI Wrapper Module
 * By: Braden McDorman
 * License: GNU GPLv3
 */

/*
 * Class:     cbccore_low_Input
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
 * Class:     cbccore_low_Input
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
 * Class:     cbccore_low_Input
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
 * Class:     cbccore_low_Input
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
 * Class:     cbccore_low_Input
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
 * Class:     cbccore_low_Input
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
 * Class:     cbccore_low_Input
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

