package t2;
import java.util.*;
public class Main {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		//Declaracao das variaveis do input
		int p = 0, a = 0, r = 0, t = 0;
		
		p = in.nextInt();
		a = in.nextInt();
		r = in.nextInt();
		
		in.nextLine();
		
		//Declaracao do vector de pares de regras;
		int[][] rules = new int[r][2];

		//Leitura das regras
		for(int i = 0; i < r; i++){
			rules[i][0] = in.nextInt();
			rules[i][1] = in.nextInt();
			in.nextLine();
		}
		
		t = in.nextInt();
		in.nextLine();
		
		//Definição do problema
		ToWin problem = new ToWin(p,a,rules,r);
		
		//Leitura dos casos de teste
		for(int i = 0; i < t; i++){
			System.out.println(problem.solve(Integer.parseInt(in.nextLine())));
		}

		in.close();
	}
}
