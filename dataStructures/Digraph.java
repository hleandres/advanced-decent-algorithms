package dataStructures;
/**
 * @author Henrique Leandres Nº 34802
 * @author Joao Pinto Nº 35318
 */
import java.util.Iterator;

public interface Digraph<L> extends Graph<L>{
	
	/**
	 * Returns the in-degree of the specified int.
	 */
	int inDegree( int vertex );
	
	/** 
	 * Returns the out-degree of the specified int.
	 */
	int outDegree( int vertex );
	
	/** 
	 * Returns an iterator of the vertices adjacent to the specified int 
	 * along incoming edges to it.
	 */
	Iterator<Integer> inAdjacentVertices( int vertex );
	
	/** 
	 * Returns an iterator of the vertices adjacent to the specified int 
	 * along outgoing edges from it.
	*/
	Iterator<Integer> outAdjacentVertices( int vertex );
	
	/** 
	 * Returns an iterator of the incoming edges to the specified int. 
	 */
	Iterator<Edge<L>> inIncidentEdges( int vertex );
	/**
	 * Returns an iterator of the outgoing edges from the specified int.
	 */
	Iterator<Edge<L>> outIncidentEdges( int vertex );
}
