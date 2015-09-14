package toyrobotsimulator;

import java.io.IOException;
import java.io.OutputStream;

public class StringOutputStream extends OutputStream {

	private StringBuilder contents;
	
	public StringOutputStream() {
		contents = new StringBuilder();
	}
	
	@Override
	public void write(int b) throws IOException {
		addContent(b);
	}

	private void addContent(int b) {
		addContent(asChar(b));
	}
	
	private void addContent(char character) {
		contents.append(character);
	}
	
	private char asChar(int i) {
		return (char)i;
	}

	public String contents() {
		return contents.toString();
	}
}