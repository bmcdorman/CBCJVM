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

package cbccore.low;

/**
 * Stuff like clearing the screen. Last I checked this stuff is broken, so just use something like
 * System.out.print("\n\n\n\n\n\n\n\n\n");
 * 
 * @author Braden McDorman
 */

public class Display {
	
	/**
	 * Clears display and pust cursor in upper left
	 */
	public native void display_clear();
	
	
	
	/**
	 * Clear the CBC display
	 */
	public native void cbc_display_clear(); /*Clears console display on CBC*/
}
