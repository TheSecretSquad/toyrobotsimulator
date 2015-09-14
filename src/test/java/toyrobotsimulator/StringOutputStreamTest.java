package toyrobotsimulator;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class StringOutputStreamTest {

	private StringOutputStream stringOutputStream;
	
	@Before
	public void setUp() throws Exception {
		stringOutputStream = new StringOutputStream();
	}

	private int characterIntValue(char character) {
		return (int)character;
	}
	
	private void verifyOutputStreamContents(String contents) {
		assertEquals(contents, stringOutputStream.contents());
	}
	
	@Test
	public void WhenWriting_SingleCharacter_StreamContainsCharacter() throws IOException {
		stringOutputStream.write(characterIntValue('a'));
		verifyOutputStreamContents("a");
	}
	
	@Test
	public void WhenWriting_MultipleCharacters_StreamContainsCharacters() throws IOException {
		stringOutputStream.write(characterIntValue('a'));
		stringOutputStream.write(characterIntValue('b'));
		verifyOutputStreamContents("ab");
	}
}