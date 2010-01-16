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

package cbccore.sensors.camera;

/**
 * 
 * @author Braden McDorman
 *
 */

public class Channel {
	private cbccore.low.Camera lowCamera;
	private int ch = 0;
	public Channel(cbccore.low.Camera lowCamera, int ch) {
		this.lowCamera = lowCamera;
		this.ch = ch;
	}
	public int getBlobCount() {
		return lowCamera.track_count(ch);
	}
	public Blob getBlob(int i) {
		return new Blob(this, i);
	}
	public int getChannelID() {
		return ch;
	}
}
