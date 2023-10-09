# [Bronze V] Lucky 7 - 30224

[문제 링크](https://www.acmicpc.net/problem/30224)

### 성능 요약

메모리: 11468 KB, 시간: 80 ms

### 분류

구현, 수학

### 제출 일자

2023년 10월 9일 17:39:55

- 문제
    
    ### 문제 설명
    
    Fact or Fiction, some people consider 7 to be a lucky digit/number.
    
    Given a number, determine how lucky the number is by printing one of four values:
    
    Print 0 if the number does not contain 7 and is not divisible by 7.
    Print 1 if the number does not contain 7 but is divisible by 7.
    Print 2 if the number does contain 7 but is not divisible by 7.
    Print 3 if the number does contain 7 and is divisible by 7.
    
    ### 입력
    
    There is only one input line; it contains an integer between 1 and 10^9, inclusive.
    
    ### 출력
    
    Print one of the four messages as described above.
    

---


### 문제 요약

간단한 입출력 관련 문제이지만 영어로 되어 있는 것이 함정~!

7의 포함 여부와 7로 나누어 떨어지는지를 확인하고 그에 맞는 타입을 출력한다.

### 작성 코드

```jsx
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
```
