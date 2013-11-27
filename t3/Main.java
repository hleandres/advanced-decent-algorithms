package t3;
import java.util.*;

/**
 * @author Henrique Leandres Nº 34802
 * @author Joao Pinto Nº 35318
 */

public class Main {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		//Declaracao das variaveis do input
		int p = 0, q = 0, s = 0, d = 0;
		
		p = Integer.parseInt(in.nextLine());
		
		//Declaracao da matriz que representa o horario;
		char[][] schedule = new char[p][7];
		String[] temp = null;
		
		//Leitura dos horarios de cada pessoa
		for(int i = 0; i < p; i++){
			temp = in.nextLine().split(" ");
			for(int j = 0; j < 7; j++){
				schedule[i][j] = temp[j].charAt(0);
			}
		}	
		
		q = in.nextInt();
		in.nextLine();
		
		int tmp = 0;
		//Leitura dos casos de teste
		for(int i = 0; i < q; i++){
			s = in.nextInt();
			d = in.nextInt();
			in.nextLine();

			bookDelivery problem = new bookDelivery(p,schedule);
			tmp = problem.solve(s,d);
			
			if(tmp == Integer.MAX_VALUE)
				System.out.println("impossible");
			else
				System.out.println(tmp);
		}

		in.close();
	}
}
