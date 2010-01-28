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

package cbccore.vision;

import cbccore.Device;

/**
 * Access to the CBC-C Camera Libraries through a cleaner API<p>
 * Documentation stolen from the KISS-C documentation
 *
 * @author  Benjamin Woodruff
 * @see     cbccore.low.Camera
 */

public class Camera {
	private cbccore.low.Camera lowCamera = Device.getLowCameraController();
	private static int updateLocks = 0; //we want this to be global
	private static boolean initialized = false;
	
	public Camera() {
		if(!initialized) {
			initialized = true;
			init();
		}
	}
	
	/**
	 * Initializes the camera the necessity of this function has been questioned
	 * (although that was back on the XBC) and thus I am unsure if it is
	 * required. But give it a try anyways. Call this first!
	 */
	private static void init() {
		Device.getLowCameraController().track_init();
	}
	
	
	
	/**
	 * If there is a lock, it will keep the update method from being called in
	 * another thread (or the current thread for that matter). <u>To avoid
	 * bad locking (program freezing up), always unlock before exiting your
	 * function or calling update.</u><p>
	 * If you are not using threads, <u><b>DO NOT CALL THIS FUNCTION</b></u>. If
	 * you do not know what threading is, <u><b>DO NOT CALL THIS
	 * FUNCTION</b></u>. It is better to not call this function than to call it
	 * and mess it up. (although it is prefered that you use this function
	 * correctly) This function is so very dangerous to use incorrectly that it
	 * is hard to explain in words.<p>
	 * ALWAYS FOLLOW THIS FUNCTION WITH <code>removeUpdateLock();</code>
	 *
	 */
	public void addUpdateLock() {
		++updateLocks;
	}
	
	
	/**
	 * 
	 */
	public void removeUpdateLock() {
		--updateLocks;
	}
	
	
	
	public boolean isLocked() {
		return updateLocks > 0;
	}
	
	
	
	/**
	 * Determine if tracking data is available which is newer than the data processed by the last call to <code>update()</code>.
	 *
	 * @return True if new value is available, false otherwise
	 */
	public boolean isNewDataAvailable() {
		return lowCamera.track_is_new_data_available() != 0;
	}
	
	
	
	/**
	 * Process tracking data for a new frame and make it available for retrieval by the other methods.
	 * 
	 * @see #isNewDataAvailable
	 */
	public void update() { //we will syncronize this ourselves
		if(!isNewDataAvailable()) { return; }
		long startTime = System.currentTimeMillis();
		while(updateLocks > 0) {
			if(System.currentTimeMillis() - startTime > 10000l) {
				System.out.println("Camera has been locked for over 10 seconds. It appears that you may have called addUpdateLock without calling removeUpdateLock.");
				startTime += 5000l;
			}
			Thread.yield();
		}
		addUpdateLock();
		lowCamera.track_update();
		removeUpdateLock();
	}
	
	
	
	/**
	 * Use to return value is the frame number used to generate the tracking data.
	 *
	 * @return  the frame number used to genenerate the tracking code
	 * @see     #update
	 * @see     #getCaptureTime
	 */
	public int getFrameNumber() {
		addUpdateLock();
		int val = lowCamera.track_get_frame();
		removeUpdateLock();
		return val;
	}
	
	
	
	/**
	 * Use to return the number of blobs available for the channel ch, which is a color channel numbered 0 through 3.
	 * 
	 * @param  ch  from channel (range 0-2)
	 * @see        #init
	 * @see        #update
	 */
	public int getAvailableBlobLength(int ch) {
		addUpdateLock();
		int val = lowCamera.track_count(ch);
		removeUpdateLock();
		return val;
	}
	
	
	
	/**
	 * Gets blob at the specified index. This is essentially the same as
	 * making a new BlobList from this Camera and calling get on it.
	 * 
	 * @param  blob  the index of the desired blob
	 */
	public Blob getBlob(int ch, int blob) {
		addUpdateLock();
		Blob val = new Blob(this, ch, blob);
		removeUpdateLock();
		return val;
	}
	
	
	
	/**
	 * Don't call this function. Use Blob and BlobList classes
	 * 
	 * @see    #getBlob
	 * @see    BlobList
	 * @see    Blob
	 */
	public int __getBlobSize(int ch, int i) {
		addUpdateLock();
		int val = lowCamera.track_size(ch, i);
		removeUpdateLock();
		return val;
	}
	
	
	
	/**
	 * Don't call this function. Use Blob and BlobList classes
	 * 
	 * @see    #getBlob
	 * @see    BlobList
	 * @see    Blob
	 */
	public int __getBlobX(int ch, int i) {
		addUpdateLock();
		int val = lowCamera.track_x(ch, i);
		removeUpdateLock();
		return val;
	}
	
	
	
	/**
	 * Don't call this function. Use Blob and BlobList classes
	 * 
	 * @see    #getBlob
	 * @see    BlobList
	 * @see    Blob
	 */
	public int __getBlobY(int ch, int i) {
		addUpdateLock();
		int val = lowCamera.track_y(ch, i);
		removeUpdateLock();
		return val;
	}
	
	
	
