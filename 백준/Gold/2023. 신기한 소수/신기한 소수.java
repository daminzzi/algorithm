import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	static boolean isPrime(int n) {		
		for(int i = 2; i*i<=n; i++) {
			if (n%i == 0) return false;
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int N = sc.nextInt();
				
xx:		for(int i = (int)Math.pow(10, N-1)*2; i<Math.pow(10, N); i++) {
			int divisor = (int)Math.pow(10, N-1);
			if(isPrime(i/divisor)) {
				while(divisor >= 1) {
					if(isPrime(i/divisor)) {
						divisor /= 10;
					}
					else {
						continue xx;
					}
				}
				sb.append(i).append('\n');
			}
		}
		System.out.println(sb);
	}
}