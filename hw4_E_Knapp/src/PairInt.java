

public class PairInt {
	
	//private data fields
	private int x;
	private int y;
	
	//constructor
	public PairInt(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	//getters -- x and y
	public int getX() {
		return x;
	}
	
	
	public int getY() {
		return y;
	}
	
	//setters -- x and y
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	
	@Override
	public boolean equals(Object p) {
		if (p == null) {
			return false;
		}
		
		PairInt coordinate = (PairInt) p;
		return (this.x == coordinate.x && this.y == coordinate.y);
	}
	
	
	@Override
	public String toString() {
		return ("(" + x + ", " + y + ")");
	}
	
	
	public PairInt copy() {
		return new PairInt(x, y);
	}
	
	
	// Eric Knapp
	// 4.16.2021
	// hw4: Recursion
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
