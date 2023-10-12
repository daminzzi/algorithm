import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String ip = br.readLine();
		int cnt = 7; // 몇 개의 ip 주소 블록을 작성해야하는지
		String[] address = new String[8]; //주소 블록 저장
		char[] buf = new char[] { '0', '0', '0', '0' }; //주소 블록의 값 저장
		int bidx = 3; //주소 블록 인덱스

		//현재 입력된 주소의 콜론 개수 count
		int colon = 0; 
		for (int i = 0; i < ip.length(); i++) {
			if (ip.charAt(i) == ':') {
				colon++;
			}
		}
		
		//입력된 주소의 제일 뒤부터 주소를 읽으면서 변환함
		for (int i = ip.length() - 1; i >= 0; i--) {
			//해당 위치가 콜론이라면 하나의 블록 생성됨
			if (ip.charAt(i) == ':') {
				address[cnt--] = String.valueOf(buf);
				colon--;
				buf = new char[] { '0', '0', '0', '0' };
				bidx = 3;
				if(i> 0 && ip.charAt(i-1)==':') { //콜론 그룹이라면
					while(colon <= cnt) { //생성되어야 하는 그룹 수 만큼
						//주소값에 0000 채우기
						address[cnt--] = String.valueOf(buf);
					}
					i--; //콜론 그룹에 있는 콜론을 다시 읽는 것을 방지하기 위해서 i--;
				}
			} else { //버퍼 뒤에서부터 채워서 000* -> 00** -> 0*** -> **** 이런식으로 버퍼가 채워짐 
				buf[bidx--] = ip.charAt(i);
			}
		}
		//맨 앞이 콜론 
		address[cnt--] = String.valueOf(buf);

		for (int i = 0; i < 7; i++) {
			System.out.print(address[i].toString() + ":");
		}
		System.out.println(address[7].toString());
	}

}
