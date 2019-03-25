// BJ_14502.cpp : 이 파일에는 'main' 함수가 포함됩니다. 거기서 프로그램 실행이 시작되고 종료됩니다.
//

#include "pch.h"
#include <iostream>
#include <cstring>

typedef struct POINT {
	int i;
	int j;
} Point;

int N, M;
int input[10][10];
int copyInput[10][10];
int numberOfCase[3];
int zeroCount;
int virusCount;
int max;
Point zeroPosition[64];
Point virusPosition[64];

int direction[2][4] = { {0, 1, 0, -1},
					   {1, 0, -1, 0} };

void dfs(int i, int j) {
	input[i][j] = 2;
	for (int k = 0; k < 4; k++) {
		int next_i = i + direction[0][k];
		int next_j = j + direction[1][k];
		if (input[next_i][next_j] == 1 || input[next_i][next_j] == -1 || input[next_i][next_j] == 2)
			continue;
		dfs(next_i, next_j);
	}
}

void copy() {
	for (int i = 1; i <= N; i++)
		for (int j = 1; j <= M; j++)
			input[i][j] = copyInput[i][j];
}

void combination(int arr[], int index, int n, int r, int target) 
{
	if (r == 0) {
		int zCount = 0;
		for (int i = 0; i < 3; i++) {
			input[zeroPosition[arr[i]].i][zeroPosition[arr[i]].j] = 1;
		}
		for (int i = 0; i < virusCount; i++) {
			dfs(virusPosition[i].i, virusPosition[i].j);
		}
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (input[i][j] == 0) zCount++;
			}
		}
		if (max < zCount) max = zCount;
		copy();
	} else if (target == n) return; 
	else {
		arr[index] = target;
		combination(arr, index + 1, n, r - 1, target + 1);
		combination(arr, index, n, r, target + 1);
	}
}

int main()
{
	memset(input, -1, sizeof(input));
	memset(copyInput, -1, sizeof(copyInput));
	scanf("%d %d", &N, &M);
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= M; j++) {
			scanf("%d", &input[i][j]);
			copyInput[i][j] = input[i][j];
			if (input[i][j] == 0) {
				zeroPosition[zeroCount].i = i;
				zeroPosition[zeroCount++].j = j;
			}
			if (input[i][j] == 2) {
				virusPosition[virusCount].i = i;
				virusPosition[virusCount++].j = j;
			}
		}
	}
	combination(numberOfCase, 0, zeroCount, 3, 0);
	printf("%d\n", max);
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
