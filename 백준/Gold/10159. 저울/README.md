# [Gold IV] 저울 - 10159 

[문제 링크](https://www.acmicpc.net/problem/10159) 

### 성능 요약

메모리: 12812 KB, 시간: 116 ms

### 분류

플로이드–워셜, 그래프 이론, 그래프 탐색, 최단 경로

### 제출 일자

2023년 10월 25일 00:29:37

### 문제 설명

<p>무게가 서로 다른 N 개의 물건이 있다. 각 물건은 1부터 N 까지 번호가 매겨져 있다. 우리는 일부 물건 쌍에 대해서 양팔 저울로 어떤 것이 무거운 것인지를 측정한 결과표를 가지고 있다. 이 결과표로부터 직접 측정하지 않은 물건 쌍의 비교 결과를 알아낼 수도 있고 알아내지 못할 수도 있다. 예를 들어, 총 6개의 물건이 있고, 다음 5개의 비교 결과가 주어졌다고 가정하자. ([1]은 1번 물건의 무게를 의미한다.)</p>

<p>[1]>[2], [2]>[3], [3]>[4], [5]>[4], [6]>[5]</p>

<p>우리는 [2]>[3], [3]>[4]로부터 [2]>[4]라는 것을 알 수 있다. 하지만, 물건 2와 물건 6을 비교하는 경우, 앞서의 결과만으로는 어느 것이 무거운지 알 수 없다. 이와 같이, 물건 2는 물건 1, 3, 4와의 비교 결과는 알 수 있지만, 물건 5, 6과의 비교 결과는 알 수 없다. 물건 4는 모든 다른 물건과의 비교 결과를 알 수 있다. </p>

<p>비교 결과가 모순되는 입력은 없다고 가정한다. 위 예제의 기존 측정 결과에 [3]>[1]이 추가되었다고 가정하자. 이 경우 [1]>[2], [2]>[3]이므로 우리는 [1]>[3]이라는 것을 예측할 수 있는데, 이는 기존에 측정된 결과 [3]>[1]과 서로 모순이므로 이러한 입력은 가능하지 않다. </p>

<p>물건의 개수 N 과 일부 물건 쌍의 비교 결과가 주어졌을 때, 각 물건에 대해서 그 물건과의 비교 결과를 알 수 없는 물건의 개수를 출력하는 프로그램을 작성하시오. </p>

### 입력 

 <p>첫 줄에는 물건의 개수 N 이 주어지고, 둘째 줄에는 미리 측정된 물건 쌍의 개수 M이 주어진다. 단, 5 ≤ N ≤ 100 이고, 0 ≤ M ≤ 2,000이다. 다음 M개의 줄에 미리 측정된 비교 결과가 한 줄에 하나씩 주어진다. 각 줄에는 측정된 물건 번호를 나타내는 두 개의 정수가 공백을 사이에 두고 주어지며, 앞의 물건이 뒤의 물건보다 더 무겁다.</p>

### 출력 

 <p>여러분은 N개의 줄에 결과를 출력해야 한다. i 번째 줄에는 물건 i 와 비교 결과를 알 수 없는 물건의 개수를 출력한다.</p>

---

### 문제 요약

정점 간의 크기 정보가 주어질 때, 주어진 정보 만으로는 크기 비교를 확실히 할 수 없는 정점이 있을 수 있다. 1 > 3 과 1 > 2가 주어졌을 때, 1은 2와 3보다 크지만, 2와 3 중 누가 더 큰지는 주어진 정보만으로는 알 수 없다.

이때 각 정점 별로 크기 비교 결과를 알 수 없는 정점의 수를 구하자.

### 접근 아이디어

어떤 정점 v보다 작은 정점은 v보다 큰 정점과 크기 비교가 가능하다. 반대도 마찬가지.

따라서 초기 정보 입력 단계에서 v < w 라는 정보가 주어지면 visited[v][w]는 양수, visited[w][v]는 음수로 지정해주면 w < x 라는 정보가 있을 때 visited[v][w] == visited[w][x] 이므로 v는 x에 대한 크기 비교 결과를 얻을 수 있다. 음수인 경우에도 마찬가지..라고 쓰면서 근데 이거 음수는 안해봐도 되지 않나?

… 코드 고치고 다시 제출해보니까 음수인 경우는 체크하지 않아도 된다.

그렇다면 다시 한번 생각해보면 

1. v와 w 사이의 관계를 directed graph로 생각하자
2. v → w, w → x 라면 v → x 임.
3. 플로이드 워샬 알고리즘을 사용하면 각 정점에 대한 방문 가능 여부를 정리할 수 있음
4. 지금은 증가하는 경우만 체크했기 때문에 2번의 경우에서 x→v도 체크 가능하다는 것을 확인해줘야 한다. → 정점 개수 세면서 visited[v][w]와 visited[w][v] 둘다 확인하기

### 작성 코드

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10159 {

	static int N;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		int M = Integer.parseInt(br.readLine());
		
		boolean[][] visited = new boolean[N][N];
		
		for(int i = 0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken())-1;
			int w = Integer.parseInt(st.nextToken())-1;
			visited[v][w] = true;
		}
		
		for(int j = 0; j<N; j++) {
			for(int i = 0; i<N; i++) {
				for(int k = 0; k<N; k++) {
					if(!visited[i][j] || !visited[j][k]) continue;
					visited[i][k] = visited[i][j] == visited[j][k]? visited[i][j]:visited[i][k];
				}
			}
		}
		
		//결과 출력
		int cnt;
		for(int i = 0; i<N; i++) {
			cnt = N-1;
			for(int j = 0; j<N; j++) {
				if(i==j)
					continue;
				if(visited[i][j] || visited[j][i])
					cnt--;
			}
			sb.append(cnt).append('\n');
		}
		System.out.println(sb);
	}

}
```
