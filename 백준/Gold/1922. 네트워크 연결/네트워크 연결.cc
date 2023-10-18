#include <stdio.h>
#include <algorithm>

std::pair <int, std::pair<int, int> > p[100050];
int N, M;
int a,b,c; // for temporary input
int ans = 0;

namespace Kruskal{
	int parent[100050];
	int component;

	void init(int n){
		component = n;
		for(int i = 0; i < n; i++){
			parent[i] = i;
		}
	}

	int find(int u){
		if(u == parent[u]) return u;
		return parent[u] = find(parent[u]);
	}

	bool merge(int u, int v){
		u = find(u); v = find(v);
		if(u == v) return false;
		parent[u] = v;
		component--;
		return true;
	}
}

void input(){
	scanf("%d %d",&N,&M);
	for(int i = 0; i < M; i++){
		scanf("%d %d %d",&a, &b, &c);
		p[i].first = c;
		p[i].second = std::make_pair(a,b);
	}
	std::sort(p, p+M);
	Kruskal::init(N);
}

void solve(){
	int idx = 0;
	while(Kruskal::component > 1){
		if(Kruskal::merge(p[idx].second.first, p[idx].second.second)){
			ans += p[idx].first;
		}
		idx++;
	}
}

int output(){
	printf("%d\n",ans);
	return 0;
}

int main(){
	//freopen("input.txt","r",stdin);
	input();
	solve();
	output();
	return 0;
}