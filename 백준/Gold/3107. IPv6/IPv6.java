import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String ip = br.readLine();
		int cnt = 7;
		char[] buf = new char[] { '0', '0', '0', '0' };
		String[] address = new String[8];
		int bidx = 3;

		int colon = 0;

		for (int i = 0; i < ip.length(); i++) {
			if (ip.charAt(i) == ':') {
				colon++;
			}
		}
		
		
		for (int i = ip.length() - 1; i >= 0; i--) {
			if (ip.charAt(i) == ':') {
				address[cnt--] = String.valueOf(buf);
				colon--;
				buf = new char[] { '0', '0', '0', '0' };
				bidx = 3;
				if(i> 0 && ip.charAt(i-1)==':') {
					while(colon <= cnt) {
						address[cnt--] = String.valueOf(buf);
					}
					i--;
				}
			} else {
				buf[bidx--] = ip.charAt(i);
			}
		}
		while(cnt >= 0) {
			address[cnt--] = String.valueOf(buf);
		}

		for (int i = 0; i < 7; i++) {
			System.out.print(address[i].toString() + ":");
		}
		System.out.println(address[7].toString());
	}

}