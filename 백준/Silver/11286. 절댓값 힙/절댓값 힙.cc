#include <iostream>
#include <vector>
#include <queue>
#include <functional>
#include <algorithm>

using namespace std;
using ll = long long;
struct cmp {
	bool operator()(ll x, ll y) {
		if (abs(x) == abs(y)) return x > y;
		return abs(x) > abs(y);
	}
};
int N, temp;
priority_queue<ll, vector<ll>, cmp> pq;

int main() {
	ios::sync_with_stdio(0); cin.tie(0), cout.tie(0);
	//freopen("inp.txt", "r", stdin);
	cin >> N;
	while (N--) {
		cin >> temp;
		if (temp == 0) {
			if (!pq.empty()) {
				cout << pq.top() << '\n'; pq.pop();
			}
			else {
				cout << "0\n";
			}
		}
		else {
			pq.push(temp);
		}
	}
}