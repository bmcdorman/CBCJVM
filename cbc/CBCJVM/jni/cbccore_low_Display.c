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

#include "cbccore_low_Display.h"
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
 * Method:    cbccore_low_Display_clear
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_cbccore_low_Display_cbc_1display_1clear(JNIEnv* env, jobject obj)
{
#ifdef CBC
	cbccore_low_Display_clear();
#else
	printf("Java_cbccore_low_Display_cbc_1display_1clear stub\n");
#endif
}

