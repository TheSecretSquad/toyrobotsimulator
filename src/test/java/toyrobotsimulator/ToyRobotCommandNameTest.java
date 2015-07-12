package toyrobotsimulator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ToyRobotCommandNameTest {
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void WhenComparingEqual_IsCaseInsensitive() {
		assertEquals(new CommandName("TEST"), new CommandName("test"));
	}
}
