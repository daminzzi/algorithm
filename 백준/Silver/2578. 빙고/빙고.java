import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static class Pair {
		int r;
		int c;
		
		public Pair(int i, int j) {
			this.r = i;
			this.c = j;
		}
	}
	
	static Pair[] bingo = new Pair[26];
	static int[] row = new int[5];
	static int[] col = new int[5];
	static int[] dia = new int[2];
	static int bCheck = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int i = 0; i<5; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<5; j++) {
				int k = Integer.parseInt(st.nextToken());
				bingo[k] = new Pair(i, j);
			}
		}
		
		int cnt = 0, r, c, temp;
xx:		for(int i = 0; i<5; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<5; j++) {
				cnt++;
				int k = Integer.parseInt(st.nextToken());
				r = bingo[k].r;
				c = bingo[k].c;
				row[r]++; col[c]++;
				if(r == c) dia[0]++;
				if(r+c == 4) dia[1]++;
				
				temp = 0;
				for(int l = 0; l<5; l++) {
					if(isBingo(row[l])) temp++;
					if(isBingo(col[l])) temp++;
				}
				if(isBingo(dia[0])) temp++;
				if(isBingo(dia[1])) temp++;
				if(temp >= 3) {
					System.out.println(cnt);
					break xx;
				}
			}
		}

	}
	
	static boolean isBingo(int a) {
		if(a == 5) return true;
		return false;
	}
}