#include <iostream>
#include <vector>
#include <cmath>

using namespace std;

vector<int> vec;
int K, temp;

int main() {
	cin >> K;
	int num = 1;

	for (int i = 0; i < K; i++) {
		num *= 2;
	}
	num--;

	for (int i = 0; i < num; i++) {
		cin >> temp;
		vec.push_back(temp);
	}

	int idx = num / 2;
	int d = num + 1;
	num = 1;
	for (int i = 0; i < K; i++) {
		for (int j = 0; j < num; j++) {
			cout << vec[idx + j * d] << ' ';
			
		}
		cout << '\n';
		num *= 2;
		idx /= 2;
		d /= 2;
	}
}