import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int[] pi;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String T = br.readLine();
		String P = br.readLine();
		
		pi = new int[P.length()];
		
		makeTable(P);
		KMP(T, P);
		
	}
	
	static void KMP(String origin, String pattern) {
		int length = origin.length();
		StringBuilder sb = new StringBuilder();
		int cnt = 0;
		int j = 0;
		for(int i = 0; i<length; i++) {
			while(j > 0 && origin.charAt(i) != pattern.charAt(j)) {
				j = pi[j-1];
			}
			if(origin.charAt(i) == pattern.charAt(j)) {
				if(j == pattern.length()-1) {
					sb.append(i-j+1).append(' ');
					cnt++;
					j = pi[j];
				}else {
					j++;
				}
			}
		}
		System.out.println(cnt);
		System.out.println(sb);
	}
	
	static void makeTable(String pat) {
		int j = 0;
		for(int i = 1; i<pat.length(); i++) {
			while(j > 0 && pat.charAt(i)!=pat.charAt(j)) {
				j = pi[j-1];
			}
			if(pat.charAt(i) == pat.charAt(j)) {
				pi[i] = ++j;
			}
		}
	}

}