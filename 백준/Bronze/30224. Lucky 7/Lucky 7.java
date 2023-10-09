import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		String str = br.readLine();
		boolean flag = false;
		for(int i = 0; i<str.length(); i++) {
			if(str.charAt(i) == '7') {
				flag = true;
				break;
			}
		}
		
		int num = Integer.parseInt(str);
		if(flag) {
			if(num%7 == 0) System.out.println(3);
			else System.out.println(2);
		}
		else {
			if(num%7 == 0) System.out.println(1);
			else System.out.println(0);	
		}
	}

}