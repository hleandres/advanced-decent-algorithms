package t1;

import java.util.*;

public class Mosaics {

	private static final int[] BRICKS = {1,2,3,4,6,8,10,12,16};

	private static final char VOID = '.';

	public char[][] matrix;
	public int rows;
	public int columns;
	public int ways;
	
	//Cria um vector para guardar as repeticoes de cor onde o endereco 
	//corresponde ao numero de repeticoes e o conteudo representa o numero de 
	//vezes que essa repeticao se verifica
	
	//Tem como dimensao maxima c, onde este caso representa uma linha preenchida
	//com uma so cor
	public int[] repList;

	public Mosaics(char[][] matrix, int r, int c) {
		this.matrix = matrix;
		rows = r;
		columns = c;
		ways = 1;
		repList = new int[columns];
	}

	public int solve() {

		repList = convertToInt(matrix);

		//n maximo para o qual e necessario calcular combinacoes possiveis
		int maxRep = 0;
		
		for(int i = columns; i > 0; i--){
			if(repList[i] > 0){
				maxRep = i;
				break;
			}
		}
		
		int[] combs = new int[maxRep];
		
		//Tabelar a funcao no vector combs
		for(int i = 0; i < maxRep; i++){
			combs[i] = f(i+1);
		}
		
//		System.out.print("[");
//		for(int i = 0; i < columns; i++)
//			System.out.print(repList[i] + " ");
//		System.out.print("]");
		
		//Salta o caso i = 0 pois representa uma potencia de 1 e evita o
		//pior caso
		for(int i = 1; i < columns; i++){
			ways *= Math.pow(combs[i], repList[i]);
		}

		return ways;
	}

	private int calcComb(Integer n) {
		int[] cValues = new int[columns];
		int tmp = 0;
		
		for(int i = 0; i < columns; i++){
			
		}
	
		for(int i = 0; i < n; i++){
			tmp += cValues[n - i];
		}
		
		cValues[n] = tmp;
		
		return 1;
	}

	/*
	 * Converte uma matriz de caracteres para um vector de inteiros que contem
	 * as ocurrencias de repeticoes de caracteres diferentes de "."
	 */
	private int[] convertToInt(char[][] cs) {
		int tmp[] = new int[columns];
		int counter = 0;

		//Ciclo percorre linha a linha
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < columns-1; j++){
				//Caso encontre um ponto reinicia o contador e ignora
				if(cs[i][j] == VOID)
					counter = 0;
				else if(cs[i][j] == cs[i][j+1]){
					counter++;
					
					//Caso chegue ao fim da linha
					//incrementa numero de repeticoes de tamanho counter
					if(j == columns-2){
						tmp[counter]++;
						counter = 0;
					}
				}
				else if((cs[i][j] != cs[i][j+1]) || (cs[i][j+1] != VOID)){
					//Caso encontre à frente uma letra diferente ou um ponto
					//incrementa numero de repeticoes de tamanho counter
					tmp[counter]++;
					counter = 0;
				}
			}
		}

		return tmp;
	}

}
