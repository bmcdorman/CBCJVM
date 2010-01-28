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

import java.util.AbstractList;

/**
 * Todo: Document this.
 *
 * @author  Benjamin Woodruff
 * @see     Camera
 */

public class BlobList<Blob> extends AbstractList {
	private Camera camera;
	private int channel;
	
	public BlobList(Camera camera, int channel) {
		super();
		this.camera = camera;
		this.channel = channel;
	}
	
	public cbccore.vision.Blob get(int index) {
		return camera.getBlob(channel, index);
	}
	
	public int size() {
		return camera.getAvailableBlobLength(channel);
	}
}
