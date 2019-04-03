// BJ_16234.cpp : 이 파일에는 'main' 함수가 포함됩니다. 거기서 프로그램 실행이 시작되고 종료됩니다.
//

#include "pch.h"
#include <iostream>
#include <cstring>
#include <queue>

using namespace std;

typedef struct POINT {
	int i;
	int j;
} Point;

typedef struct ASSOCIATION {
	int aCount;
	int aSum;
	Point associArray[2500];
} Association;

int N, L, R;
int input[52][52];
int visited[52][52];
int direction[2][4] = { {0,1,0,-1},{1,0,-1,0} };
int flag;
int moveCount;
queue<Association> ass;

void dfs(int i, int j, int *_count, int *sum, Point sArray[]) {
	if (input[i][j] == -1)
		return;
	visited[i][j] = 1;
	sArray[*_count].i = i;
	sArray[*_count].j = j;
	(*_count)++;
	*sum += input[i][j];
	for (int k = 0; k < 4; k++) {
		int next_i = i + direction[0][k];
		int next_j = j + direction[1][k];
		int gap = abs(input[i][j] - input[next_i][next_j]);
		if (visited[next_i][next_j] == 1) continue;
		if (L <= gap && R >= gap) {
			dfs(next_i, next_j, _count, sum, sArray);
		}	
	}
}

int main()
{
	memset(input, -1, sizeof(input));
	scanf("%d %d %d", &N, &L, &R);
	for (int i = 1; i <= N; i++)
		for (int j = 1; j <= N; j++)
			scanf("%d", &input[i][j]);
	while (true) {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				int _count = 0;
				int sum = 0;
				Association a;
				dfs(i, j, &_count, &sum, a.associArray);
				if (_count != 1) {
					a.aCount = _count;
					a.aSum = sum;
					ass.push(a);
				}
				else ++flag;
			}
		}
		while (!ass.empty()) {
			int avg = ass.front().aSum / ass.front().aCount;
			for (int i = 0; i < ass.front().aCount; i++) {
				input[ass.front().associArray[i].i][ass.front().associArray[i].j] = avg;
			}
			ass.pop();
		}
		if (flag == N * N) {
			break;
		}
		flag = 0;
		moveCount++;
		memset(visited, 0, sizeof(visited));
	}
	printf("%d\n", moveCount);
}

// 프로그램 실행: <Ctrl+F5> 또는 [디버그] > [디버깅하지 않고 시작] 메뉴
// 프로그램 디버그: <F5> 키 또는 [디버그] > [디버깅 시작] 메뉴

// 시작을 위한 팁: 
//   1. [솔루션 탐색기] 창을 사용하여 파일을 추가/관리합니다.
//   2. [팀 탐색기] 창을 사용하여 소스 제어에 연결합니다.
//   3. [출력] 창을 사용하여 빌드 출력 및 기타 메시지를 확인합니다.
//   4. [오류 목록] 창을 사용하여 오류를 봅니다.
//   5. [프로젝트] > [새 항목 추가]로 이동하여 새 코드 파일을 만들거나, [프로젝트] > [기존 항목 추가]로 이동하여 기존 코드 파일을 프로젝트에 추가합니다.
//   6. 나중에 이 프로젝트를 다시 열려면 [파일] > [열기] > [프로젝트]로 이동하고 .sln 파일을 선택합니다.
