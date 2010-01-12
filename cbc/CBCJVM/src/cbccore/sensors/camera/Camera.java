package cbccore.sensors.camera;

import cbccore.Device;

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
