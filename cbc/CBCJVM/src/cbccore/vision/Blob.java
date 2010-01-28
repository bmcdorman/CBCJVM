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

/**
 * Todo: Document this.
 *
 * @author  Benjamin Woodruff
 * @see     Camera
 */

public class Blob {
	
	private Camera camera;
	private int x;
	private int y;
	private float angle;
	private int majorAxis;
	private int minorAxis;
	private int size;
	private int bboxBottom;
	private int bboxTop;
	private int bboxLeft;
	private int bboxRight;
	private int bboxWidth;
	private int bboxHeight;
	private int confidence;
	private int channel;
	private int frame;
	
	public Blob(Camera camera, int ch, int index) {
		this.camera = camera;
		this.channel = ch;
		
		updateProperties(index);
	}
	
	/**
	 * Finds the most probable continuation of the blob
	 * 
	 * @return   true if successful, false if not
	 */
	public boolean update() {
		if(camera.getFrameNumber() == frame) { return false; }
		int bestVal = Integer.MAX_VALUE;
		int bestIndex = -1;
		
		camera.addUpdateLock();
		for(int i = 0; i < camera.getAvailableBlobLength(channel); ++i) {
			int offset =
				Math.abs(camera.__getBlobX(channel, i) - getX()) +
				Math.abs(camera.__getBlobY(channel, i) - getY()) +
				//angle handled by X
				Math.abs(camera.__getBlobMajorAxis(channel, i) - getMajorAxis()) +
				Math.abs(camera.__getBlobMinorAxis(channel, i) - getMinorAxis()) +
				//skipping size, already handled my major and minor axes
				//bbox left, right, top, bottom handled by x, y, width, and height
				Math.abs(camera.__getBlobWidth(channel, i) - getWidth()) +
				Math.abs(camera.__getBlobHeight(channel, i) - getHeight()) +
				Math.abs(camera.__getBlobConfidence(channel, i) - getConfindence());
			if(offset < bestVal) {
				bestVal = offset;
				bestIndex = i;
			}
		}
		
		updateProperties(bestIndex);
		
		camera.removeUpdateLock();
		return true;
	}
	
	
	
	
	/**
	 * A helper method for updating or changing a blob's data to a new index
	 * 
	 * @param  index  The index of the new blob data
	 * @see    #update
	 */
	protected void updateProperties(int index) {
		frame = camera.getFrameNumber();
		x = camera.__getBlobX(channel, index);
		y = camera.__getBlobY(channel, index);
		angle = camera.__getBlobAngle(channel, index);
		majorAxis = camera.__getBlobMajorAxis(channel, index);
		minorAxis = camera.__getBlobMinorAxis(channel, index);
		size = camera.__getBlobSize(channel, index);
		bboxBottom = camera.__getBlobBboxBottom(channel, index);
		bboxTop = camera.__getBlobBboxTop(channel, index);
		bboxLeft = camera.__getBlobBboxLeft(channel, index);
		bboxRight = camera.__getBlobBboxRight(channel, index);
		bboxWidth = camera.__getBlobWidth(channel, index);
		bboxHeight = camera.__getBlobHeight(channel, index);
		confidence = camera.__getBlobConfidence(channel, index);
	}
	
	
	
	
	
	
	
	
	//***********************
	//****  Accessors  ******
	//***********************
	
	
	
	public int getX() {
		return x;
	}
	
	
	
	public int getY() {
		return y;
	}
	
	
	
	public float getAngle() {
		return angle;
	}
	
	
	
	public int getMajorAxis() {
		return majorAxis;
	}
	
	
	
	public int getMinorAxis() {
		return minorAxis;
	}
	
	
	
	public int getSize() {
		return size;
	}
	
	
	
	public int getBboxBottom() {
		return bboxBottom;
	}
	
	
	
	public int getBboxTop() {
		return bboxTop;
	}
	
	
	
	public int getBboxLeft() {
		return bboxLeft;
	}
	
	
	
	public int getBboxRight() {
		return bboxRight;
	}
	
	
	
	public int getWidth() {
		return bboxWidth;
	}
	
	
	
	public int getHeight() {
		return bboxHeight;
	}
	
	
	
	public int getConfindence() {
		return confidence;
	}
	
	
	
	public int getChannel() {
		return channel;
	}
}
