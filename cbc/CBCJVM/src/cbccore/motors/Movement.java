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

package cbccore.motors;

/**
 * 
 * @author Braden McDorman
 *
 */

public class Movement {
	private Direction d;
	private int speed;
	private int distance;
	public Movement(Direction d, int speed, int distance) {
		this.d = d;
		this.speed = speed;
		this.distance = distance;
	}
	public int getDistance() {
		return distance;
	}
	public int getSpeed() {
		return speed;
	}
	public Direction getDirection() {
		return d;
	}
}
