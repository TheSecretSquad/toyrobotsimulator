package toyrobotsimulator;

import static org.mockito.Mockito.verifyZeroInteractions;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EmptyCommandParametersTest {

	private EmptyCommandParameters emptyCommandParameters;
	@Mock
	private CommandParameterReceiver commandParameterReceiver;
	
	@Before
	public void setUp() throws Exception {
		emptyCommandParameters = new EmptyCommandParameters();
	}

	@Test
	public void WhenListing_ShouldNotDoAnything() {
		emptyCommandParameters.listTo(commandParameterReceiver);
		verifyZeroInteractions(commandParameterReceiver);
	}
}