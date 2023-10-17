import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static char[] expression;
	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		expression = br.readLine().toCharArray();

		Deque<Integer> num = new ArrayDeque<>();
		Deque<Character> oper = new ArrayDeque<>();

		num.push(expression[0] - '0');
		dfs(1, false, num, oper);

		System.out.println(max);
	}

	static void dfs(int idx, boolean flag, Deque<Integer> num, Deque<Character> oper) {
		if (idx == N) {
			while (!oper.isEmpty()) {
				int first = num.removeLast();
				char o = oper.removeLast();
				int second = num.removeLast();
				num.add(calc(first, second, o));
			}
			int res = num.pop();
			if (res > max) {
				max = res;
			}
			return;
		}

		Deque<Integer> tnum = new ArrayDeque(num);
		Deque<Character> toper = new ArrayDeque(oper);

		if (flag) { // 이전 연산자에서 괄호를 적용함
			// 이번 연산자에서는 괄호 적용할 수 없음
			// 그럼 나랑 내 다음 연산자 넣고 다음으로 넘어가기
			tnum.push(expression[idx + 1] - '0');
			toper.push(expression[idx]);
			dfs(idx + 2, false, tnum, toper);
		} else {
			// 괄호 적용 o
			int t = tnum.pop();
			tnum.push(calc(t, expression[idx + 1] - '0', expression[idx]));
			dfs(idx + 2, true, tnum, toper);

			// 괄호 적용 x
			num.push(expression[idx + 1] - '0');
			oper.push(expression[idx]);
			dfs(idx + 2, false, num, oper);
		}
	}

	static int calc(int a, int b, char c) {
		switch (c) {
		case ('+'):
			return a + b;
		case ('-'):
			return a - b;
		default:
			return a * b;
		}
	}
}