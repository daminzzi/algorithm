#include<iostream>
using namespace std;
#define MAX 40

int N;
bool isused1[MAX]; // 같은 열에 존재하는 퀸이 존재하는지 여부
bool isused2[MAX]; // 우상 대각선에 존재하는 퀸이 존재하는 여부
bool isused3[MAX]; // 좌상 대각선에 존재하는 퀸이 존재하는 여부
int cnt = 0; // 만족하는 배치 갯수 저장 

void solve(int level) {
	if (level == N) {
		cnt++;
		return;
	}
	for (int i = 0; i < N; i++) {
		if (isused1[i] || isused2[i+level] || isused3[level-i+N-1])continue; 
		isused1[i] = true;  
		isused2[i+level] = true;
		isused3[level-i+N-1] = true;
		solve(level + 1);
		isused1[i] = false;
		isused2[i + level] = false;
		isused3[level - i + N - 1] = false;
	}
}

int main() {
	cin >> N;
	solve(0);
	cout << cnt;
}