	/**
	 * Don't call this function. Use Blob and BlobList classes
	 * 
	 * @see    #getBlob
	 * @see    BlobList
	 * @see    Blob
	 */
	public int __getBlobConfidence(int ch, int i) {
		addUpdateLock();
		int val = lowCamera.track_confidence(ch, i);
		removeUpdateLock();
		return val;
	}
	
	
	
	/**
	 * Don't call this function. Use Blob and BlobList classes
	 * 
	 * @see    #getBlob
	 * @see    BlobList
	 * @see    Blob
	 */
	public int __getBlobBboxLeft(int ch, int i) {
		addUpdateLock();
		int val = lowCamera.track_bbox_left(ch, i);
		removeUpdateLock();
		return val;
	}
	
	
	
	/**
	 * Don't call this function. Use Blob and BlobList classes
	 * 
	 * @see    #getBlob
	 * @see    BlobList
	 * @see    Blob
	 */
	public int __getBlobBboxRight(int ch, int i) {
		addUpdateLock();
		int val = lowCamera.track_bbox_right(ch, i);
		removeUpdateLock();
		return val;
	}
	
	
	
	/**
	 * Don't call this function. Use Blob and BlobList classes
	 * 
	 * @see    #getBlob
	 * @see    BlobList
	 * @see    Blob
	 */
	public int __getBlobBboxTop(int ch, int i) {
		addUpdateLock();
		int val = lowCamera.track_bbox_top(ch, i);
		removeUpdateLock();
		return val;
	}
	
	
	
	/**
	 * Don't call this function. Use Blob and BlobList classes
	 * 
	 * @see    #getBlob
	 * @see    BlobList
	 * @see    Blob
	 */
	public int __getBlobBboxBottom(int ch, int i) {
		addUpdateLock();
		int val = lowCamera.track_bbox_bottom(ch, i);
		removeUpdateLock();
		return val;
	}
	
	
	
	/**
	 * Don't call this function. Use Blob and BlobList classes
	 * 
	 * @see    #getBlob
	 * @see    BlobList
	 * @see    Blob
	 */
	public int __getBlobWidth(int ch, int i) {
		addUpdateLock();
		int val = lowCamera.track_bbox_width(ch, i);
		removeUpdateLock();
		return val;
	}
	
	
	
	/**
	 * Don't call this function. Use Blob and BlobList classes
	 * 
	 * @see    #getBlob
	 * @see    BlobList
	 * @see    Blob
	 */
	public int __getBlobHeight(int ch, int i) {
		addUpdateLock();
		int val = lowCamera.track_bbox_height(ch, i);
		removeUpdateLock();
		return val;
	}
	
	
	
	/**
	 * Don't call this function. Use Blob and BlobList classes
	 * 
	 * @see    #getBlob
	 * @see    BlobList
	 * @see    Blob
	 */
	public float __getBlobAngle(int ch, int i) {
		addUpdateLock();
		float val = lowCamera.track_angle(ch, i);
		removeUpdateLock();
		return val;
	}
	
	
	
	/**
	 * Don't call this function. Use Blob and BlobList classes
	 * 
	 * @see    #getBlob
	 * @see    BlobList
	 * @see    Blob
	 */
	public int __getBlobMajorAxis(int ch, int i) {
		addUpdateLock();
		int val = lowCamera.track_major_axis(ch, i);
		removeUpdateLock();
		return val;
	}
	
	
	
	/**
	 * Don't call this function. Use Blob and BlobList classes
	 * 
	 * @see    #getBlob
	 * @see    BlobList
	 * @see    Blob
	 */
	public int __getBlobMinorAxis(int ch, int i) {
		addUpdateLock();
		int val = lowCamera.track_minor_axis(ch, i);
		removeUpdateLock();
		return val;
	}
	
	
	
	/**
	 * Gets the timestamp (in milliseconds) of the current frame
	 * 
	 * @return        The timestamp (in milliseconds) of the current frame
	 * @see           #getFrameNumber
	 * @see           #getPreviousCaptureTime
	 */
	public int getCaptureTime() {
		addUpdateLock();
		int val = lowCamera.track_capture_time();
		removeUpdateLock();
		return val;
	}
	
	/**
	 * Return the timestamp (in milliseconds) of the previous frame, as captured
	 * by vision hardware.  This is not necessarily the same as
	 * <code>captureTime</code> from the prevous track_update in the case where
	 * <code>update</code> was called less quickly than frames were captured and
	 * thus skipped one or more frame captures.
	 * 
	 * @return        The timestamp (in milliseconds) of the current frame
	 * @see           #getFrameNumber
	 * @see           #getCaptureTime
	 */
	public int getPreviousCaptureTime() {
		addUpdateLock();
		int val = lowCamera.track_previous_capture_time();
		removeUpdateLock();
		return val;
	}
}
