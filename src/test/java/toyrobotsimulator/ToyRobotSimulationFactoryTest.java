package toyrobotsimulator;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class ToyRobotSimulationFactoryTest {

	private ToyRobotSimulationFactory toyRobotSimulationFactory;
	
	@Before
	public void setUp() throws Exception {
		toyRobotSimulationFactory = new ToyRobotSimulationFactory();
	}

	@Test
	public void WhenCreate_ReturnsNonNullSimulation() {
		assertNotNull(toyRobotSimulationFactory.create());
	}
	
	@Test
	public void WhenCreate_CalledMultipleTimes_ReturnsNewSimulationObjects() {
		Simulation s1 = toyRobotSimulationFactory.create();
		Simulation s2 = toyRobotSimulationFactory.create();
		assertNotSame(s1, s2);
	}
	
	@Test
	public void WhenCreate_ReturnsToyRobotSimulation() {
		assertThat(toyRobotSimulationFactory.create(), instanceOf(ToyRobotSimulation.class));
	}
}