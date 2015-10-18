package toyrobotsimulator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LineMatcher {

	private final String regexStartAnchor = "^"; 
	
	public boolean isFirstIn(final String string, final String content) {
		return contentMatchesPattern(content, firstLine(string));
	}

	private boolean contentMatchesPattern(final String content, final Pattern pattern) {
		return matcherForPatternWith(pattern, content).find();
	}

	private Matcher matcherForPatternWith(final Pattern pattern, final String content) {
		return pattern.matcher(content);
	}
	
	private Pattern firstLine(final String string) {
		return linePatternWith(regexStart(string));
	}

	private String regexStart(final String string) {
		return regexStartAnchor + string;
	}
	
	private Pattern linePatternWith(final String string) {
		return Pattern.compile(lineWith(string));
	}

	private String lineWith(final String string) {
		return string + System.lineSeparator();
	}

	public boolean containsIn(final String string, final String content) {
		return content.contains(lineWith(string));
	}
}
