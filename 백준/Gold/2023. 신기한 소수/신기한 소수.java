import java.util.Scanner;

public class Main {
	static int[] l1 = {2, 3, 5, 7};
	static int[] l2 = {1, 3, 7, 9};
	static int N;
	static StringBuilder sb;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sb = new StringBuilder();
		N = sc.nextInt();
		
		if(N == 1) {
			for(int i: l1) {
				sb.append(i).append('\n');
			}
			System.out.println(sb);
			return;
		}
		
		for(int i: l1) {
			for(int j:l2) {
				check(i, j, 2);
			}
		}
		System.out.println(sb);
	}
	
	static boolean isPrime(int n) {		
		for(int i = 2; i*i<=n; i++) {
			if (n%i == 0) return false;
		}
		
		return true;
	}
	
	static void check(int origin, int j, int depth) {
		origin = origin*10 + j;
		if(!isPrime(origin)) {
			return;
		}
		if((depth == N)) {
			sb.append(origin).append('\n');
			return;
		}
		for(int k:l2) {
			check(origin, k, depth+1);
		}
	}

}