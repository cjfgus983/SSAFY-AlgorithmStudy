import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 이기는 경우 / 지는 경우 출력
 * 1. dfs 재귀로 9칸 짜리 배열 하나에 상대방의 순열을 저장
 * 2. dfs 탈출조건에서 내 카드와 상대 카드를 비교하여 내 합산 점수와 상대 합산점수를 저장
 * 3. 합산 점수를 기준으로 승/무승부/패 각각 판단
 * */


public class SWEA_6808_규영이와인영이의카드게임_김철현 {
	
	static final int CARD_CNT = 9;
	
	static int myCard[] = new int[9];
	static int youCard[] = new int[9];
	static int youCardPermutation[] = new int[9];
	static boolean visit[] = new boolean[9];
	
	static int winRate = 0;
	static int loseRate = 0;
	
	// 내 카드 조합과 상대 카드조합 간의 대결 
	// 내가 이기면 양수, 상대가 이기면 음수, 무승부면 0 리턴
	static int fight()
	{
		int myScore = 0;
		int youScore = 0;
		for(int idx = 0; idx <CARD_CNT;idx++)
		{
			if(myCard[idx] > youCardPermutation[idx])
			{
				myScore = myScore + myCard[idx] + youCardPermutation[idx];
			}
			else if(myCard[idx] < youCardPermutation[idx]) {
				youScore = youScore + myCard[idx] + youCardPermutation[idx];
			}
		}
		return myScore - youScore;
	}
	
	static void dfs(int nowCnt)
	{
		if(nowCnt == CARD_CNT)
		{
			if(fight() > 0)
				winRate++;
			else if(fight() < 0)
				loseRate++;
			return;
		}
		for(int idx = 0; idx < CARD_CNT; idx++)
		{
			if(visit[idx])
				continue;
			youCardPermutation[nowCnt] = youCard[idx];
			visit[idx] = true;
			dfs(nowCnt + 1);
			visit[idx] = false;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine().trim());
		for(int testCase = 1; testCase <=T;testCase++)
		{
			// 초기화
			winRate = 0;
			loseRate = 0;
			// 내 카드 리스트 저장
			st = new StringTokenizer(br.readLine().trim());
			for(int idx=0;idx<CARD_CNT;idx++)
			{
				myCard[idx] = Integer.parseInt(st.nextToken());
			}
			// 상대 카드 저장
			int youCardIdx = 0;
			for(int cardNum = 1;cardNum<=18;cardNum++)
			{
				// 내 카드리스트에 있는지 확인
				boolean exist = false;
				for(int myCardIdx = 0; myCardIdx < CARD_CNT; myCardIdx++)
				{
					if(myCard[myCardIdx] == cardNum)
					{
						exist = true;
						break;
					}
				}
				if(!exist)
				{
					youCard[youCardIdx++] = cardNum;
				}
			}
			// 순열로 상대 카드 순서 선택
			dfs(0);
			sb.append("#").append(testCase).append(" ").append(winRate).append(" ").append(loseRate).append('\n');
		}
		System.out.println(sb.toString());
	}
}
