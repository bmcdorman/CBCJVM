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

import cbccore.Device;

/**
 * 
 * @author Braden McDorman
 *
 */

public class Blob {
	private int x = 0;
	private int y = 0;
	private int width = 0;
	private int height = 0;
	private int bottom = 0;
	private int top = 0;
	private int left = 0;
	private int right = 0;
	private float angle = 0;
	private int minorAxis = 0;
	private int majorAxis = 0;
	private int confidence = 0;
	private int size = 0;
	private cbccore.low.Camera lowCamera = Device.getLowCameraController();
	public Blob(Channel c, int i) {
		int ch = c.getChannelID();
		x = lowCamera.track_x(ch, i);
		y = lowCamera.track_y(ch, i);
		width = lowCamera.track_bbox_width(ch, i);
		height = lowCamera.track_bbox_height(ch, i);
		bottom = lowCamera.track_bbox_bottom(ch, i);
		top = lowCamera.track_bbox_top(ch, i);
		left = lowCamera.track_bbox_left(ch, i);
		right = lowCamera.track_bbox_right(ch, i);
		angle = lowCamera.track_angle(ch, i);
		minorAxis = lowCamera.track_minor_axis(ch, i);
		majorAxis = lowCamera.track_major_axis(ch, i);
		confidence = lowCamera.track_confidence(ch, i);
		size = lowCamera.track_size(ch, i);
	}
	public int getCenterX() {
		return x;
	}
	public int getCenterY() {
		return y;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public int getBottomY() {
		return bottom;
	}
	public int getTopY() {
		return top;
	}
	public int getLeftX() {
		return left;
	}
	public int getRightX() {
		return right;
	}
	public float getAngle() {
		return angle;
	}
	public int getMinorAxis() {
		return minorAxis;
	}
	public int getMajorAxis() {
		return majorAxis;
	}
	public int getConfidence() {
		return confidence;
	}
	public int getSize() {
		return size;
	}
}
