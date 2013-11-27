package dataStructures;
/**
 * @author Henrique Leandres Nº 34802
 * @author Joao Pinto Nº 35318
 */
import java.util.*;

public class AdjacencyListDigraph<L> implements Digraph<L> {
	
	private List<LinkedList<Integer>> successors;
	private List<LinkedList<Integer>> ancestors;
	private int nEdges;
	
	
	public AdjacencyListDigraph(int vertices){
		//Inicializa duas matrizes de listas ligadas
		successors = new ArrayList<LinkedList<Integer>>(vertices);
		for(int i = 0; i < vertices; i++){
			successors.add(new LinkedList<Integer>());
		}
		
		ancestors = new ArrayList<LinkedList<Integer>>(vertices);
		for(int i = 0; i < vertices; i++){
			ancestors.add(new LinkedList<Integer>());
		}
	}
	
	@Override
	public int numVertices() {
		return successors.size();
	}

	@Override
	public int numEdges() {
		return nEdges;
	}

	@Override
	public Iterator<Integer> vertices() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<Edge<L>> edges() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int aVertex() {
		//Devolve um vertice arbitrario
		return 0;
	}

	@Override
	public Edge<L> insertEdge(int vertex1, int vertex2, L label) {
		//Cria arco e adiciona vertice 2 como sucessor do primeiro e
		//vertice 1 como antecessor do segundo
		Edge<L> edge = new GraphEdge<L>(vertex1, vertex2, label);
		
		successors.get(vertex1).add(vertex2);
		ancestors.get(vertex2).add(vertex1);
		
		return edge;
	}

	@Override
	public void removeEdge(Edge<L> edge) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean edgeExists(int vertex1, int vertex2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int inDegree(int vertex) {
		//Devolve antecessores de um vertice
		return ancestors.get(vertex).size();
	}

	@Override
	public int outDegree(int vertex) {	
		//Devolve sucessores de um vertice
		return successors.get(vertex).size();
	}

	@Override
	public Iterator<Integer> inAdjacentVertices(int vertex) {
		//Devolve iterador dos antecessores de um vertice
		return ancestors.get(vertex).iterator();
	}

	@Override
	public Iterator<Integer> outAdjacentVertices(int vertex) {
		//Devolve iterador dos sucessores de um vertice
		return successors.get(vertex).iterator();
	}

	@Override
	public Iterator<Edge<L>> inIncidentEdges(int vertex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<Edge<L>> outIncidentEdges(int vertex) {
		// TODO Auto-generated method stub
		return null;
	}
	
//	public void print(){
//		//Imprime listas de adjacências
//		System.out.println("successors");
//		for(LinkedList<Integer> l : successors){
//			for(Integer i : l){
//				System.out.print(i+" ");
//			}
//			System.out.println();
//		}
//		System.out.println("ancestors");
//		for(LinkedList<Integer> l : ancestors){
//			for(Integer i : l){
//				System.out.print(i+" ");
//			}			
//			System.out.println();
//		}
//	}

}
