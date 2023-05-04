#include <iostream>
#include <vector>

using namespace std;

int dx[8] = { 1, 1, 0, -1, -1, -1, 0, 1 };
int dy[8] = { 0, 1, 1, 1, 0, -1, -1, -1 };

int** map;
int** visited;
int w, h;

void dfs(int y, int x) {
	for (int i = 0; i < 8; i++) {
		int nx = x + dx[i];
		int ny = y + dy[i];
		if (nx < 0 || ny < 0 || nx >= w || ny >= h) continue;
		if (!visited[ny][nx] && map[ny][nx]) {
			visited[ny][nx] = 1;
			dfs(ny, nx);
		}
	}
}

int solve(int w, int h) {
	map = new int* [h];
	visited = new int* [h];
	int ret = 0;
	for (int i = 0; i < h; i++) {
		map[i] = new int[w];
		visited[i] = new int[w];
	}
	for (int i = 0; i < h; i++) {
		for (int j = 0; j < w; j++) {
			cin >> map[i][j];
			visited[i][j] = 0;
		}
	}
	
	for (int i = 0; i < h; i++) {
		for (int j = 0; j < w; j++) {
			if (!visited[i][j] && map[i][j]) {
				visited[i][j] = 1;
				dfs(i, j);
				ret++;
			}
		}
	}

	delete[] map;
	delete[] visited;
	return ret;
}

int main() {
	ios_base::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	while (1) {
		cin >> w >> h;
		if (!w&& !h) break;
		cout << solve(w, h) << '\n';
	}
	return 0;
}