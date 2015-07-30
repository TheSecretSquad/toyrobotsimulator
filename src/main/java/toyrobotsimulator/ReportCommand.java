package toyrobotsimulator;

public class ReportCommand implements Command {

	private final ReportAction reportAction;

	public ReportCommand(final ReportAction reportAction) {
		this.reportAction = reportAction;
	}

	@Override
	public void execute() {
		reportAction.report();		
	}
}