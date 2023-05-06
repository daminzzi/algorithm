#include <iostream>

using namespace std;

int M, N;
int num[1000001];

void primeNum(int m, int n) {
	for (int i = 2; i <= n; i++) {
		num[i] = i;
	}
	for (int i = 2; i <= n; i++) {
		if (num[i] == 0) continue;
		for (int j = 2 * i; j <= n; j += i) {
			num[j] = 0;
		}
	}
	for (int i = m; i <= n; i++) {
		if (num[i] != 0) cout << num[i] << '\n';
	}
}

int main() {
	ios_base::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	cin >> M >> N;
	primeNum(M, N);
}