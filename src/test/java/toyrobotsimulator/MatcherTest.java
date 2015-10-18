package toyrobotsimulator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

public class MatcherTest {

	private LineMatcher lineMatcher;
	private String line1Content;
	private String line2Content;
	private String lines;
	private String lineContentNotInLines;
	
	@Before
	public void setUp() throws Exception {
		lineMatcher = new LineMatcher();
		line1Content = "line 1";
		line2Content = "line 2";
		lineContentNotInLines = "not in lines";
		createLinesString(line1Content, line2Content);
	}
	
	/**
	 * Each line in lineList is concatenated with line separators to create a string such that
	 * ["l1", "l2"] -> "l1" + separator + "l2" + separator
	 */
	private void createLinesString(final String ... lineList) {
		String newLine = System.lineSeparator();
		lines = Arrays.asList(lineList)
				.stream()
				.collect(Collectors.joining(/* delimiter */newLine, /*prefix*/"", /* suffix */newLine));
	}
	
	@Test
	public void Line1IsTheFirstLineInLines() {
		assertTrue(lineMatcher.isFirstIn(line1Content, lines));
	}
	
	@Test
	public void Line2IsNotTheFirstLineInLines() {
		assertFalse(lineMatcher.isFirstIn(line2Content, lines));
	}
	
	@Test
	public void LinesContainsLine2() {
		assertTrue(lineMatcher.containsIn(line2Content, lines));
	}
	
	@Test
	public void LinesDoesNotContainLineThatIsNotInLines() {
		assertFalse(lineMatcher.containsIn(lineContentNotInLines, lines));
	}
}