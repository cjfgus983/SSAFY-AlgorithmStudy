package ChulHyeon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*

입력
첫째 줄에 컴퓨터의 수 N (1 ≤ N ≤ 1000)가 주어진다.

둘째 줄에는 연결할 수 있는 선의 수 M (1 ≤ M ≤ 100,000)가 주어진다.

셋째 줄부터 M+2번째 줄까지 총 M개의 줄에 각 컴퓨터를 연결하는데 드는 비용이 주어진다. 이 비용의 정보는 세 개의 정수로 주어지는데, 만약에 a b c 가 주어져 있다고 하면 a컴퓨터와 b컴퓨터를 연결하는데 비용이 c (1 ≤ c ≤ 10,000) 만큼 든다는 것을 의미한다. a와 b는 같을 수도 있다.

출력
모든 컴퓨터를 연결하는데 필요한 최소비용을 첫째 줄에 출력한다.

예제 입력 1
6
9
1 2 5
1 3 4
2 3 2
2 4 7
3 4 6
3 5 11
4 5 3
4 6 8
5 6 8
예제 출력 1
23

*/
public class BOJ_1922_네트워크연결_김철현 {



    static int vCnt;
    static int eCnt;

    static int[] parent; // 직계 부모
    static int[] rank;

    static void init() {
        for(int i=1; i <= vCnt; i++) {
            parent[i] = i;
        }
    }

    static int find(int u) {
        if(parent[u] == u)
            return u;
        parent[u] = find(parent[u]); // 경로 압축
        return parent[u];
    }

    static boolean isConnect(int u, int v) {
        if(find(u) == find(v)) // 이미 연결되어 있음
        {
            return true;
        }
        return false;
    }

    static void union(int u, int v) {
        // 연결 안되어있을 때만 합칠거임
        int rootU = find(u);
        int rootV = find(v);

        if(rank[rootU] > rank[rootV])
        {
            parent[rootV] = rootU;
        }
        else if(rank[rootU] < rank[rootV])
        {
            parent[rootU] = rootV;
        }
        else // 같으면
        {
            parent[rootU] = rootV;
            rank[rootV]++;
        }
    }

    static class Edge implements Comparable<Edge>{
        int u, v, weight;

        public Edge(int u, int v, int weight)
        {
            this.u = u;
            this.v = v;
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

        vCnt = Integer.parseInt(br.readLine().trim());
        eCnt = Integer.parseInt(br.readLine().trim());

        parent = new int[vCnt + 1];
        rank = new int[vCnt + 1];

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

        int result = 0;

        for(Edge edge : list)
        {
            int u = edge.u;
            int v = edge.v;
            int w = edge.weight;
            if(!isConnect(u, v)) // 연결 안되어있으면
            {
                union(u, v);
                result += w;
            }
        }
        System.out.println(result);

    }
}