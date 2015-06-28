package toyrobotsimulator;

import static org.mockito.Mockito.inOrder;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class OrderedCommandParametersTest {

	private OrderedCommandParameters orderedCommandParameters;
	private String[] parameters;
	@Mock
	private CommandParameterReceiver commandParameterReceiver;
	
	@Before
	public void setUp() throws Exception {
		parameters = new String[] {"first", "second", "third"};
		orderedCommandParameters = new OrderedCommandParameters(parameters);
	}

	@Test
	public void WhenListing_ShouldReceiveInOrderDeclaredInConstructorArgument() {
		InOrder inOrder = inOrder(commandParameterReceiver, commandParameterReceiver, commandParameterReceiver);
		orderedCommandParameters.listTo(commandParameterReceiver);
		inOrder.verify(commandParameterReceiver).receive("first");
		inOrder.verify(commandParameterReceiver).receive("second");
		inOrder.verify(commandParameterReceiver).receive("third");
	}
}
