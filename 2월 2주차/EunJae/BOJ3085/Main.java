package BOJ3085;

/*
 * 문제 사탕게임
상근이는 어렸을 적에 "봄보니 (Bomboni)" 게임을 즐겨했다.

가장 처음에 N×N크기에 사탕을 채워 놓는다. 사탕의 색은 모두 같지 않을 수도 있다. 상근이는 사탕의 색이 다른 인접한 두 칸을 고른다. 그 다음 고른 칸에 들어있는 사탕을 서로 교환한다. 이제, 모두 같은 색으로 이루어져 있는 가장 긴 연속 부분(행 또는 열)을 고른 다음 그 사탕을 모두 먹는다.

사탕이 채워진 상태가 주어졌을 때, 상근이가 먹을 수 있는 사탕의 최대 개수를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 보드의 크기 N이 주어진다. (3 ≤ N ≤ 50)

다음 N개 줄에는 보드에 채워져 있는 사탕의 색상이 주어진다. 빨간색은 C, 파란색은 P, 초록색은 Z, 노란색은 Y로 주어진다.

사탕의 색이 다른 인접한 두 칸이 존재하는 입력만 주어진다.

출력
첫째 줄에 상근이가 먹을 수 있는 사탕의 최대 개수를 출력한다.

예제 입력 1 
3
CCP
CCP
PPC
예제 출력 1 
3
예제 입력 2 
4
PPPP
CYZY
CCPY
PPCC
예제 출력 2 
4
예제 입력 3 
5
YCPZY
CYZZP
CCPPP
YCYZC
CPPZZ
예제 출력 3 
4
 */
import java.io.*;
import java.util.Arrays;
public class Main {
	static int max = 0;
	static char[][] board;
	static int N;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		board = new char[N][N];
		for(int i =0; i<N; i++) {
			String s = br.readLine();
			for (int j =0; j<N; j++) {
				board[i][j]= s.charAt(j);
			}
		}
		
		
		
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++) {
				check(row,col); //그냥 확인
				if(row<N-1) {
				swap(row,col, row+1,col); //아래칸과 바꾸기
				check(row,col); //바꾸고 확인
				swap(row+1,col,row,col); //원복
				}
				if(col<N-1) {
				swap(row,col,row,col+1); //오른쪽칸과 바꾸기
				check(row,col); //바꾸고 확인
				swap(row,col+1,row, col); //원복
				}

			}
		}
		System.out.println(max);
	}
	
	static void swap(int r, int c,int nr, int nc) {
//		System.out.printf("r :%d c:%d nr:%d nc:%d\n",r,c,nr,nc);
		if(nr>=N || nc>=N || c>=N || r>=N) {
			return;
		}
//		for(int i =0; i<N;i++) {
//			System.out.println(Arrays.toString(board[i]));
//		}
		char tmp = board[r][c];
		board[r][c] = board[nr][nc];
		board[nr][nc] = tmp;
//		for(int i =0; i<N;i++) {
//			System.out.println(Arrays.toString(board[i]));
//		}
	}
	
	static void check(int row,int col) {
		for(int i =0;i<2;i++) {  // i=0 인경우, 교환의 주체, i=1인 경우 교환의 객체 행,열 검사
			int strike = 1;
			for(int c =0;c<N-1;c++) {
				if(row+i>=N) break;
				if(board[row+i][c] == (board[row+i][c+1])) {
					strike +=1;
				}
				else {
					max = Math.max(strike, max);
					strike =1;
				}
				max = Math.max(strike, max);
			}
			strike =1;
			for(int r =0;r<N-1;r++) {
				if(col+i>=N)break;
				if(board[r][col+i] == board[r+1][col+i]) {
					strike +=1;
				}
				else {
					max = Math.max(strike, max);
					strike =1;
				}
				max = Math.max(strike, max);
			}
		}

	}
}
