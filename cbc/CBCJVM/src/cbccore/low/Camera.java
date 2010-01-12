package cbccore.low;

public class Camera {
	public native void track_init();
	// Use            
	public native int track_is_new_data_available(); 
	// to determine if tracking data is available which is newer than the data processed by the last call to track_update().                                                                                                                      
	// Use 
	public native void track_update(); 
	// to process tracking data for a new frame and make it available for retrieval by the following calls.

	// Use 
	public native int track_get_frame(); 
	// to return value is the frame number used to generate the tracking data.

	// Use 
	public native int track_count(int ch); 
	// to return the number of blobs available for the channel ch, which is a color channel numbered 0 through 3.

	// Use the following functions of the form 
	// int track_property(int ch, int i)       
	//to return the value of a given property for the blob from channel ch (range 0-2), index i (range 0 to track_count(ch)-1). Fill in track_property from one of the following:                                                                 

	// Gets the number of pixels in the blob.
	public native int track_size(int ch, int i);           

	//  (note: 0,0 is the upper left; 159x119 is the lower right)
	// Gets the pixel x coordinate of the centroid of the blob   
	public native int track_x(int ch, int i);                                  

	// Gets the pixel y coordinate of the centroid of the blob
	public native int track_y(int ch, int i);                               
	                                                          
	// Gets the confidence for seeing the blob as a percentage of the blob pixel area/bounding box area (range 0-100, low numbers bad, high numbers good)                                                                                         
	public native int track_confidence(int ch, int i);                                                                                   

	// Gets the pixel x coordinate of the leftmost pixel in the blob
	public native int track_bbox_left(int ch, int i);                             

	// Gets the pixel x coordinate of the rightmost pixel in the blob
	public native int track_bbox_right(int ch, int i);                             

	// Gets the pixel y coordinate of the topmost pixel in the blob
	public native int track_bbox_top(int ch, int i);                             

	// Gets the pixel y coordinate of the bottommost pixel in the blob
	public native int track_bbox_bottom(int ch, int i);                             

	// Gets the pixel x width of the bounding box of the blob. This is equivalent to track_bbox_right - track_bbox_left + 1
	public native int track_bbox_width(int ch, int i);                                                                                   

	// Gets the pixel y height of the bounding box of the blob. This is equivalent to track_bbox_bottom - track_bbox_top + 1                                                                                                                      
	public native int track_bbox_height(int ch, int i);                                                                                  

	// Gets the angle in radians of the major axis of the blob.
	// Zero is horizontal and when the left end is higher than the right end the angle will be positive.
	// The range is -PI/2 to +PI/2.
	public native float track_angle(int ch, int i);

	// Gets the length in pixels of the major and minor axes of the bounding ellipse
	public native int track_major_axis(int ch, int i);
	public native int track_minor_axis(int ch, int i);

	// Return the timestamp (in milliseconds) of the current frame
	public native int track_capture_time();

	// Return the timestamp (in milliseconds) of the previous frame, as captured by
	// vision hardware.  This is not necessarily the same as track_capture_time
	// from the prevous track_update in the case where track_update was called less
	// quickly than frames were captured and thus skipped one or more frame captures
	public native int track_previous_capture_time();
	
}
