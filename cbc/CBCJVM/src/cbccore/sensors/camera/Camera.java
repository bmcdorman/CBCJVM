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

public class Camera {
	private cbccore.low.Camera lowCamera = Device.getLowCameraController();
	public Camera() {
		lowCamera.track_init();
	}      
	public void update() {
		lowCamera.track_update();
	}
	public boolean dataAvailable() {
		return lowCamera.track_is_new_data_available() == 1 ? true : false;
	}
	public int getFrameNumber() {
		return lowCamera.track_get_frame();
	}
	public int captureTime() {
		return lowCamera.track_capture_time();
	}
	public int previousCaptureTime() {
		return lowCamera.track_previous_capture_time();
	}
}
