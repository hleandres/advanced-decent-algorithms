package t3;

import java.util.*;
import java.util.Map.Entry;

import dataStructures.*;

/**
 * @author Henrique Leandres Nº 34802
 * @author Joao Pinto Nº 35318
 */


public class bookDelivery {

	private final static Pair<Integer,Character> DESTINATION = 
			new Pair<Integer,Character>(0,'*');

	private HashMap<Pair<Integer,Character>,Integer> map;
	private int counter;
	int sourceVertex, destinationVertex;
	private List<HashMap<Pair<Integer,Character>,Integer>> graph;

	private char[][] schedule;

	public bookDelivery(int p, char[][] schedule){
		counter = 0;
		//criar mapa dia/actividade -> vertice
		this.schedule = schedule;
		map = new HashMap<Pair<Integer,Character>,Integer>();

		for(int i = 0; i < p; i++){
			for(int j = 0; j < 7; j++){
				Pair<Integer, Character> tmpkey = 
						new Pair<Integer,Character>(j,schedule[i][j]);
				//so adiciona novo vertice se par dia/actividade for novo!
				if(schedule[i][j]!='-' && !map.containsKey(tmpkey)){
					map.put(tmpkey, counter);
					counter++;
				}
			}
		}

		sourceVertex = counter; destinationVertex = counter+1;

		//criar lista de adjacencias de sucessores para guardar ligacoes entre
		//vertices
		graph = 
				new ArrayList<HashMap<Pair<Integer,Character>,Integer>>(destinationVertex+1);
		
		for(int i = 0; i < destinationVertex+1; i++){
			graph.add(new HashMap<Pair<Integer,Character>,Integer>());
		}

		//preencher lista de sucessores

		//tratamento linha a linha
		for(int i = 0; i < p; i++){

			boolean first = true, loop = false;
			int j = 0, previous = -1, firstPos = -1;

			while(!loop){
				if(schedule[i][j] != '-'){
					if(first){
						first = false;
						previous = firstPos = j;
					}
					else if(j == firstPos && firstPos != previous){
						//vai buscar ao diccionario o numero do vertice anterior
						//dando o dia e o caracter nesse dia
						int prevVertex = map.get(new Pair<Integer,Character>
						(previous,schedule[i][previous]));

						//vai buscar ao diccionario o numero do vertice do dia
						//que esta a ser visitado na iteracao e o caracter desse
						//dia
						Pair<Integer,Character> vertex = 
								new Pair<Integer,Character>(j,schedule[i][j]);

						//calcula a diferenca entre os dias: como nao deu a 
						//volta, j > previous
						int length = j + 7 - previous;

						//adicionar current (j) ao previous;
						graph.get(prevVertex).put(vertex,length);
						loop = true;
					}
					//Caso em que pessoa so tem uma actividade num so dia
					else if(j == firstPos && firstPos == previous){
						loop = true;
					}
					else{
						//encontrar vertices no mapa

						///vai buscar ao diccionario o numero do vertice anterior
						//dando o dia e o caracter nesse dia
						int prevVertex = map.get(new Pair<Integer,Character>
						(previous,schedule[i][previous]));

						//vai buscar ao diccionario o numero do vertice do dia
						//que esta a ser visitado na iteracao e o caracter desse
						//dia
						Pair<Integer,Character> vertex = 
								new Pair<Integer,Character>
						(j,schedule[i][j]);

						//calcula a diferenca entre os dias: como nao deu a 
						//volta, j > previous
						int length = j - previous;

						//adicionar current (j) ao previous;
						graph.get(prevVertex).put(vertex,length);
						previous = j;
					}

				}
				j = (j+1) % 7;
			}	
		}	
//		for(HashMap<Pair<Integer,Character>,Integer> h: successors){
//			Iterator<Entry<Pair<Integer, Character>, Integer>> it = 
//					h.entrySet().iterator();
//			
//			while(it.hasNext()){
//				Entry<Pair<Integer, Character>, Integer> pa = it.next();
//				System.out.print("<"+map.get(pa.getKey())+","+pa.getValue()+">");
//			}
//			
//			System.out.println();
//		}
	}

	public int solve(int s, int d) {

		//Introduz actividades\dia da origem no grafo
		for(int j = 0; j < 7; j++){
			if(schedule[s][j] != '-'){
				Pair<Integer,Character> vertex = 
						new Pair<Integer,Character>(j,schedule[s][j]);
				graph.get(sourceVertex).put(vertex,0);
			}
		}

		//Introduz actividades/dia do destino no mapa actividaces/dia -> vertice
		map.put(DESTINATION,destinationVertex);

		//Introduz destino em actividades/dia da ultima linha
		for(int j = 0; j < 7; j++){
			if(schedule[d][j] != '-'){
				int destinationActivity = map.get(new Pair<Integer,Character>
				(j,schedule[d][j]));
				graph.get(destinationActivity).put(DESTINATION, 0);
			}
		}

//		for(HashMap<Pair<Integer,Character>,Integer> h: successors){
//			Iterator<Entry<Pair<Integer, Character>, Integer>> it = 
//					h.entrySet().iterator();
//
//			while(it.hasNext()){
//				Entry<Pair<Integer, Character>, Integer> pa = it.next();
//				System.out.print("<"+map.get(pa.getKey())+","+pa.getValue()+">");
//			}
//			System.out.println();
//		}

		int res = -1;

//		System.out.println(successors.size());
//		System.out.println(destinationVertex);
		
		res = dijkstra(graph,sourceVertex,destinationVertex); 

		return res;
	}

	private int dijkstra(List<HashMap<Pair<Integer, Character>, Integer>> graph, 
			int origin, int destination) {

		boolean[] selected = new boolean[graph.size()];
		int[] length = new int[graph.size()];

		AdaptMinPriQueue<Integer,Integer> connected = 
				new FibonacciQueue<Integer,Integer>(graph.size());

		for(int i = 0; i < graph.size(); i++){
			selected[i] = false;
			length[i] = Integer.MAX_VALUE;
		}

		length[origin] = 0;
		connected.insert(length[origin], origin);

		do{
			int vertex = connected.removeMin().getValue();
			selected[vertex] = true;

//			System.out.println("vertice a explorar: "+vertex);

			exploreVertex(graph, vertex, selected, length, connected);

//			for(int i: length){
//				if(i == Integer.MAX_VALUE)
//					System.out.print("inf ");
//				else
//					System.out.print(i+" ");
//			}
//			System.out.println();
//			for(boolean b: selected){
//				System.out.print(b+" ");
//			}
//			System.out.println();
//			System.out.println();

		}while(!connected.isEmpty());

		return length[destination];
	}

	private void exploreVertex(List<HashMap<Pair<Integer, Character>, Integer>> 
	graph, int source, boolean[] selected, int[] length,
	AdaptMinPriQueue<Integer, Integer> connected) {

		Iterator<Entry<Pair<Integer, Character>, Integer>> it = 
				graph.get(source).entrySet().iterator();
		while(it.hasNext()){
			
			Entry<Pair<Integer, Character>, Integer> e = it.next();
			int vertex = map.get(e.getKey());

			if(!selected[vertex]){
				int newLength = length[source] + e.getValue();
				if(newLength < length[vertex]){
					//Actualiza menor caminho de source a vertex
					boolean vertexIsInQueue = length[vertex] < Integer.MAX_VALUE;
					length[vertex] = newLength;

					if( vertexIsInQueue ){
						connected.decreaseKey(vertex,length[vertex]);
					}
					else{
						connected.insert(length[vertex],vertex);
					}
				}
			}

		}

	}

}
