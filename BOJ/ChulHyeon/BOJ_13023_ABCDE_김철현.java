import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 
 * dfs 돌려서 cnt 가 5만 되면 success 
 * 일반 dfs처럼 하면 되는데 시작지점을 각각 한번씩 잡아야함
 * 예를 들어 0번 노드가 시작점이 될 수있고 ..... n-1 번 노드가 시작점이 될 수 있음
 * 이걸 다 넣어봐야하는데 이러면 시간초과
 * 
 * 결론은 하나씩 다 넣어보긴 하는데 result == 1 이 되어버렸다면 더 찾아볼 필요가 없으므로 break 밟고 바로 출력한다.
 * 
 * 
 * */

public class BOJ_13023_ABCDE_김철현 {
	
	static int vCnt; // 사람의 수
	static int eCnt; // 관계의 수
	
	static int result;
	
	static ArrayList<Integer> rel[]; 
	
	static boolean visit[];
	
	static void dfs(int nowVertex, int nowCnt) {
		if(nowCnt == 5)
		{
			result = 1;
			return;
		}
		for(Integer nextVertex : rel[nowVertex])
		{
			if(visit[nextVertex])
				continue;
			visit[nextVertex] = true;
			dfs(nextVertex, nowCnt + 1);
			visit[nextVertex] = false;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine().trim());
		vCnt = Integer.parseInt(st.nextToken());
		eCnt = Integer.parseInt(st.nextToken());
		
		// 관계 초기화
		rel = new ArrayList[vCnt+1];
		// 방문 초기화
		visit = new boolean[vCnt + 1];
		for(int idx = 0; idx <= vCnt; idx++) {
			rel[idx] = new ArrayList<>();
		}
		
		// edgeCnt 만큼 입력을 받음
		for(int idx = 0; idx < eCnt; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			int person1 = Integer.parseInt(st.nextToken());
			int person2 = Integer.parseInt(st.nextToken());
			
			rel[person1].add(person2);
			rel[person2].add(person1);
		}
		
		// 하나 씩 방문 시작
		for(int person = 0; person < vCnt; person++)
		{
			visit[person] = true;
			dfs(person, 1);
			visit[person] = false;
			if(result == 1)
			{
				break;
			}
		}
		
		// 출력
		System.out.println(result);
	}
}
