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

#include "cbc_Create.h"
#include <cbc.h>
#include <stdio.h>

/*
 * CBC_Create JNI Wrapper Module
 * By: Tommy MacWilliam (Bugfixes by Benjamin Woodruff)
 * License: GNU GPLv3
 */

 /*
 * Class:     cbc_Create
 * Method:    create_connect
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_cbc_Create_create_1connect(JNIEnv *env, jobject obj)
{
#ifdef CBC
    return create_connect();
#else
   	printf("Java_cbc_Create_create_1connect stub\n");
   	return -1;
#endif
}

/*
 * Class:     cbc_Create
 * Method:    create_disconnect
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_cbc_Create_create_1disconnect(JNIEnv *env, jobject obj)
{
#ifdef CBC
    create_disconnect();
#else
    printf("Java_cbc_Create_create_1disconnect stub\n");
#endif
}

/*
 * Class:     cbc_Create
 * Method:    create_start
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_cbc_Create_create_1start(JNIEnv *env, jobject obj)
{
#ifdef CBC
    create_start();
#else
    printf("Java_cbc_Create_create_1start stub\n");
#endif
}

/*
 * Class:     cbc_Create
 * Method:    create_passive
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_cbc_Create_create_1passive(JNIEnv *env, jobject obj)
{
#ifdef CBC
    create_passive();
#else
    printf("Java_cbc_Create_create_1passive stub\n");
#endif
}

/*
 * Class:     cbc_Create
 * Method:    create_safe
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_cbc_Create_create_1safe(JNIEnv *env, jobject obj)
{
#ifdef CBC
    create_safe();
#else
    printf("Java_cbc_Create_create_1safe stub\n");
#endif
}

/*
 * Class:     cbc_Create
 * Method:    create_full
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_cbc_Create_create_1full(JNIEnv *env, jobject obj)
{
#ifdef CBC
    create_full();
#else
    printf("Java_cbc_Create_create_1full stub\n");
#endif
}

/*
 * Class:     cbc_Create
 * Method:    create_spot
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_cbc_Create_create_1spot(JNIEnv *env, jobject obj)
{
#ifdef CBC
    create_spot();
#else
    printf("Java_cbc_Create_create_1spot stub\n");
#endif
}

/*
 * Class:     cbc_Create
 * Method:    create_cover
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_cbc_Create_create_1cover(JNIEnv *env, jobject obj)
{
#ifdef CBC
    create_cover();
#else
    printf("Java_cbc_Create_create_1cover stub\n");
#endif
}

/*
 * Class:     cbc_Create
 * Method:    create_demo
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_cbc_Create_create_1demo(JNIEnv *env, jobject obj, jint d)
{
#ifdef CBC
    create_demo(d);
#else
    printf("Java_cbc_Create_create_demo1 stub\n");
#endif
}

/*
 * Class:     cbc_Create
 * Method:    create_cover_dock
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_cbc_Create_create_1cover_1dock(JNIEnv *env, jobject obj)
{
#ifdef CBC
    create_cover_dock();
#else
    printf("Java_cbc_Create_create_1cover_1dock stub\n");
#endif
}

/*
 * Class:     cbc_Create
 * Method:    create_mode
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_cbc_Create_create_1mode(JNIEnv *env, jobject obj)
{
#ifdef CBC
    return create_mode();
#else
    printf("Java_cbc_Create_create_1mode stub\n");
    return -1;
#endif
}

/*
 * Class:     cbc_Create
 * Method:    create_sensor_update
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_cbc_Create_create_1sensor_1update(JNIEnv *env, jobject obj)
{
#ifdef CBC
    return create_sensor_update();
#else
    printf("Java_cbc_Create_1sensor_1update stub\n");
    return -1;
#endif
}

/*
 * Class:     cbc_Create
 * Method:    create_wall
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_cbc_Create_create_1wall(JNIEnv *env, jobject obj)
{
#ifdef CBC
    return create_wall();
#else
    printf("Java_cbc_Create_1wall stub\n");
    return -1;
#endif
}

/*
 * Class:     cbc_Create
 * Method:    create_buttons
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_cbc_Create_create_1buttons(JNIEnv *env, jobject obj)
{
#ifdef CBC
    return create_buttons();
#else
    printf("Java_cbc_Create_create_1buttons stub\n");
    return -1;
#endif
}

/*
 * Class:     cbc_Create
 * Method:    create_bumpdrop
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_cbc_Create_create_1bumpdrop(JNIEnv *env, jobject obj)
{
#ifdef CBC
    return create_bumpdrop();
#else
    printf("Java_cbc_Create_create_1bumpdrop stub\n");
    return -1;
#endif
}

/*
 * Class:     cbc_Create
 * Method:    create_cliffs
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_cbc_Create_create_1cliffs(JNIEnv *env, jobject obj)
{
#ifdef CBC
    return create_cliffs();
#else
    printf("Java_cbc_Create_create_1cliffs stub\n");
    return -1;
#endif
}

/*
 * Class:     cbc_Create
 * Method:    create_angle
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_cbc_Create_create_1angle(JNIEnv *env, jobject obj)
{
#ifdef CBC
    return create_angle();
#else
    printf("Java_cbc_Create_create_1angle stub\n");
    return -1;
#endif
}

/*
 * Class:     cbc_Create
 * Method:    create_distance
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_cbc_Create_create_1distance(JNIEnv *env, jobject obj)
{
#ifdef CBC
    return create_distance();
#else
    printf("Java_cbc_Create_create_1distance stub\n");
    return -1;
#endif
}

/*
 * Class:     cbc_Create
 * Method:    create_velocity
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_cbc_Create_create_1velocity(JNIEnv *env, jobject obj)
{
#ifdef CBC
    return create_velocity();
#else
    printf("Java_cbc_Create_create_1velocity stub\n");
#endif
}

/*
 * Class:     cbc_Create
 * Method:    create_read_IR
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_cbc_Create_create_1read_1IR(JNIEnv *env, jobject obj)
{
#ifdef CBC
    return create_read_IR();
#else
    printf("Java_cbc_Create_create_1read_1IR stub\n");
    return -1;
#endif
}

/*
 * Class:     cbc_Create
 * Method:    create_overcurrents
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_cbc_Create_create_1overcurrents(JNIEnv *env, jobject obj)
{
#ifdef CBC
    return create_overcurrents();
#else
    printf("Java_cbc_Create_create_1overcurrents stub\n");
    return -1;
#endif
}

/*
 * Class:     cbc_Create
 * Method:    create_battery_charge
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_cbc_Create_create_1battery_1charge(JNIEnv *env, jobject obj)
{
#ifdef CBC
    return create_battery_charge();
#else
    printf("Java_cbc_Create_create_1battry_1charge stub\n");
    return -1;
#endif
}

/*
 * Class:     cbc_Create
 * Method:    create_cargo_bay_inputs
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_cbc_Create_create_1cargo_1bay_1inputs(JNIEnv *env, jobject obj)
{
#ifdef CBC
	return create_cargo_bay_inputs();
#else
    printf("Java_cbc_Create_create_1cargo_1bay_1inputs stub\n");
    return -1;
#endif
}

/*
 * Class:     cbc_Create
 * Method:    create_stop
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_cbc_Create_create_1stop(JNIEnv *env, jobject obj)
{
#ifdef CBC
    create_stop();
#else
    printf("Java_cbc_Create_create_1stop stub\n");
#endif
}

/*
 * Class:     cbc_Create
 * Method:    create_drive
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_cbc_Create_create_1drive(JNIEnv *env, jobject obj, jint speed, jint radius)
{
#ifdef CBC
    create_drive(speed, radius);
#else
    printf("Java_cbc_Create_create_1drive stub\n");
#endif
}


/*
 * Class:     cbc_Create
 * Method:    create_drive_straight
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_cbc_Create_create_1drive_1straight(JNIEnv *env, jobject obj, jint speed)
{
#ifdef CBC
    create_drive_straight(speed);
#else
    printf("Java_cbc_Create_create_1drive_1straight stub\n");
#endif
}

/*
 * Class:     cbc_Create
 * Method:    create_spin_CW
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_cbc_Create_create_1spin_1CW(JNIEnv *env, jobject obj, jint speed)
{
#ifdef CBC
    create_spin_CW(speed);
#else
    printf("Java_cbc_Create_create_1spin_1CW stub\n");
#endif
}

/*
 * Class:     cbc_Create
 * Method:    create_spin_CCW
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_cbc_Create_create_1spin_1CCW(JNIEnv *env, jobject obj, jint speed)
{
#ifdef CBC
    create_spin_CCW(speed);
#else
    printf("Java_cbc_Create_create_1spin_1CCW stub\n");
#endif
}

/*
 * Class:     cbc_Create
 * Method:    create_drive_direct
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_cbc_Create_create_1drive_1direct(JNIEnv *env, jobject obj, jint r_speed, jint l_speed)
{
#ifdef CBC
    create_drive_direct(r_speed, l_speed);
#else
    printf("Java_cbc_Create_create_1drive_1direct stub\n");
#endif
}

/*
 * Class:     cbc_Create
 * Method:    create_spin_block
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_cbc_Create_create_1spin_1block(JNIEnv *env, jobject obj, jint speed, jint angle)
{
#ifdef CBC
    return create_spin_block(speed, angle);
#else
    printf("Java_cbc_Create_create_1spin_1block stub\n");
    return -1;
#endif
}

/*
 * Class:     cbc_Create
 * Method:    create_advance_led
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_cbc_Create_create_1advance_1led(JNIEnv *env, jobject obj, jint on)
{
#ifdef CBC
    create_advance_led(on);
#else
    printf("Java_cbc_Create_create_1advance_1led stub\n");
#endif
}

/*
 * Class:     cbc_Create
 * Method:    create_play_led
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_cbc_Create_create_1play_1led(JNIEnv *env, jobject obj, jint on)
{
#ifdef CBC
    create_play_led(on);
#else
    printf("Java_cbc_Create_create_1play_1led stub\n");
#endif
}

/*
 * Class:     cbc_Create
 * Method:    create_power_led
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_cbc_Create_create_1power_1led(JNIEnv *env, jobject obj, jint color, jint brightness)
{
#ifdef CBC
    create_power_led(color, brightness);
#else
    printf("Java_cbc_Create_create_1power_1led stub\n");
#endif
}

/*
 * Class:     cbc_Create
 * Method:    create_digital_output
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_cbc_Create_create_1digital_1output(JNIEnv *env, jobject obj, jint bits)
{
#ifdef CBC
    create_digital_output(bits);
#else
    printf("Java_cbc_Create_1digital_1output stub\n");
#endif
}

/*
 * Class:     cbc_Create
 * Method:    create_pwm_low_side_drivers
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_cbc_Create_create_1pwm_1low_1side_1drivers(JNIEnv *env, jobject obj, jint pwm2, jint pwm1, jint pwm0)
{
#ifdef CBC
    create_pwm_low_side_drivers(pwm2, pwm1, pwm0);
#else
    printf("Java_cbc_Create_create_1pwm_1low_1side_1drivers stub\n");
#endif
}

/*
 * Class:     cbc_Create
 * Method:    create_low_side_drivers
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_cbc_Create_create_1low_1side_1drivers(JNIEnv *env, jobject obj, jint pwm2, jint pwm1, jint pwm0)
{
#ifdef CBC
    create_low_side_drivers(pwm2, pwm1, pwm0);
#else
    printf("Java_cbc_Create_create_1low_1side_1drivers stub\n");
#endif
}

/*
 * Class:     cbc_Create
 * Method:    create_load_song
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_cbc_Create_create_1load_1song(JNIEnv *env, jobject obj, jint num)
{
#ifdef CBC
    create_load_song(num);
#else
    printf("Java_cbc_Create_create_1load_1song stub\n");
#endif
}

/*
 * Class:     cbc_Create
 * Method:    create_play_song
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_cbc_Create_create_1play_1song(JNIEnv *env, jobject obj, jint num)
{
#ifdef CBC
    create_play_song(num);
#else
    printf("Java_cbc_Create_create_1play_1song stub\n");
#endif
}

/*
 * Class:     cbc_Create
 * Method:    create_write_byte
 * Signature: (C)V
 */
JNIEXPORT void JNICALL Java_cbc_Create_create_1write_1byte(JNIEnv *env, jobject obj, jchar write_byte)
{
#ifdef CBC
    create_write_byte(write_byte);
#else
    printf("Java_cbc_Create_create_1write_1byte stub\n");
#endif
}

/*
 * Class:     cbc_Create
 * Method:    create_clear_serial_buffer
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_cbc_Create_create_1clear_1serial_1buffer(JNIEnv *env, jobject obj)
{
#ifdef CBC
    create_clear_serial_buffer();
#else
    printf("Java_cbc_Create_create_1clear_1serial_1buffer stub\n");
#endif
}

#ifdef __cplusplus
}
#endif

