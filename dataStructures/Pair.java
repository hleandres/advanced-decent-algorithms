package dataStructures;
/**
 * @author Henrique Leandres Nº 34802
 * @author Joao Pinto Nº 35318
 */
public class Pair<X, Y> {
	private X x;
	private Y y;
	public Pair(X l, Y r){
		this.x = l;
		this.y = r;
	}
	public X getX(){ return x; }
	public Y getY(){ return y; }
	public void setX(X l){ this.x = l; }
	public void setY(Y r){ this.y = r; }
	
	public int hashCode() {
		return (x != null ? x.hashCode() : 0) + 31 *
				(y != null ? y.hashCode() : 0);
	}

	public boolean equals(Object o) {
		if (o == null || o.getClass() != this.getClass()){ 
			return false; 
		}
		
		Pair that = (Pair) o;

		return (x == null ? that.x == null : x.equals(that.x))
				&& (y == null ? that.y == null : y.equals(that.y));
	}
	
}
