package t1;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int r = 0, c = 0, w = 0;

		r = in.nextInt();
		c = in.nextInt();
		in.nextLine();

		char[][] matrix = new char[r][c];

		String temp = "";
		for(int i = 0; i < r; i++){
			temp = in.nextLine().toUpperCase();
			System.out.println(temp);
			for(int j = 0; j < c; j++){
				matrix[i][j] = temp.charAt(j);
			}
		}

		Mosaics problem = new Mosaics(matrix, r, c);

		w = problem.solve();

		System.out.println(w);
	}
}
