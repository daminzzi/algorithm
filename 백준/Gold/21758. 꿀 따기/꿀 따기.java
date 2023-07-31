import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		long[] honey = new long[N];
		long totalHoney = 0;
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i<N; i++) {
			honey[i] = Long.parseLong(st.nextToken());
			totalHoney += honey[i];
		}
		
		long maxHoney = 0;
		
		long fixBee = totalHoney - honey[0];
		long moveBee = fixBee;
		
		for(int i = 1; i < honey.length -1; i++) {
			long sum = fixBee - honey[i];
			moveBee = moveBee - honey[i];
			sum += moveBee;
			maxHoney = Math.max(sum, maxHoney);
		}
		
		
		fixBee = fixBee + honey[0] - honey[honey.length -1];
		moveBee = fixBee;
	
		for(int i = honey.length - 2; i >= 0; i--) {
			long sum = fixBee - honey[i];
			moveBee = moveBee - honey[i];
			sum += moveBee;
			maxHoney = Math.max(sum, maxHoney);
		}
		
		fixBee = 0;
		moveBee = totalHoney - honey[honey.length -1];
		
		for(int i = 1; i < honey.length -1; i++) {
			long sum = 0;
			fixBee += honey[i];
			moveBee = moveBee - honey[i-1];
			sum = moveBee + fixBee;
			maxHoney = Math.max(sum, maxHoney);
		}
		
		System.out.println(maxHoney);
	}

}
