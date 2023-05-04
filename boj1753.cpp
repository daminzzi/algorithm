#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
#define INF 1e9

using namespace std;

int V, E;
int st;
vector<pair<int, int>>* edge;
int* dist;

void dijkstra(int st) {
	priority_queue<pair<int, int>> pq;
	pq.push({ 0, st });
	dist[st] = 0;

	while (!pq.empty()) {
		int d = -pq.top().first;
		int now = pq.top().second;

		pq.pop();

		if (dist[now] < d) continue;

		for (int i = 0; i < edge[now].size(); i++) {
			int cost = d + edge[now][i].second;

			if (cost < dist[edge[now][i].first]) {
				dist[edge[now][i].first] = cost;
				pq.push({-cost, edge[now][i].first });
			}
		}
	}
}

int main() {
	ios_base::sync_with_stdio(0); cin.tie(0);
	cin >> V >> E;
	cin >> st;
	
	edge = new vector<pair<int, int>>[V+1];
	dist = new int[V+1];
	int u, v, w;

	for (int i = 1; i <= V; i++) {
		dist[i] = INF;
	}

	for (int i = 0; i < E; i++) {
		cin >> u >> v >> w;
		edge[u].push_back(make_pair(v, w));
	}
	
	dijkstra(st);

	for (int i = 1; i <= V; i++) {
		if (dist[i] == INF) cout << "INF\n";
		else cout << dist[i] <<'\n';
	}	
}