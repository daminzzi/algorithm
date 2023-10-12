# [Gold V] IPv6 - 3107 

[문제 링크](https://www.acmicpc.net/problem/3107) 

### 성능 요약

메모리: 11456 KB, 시간: 76 ms

### 분류

구현, 문자열

### 제출 일자

2023년 10월 12일 14:31:02

### 문제 설명

<p>IPv6은 길이가 128비트인 차세대 인터넷 프로토콜이다.</p>

<p>IPv6의 주소는 32자리의 16진수를 4자리씩 끊어 나타낸다. 이때, 각 그룹은 콜론 (:)으로 구분해서 나타낸다.</p>

<p>예를 들면, 다음과 같다.</p>

<pre>2001:0db8:85a3:0000:0000:8a2e:0370:7334</pre>

<p>32자리의 16진수는 사람이 읽고 쓰기에 불편하고, 대부분의 자리가 0이기 때문에 아래와 같이 축약할 수 있다.</p>

<ol>
	<li>각 그룹의 앞자리의 0의 전체 또는 일부를 생략 할 수 있다. 위의 IPv6을 축약하면, 다음과 같다</li>
</ol>

<pre>2001:db8:85a3:0:00:8a2e:370:7334</pre>

<ol start="2">
	<li>만약 0으로만 이루어져 있는 그룹이 있을 경우 그 중 한 개 이상 연속된 그룹을 하나 골라 콜론 2개(::)로 바꿀 수 있다.</li>
</ol>

<pre>2001:db8:85a3::8a2e:370:7334</pre>

<p>2번째 규칙은 모호함을 방지하기 위해서 오직 한 번만 사용할 수 있다.</p>

<p>올바른 축약형 IPv6주소가 주어졌을 때, 이를 원래 IPv6 (32자리의 16진수)로 복원하는 프로그램을 작성하시오.</p>

### 입력 

 <p>첫째 줄에 올바른 IPv6 주소가 주어진다. 이 주소는 최대 39글자이다. 또한, 주소는 숫자 0-9, 알파벳 소문자 a-f, 콜론 :으로만 이루어져 있다.</p>

### 출력 

 <p>첫째 줄에, 입력으로 주어진 IPv6의 축약되지 않은 형태를 출력한다.</p>

---

### 접근 아이디어

1. 입력된 주소의 콜론 개수를 센다
    
    몇개의 주소 블록이 생략되었는지 확인할 수 있음
    
2. 입력된 주소의 제일 뒤부터 주소를 읽으면서 변환한다
    
    이 때 콜론 그룹이 있는 경우 생략된 콜론 그룹만큼 0000 을 주소 블록에 집어넣는다
    
    즉 1번 과정에서 5개의 콜론이 나오는 경우 ex) aa:bb:cc::dd:ee 3개의 주소 블록이 생략된 것인데
    
    이 때 cnt와 colon의 변화를 살펴보면 아래 표와 같은데 생략된 주소 블록의 경우 cnt를 colon의 개수와 맞을 때까지 줄여주면서 주소를 정비한다.
    
    |  | ee | dd | :: | :: | :: | cc | bb | aa |  |
    | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
    | cnt | 7 | 6 | 5 | 4 | 3 | 2 | 1 | 0 |  |
    | colon | 5 | 4 | 3 | 3 | 3 | 2 | 1 | 0 |  |
3. 마지막에 buf에 저장된 주소만 없애주면 끝!

### 구현 코드

```java
package algorithm_study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ3107_IPv6 {

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
				System.out.println(cnt+" "+colon);
				address[cnt--] = String.valueOf(buf);
				colon--;
				buf = new char[] { '0', '0', '0', '0' };
				bidx = 3;
				if(i> 0 && ip.charAt(i-1)==':') { //콜론 그룹이라면
					while(colon <= cnt) { //생성되어야 하는 그룹 수 만큼
						//주소값에 0000 채우기
						address[cnt--] = String.valueOf(buf);
					}
					colon--;
					i--; //콜론 그룹에 있는 콜론을 다시 읽는 것을 방지하기 위해서 i--;
				}
			} else { //버퍼 뒤에서부터 채워서 000* -> 00** -> 0*** -> **** 이런식으로 버퍼가 채워짐 
				buf[bidx--] = ip.charAt(i);
			}
		}
		
		//버퍼에 남은 첫번째 주소 블록을 채워주고
		address[cnt--] = String.valueOf(buf);

		//답을 프린트
		for (int i = 0; i < 7; i++) {
			System.out.print(address[i].toString() + ":");
		}
		System.out.println(address[7].toString());
	}

}
```
