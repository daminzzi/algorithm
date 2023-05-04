#include<iostream>
#include<vector>

using namespace std;

int N, M, maximum;
vector<pair<int, int>> arr1;
vector<pair<int, int>> arr2;
int nidx, midx;

int main(int argc, char** argv)
{
	cin >> N >> M;
	int temp1, temp2;
	for (int i = 0; i < N; i++) {
		cin >> temp1 >> temp2;
		if (i != 0) temp1 += arr1.back().first;
		arr1.push_back(make_pair(temp1, temp2));
	}
	for (int i = 0; i < M; i++) {
		cin >> temp1 >> temp2;
		if(i!=0) temp1 += arr2.back().first;
		arr2.push_back(make_pair(temp1, temp2));
	}
	int comp1, comp2;
	while (nidx < N && midx < M) {
		comp1 = arr2[midx].second - arr1[nidx].second;
		if (maximum < comp1) maximum = comp1;
		if (arr1[nidx].first > arr2[midx].first) {
			midx++;
		}
		else if (arr1[nidx].first == arr2[midx].first) {
			nidx++; midx++;
		}
		else nidx++;
	}
	cout << maximum;
	return 0;
}