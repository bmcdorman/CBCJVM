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
 * Direct access to the CBC-C Camera Libraries<p>
 * Documentation stolen from the KISS-C documentation
 *
 * @author  Braden McDorman
 * @see     cbccore.Device
 */

public class Camera {
	
	/**
	 * Initializes the camera the necessity of this function has been questioned
	 * (although that was back on the XBC) and thus I am unsure if it is
	 * required. But give it a try anyways. Call this first!
	 */
	public native void track_init();
	
	
	
	/**
	 * Determine if tracking data is available which is newer than the data processed by the last call to <code>track_update()</code>.
	 *
	 * @return Some value other than 0 if true (probably 1), 0 if false
	 */
	public native int track_is_new_data_available();
	
	
	
	/**
	 * Process tracking data for a new frame and make it available for retrieval by the other methods.
	 * 
	 * @see #track_is_new_data_available
	 */
	public native void track_update();
	
	
	
	/**
	 * Use to return value is the frame number used to generate the tracking data.
	 *
	 * @return  the frame number used to genenerate the tracking code
	 * @see     #track_update
	 * @see     #track_capture_time
	 */
	public native int track_get_frame();
	
	
	
	/**
	 * Use to return the number of blobs available for the channel ch, which is a color channel numbered 0 through 3.
	 * 
	 * @param  ch  from channel (range 0-2)
	 * @see        #track_init
	 * @see        #track_update
	 */
	public native int track_count(int ch);
	
	
	
	/**
	 * Gets the number of pixels in the blob.
	 * 
	 * @param  ch  from channel (range 0-2)
	 * @param  i   index (range 0 to track_count(ch)-1)
	 * @return     the number of pixels in the blob
	 * @see        #track_init
	 * @see        #track_update
	 * @see        #track_bbox_width
	 * @see        #track_bbox_height
	 */
	public native int track_size(int ch, int i);
	
	
	
	/**
	 * Gets the pixel x coordinate of the centroid of the blob<p>
	 * (note: 0,0 is the upper left; 159x119 is the lower right)
	 * 
	 * @param  ch  from channel (range 0-2)
	 * @param  i   index (range 0 to track_count(ch)-1)
	 * @return     he pixel x coordinate of the centroid of the blob
	 * @see        #track_init
	 * @see        #track_update
	 * @see        #track_y
	 * @see        #track_angle
	 */
	public native int track_x(int ch, int i);
	
	
	
	/**
	 * Gets the pixel y coordinate of the centroid of the blob<p>
	 * (note: 0,0 is the upper left; 159x119 is the lower right)
	 * 
	 * @param  ch  from channel (range 0-2)
	 * @param  i   index (range 0 to track_count(ch)-1)
	 * @return     the number of pixels in the blob.
	 * @see        #track_init
	 * @see        #track_update
	 * @see        #track_x
	 */
	public native int track_y(int ch, int i);
	
	
	
	/**
	 * Gets the confidence for seeing the blob as a percentage of the blob pixel area/bounding box area
	 * 
	 * @param  ch  from channel (range 0-2)
	 * @param  i   index (range 0 to track_count(ch)-1)
	 * @return     The confidence for seeing the blob as a percentage of the blob pixel area/bounding box area (range 0-100, low numbers bad, high numbers good)
	 * @see        #track_init
	 * @see        #track_update
	 */
	public native int track_confidence(int ch, int i);
	
	
	
	/**
	 * Gets the pixel x coordinate of the leftmost pixel in the blob
	 * 
	 * @param  ch  from channel (range 0-2)
	 * @param  i   index (range 0 to track_count(ch)-1)
	 * @return     The pixel x coordinate of the leftmost pixel in the blob.
	 * @see        #track_init
	 * @see        #track_update
	 */
	public native int track_bbox_left(int ch, int i);
	
	
	
	/**
	 * Gets the pixel x coordinate of the rightmost pixel in the blob
	 * 
	 * @param  ch  from channel (range 0-2)
	 * @param  i   index (range 0 to track_count(ch)-1)
	 * @return     The pixel x coordinate of the rightmost pixel in the blob.
	 * @see        #track_init
	 * @see        #track_update
	 */
	public native int track_bbox_right(int ch, int i);
	
	
	
