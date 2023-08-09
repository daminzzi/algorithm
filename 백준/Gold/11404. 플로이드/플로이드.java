import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M;
	static int[][] city;
	static final int MAX = 30000000;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		city = new int[N+1][N+1];
		for(int i = 1; i<=N; i++) {
			for(int j = 1; j<=N; j++) {
				city[i][j] = MAX;
				if(i==j) city[i][j] = 0;
			}
		}
		
		
		int s, e, c;
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			if(city[s][e] > c) {
				city[s][e] = c;
			}
		}
		
		boolean flag = true;
		
		while(flag) {
			flag = false;
			for(int i = 1; i<=N; i++) {
				for(int j = 1; j<=N; j++) {
					if(city[i][j] != MAX && city[i][j] != 0) {
						for(int k = 1; k<=N; k++) {
							if(j == k) continue;
							if(city[i][j]+city[j][k] < city[i][k]) {
								city[i][k] = city[i][j] + city[j][k];
								flag = true;
							}
						}
					}
				}
			}
		}
		
		
		for(int i = 1; i<=N; i++) {
			for(int j = 1; j<=N; j++) {
				if(city[i][j] >= MAX) city[i][j] = 0;
				sb.append(city[i][j]).append(' ');
			}
			sb.append('\n');
		}
		
		System.out.println(sb);
	}

}