package toyrobotsimulator;

public class TableTop implements Environment {

	private final int dimension;
	
	public TableTop(int dimension) {
		this.dimension = dimension;
	}
	
	@Override
	public void reportTo(final ReportStream reportStream) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void placeAtPositionWith(final Position position, final EdgeDetector edgeDetector) {
		if(!position.isBetween(new Position(1,1), new Position(5,5)))
			edgeDetector.detect(this);
	}

	@Override
	public void moveInDirectionWith(final Direction direction, final EdgeDetector edgeDetector) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void avoid() {
		// TODO Auto-generated method stub
		
	}
}