package toyrobotsimulator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DirectionTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void WhenComparingEqual_IsValueEqual() {
		assertEquals(new Direction("TEST"), new Direction("TEST"));
	}
	
	@Test
	public void WhenComparingEqual_IsCaseInsensitive() {
		assertEquals(new Direction("TEST"), new Direction("test"));
	}
}