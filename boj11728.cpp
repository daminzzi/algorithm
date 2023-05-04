#include <iostream>
#include <vector>

using namespace std;
int N, M;
vector<int> arr1;
vector<int> arr2;
vector<int> total;
int nidx, midx;

int main() {
	ios_base::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	cin >> N >> M;
	int temp;
	for (int i = 0; i < N; i++) {
		cin >> temp;
		arr1.push_back(temp);
	}
	for (int i = 0; i < M; i++) {
		cin >> temp;
		arr2.push_back(temp);
	}

	while (nidx < N && midx < M) {
		if (arr1[nidx] <= arr2[midx]) {
			total.push_back(arr1[nidx++]);
		}
		else {
			total.push_back(arr2[midx++]);
		}
	}
	if (nidx < N) {
		while (nidx != N) {
			total.push_back(arr1[nidx++]);
		}
	}
	if (midx < M) {
		while (midx != M) {
			total.push_back(arr2[midx++]);
		}
	}

	for (int i = 0; i < total.size(); i++) {
		cout << total[i] << ' ';
	}
}