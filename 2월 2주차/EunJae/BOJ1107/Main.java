package BOJ1107;

/*
 * 문제 리모컨
수빈이는 TV를 보고 있다. 수빈이는 채널을 돌리려고 했지만, 버튼을 너무 세게 누르는 바람에, 일부 숫자 버튼이 고장났다.

리모컨에는 버튼이 0부터 9까지 숫자, +와 -가 있다. +를 누르면 현재 보고있는 채널에서 +1된 채널로 이동하고, -를 누르면 -1된 채널로 이동한다. 
채널 0에서 -를 누른 경우에는 채널이 변하지 않고, 채널은 무한대 만큼 있다.

수빈이가 지금 이동하려고 하는 채널은 N이다. 어떤 버튼이 고장났는지 주어졌을 때, 채널 N으로 이동하기 위해서 버튼을 최소 몇 번 눌러야하는지 구하는 프로그램을 작성하시오.

수빈이가 지금 보고 있는 채널은 100번이다.

입력
첫째 줄에 수빈이가 이동하려고 하는 채널 N (0 ≤ N ≤ 500,000)이 주어진다.
 둘째 줄에는 고장난 버튼의 개수 M (0 ≤ M ≤ 10)이 주어진다.
  고장난 버튼이 있는 경우에는 셋째 줄에는 고장난 버튼이 주어지며, 같은 버튼이 여러 번 주어지는 경우는 없다.

출력
첫째 줄에 채널 N으로 이동하기 위해 버튼을 최소 몇 번 눌러야 하는지를 출력한다.

예제 입력 1 
5457
3
6 7 8
예제 출력 1 
6


idea-> 한자리씩 비교하자

if want 54572
but 5 ,2 broken
-> 4 or 6
if choose 4 -> 4XXXX  i will choice Largest num as X : 49999 -> ansS =4573
if choose 6 -> 6XXXX  i will choice Smallest num as X : 60000 -> ansL = 5428
ans = Min(ansS, ansL)
만약, 4,6도 고장이라면,? 3,7을 비교-> 더 차이가 작은 쪽으로 사용



 */
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
//import java.util.StringTokenizer;
//
//public class Main {
//	static boolean[] broken = new boolean[10];
//	public static void main(String[] args) throws Exception {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//		
//		String strN = (br.readLine()); //목표치
//		int N = Integer.parseInt(strN);
//		int len = strN.length();
//		int M = Integer.parseInt(br.readLine());
//		StringTokenizer st = new StringTokenizer(br.readLine()," ");
//		
//		for(int index =0; index<M;index++) {
//			broken[Integer.parseInt(st.nextToken())] = true;
//		}
//
//		int smallest=-1;
//		int largest=-1;
//		
//		for(int i = 0; i<10;i++) {
//			if(!broken[i]) {
//				smallest = i;
//			}
//		}
//		for(int i = 9; i>=0;i--) {
//			if(!broken[i]) {
//				largest = i;
//			}
//		}
//		int ans = 0;
//		//첫자리가 고장났는지 확인
//		int pointer = 0;
//		int upcnt=0, downcnt=0;
//		while(pointer <len) {
//			
//			int target = strN.charAt(pointer) -'0'; //숫자 확인
//			System.out.println(pointer+" "+target+broken[target]);
//			if(broken[target]) { //고장남?
//				
//				//어디가 더 가까울까?
//				for(int up=target+1;up<=9;up++) {
//					upcnt +=1;
//					if(!broken[up]) {
//						break;
//					}
//				}
//				for(int down=target-1;down>=0;down--) {
//					downcnt +=1;
//					if(!broken[down]) {
//						break;
//					}
//				}
//				System.out.println("up:  "+upcnt+"  down : "+downcnt);
//				if(upcnt > downcnt) { //낮은 숫자가 더 가까우면
//					while(pointer<len) { //나머지를
//					ans=ans*10+largest; //최대 숫자로도배
//					pointer ++;
//					System.out.println(ans);
//					}
//					ans = Math.abs(ans-N);
//					break;
//					
//				}else if (upcnt < downcnt) { //큰 숫자가 더 가까우면
//					while(pointer<len) { //나머지를
//					ans=ans*10+smallest; //최소 숫자로도배
//					pointer ++;
//					System.out.println(ans);
//					}
//					ans = Math.abs(ans-N);
//					break;
//					
//				}else if ( upcnt == downcnt) { //같은 거리라면
//					int upans = ans;
//					int downans = ans;
//
//					downans=downans*10+target-downcnt;
//					upans = upans*10+target+upcnt;
//					pointer++;
//					
//					int temp = pointer;
//					while(temp<len) { //나머지를
//					downans=downans*10+largest; //최대 숫자로도배
//					temp ++;
//					}
//					
//					while(pointer<len) { //나머지를
//					upans=upans*10+smallest; //최대 숫자로도배
//					pointer ++;
//					}
//					System.out.println(upans);
//					System.out.println(downans);
//					ans = Math.min(
//							Math.abs(N-downans),
//							Math.abs(N-upans));
//					System.out.println("추가횟수"+ans);
//					break;
//				}
//				
//			}else { //고장안났으면 바로누름
//				ans=ans*10+target;
//				pointer++;
//				if(pointer ==len) { //마지막 글자였어?
//					ans = Math.abs(N-ans);
//				}
//			}
//		}
//		bw.write(strN.length()+ans+"\n");
//		bw.flush();
//	}
//	
//}


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {
    static HashSet<Integer> brokenButtons = new HashSet<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int targetChannel = Integer.parseInt(br.readLine());  // 이동하려는 채널
        int brokenCount = Integer.parseInt(br.readLine());  // 고장난 버튼 개수

        if (brokenCount > 0) {
            String[] brokenNums = br.readLine().split(" ");
            for (String num : brokenNums) {
                brokenButtons.add(Integer.parseInt(num));
            }
        }

        // 초기값: +,- 버튼으로만 이동하는 경우
        int minPress = Math.abs(targetChannel - 100);

        // 모든 가능한 채널(0~999999) 탐색
        for (int i = 0; i <= 999999; i++) {
            int len = getPressCount(i);
            if (len > 0) {  // 해당 숫자를 만들 수 있다면
                int pressCount = len + Math.abs(i - targetChannel); // 버튼 누른 횟수 + 이동 거리
                minPress = Math.min(minPress, pressCount);
            }
        }

        System.out.println(minPress);
    }

    // 특정 숫자를 만들 수 있는지 확인하고, 만들 수 있다면 버튼 누르는 횟수를 반환
    static int getPressCount(int num) {
        if (num == 0) return brokenButtons.contains(0) ? 0 : 1;

        int count = 0;
        while (num > 0) {
            if (brokenButtons.contains(num % 10)) return 0; // 고장난 숫자가 있으면 만들 수 없음
            count++;
            num /= 10;
        }
        return count;
    }
}

