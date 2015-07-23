package toyrobotsimulator;

import org.junit.Test;

public class DirectionTest {

	@Test(expected=InvalidDirectionException.class)
	public void WhenCreating_WithInvalidDirectionString_ThrowsException() {
		Direction.createFrom("invalid");
	}
	
	@Test
	public void WhenCreating_AcceptsCaseInsensitiveInput() {
		Direction.createFrom("NORTH");
		Direction.createFrom("north");
	}
}