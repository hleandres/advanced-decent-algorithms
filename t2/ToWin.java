/**
 * 
 */
package t2;

import dataStructures.*;

import java.util.*;

/**
 * @author henrique
 *
 */
public class ToWin {
	
	private Digraph<?> graph;
	int p, a;
	
	public ToWin(int p, int a, int[][] rules, int r) {
		this.p = p;
		this.a = a;
		
		graph = new AdjacencyListDigraph<>(a);
		
		for(int i = 0; i < r; i++){
			graph.insertEdge(rules[i][0], rules[i][1], null);
		}
	}

	public String solve(int i) {
		if(ancestors(graph, i) >= p)
			return "Sorry";
		else if(p >= a-successors(graph, i))
			return "Congratulations!";
		else
			return "You have chances";
	}
	
	private int successors(Digraph<?> graph, int root) {
		boolean[] explored = new boolean[graph.numVertices()];
		Stack<Integer> foundUnexplored = new Stack<Integer>();
		int suc = 0;
		
		for(int i = 0; i < graph.numVertices(); i++)
			explored[i] = false;
		
		foundUnexplored.push(root);
		
		do{
			Integer vertex = foundUnexplored.pop();
			if(!explored[vertex])
			{	suc++; explored[vertex] = true;
				int w = 0;
				
				Iterator<Integer> successors = graph.outAdjacentVertices(vertex);
				while(successors.hasNext()){
					w = successors.next();
					if(!explored[w]){
						foundUnexplored.push(w);
					}
				}
			}
		}while(!foundUnexplored.empty());
		
		return suc-1;
	}	
	
	private int ancestors(Digraph<?> graph, int root) {
		boolean[] explored = new boolean[graph.numVertices()];
		Stack<Integer> foundUnexplored = new Stack<Integer>();
		int anc = 0;
		
		for(int i = 0; i < graph.numVertices(); i++)
			explored[i] = false;
		
		foundUnexplored.push(root);
		
		do{
			Integer vertex = foundUnexplored.pop();
			if(!explored[vertex])
			{
				//Treat
				anc++; 
				explored[vertex] = true;
				int w = 0;
				
				Iterator<Integer> ancestors = graph.inAdjacentVertices(vertex);
				while(ancestors.hasNext()){
					w = ancestors.next();
					if(!explored[w]){
						foundUnexplored.push(w);
					}
				}
			}
		}while(!foundUnexplored.isEmpty());
		
		return anc-1;
	}
}
