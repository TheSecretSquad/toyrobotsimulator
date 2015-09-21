package toyrobotsimulator;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SimulatorTest {

	private Simulator simulator;
	@Mock
	private SimulationFactory simulationFactory;
	@Mock
	private Simulation simulation;
	
	@Before
	public void setUp() throws Exception {
		simulator = new Simulator(simulationFactory);
		when(simulationFactory.create()).thenReturn(simulation);
	}

	@Test
	public void WhenSimulating_ShouldCreateSimulation() {
		simulator.simulate();
		verify(simulationFactory).create();
	}
	
	@Test
	public void WhenSimulating_ShouldRunSimulation() {
		simulator.simulate();
		verify(simulation).run();
	}
}