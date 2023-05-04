#include <iostream>

using namespace std;

int N, M;
int* arr;
int* visited;

void dfs(int num, int depth) {
	if (depth == M) {
		for (int i = 0; i < M; i++) {
			cout << visited[i] << ' ';
		}
		cout << '\n';
		return;
	}

	for (int i = num; i < N; i++) {
		visited[depth] = arr[i];
		dfs(i, depth + 1);
	}
}

int main() {
	ios_base::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	cin >> N >> M;
	arr = new int[N];
	visited = new int[M];
	for (int i = 0; i < N; i++) {
		arr[i] = i + 1;
	}

	dfs(0, 0);
}