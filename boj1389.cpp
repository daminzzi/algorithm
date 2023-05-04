#include <iostream>
#include <vector>
#include <queue>
#include <cstring>
#define MAX 1e9

using namespace std;

int N, M;
vector<int> map[101];
int bacon[101];
bool visited[101];
queue<int> q;

void bfs(int st) {
	q.push(st);
	visited[st] = true;
	while (!q.empty()) {
		int f = q.front();
		q.pop();
		for (int i = 0; i < map[f].size(); i++) {
			int next = map[f][i];
			if (!visited[next]) {
				bacon[next] = bacon[f] + 1;
				q.push(next);
				visited[next] = true;
			}
		}
	}
}

int main() {
	ios_base::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	cin >> N >> M;
	
	int a, b;
	for (int i = 0; i < M; i++) {
		cin >> a >> b;
		map[a].push_back(b);
		map[b].push_back(a);
	}
	
	int min = MAX;
	int res = -1;
	for (int i = 1; i <= N; i++) {
		bfs(i);
		for (int j = 1; j <= N; j++) cout << bacon[j] << ' ';
		cout << endl;
		int sum = 0;
		for (int j = 1; j <= N; j++) sum += bacon[j];
		if (min > sum) {
			min = sum;
			res = i;
		}
		memset(visited, false, sizeof(visited));
		memset(bacon, 0, sizeof(bacon));
	}

	cout << res;

	return 0;
}