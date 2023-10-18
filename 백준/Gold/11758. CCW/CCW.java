import java.io.*;
import java.util.*;
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int[] x = new int[3];
		int[] y = new int[3];
		for(int i = 0; i<3; i++) {
			st = new StringTokenizer(br.readLine());
			x[i] = Integer.parseInt(st.nextToken());
			y[i] = Integer.parseInt(st.nextToken());
		}
		
		//세로로 일직선인 경우 기울기 접근 불가능 따로 처리
		if(x[0] == x[2]) {
			if(x[0] == x[1]) { //x끼리일직선
				System.out.println(0);
				return;
			}
			if(y[0] < y[2]) { //P1이 P3보다 아래에 있을 때
				if(x[1] < x[0]) {
					System.out.println(-1);
					return;
				}else {
					System.out.println(1);
					return;
				}
			}
			else { //P1이 P3보다 위에 있을 때
				if(x[1] < x[0]) {
					System.out.println(1);
					return;
				}else {
					System.out.println(-1);
					return;
				}
			}
		}
		
		//그 외의 경우
		int a = y[0]-y[2];
		int b = y[0]*(x[0]-x[2])-a*x[0];
		int tX = a*x[1]+b;
		int tY = y[1]*(x[0]-x[2]);
		//System.out.printf("%d*y=%d*x+%d\n", x[0]-x[2], a, b);
		//System.out.println(tY+" "+tX);
		if(x[0] < x[2]) {
			if(tY == tX) {
				System.out.println(0);
				return;
			}
			else if(tY < tX) {
				System.out.println(-1);
				return;
			}
			else {
				System.out.println(1);
				return;
			}
		}
		
		else {
			if(tY == tX) {
				System.out.println(0);
				return;
			}
			else if(tY > tX) {
				System.out.println(1);
				return;
			}
			else {
				System.out.println(-1);
				return;
			}
		}
	}

}