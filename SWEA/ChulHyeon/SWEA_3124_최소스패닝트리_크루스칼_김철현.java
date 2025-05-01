import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


// 1. 입력하고 정렬하기 위해 리스트에 넣기 -> 가중치 순으로 edge 뽑아내기 위함
// 2. 연결 안 되어 있으면 2개를 합친다.
// *
// * find
// * 자신의 부모가 자신이라면 루트임
// * 재귀로 자신의 최상위 조상을 저장하고 리턴
// *
// * isConnect
// * find를 통해 최고 조상이 같은지 조사
// *
// * union
// * rank를 기준으로 최상위 조상을 합치기
//
// * */


public class SWEA_3124_최소스패닝트리_크루스칼_김철현 {
    static int vCnt; // 점의 개수
    static int eCnt; // edge 의 개수

    static int[] parent; // 직계 부모
    static int[] rank;

    static void init() {
        for(int i=1; i <= vCnt; i++) {
            parent[i] = i;
        }
    }

    static int find(int vertex) {
        if(parent[vertex] == vertex)
            return vertex;
        parent[vertex] = find(parent[vertex]); // 경로 압축
        return parent[vertex];
    }

    static boolean isConnect(int vertex1, int vertex2) {
        if(find(vertex1) == find(vertex2)) // 이미 연결되어 있음
        {
            return true;
        }
        return false;
    }

    static void union(int vertex1, int vertex2) {
        // 연결 안되어있을 때만 합칠거임
        int root1 = find(vertex1);
        int root2 = find(vertex2);

        if(rank[root1] > rank[root2])
        {
            parent[root2] = root1;
        }
        else if(rank[root1] < rank[root2])
        {
            parent[root1] = root2;
        }
        else // 같으면
        {
            parent[root1] = root2;
            rank[root2]++;
        }
    }

    static class Edge implements Comparable<Edge>{
        int vertex1, vertex2, weight;

        public Edge(int vertex1, int vertex2, int weight)
        {
            this.vertex1 = vertex1;
            this.vertex2 = vertex2;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }


    static List<Edge> list = new ArrayList<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int testCase = 1; testCase <= T; testCase++) {
            st = new StringTokenizer(br.readLine());
            list = new ArrayList<>();

            vCnt = Integer.parseInt(st.nextToken());
            eCnt = Integer.parseInt(st.nextToken());

            parent = new int[vCnt + 1];
            rank = new int[vCnt + 1];

            // 1. 입력하고 정렬하기 위해 리스트에 넣기 -> 가중치 순으로 edge 뽑아내기 위함
            for(int i=0;i<eCnt;i++) {
                st = new StringTokenizer(br.readLine().trim());
                int from, to, weight;
                from = Integer.parseInt(st.nextToken());
                to = Integer.parseInt(st.nextToken());
                weight =  Integer.parseInt(st.nextToken());
                list.add(new Edge(from, to, weight));
            }
            Collections.sort(list);

            init();

            long result = 0;

            for(Edge edge : list)
            {
                int vertex1 = edge.vertex1;
                int vertex2 = edge.vertex2;
                int weight = edge.weight;
                // 2. 연결 안 되어 있으면 2개를 합친다.
                if(!isConnect(vertex1, vertex2))
                {
                    union(vertex1, vertex2);
                    result += weight;
                }
            }
            sb.append("#").append(testCase).append(" ").append(result).append("\n");
        }
        System.out.println(sb.toString());
    }
}