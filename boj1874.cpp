#include <iostream>
#include <vector>
#include <stack>

using namespace std;

int N, temp;

stack<int> st;
vector<int> num;
vector<char> ret;

int nidx = 0, ridx = 0;

int main() {
	ios_base::sync_with_stdio(0); cin.tie(0);
	cin >> N;
	
	for (int i = 0; i < N; i++) {
		cin >> temp;
		num.push_back(temp);
	}

	for (int i = 1; i <= N; i++) {
		st.push(i);
		ret.push_back('+');

		while (!st.empty() && st.top() == num[nidx]) {
			st.pop();
			ret.push_back('-');
			nidx++;
		}
	}

	if (!st.empty()) cout << "NO";
	else {
		while (ridx < ret.size()) {
			cout << ret[ridx++] << '\n';
		}
	}
}