package toyrobotsimulator;

public interface ReportStream extends PositionReportStream {
	
	void report(final Direction direction);
}