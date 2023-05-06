#include <iostream>
#include <string>

using namespace std;

int T;
int num[11][7] = {
	{0, 0, 0, 0, 0, 0, 0},
	{1, 1, 1, 1, 1, 1, 0},
	{0, 1, 1, 0, 0, 0, 0},
	{1, 1, 0, 1, 1, 0 ,1},
	{1, 1, 1, 1, 0, 0, 1},
	{0, 1, 1, 0, 0, 1, 1},
	{1, 0, 1, 1, 0, 1, 1},
	{1, 0, 1, 1, 1, 1, 1},
	{1, 1, 1, 0, 0, 1, 0},
	{1, 1, 1, 1, 1, 1, 1},
	{1, 1, 1, 1, 0, 1, 1}
};

int XOR ( int* a, int* b ) {
	int ret = 0;
	for (int i = 0; i < 7; i++) {
		if (a[i] != b[i]) ret++;
	}
	return ret;
}

int main() {
	cin >> T;
	for (int i = 0; i < T; i++) {
		string a, b;
		string a1 = "-----";
		string b1 = "-----";
		cin >> a >> b;
		a1.replace(5 - a.size(), a.size(), a);
		b1.replace(5 - b.size(), b.size(), b);
		int tempa, tempb;
		int sum = 0;
		for (int j = 0; j < 5; j++) {
			if (a[j] == '-') tempa = -1;
			else tempa = a[j] - '0';
			if (b[j] == '-') tempb = -1;
			else tempb = b[j] - '0';
			sum += XOR(num[tempa + 1], num[tempb + 1]);
		}
		cout << sum << '\n';
	}
}