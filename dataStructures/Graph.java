package dataStructures;
/**
 * @author Henrique Leandres Nº 34802
 * @author Joao Pinto Nº 35318
 */
import java.util.Iterator;

public interface Graph<L> {

	/** 
	 * Returns the number of vertices.
	 */
	int numVertices( );
	
	/**
	 * Returns the number of edges.
	 */
	int numEdges( );
	
	/**
	 * Returns an iterator of the vertices.
	 */
	Iterator<Integer> vertices( );
	
	/**
	 * Returns an iterator of the edges.
	 */
	Iterator<Edge<L>> edges( );
	
	/**
	 * Returns an arbitrary vertex.
	 */
	int aVertex( );
	
	/**
	* Inserts and returns the edge (vertex1, vertex2) and  associates it with the specified label.
	*/
	Edge<L> insertEdge( int vertex1, int vertex2, L label );
	
	/**
	* Removes the specified edge.
	*/
	void removeEdge( Edge<L> edge );
	
	/**
	* Returns true if there is an edge of the form (vertex1, vertex2).
	*/
	boolean edgeExists( int vertex1, int vertex2 );
}
