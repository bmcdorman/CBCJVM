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

package cbccore.interaction;

/**
 * 
 * @author Braden McDorman
 *
 */

public class InteractionProtocol {
	public enum Command
	{
		STOP_PROGRAM,
	}
	public InteractionProtocol() {
		String data = "System.out.println(\"Hello World!\");";
		System.out.println(getMethod(data));
		String[] args = getArgs(data);
		for(int i = 0; i < args.length; ++i) {
			System.out.println(args[i]);
		}
	}
	public static void main(String[] args) {
		new InteractionProtocol();
	}
	public String getMethod(String data) {
		String method = data.substring(0, data.indexOf('('));
		return method;
	}
	public String[] getArgs(String data) {
		String args = data.substring(data.indexOf('(') + 1);
		String[] split = args.split(",");
		split[split.length - 1] = split[split.length - 1].substring(0, split[split.length - 1].indexOf(')'));
		return split;
	}
}
