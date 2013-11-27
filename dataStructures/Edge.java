package dataStructures;
/**
 * @author Henrique Leandres Nº 34802
 * @author Joao Pinto Nº 35318
 */
public interface Edge<L> {

	/**
	 * Returns the edge label.
	 */
	L label();
	
	/**
	 * Returns an array of length 2 with the edge end-vertices.
	 */
	int[] endVertices( );
	
	/**
	 * Returns the edge end-vertex that is distinct from the specified vertex.
	 */
	int oppositeVertex( int endVertex );
}