	/**
	 * Gets the pixel y coordinate of the topmost pixel in the blob
	 * 
	 * @param  ch  from channel (range 0-2)
	 * @param  i   index (range 0 to track_count(ch)-1)
	 * @return     The pixel y coordinate of the topmost pixel in the blob
	 * @see        #track_init
	 * @see        #track_update
	 */
	public native int track_bbox_top(int ch, int i);
	
	
	
	/**
	 * Gets the pixel y coordinate of the bottommost pixel in the blob
	 * 
	 * @param  ch  from channel (range 0-2)
	 * @param  i   index (range 0 to track_count(ch)-1)
	 * @return     The pixel y coordinate of the bottommost pixel in the blob
	 * @see        #track_init
	 * @see        #track_update
	 */
	public native int track_bbox_bottom(int ch, int i);
	
	
	
	/**
	 * Gets the pixel x width of the bounding box of the blob. This is equivalent to track_bbox_right - track_bbox_left + 1
	 * 
	 * @param  ch  from channel (range 0-2)
	 * @param  i   index (range 0 to track_count(ch)-1)
	 * @return     The pixel x width of the bounding box of the blob. This is equivalent to track_bbox_right - track_bbox_left + 1
	 * @see        #track_init
	 * @see        #track_update
	 */
	public native int track_bbox_width(int ch, int i);
	
	
	
	/**
	 * Gets the pixel y height of the bounding box of the blob. This is equivalent to track_bbox_bottom - track_bbox_top + 1
	 * 
	 * @param  ch  from channel (range 0-2)
	 * @param  i   index (range 0 to track_count(ch)-1)
	 * @return     The pixel y height of the bounding box of the blob.
	 * @see        #track_init
	 * @see        #track_update
	 */
	public native int track_bbox_height(int ch, int i);
	
	
	
	/**
	 * Gets the angle in radians of the major axis of the blob.<p>
	 * Zero is horizontal and when the left end is higher than the right end the angle will be positive.<p>
	 * The range is -PI/2 to +PI/2.<p>
	 * If you don't know about eclipses you might start <a href="http://mathworld.wolfram.com/Ellipse.html">here</a>
	 * or ask your local math teacher about major and minor axes.
	 * 
	 * @param  ch  from channel (range 0-2)
	 * @param  i   index (range 0 to track_count(ch)-1)
	 * @return     The angle in radians of the major axis of the blob
	 * @see        #track_init
	 * @see        #track_update
	 */
	public native float track_angle(int ch, int i);
	
	
	
	/**
	 * Gets the length in pixels of the major axis of the bounding ellipse
	 * If you don't know about eclipses you might start <a href="http://mathworld.wolfram.com/Ellipse.html">here</a>
	 * or ask your local math teacher about major and minor axes.
	 * 
	 * @param  ch  from channel (range 0-2)
	 * @param  i   index (range 0 to track_count(ch)-1)
	 * @return     The length in pixels of the major and minor axes of the bounding ellipse
	 * @see        #track_init
	 * @see        #track_update
	 */
	public native int track_major_axis(int ch, int i);
	
	
	
	/**
	 * Gets the length in pixels of the minor axis of the bounding ellipse
	 * If you don't know about eclipses you might start <a href="http://mathworld.wolfram.com/Ellipse.html">here</a>
	 * or ask your local math teacher about major and minor axes.
	 * 
	 * @param  ch  from channel (range 0-2)
	 * @param  i   index (range 0 to track_count(ch)-1)
	 * @return     The length in pixels of the major and minor axes of the bounding ellipse
	 * @see        #track_init
	 * @see        #track_update
	 */
	public native int track_minor_axis(int ch, int i);
	
	
	
	/**
	 * Gets the timestamp (in milliseconds) of the current frame
	 * 
	 * @return        The timestamp (in milliseconds) of the current frame
	 * @see           #track_get_frame
	 * @see           #track_previous_capture_time
	 */
	public native int track_capture_time();
	
	/**
	 * Return the timestamp (in milliseconds) of the previous frame, as captured
	 * by vision hardware.  This is not necessarily the same as
	 * track_capture_time from the prevous track_update in the case where
	 * track_update was called less quickly than frames were captured and thus
	 * skipped one or more frame captures
	 * 
	 * @return        The timestamp (in milliseconds) of the current frame
	 * @see           #track_get_frame
	 * @see           #track_capture_time
	 */
	public native int track_previous_capture_time();

}
