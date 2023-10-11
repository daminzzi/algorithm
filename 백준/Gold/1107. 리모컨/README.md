# [Gold V] 리모컨 - 1107 

[문제 링크](https://www.acmicpc.net/problem/1107) 

### 성능 요약

메모리: 121836 KB, 시간: 256 ms

### 분류

브루트포스 알고리즘

### 제출 일자

2023년 10월 10일 18:23:52

### 문제 설명

<p>수빈이는 TV를 보고 있다. 수빈이는 채널을 돌리려고 했지만, 버튼을 너무 세게 누르는 바람에, 일부 숫자 버튼이 고장났다.</p>

<p>리모컨에는 버튼이 0부터 9까지 숫자, +와 -가 있다. +를 누르면 현재 보고있는 채널에서 +1된 채널로 이동하고, -를 누르면 -1된 채널로 이동한다. 채널 0에서 -를 누른 경우에는 채널이 변하지 않고, 채널은 무한대 만큼 있다.</p>

<p>수빈이가 지금 이동하려고 하는 채널은 N이다. 어떤 버튼이 고장났는지 주어졌을 때, 채널 N으로 이동하기 위해서 버튼을 최소 몇 번 눌러야하는지 구하는 프로그램을 작성하시오. </p>

<p>수빈이가 지금 보고 있는 채널은 100번이다.</p>

### 입력 

 <p>첫째 줄에 수빈이가 이동하려고 하는 채널 N (0 ≤ N ≤ 500,000)이 주어진다.  둘째 줄에는 고장난 버튼의 개수 M (0 ≤ M ≤ 10)이 주어진다. 고장난 버튼이 있는 경우에는 셋째 줄에는 고장난 버튼이 주어지며, 같은 버튼이 여러 번 주어지는 경우는 없다.</p>

### 출력 

 <p>첫째 줄에 채널 N으로 이동하기 위해 버튼을 최소 몇 번 눌러야 하는지를 출력한다.</p>

 ---

### 문제 요약

리모컨 숫자 버튼 몇가지가 고장이 났고, 최소한의 버튼을 눌러서 목표 번호까지 이동하고자 한다.

시작 번호는 100, 채널 번호는 0~무한대, 고장난 버튼 수는 0~10, 목표 채널 번호는 0~500,000이다.

### 접근 아이디어

일단 제일 첫번째로 생각할 수 있는 방법은 100에서 +, - 버튼 둘 중 하나만 눌러보면서 목표번호까지 이동하는 것이다. 이를 기준점으로 잡고 또 다른 방법을 탐색해보고 다른 방법들이 첫번째 방식보다 작다면 그 값이 버튼을 누르는 최소횟수일 것이다.

또 다른 방법 중 하나는 목표로 하는 채널에 대해서 가장 가까운 누를 수 있는 채널을 찾아서 +, -로 목표 채널까지 이동하는 것이다. 이때 필요한 누르는 버튼의 횟수는 `(누를 수 있는 채널의 자릿수 + +,- 버튼을 누르는 횟수)`이다. 이 값이 첫 번째 방법으로 구한 횟수보다 작다면 이 값을 최소 횟수로 정할 수 있고, 이보다 크다면 목표채널에서 더 먼 숫자는 탐색을 할 필요는 없다. 더 먼 수를 찾는다면 버튼을 누르는 횟수는 늘어날 수 밖에 없기 때문에 첫 번째 방법으로 구한 횟수가 답이 될 것이다.

### 작성 코드

```java
 import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static String N, M;
	static int ans;
	static boolean[] isBroken = new boolean[10];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = st.nextToken();
		
		st = new StringTokenizer(br.readLine());
		int k = Integer.parseInt(st.nextToken());
		if(k != 0) {
			st = new StringTokenizer(br.readLine());
		
			for(int i = 0; i<k; i++) {
				isBroken[Integer.parseInt(st.nextToken())] = true;
			}
		}		
		
		int num = Integer.parseInt(N);
		ans = Math.abs(num-100); //+, -만 가지고 N까지 이동하는데 필요한 횟수
		
		if(k == 10) {
			System.out.println(ans);
			return;
		}
		
		int diff = 0; //n과 수 사이의 차이
		
		while(true) {
			int plus = num+diff;
			int minus = num-diff;
			String p = Integer.toString(plus);
			String m = Integer.toString(minus);
			int ptemp = Integer.MAX_VALUE; //해당 번호까지 이동에 필요한 횟수
			int mtemp = Integer.MAX_VALUE; //해당 번호까지 이동에 필요한 횟수
//			System.out.println(plus+" "+minus);
			
			if(isPossible(plus)) {
				ptemp = p.length()+diff;
			}
			
			if(minus >= 0 && isPossible(minus)) {
				mtemp = m.length()+diff;
			}
			
			int min = Math.min(ptemp, mtemp);
			if(min < Integer.MAX_VALUE) {
				if(min >= ans) break;
//				System.out.println(ans+" "+p+" "+m);
				System.out.println(min);
				return;
			}
			
			if(ans < diff+p.length()) {
//				System.out.println(p+" "+m);
				break;
			}
			
//			ans = Math.min(ptemp, mtemp);
			diff++;
		}
		
		System.out.println(ans);
		
	}
	
	static boolean isPossible(int num) {
		String str = Integer.toString(num);
		for(int i = 0; i<str.length(); i++) {
			if(isBroken[str.charAt(i)-'0']) {
				return false;
			}
		}
		return true;
	}

}
```

