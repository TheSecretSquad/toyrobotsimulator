package toyrobotsimulator;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ReportCommandTest {

	private ReportCommand reportCommand;
	@Mock
	private ReportAction reportAction;
	
	@Before
	public void setUp() throws Exception {
		reportCommand = new ReportCommand(reportAction);
	}

	@Test
	public void WhenExecutingCommand_ShouldReport() {
		reportCommand.execute();
		verify(reportAction).report();
	}
}