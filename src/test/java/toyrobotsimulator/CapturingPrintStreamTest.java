package toyrobotsimulator;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class CapturingPrintStreamTest {

	private CapturingPrintStream capturingPrintStream;
	
	@Before
	public void setUp() throws Exception {
		capturingPrintStream = new CapturingPrintStream();
	}
	
	private void verifyOutputStreamContents(String contents) {
		assertEquals(contents, capturingPrintStream.contents());
	}
	
	@Test
	public void CapturesSingleCharacter() {
		capturingPrintStream.print("a");
		verifyOutputStreamContents("a");
	}
	
	@Test
	public void CapturesMultipleCharacters() {
		capturingPrintStream.print("a");
		capturingPrintStream.print("b");
		verifyOutputStreamContents("ab");
	}
}
