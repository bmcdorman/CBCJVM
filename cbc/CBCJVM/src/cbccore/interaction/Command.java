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

import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * 
 * @author Braden McDorman
 *
 */

public class Command {
	Method method;
	Object target;
	Object[] args;
	static final Object[] nullargs = new Object[] {};
	public Command(Object target, Method m) {
		this(target, m, nullargs);
	}
	public Command(Object target, Method method, Object[] args) {
		this.target = target;
		this.method = method;
		this.args = args;
	}
	public Object invoke() throws IllegalAccessException, InvocationTargetException {
		return method.invoke(target, args); // Use reflection to invoke the method
	}
	// Factory
	@SuppressWarnings(value = { "all" })
	public static Command parse(Object target, String text) throws IOException {
		String methodname;
		ArrayList args = new ArrayList();
		ArrayList types = new ArrayList();
		StreamTokenizer t = new StreamTokenizer(new StringReader(text));
		int c = t.nextToken();
		if (c != StreamTokenizer.TT_WORD) throw new IOException("Could not determine method name");
		methodname = t.sval;
		c = t.nextToken();
		if (c == '(') {
			for (;;) {
				c = t.nextToken();
				if (c == ')') {
					c = t.nextToken();
					if (c != ';')
						t.pushBack();
					break;
				}
				if (c == StreamTokenizer.TT_WORD) {
					if (t.sval.equals("true")) {
						args.add(Boolean.TRUE);
						types.add(boolean.class);
					} else if (t.sval.equals("false")) {
						args.add(Boolean.FALSE);
						types.add(boolean.class);
					} else {
						args.add(t.sval);
						types.add(String.class);
					}
				} else if (c == '"') {
					args.add(t.sval);
					types.add(String.class);
				} else if (c == StreamTokenizer.TT_NUMBER) {
					int i = (int) t.nval;
					if (i == t.nval) {
						args.add(new Integer(i));
						types.add(int.class);
					} else {
						args.add(new Double(t.nval));
						types.add(double.class);
					}
				} else {
					throw new IOException("Unexpected value of \"" + t.sval + "\" in arguments");
				}
				c = t.nextToken();
				if (c != ',') {
					t.pushBack();
				}
			}
		} else if (c != ';') {
			t.pushBack();
		}
		Object[] argValues = args.toArray();
		Class[] argtypes = (Class[]) types.toArray(new Class[argValues.length]);
		Method method;
		try {
			method = target.getClass().getMethod(methodname, argtypes);
		} catch (Exception e) {
			throw new IOException("No such method found, or wrong arguments");
		}
		return new Command(target, method, argValues);
	}
}
