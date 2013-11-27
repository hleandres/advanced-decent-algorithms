package dataStructures;
/**
 * @author Henrique Leandres Nº 34802
 * @author Joao Pinto Nº 35318
 */
public class GraphEdge<L> implements Edge<L> {

	private L label;
	private int[] vertices = new int[2];
	
	public GraphEdge(int v1, int v2, L value){
		label = value;
		vertices[0] = v1;
		vertices[1] = v2;
	}
	
	@Override
	public L label() {
		return label;
	}

	@Override
	public int[] endVertices() {
		return vertices;
	}

	@Override
	public int oppositeVertex(int endVertex) {
		if(endVertex == vertices[0])
			return vertices[1];
		else
			return vertices[0];
	}

}
