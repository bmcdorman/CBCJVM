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

import cbccore.sensors.vision.Blob;
import java.util.AbstractList;
import java.util.ListIterator;
import java.util.Iterator;

/**
 * Todo: Document this.
 *
 * @author  Benjamin Woodruff
 * @see     Camera
 */

public class BlobList extends AbstractList<cbccore.sensors.vision.Blob> {
	private Camera camera;
	private int channel;
	
	public BlobList(Camera camera, int channel) {
		super();
		this.camera = camera;
		this.channel = channel;
	}
	
	public cbccore.sensors.vision.Blob get(int index) {
		return camera.getBlob(channel, index);
	}
	
	public int size() {
		return camera.getAvailableBlobLength(channel);
	}
	
	/*
	@SuppressWarnings("unchecked")
	public Iterator<Blob> iterator() {
		return (Iterator<Blob>)super.iterator();
	}
	
	@SuppressWarnings("unchecked")
	public ListIterator<Blob> listIterator() {
		return (ListIterator<Blob>)super.listIterator();
	}
	
	@SuppressWarnings("unchecked")
	public ListIterator<Blob> listIterator(int index) {
		return (ListIterator<Blob>)super.listIterator(index);
	}*/
}
