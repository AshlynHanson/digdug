//creates a Rock object in a location on the grid

public class Rock {
	private Location loc;
	

	
	public Rock(Location loc) {
		this.loc = loc;

	}
	

	public String getID() {
		return "R";
	}
	
	public boolean isLocated(Location loc) {
		if (this.loc.getRow() == loc.getRow() && this.loc.getCol() == loc.getCol()) {
			return true;
		} else {
			return false;
		}
	}
	
}
