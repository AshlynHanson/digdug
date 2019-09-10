
public class Location {
	
	private int row;
	private int col;
	
	public Location() {
		this.row = 0;
		this.row = 0;
	}
	
	public Location(int r, int c) {
		this.row = r;
		this.col = c;
	}
	
	public int getRow() {
		return this.row;
	}
	
	public int getCol() {
		return this.col;
	}
	
	public void move(int dr, int dc) {
		this.row += dr;
		this.col += dc;
	}
	
	public String toString() {
		return "(" + this.row + ", " + this.col + ")";
	}
}
