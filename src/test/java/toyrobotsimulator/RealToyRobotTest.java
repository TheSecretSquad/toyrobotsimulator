package toyrobotsimulator;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RealToyRobotTest {

	private RealToyRobot realToyRobot;
	// Robot needs to filter commands before
	// they are reflected in the environment
	// Environment?
	@Before
	public void setUp() throws Exception {
		realToyRobot = new RealToyRobot();
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
