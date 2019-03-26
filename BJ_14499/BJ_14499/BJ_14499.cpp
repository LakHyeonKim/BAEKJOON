// BJ_14499.cpp : 이 파일에는 'main' 함수가 포함됩니다. 거기서 프로그램 실행이 시작되고 종료됩니다.
//

#include "pch.h"
#include <iostream>
#include <cstring>

int N, M, X, Y, K;
int map[22][22];
int command[1001];
int direction[2][5] = { {0, 0, 0, -1, 1},{0, 1, -1, 0, 0} }; // 1:E, 2:W, 3:N, 4:S
int dice[7]; // 1:위, 2:북, 3:동, 4:아래, 5:남, 6:서

int move(int moveInfo) {
	int next_x = X + direction[0][moveInfo];
	int next_y = Y + direction[1][moveInfo];
	int temp1, temp2;
	if (map[next_x][next_y] == -1) return -1;
	else {
		switch (moveInfo)
		{
		case 1: //동쪽으로 이동
			temp1 = dice[4];
			dice[4] = dice[3];
			temp2 = dice[6];
			dice[6] = temp1;
			temp1 = dice[1];
			dice[1] = temp2;
			dice[3] = temp1;
			if (map[next_x][next_y] == 0)
				map[next_x][next_y] = dice[4];
			else {
				dice[4] = map[next_x][next_y];
				map[next_x][next_y] = 0;
			}
			break;
		case 2: //서쪽으로 이동
			temp1 = dice[4];
			dice[4] = dice[6];
			temp2 = dice[3];
			dice[3] = temp1;
			temp1 = dice[1];
			dice[1] = temp2;
			dice[6] = temp1;
			if (map[next_x][next_y] == 0)
				map[next_x][next_y] = dice[4];
			else {
				dice[4] = map[next_x][next_y];
				map[next_x][next_y] = 0;
			}
			break;
		case 3: //북쪽으로 이동
			temp1 = dice[4];
			dice[4] = dice[2];
			temp2 = dice[5];
			dice[5] = temp1;
			temp1 = dice[1];
			dice[1] = temp2;
			dice[2] = temp1;
			if (map[next_x][next_y] == 0)
				map[next_x][next_y] = dice[4];
			else {
				dice[4] = map[next_x][next_y];
				map[next_x][next_y] = 0;
			}
			break;
		case 4: //남쪽으로 이동
			temp1 = dice[4];
			dice[4] = dice[5];
			temp2 = dice[2];
			dice[2] = temp1;
			temp1 = dice[1];
			dice[1] = temp2;
			dice[5] = temp1;
			if (map[next_x][next_y] == 0)
				map[next_x][next_y] = dice[4];
			else {
				dice[4] = map[next_x][next_y];
				map[next_x][next_y] = 0;
			}
			break;
		}
		X = next_x; Y = next_y;
		return dice[1];
	}
}

int main()
{
	memset(map, -1, sizeof(int) * 22 * 22);
	scanf("%d %d %d %d %d", &N, &M, &X, &Y, &K);
	for (int i = 1; i <= N; i++)
		for (int j = 1; j <= M; j++)
			scanf("%d", &map[i][j]);
	for (int i = 1; i <= K; i++)
		scanf("%d", &command[i]);
	X++; Y++;
	for (int i = 1; i <= K; i++) {
		int result = move(command[i]);
		if(result != -1)
			printf("%d\n", result);
	}
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
