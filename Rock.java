/**
creates a Rock object in a location on the grid
*/
public class Rock {
	private Location loc;
	

	
	public Rock(Location loc) {
		this.loc = loc;

	}
	

	public String getID() {
		return "R";
	}
	
	/**
	Checks if the location of the rock is equal to the current location
	@param loc the Location that is being checked against the Rock's current location
	@return boolean wether location is equal or not
	*/
	public boolean isLocated(Location loc) {
		if (this.loc.getRow() == loc.getRow() && this.loc.getCol() == loc.getCol()) {
			return true;
		} else {
			return false;
		}
	}
	
}
