#include "cbc_Camera.h"
#include <stdio.h>
#include <cbc.h>
#include <track.h>

/*
 * CBC_Camera JNI Wrapper Module
 * By: Braden McDorman
 * License: GNU GPLv3
 */

/*
 * Class:     cbc_Camera
 * Method:    track_init
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_cbccore_low_Camera_track_1init(JNIEnv* env, jobject obj)
{
#ifdef CBC
	track_init();
#else
	printf("Java_cbccore_low_Camera_track_1init stub\n");
#endif
}

/*
 * Class:     cbc_Camera
 * Method:    track_is_new_data_available
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_cbccore_low_Camera_track_1is_1new_1data_1available(JNIEnv* env, jobject obj)
{
#ifdef CBC
	return track_is_new_data_available();
#else
	printf("Java_cbccore_low_Camera_track_1is_1new_1data_1available stub\n");
	return -1;
#endif
}

/*
 * Class:     cbc_Camera
 * Method:    track_update
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_cbccore_low_Camera_track_1update(JNIEnv* env, jobject obj)
{
#ifdef CBC
	return track_update();
#else
	printf("Java_cbccore_low_Camera_track_1update stub\n");
#endif
}

/*
 * Class:     cbc_Camera
 * Method:    track_get_frame
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_cbccore_low_Camera_track_1get_1frame(JNIEnv* env, jobject obj)
{
#ifdef CBC
	return track_get_frame();
#else
	printf("Java_cbccore_low_Camera_track_1get_1frame stub\n");
	return -1;
#endif
}

/*
 * Class:     cbc_Camera
 * Method:    track_count
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_cbccore_low_Camera_track_1count(JNIEnv* env, jobject obj, jint ch)
{
#ifdef CBC
	return track_count(ch);
#else
	printf("Java_cbccore_low_Camera_track_1count stub\n");
	return -1;
#endif
}

/*
 * Class:     cbc_Camera
 * Method:    track_size
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_cbccore_low_Camera_track_1size(JNIEnv* env, jobject obj, jint ch, jint i)
{
#ifdef CBC
	return track_size(ch, i);
#else
	printf("Java_cbccore_low_Camera_track_1size stub\n");
	return -1;
#endif
}

/*
 * Class:     cbc_Camera
 * Method:    track_x
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_cbccore_low_Camera_track_1x(JNIEnv* env, jobject obj, jint ch, jint i)
{
#ifdef CBC
	return track_x(ch, i);
#else
	printf("Java_cbccore_low_Camera_track_1x stub\n");
	return -1;
#endif
}

/*
 * Class:     cbc_Camera
 * Method:    track_y
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_cbccore_low_Camera_track_1y(JNIEnv* env, jobject obj, jint ch, jint i)
{
#ifdef CBC
	return track_y(ch, i);
#else
	printf("Java_cbccore_low_Camera_track_1y stub\n");
	return -1;
#endif
}

/*
 * Class:     cbc_Camera
 * Method:    track_confidence
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_cbccore_low_Camera_track_1confidence(JNIEnv* env, jobject obj, jint ch, jint i)
{
#ifdef CBC
	return track_confidence(ch, i);
#else
	printf("Java_cbccore_low_Camera_track_1confidence stub\n");
	return -1;
#endif
}

/*
 * Class:     cbc_Camera
 * Method:    track_bbox_left
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_cbccore_low_Camera_track_1bbox_1left(JNIEnv* env, jobject obj, jint ch, jint i)
{
#ifdef CBC
	return track_bbox_left(ch, i);
#else
	printf("Java_cbccore_low_Camera_track_1bbox_1left stub\n");
	return -1;
#endif
}

/*
 * Class:     cbc_Camera
 * Method:    track_bbox_right
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_cbccore_low_Camera_track_1bbox_1right(JNIEnv* env, jobject obj, jint ch, jint i)
{
#ifdef CBC
	return track_bbox_right(ch, i);
#else
	printf("Java_cbccore_low_Camera_track_1bbox_1right stub\n");
	return -1;
#endif
}

/*
 * Class:     cbc_Camera
 * Method:    track_bbox_top
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_cbccore_low_Camera_track_1bbox_1top(JNIEnv* env, jobject obj, jint ch, jint i)
{
#ifdef CBC
	return track_bbox_top(ch, i);
#else
	printf("Java_cbccore_low_Camera_track_1bbox_1top stub\n");
	return -1;
#endif
}

/*
 * Class:     cbc_Camera
 * Method:    track_bbox_bottom
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_cbccore_low_Camera_track_1bbox_1bottom(JNIEnv* env, jobject obj, jint ch, jint i)
{
#ifdef CBC
	return track_bbox_bottom(ch, i);
#else
	printf("ava_cbc_Camera_track_1bbox_1bottom stub\n");
	return -1;
#endif
}

/*
 * Class:     cbc_Camera
 * Method:    track_bbox_width
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_cbccore_low_Camera_track_1bbox_1width(JNIEnv* env, jobject obj, jint ch, jint i)
{
#ifdef CBC
	return track_bbox_width(ch, i);
#else
	printf("Java_cbccore_low_Camera_track_1bbox_1width stub\n");
	return -1;
#endif
}

/*
 * Class:     cbc_Camera
 * Method:    track_bbox_height
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_cbccore_low_Camera_track_1bbox_1height(JNIEnv* env, jobject obj, jint ch, jint i)
{
#ifdef CBC
	return track_bbox_height(ch, i);
#else
	printf("Java_cbccore_low_Camera_track_1bbox_1height stub\n");
	return -1;
#endif
}

/*
 * Class:     cbc_Camera
 * Method:    track_angle
 * Signature: (II)F
 */
JNIEXPORT jfloat JNICALL Java_cbccore_low_Camera_track_1angle(JNIEnv* env, jobject obj, jint ch, jint i)
{
#ifdef CBC
	return track_angle(ch, i);
#else
	printf("Java_cbccore_low_Camera_track_1angle stub\n");
	return -1.0f;
#endif
}

/*
 * Class:     cbc_Camera
 * Method:    track_major_axis
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_cbccore_low_Camera_track_1major_1axis(JNIEnv* env, jobject obj, jint ch, jint i)
{
#ifdef CBC
	return track_major_axis(ch, i);
#else
	printf("Java_cbccore_low_Camera_track_1major_1axis stub\n");
	return -1;
#endif
}

/*
 * Class:     cbc_Camera
 * Method:    track_minor_axis
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_cbccore_low_Camera_track_1minor_1axis(JNIEnv* env, jobject obj, jint ch, jint i)
{
#ifdef CBC
	return track_minor_axis(ch, i);
#else
	printf("Java_cbccore_low_Camera_track_1minor_1axis stub\n");
	return -1;
#endif
}

/*
 * Class:     cbc_Camera
 * Method:    track_capture_time
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_cbccore_low_Camera_track_1capture_1time(JNIEnv* env, jobject obj)
{
#ifdef CBC
	return track_capture_time();
#else
	printf("JNICALL Java_cbccore_low_Camera_track_1capture_1time stub\n");
	return -1;
#endif
}

/*
 * Class:     cbc_Camera
 * Method:    track_previous_capture_time
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_cbccore_low_Camera_track_1previous_1capture_1time(JNIEnv* env, jobject obj)
{
#ifdef CBC
	return track_previous_capture_time();
#else
	printf("Java_cbccore_low_Camera_track_1previous_1capture_1time stub\n");
	return -1;
#endif
}
