package BOJ1759;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 1759 암호만들기
 * 문제
 * 암호는 서로다른 L개의 알파뱃 소문자로 구성된다. 
 * 1. 1개 이상의 모음 +2개의 자음으로 구성되어 있다.
 * 2. 암호는 오름차순이다.
 * C개의 문자중에서 골라야한다.
 * 풀이
 * 오름차순 = 순서가 중요하지 않음 = 조합 or 부분집합
 * 모음에서 1개이상 고르는 부분 집합 (n개 선택)
 * + 자음에서 2개 이상 고르는 부분집합 으로 접근하기
 * 
 */
public class Main {
	static int L,C,voIndex,conIndex;
	static char[] elements;
	static char[] selected;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		selected = new char[L];
		elements = new char[C];
		voIndex= 0;
		conIndex=0;
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i<C;i++) {
			char c = st.nextToken().charAt(0);
			elements[i] =c;
		}

		Arrays.sort(elements);

		dfs(0,0,0);
	}
	
	static void dfs(int elementIndex,int selectedIndexVo,int selectedIndexCon) {
		if (selectedIndexCon+selectedIndexVo == L) {
			if(selectedIndexVo<1||selectedIndexCon<2) {
//				실패
				return;
			}
			System.out.println(selected);
			return;
		}
		if (elementIndex ==C) {
			return;
		}
		char c = elements[elementIndex];
		if (c=='a'|| c=='e'|| c=='i'|| c=='o'|| c=='u') {

			//모음이라면 모음선택후 넘어가기
			selected[selectedIndexCon+selectedIndexVo]= c;
			dfs(elementIndex+1,selectedIndexVo+1,selectedIndexCon);
			//선택안하고 넘어가기
			selected[selectedIndexCon+selectedIndexVo]= '-';
			dfs(elementIndex+1,selectedIndexVo,selectedIndexCon);
			
			return;
		}
		//자음 선택하고 넘어가기
		selected[selectedIndexCon+selectedIndexVo]= c;
		dfs(elementIndex+1,selectedIndexVo,selectedIndexCon+1);
		//선택안하고 넘어가기
		selected[selectedIndexCon+selectedIndexVo]= '-';
		dfs(elementIndex+1,selectedIndexVo,selectedIndexCon);
	}
}
