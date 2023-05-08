#include <iostream>
#include <vector>

using namespace std;

int T, N;

int main() {
	cin >> T;
	while (T--) {
		cin >> N;
		if (!N) {
			cout << 0 << '\n';
			continue;
		}
		vector<pair<string, int>> closet;
		string name, type;
		
		for (int i = 0; i < N; i++) {
			cin >> name >> type;
			int flag = 0;
			for (int i = 0; i < closet.size(); i++) {
				if (closet[i].first == type) {
					closet[i].second++;
					flag = 1;
					break;
				}
			}
			if (!flag) {
				closet.push_back({ type, 1 });
			}
		}
		int res = 1;
		for (int i = 0; i < closet.size(); i++) {
			res *= (1 + closet[i].second);
		}
		cout << res - 1 << '\n';
	}
}
