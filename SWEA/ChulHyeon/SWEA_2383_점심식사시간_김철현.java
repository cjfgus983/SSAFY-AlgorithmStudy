import java.io.*;
import java.util.*;

/*
* 1. dfs로 먼저 각 사람들에게 0번 계단, 1번 계단 중 어디로 보낼지를 결정
* 2. dfs의 기저조건에서 각 배정 상태에서 시뮬레이션을 돌리고 결과를 갱신한다.
*
* 3. simulation
*   1. arrivals배열을 만든다.arrivals[0] 은 0번 계단에 도착할 사람들의 시간을 의미
*   2. 이걸 오름차순 하면 각 계단에 빨리 도착하는 사람들의 시간 순서대로 정렬되겠지
*   3. 각 계단별로 모든 사람들이 내려갈때까지의 시간을 구하고 그 중에 큰 것이 정답
*
* 4. runStair 한 계단에서 배정된 모든 사람들이 내려가는 시간
*   1. 대기 인원이 남이있고, 큐가 빌 때 까지 반복
*       1. 계단이 비었다는건 누군가 계단까지 가줘야 한다는 것 time 에 가야할 시간을 더해준다
*       2. 계단에 사람이 있는데 맨 앞의 사람이 시간이 다 지났다면 제거
*       3. 도착했고 계단이 비어있다면 큐에 추가, 현재시간 + 내려가야 할 시간
*
* */

public class SWEA_2383_점심식사시간_김철현 {

    // 좌표 저장용 클래스
    static class Pos {
        int r, c;
        Pos(int r, int c){ this.r = r; this.c = c; }
    }

    static int size, personCnt, answer;
    static int[][] map;
    static List<Pos> people = new ArrayList<>();      // 사람 위치 리스트
    static List<Pos> stairs = new ArrayList<>();      // 계단 위치 리스트
    static List<Integer> stairLen = new ArrayList<>();// 각 계단 길이
    static int[] assign;                               // 사람별 할당 계단(0 or 1)

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim()); // 테스트 케이스 수
        StringBuilder sb = new StringBuilder();

        for(int testCase = 1; testCase <= T; testCase++){
            size = Integer.parseInt(br.readLine().trim());    // 한 변 길이
            map = new int[size][size];
            people.clear(); stairs.clear(); stairLen.clear(); // 초기화

            // 입력 읽기
            for(int i = 0; i < size; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 0; j < size; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if(map[i][j] == 1) {
                        people.add(new Pos(i, j));               // 사람
                    } else if(map[i][j] > 1) {
                        stairs.add(new Pos(i, j));               // 계단 위치
                        stairLen.add(map[i][j]);                // 계단 길이
                    }
                }
            }

            personCnt = people.size();                       // 총 사람 수
            assign = new int[personCnt];                     // 계단 할당 배열
            answer = Integer.MAX_VALUE;                      // 최소 시간 초기값

            dfs(0);                                          // 모든 할당 경우 탐색
            sb.append("#").append(testCase).append(" ").append(answer).append("\n");
        }

        System.out.print(sb); // 결과 출력
    }

    // idx번째 사람에게 0번 혹은 1번 계단을 할당하는 DFS
    static void dfs(int idx){
        if(idx == personCnt){
            answer = Math.min(answer, simulate()); // 할당 완료 -> 시뮬레이션 후 최솟값 갱신
            return;
        }
        for(int s = 0; s < 2; s++){
            assign[idx] = s;
            dfs(idx + 1);
        }
    }

    // 현재 assign 배열 기준으로 전체 시뮬레이션 실행  -> 소요시간 반환
    static int simulate(){
        // 계단이 2개니까 2개의 리스트를 생성
        List<Integer>[] arrivals = new ArrayList[2];
        for(int i = 0; i < 2; i++) arrivals[i] = new ArrayList<>();

        // 각 사람별 도착 시간 계산하여 해당 계단 리스트에 추가
        for(int i = 0; i < personCnt; i++){
            Pos p = people.get(i); // 사람의 위치
            Pos st = stairs.get(assign[i]); // 계단의 위치
            int time = Math.abs(p.r - st.r) + Math.abs(p.c - st.c); // 계단까지 이동하는 시간
            arrivals[assign[i]].add(time); // 현재 사람이 배정된 계단리스트에 대해 시간 추가
        }

        // 두 계단 각각 시뮬레이션 -> 둘 중 더 큰 값이 총 소요 시간
        int t0 = runStair(arrivals[0], stairLen.get(0));
        int t1 = runStair(arrivals[1], stairLen.get(1));
        return Math.max(t0, t1);
    }

    // 한 계단에서 내려가는 시뮬레이션 (용량 3명, 길이 = length)
    static int runStair(List<Integer> arr, int length){
        if(arr.isEmpty()) return 0;                  // 해당 계단으로 갈 사람이 없으면 0

        Collections.sort(arr);                        // 도착 시간 오름차순 정렬

        PriorityQueue<Integer> inStair = new PriorityQueue<>(); // 계단에 머무르는 사람들의 종료시간
        int time = 0, idx = 0;
        // idx가 arr를 넘었다는 것은 모든 사람들을 처리 했다는 것
        // 시간은 0초부터 시작
        // 대기 인원 + 계단 내 인원이 모두 처리될 때까지 반복
        while(idx < arr.size() || !inStair.isEmpty()){
            // 1. 계단이 비었다는건 누군가 하나 도착해야한다는것, 시간을 누구 하나 도착할때까지로 변경
            if(inStair.isEmpty() && idx < arr.size()) {
                time = arr.get(idx);
            }
            // 2. 완료된 사람들 제거
            // 계단에 사람이 있는데 가장 앞에 있는 사람의 시간이 만료 되었을 때 맨 앞 사람을 꺼낸다.
            while(!inStair.isEmpty() && inStair.peek() <= time) {
                inStair.poll();
            }
            // 3. 도착했고, 계단이 비어있다면 입장
            while(idx < arr.size() && arr.get(idx) <= time && inStair.size() < 3) {
                inStair.offer(time + length);
                idx++;
            }
            time++;
        }
        return time;
    }
}
