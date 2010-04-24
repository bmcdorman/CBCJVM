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

package cbccore.sensors.vision;

/**
 * Provides an API for doing item-specific calculations/things with a blob. Acts
 * as a wrapper for a blob item, as to allow a tiered api. This class requires
 * more information about an object than Blob. May eventually extend Blob.
 *
 * @author  Benjamin Woodruff
 * @see     Blob
 * @see     Camera
 */

public class BlobItem {
	
	double diameterAtMeter; //found via sqrt(track_size(ch, index))
	Blob blob;
	
	public BlobItem(Blob blob, double diameterAtMeter) {
		this.blob = blob;
		this.diameterAtMeter = diameterAtMeter;
	}
	
	public Blob getBlob() {
		return blob;
	}
	
	/**
	 * Gets an appoximated distance, given <code>blob.getSize()</code>
	 * 
	 * @return   The distance to the item (blob) in cm
	 */
	public double getDistance() {
		return Math.sqrt((double)blob.getSize())/diameterAtMeter*100.;
	}
}
