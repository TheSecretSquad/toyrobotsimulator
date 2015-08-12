package toyrobotsimulator;

public interface ReportStream {

	void report(final Position position);
	
	void report(final Direction direction);
}