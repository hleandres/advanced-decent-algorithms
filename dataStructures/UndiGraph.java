package dataStructures;
/**
 * @author Henrique Leandres Nº 34802
 * @author Joao Pinto Nº 35318
 */
import java.util.Iterator;

public interface UndiGraph<L> extends Graph<L>{
	
	/**
	 *  Returns the degree of the specified vertex.
	 */
	int degree( int vertex );
	
	/** 
	 * Returns an iterator of the vertices adjacent to the specified
	 * vertex.
	 */
	Iterator<Integer> adjacentVertices( int vertex );
	
	/** 
	 * Returns an iterator of the edges incident upon the specified vertex.
	 */
	Iterator<Edge<L>> incidentEdges( int vertex );
}
