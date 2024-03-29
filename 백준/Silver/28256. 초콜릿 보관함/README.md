# [Silver II] 초콜릿 보관함 - 28256 

[문제 링크](https://www.acmicpc.net/problem/28256) 

### 성능 요약

메모리: 11872 KB, 시간: 96 ms

### 분류

그래프 이론, 그래프 탐색, 구현, 정렬, 문자열

### 제출 일자

2024년 2월 20일 10:52:28

### 문제 설명

<p>코코는 $3\times 3$의 사각 격자 모양의 초콜릿 보관함을 갖고 있다. 이 보관함은 가운데 칸이 막혀 있고 그 자리에 숫자를 표시하는 화면이 붙어 있다. 나머지 8칸에는 초콜릿을 최대 하나씩 보관할 수 있다.</p>

<p>화면에는 숫자가 최대 4개까지 표시되고, 각각의 숫자는 초콜릿이 들어있는 연결된 칸의 개수를 나타낸다. 숫자가 여러 개이면 오름차순으로 표시된다. 두 칸이 한 변을 따라 맞닿아 있으면 그 두 칸은 연결되어 있다고 한다.</p>

<p style="text-align: center;"><img alt="" src="" style="width: 720px; height: 180px;"></p>

<p>코코는 똑같은 초콜릿 보관함을 하나 더 만들어서 한별이에게 선물하려고 한다. 버그가 있을지 모른다고 걱정하는 코코를 위해, 보관함의 테스트를 도와주자.</p>

### 입력 

 <p>첫 줄에는 테스트 케이스의 개수 $T$가 주어진다. ($1\le T\le 100$)</p>

<p>각 테스트 케이스는 4줄로 이루어져 있다. 첫 3줄에는 초콜릿 보관함의 상태가 주어진다. <code>O</code>는 그 칸에 초콜릿이 있음, <code>X</code>는 없음을 뜻하며, 가운데 칸은 <code>-</code>로 표시된다. 4번째 줄에는 화면에 표시된 숫자의 개수 $n$과 숫자의 목록 $a_1,a_2,\cdots ,a_n$이 순서대로 주어진다. ($0\le n\le 4$, $1\le a_1\le a_2\le\cdots\le a_n\le 8$)</p>

### 출력 

 <p>각 테스트 케이스마다, 화면의 표시가 올바르다면 <code>1</code>, 아니라면 <code>0</code>을 출력한다.</p>

