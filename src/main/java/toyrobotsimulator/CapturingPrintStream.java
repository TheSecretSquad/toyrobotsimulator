package toyrobotsimulator;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class CapturingPrintStream extends PrintStream {
	
	public CapturingPrintStream() {
		super(new ByteArrayOutputStream());
	}
	
	public String contents() {
		return capturedContents();
	}
	
	private ByteArrayOutputStream outputStream() {
		return ((ByteArrayOutputStream)this.out);
	}
	
	private String capturedContents() {
		try {
			return outputStream().toString("UTF-8");
		}
		catch (UnsupportedEncodingException e) {
			return "";
		}
	}
}