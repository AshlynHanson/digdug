import java.util.Random;

/**
* This class creates a Dig Dug object  that has some location and moves in some direction in the grid
*/
public class DigDug {
	
	private Location loc;
	private int dir;
	
	
	public DigDug(Location loc, int dir) {
		this.loc = loc;
		this.dir = dir;
	}

	public Location getLoc() {
		return this.loc;
	}
	
	
	public String getID() {
		return "D";
	}
	
	/*
	* checks if the location of the DigDug object is equal to the location being checked
	*/
	public boolean isLocated(Location loc) {
		if (this.loc.getRow() == loc.getRow() && this.loc.getCol() == loc.getCol()) {
			return true;
		} else {
			return false;
		}
	}
	
	public void move(int dr, int dc) {
		this.loc.move(dr, dc);
		if (dc == 1) {
			this.dir = 0;
		}
		else if (dc == -1) {
			this.dir = 180;
		}
		else if (dr == 1) {
			this.dir = 270;
		}
		else if (dr == -1) {
			this.dir = 90;
		}
	}
	
	public String toString() {
		return "*** Dig Dug:  Loc = " + this.loc.toString() + "  Dir = " + this.dir + " ***";
	}
}
