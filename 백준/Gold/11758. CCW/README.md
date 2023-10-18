# [[Gold V] CCW - 11758](https://github.com/daminzzi/algorithm/tree/main/%EB%B0%B1%EC%A4%80/Gold/11758.%E2%80%85CCW#gold-v-ccw---11758)

[문제 링크](https://www.acmicpc.net/problem/11758)

### [성능 요약](https://github.com/daminzzi/algorithm/tree/main/%EB%B0%B1%EC%A4%80/Gold/11758.%E2%80%85CCW#%EC%84%B1%EB%8A%A5-%EC%9A%94%EC%95%BD)

메모리: 11544KB, 시간: 76ms

### [분류](https://github.com/daminzzi/algorithm/tree/main/%EB%B0%B1%EC%A4%80/Gold/11758.%E2%80%85CCW#%EB%B6%84%EB%A5%98)

기하학

### [제출 일자](https://github.com/daminzzi/algorithm/tree/main/%EB%B0%B1%EC%A4%80/Gold/11758.%E2%80%85CCW#%EC%A0%9C%EC%B6%9C-%EC%9D%BC%EC%9E%90)

2023년 10월 18일 23:47:45

- 문제
    
    ### [문제 설명](https://github.com/daminzzi/algorithm/tree/main/%EB%B0%B1%EC%A4%80/Gold/11758.%E2%80%85CCW#%EB%AC%B8%EC%A0%9C-%EC%84%A4%EB%AA%85)
    
    2차원 좌표 평면 위에 있는 점 3개 P1, P23가 주어진다. P를 순서대로 이은 선분이 어떤 방향을 이루고 있는지 구하는 프로그램을 작성하시오.
    
    ### [입력](https://github.com/daminzzi/algorithm/tree/main/%EB%B0%B1%EC%A4%80/Gold/11758.%E2%80%85CCW#%EC%9E%85%EB%A0%A5)
    
    첫째 줄에 P1의 (x, y), 둘째 줄에 P2), 셋째 줄에 P3)가 주어진다. (-10,000 ≤ x, x ≤ 10,000) 모든 좌표는 정수이다. P, P의 좌표는 서로 다르다.
    
    ### [출력](https://github.com/daminzzi/algorithm/tree/main/%EB%B0%B1%EC%A4%80/Gold/11758.%E2%80%85CCW#%EC%B6%9C%EB%A0%A5)
    
    P1, P23를 순서대로 이은 선분이 반시계 방향을 나타내면 1, 시계 방향이면 -1, 일직선이면 0을 출력한다.
    

---

### 문제 요약

세 점을 차례로 이었을 때 어떤 방향을 이루고 있는지?

### 접근 아이디어

문제 해결에 대한 초기 접근 아이디어는 아래와 같다.

1. 문제에서 P1과 P3를 이은 선분을 확장시켜 직선의 방정식을 만들 수 있다.
2. x1과 x3를 비교했을 때 x1 < x3 이면 직선의 방정식에 P2의 x좌표를 대입했을 때 실제 P2의 y좌표보다 방정식에 대입한 값이 더 크다면 반시계 방향, 더 작다면 시계방향으로 돈다.
3. x1 > x3라면 2번을 반대로 적용할 수 있다.
4. x1 == x3라면 기울기를 사용할 수 없기 때문에 다른 방식으로 비교해야 된다.

처음에는 직선의 방정식을 구해서 접근하는 방식을 생각했는데, double 자료형의 표현 방식인 부동소수점으로 인해서 epsilon을 적용해서 차이를 적용하는 방법을 적용해봐도 계속 틀렸습니다가 나왔다. 

그래서 double형을 쓰지 않고 풀 수 있는 방법이 있을지 고민해보다가

어차피 각 x좌표들의 차, y좌표들의 차는 정수이기 때문에 기울기의 분모에 해당하는 x좌표들의 차를 직선의 방정식 양변에 곱한다면 실수 자료형 없이 값을 비교할 수 있다는 것을 생각했다. 또한 이러한 방식으로 문제를 해결한다면 원래는

`y= ax+b` 로 표현되던 식이 `(x1-x3)y = (y1-y3)x + y1*(x1-x3) - (y1-y3)*x1` 조금은 복잡하지만 이런 형태로 바뀌는데 이렇게 되면 아까 초기 접근 아이디어에서 2,3,4번을 생각할 필요 없이(x1-x3를 양 변에 곱했기 때문에) 직선의 방정식에서 x, y에 x2, y2를 대입한 값 좌변, 우변의 크기 비교만 해주면 된다.

### 작성 코드

```java
import java.io.*;
import java.util.*;

public class BOJ11758_CCW {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int[] x = new int[3];
		int[] y = new int[3];
		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			x[i] = Integer.parseInt(st.nextToken());
			y[i] = Integer.parseInt(st.nextToken());
		}

		int a = y[0] - y[2];
		int b = y[0] * (x[0] - x[2]) - a * x[0];
		int tX = a * x[1] + b;
		int tY = y[1] * (x[0] - x[2]);
		// System.out.printf("%d*y=%d*x+%d\n", x[0]-x[2], a, b);
		// System.out.println(tY+" "+tX);

		if (tY == tX) {
			System.out.println(0);
			return;
		} else if (tY < tX) {
			System.out.println(-1);
			return;
		} else {
			System.out.println(1);
			return;
		}

	}

}
```
