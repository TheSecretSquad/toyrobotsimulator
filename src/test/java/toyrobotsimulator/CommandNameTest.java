package toyrobotsimulator;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CommandNameTest {
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void WhenComparingEqual_IsValueEqual() {
		assertEquals(new CommandName("TEST"), new CommandName("TEST"));
	}
	
	@Test
	public void WhenComparingEqual_IsCaseInsensitive() {
		assertEquals(new CommandName("TEST"), new CommandName("test"));
	}
}