package cbccore.low.simulator;

import cbccore.low.Display;
import cbccore.CBCSimulator;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
 
import javax.swing.JTextArea;
import javax.swing.text.JTextComponent;

public class SimulatedDisplay extends Display {
	
	protected CBCSimulator cbc;
	private PrintStream out;
	private JTextArea textBox;
	
	public SimulatedDisplay(CBCSimulator c) {
		cbc = c;
		textBox = new JTextArea("in ./cbc: file exsists\n");
		textBox.setLineWrap(true);
		out = new PrintStream(new TextAreaOutputStream(textBox, TextAreaOutputStream.DEFAULT_BUFFER_SIZE));
	}
	
	public void display_clear() { /* Clears display and pust cursor in upper left*/
		for(int i = 0; i < 50; ++i) System.out.println("");
	}
	
	public void cbc_display_clear() { /*Clears console display on CBC*/
		display_clear();
	}
	
	public PrintStream getPrintStream() {
		return out;
	}
	
	public JTextArea getTextBox() {
		return textBox;
	}
}

/*TextAreaOutputStream.java
======================
 * Created on Mar 13, 2005 by @author Tom Jacobs
 * stolen from: http://forums.sun.com/thread.jspa?threadID=709187&messageID=4105977#4105977
 */
 
class TextAreaOutputStream extends OutputStream {
	public static final int DEFAULT_BUFFER_SIZE = 1;
	
	JTextArea mText;
	byte mBuf[];
	int mLocation;
	public TextAreaOutputStream(JTextArea component) {
		this(component, DEFAULT_BUFFER_SIZE);
	}
 
	public TextAreaOutputStream(JTextArea component, int bufferSize) {
		mText = component;
		if (bufferSize < 1) bufferSize = 1;
		mBuf = new byte[bufferSize];
		mLocation = 0;
	}
	
	//@Override
	public void write(int arg0) throws IOException {
		//System.err.println("arg = "  + (char) arg0);
		mBuf[mLocation++] = (byte)arg0;
		if (mLocation == mBuf.length) {
			flush();
		}
	}
	
	public void flush() {
		mText.append(new String(mBuf, 0, mLocation));
		mLocation = 0;
		try {
			Thread.sleep(1);
		}
		catch (Exception ex) {}
	}
}
