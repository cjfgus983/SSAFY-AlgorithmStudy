package BOJ2458;

import java.util.*;

public class Main {
    static List<List<Integer>> parent;
    static List<List<Integer>> child;
    static boolean[] visit;

    public static void bfs(int start, int n) {
        Queue<Integer> q = new LinkedList<>();
        visit[start] = true;
        q.add(start);

        while (!q.isEmpty()) {
            int now = q.poll();
            for (int i = 0; i < parent.get(now).size(); i++) {
                int p = parent.get(now).get(i);
                if (visit[p]) // 이미 방문 했으면
                    continue;
                q.add(p);
                visit[p] = true;
            }
        }

        q.add(start);
        while (!q.isEmpty()) {
            int now = q.poll();
            for (int i = 0; i < child.get(now).size(); i++) {
                int c = child.get(now).get(i);
                if (visit[c]) // 이미 방문 했으면
                    continue;
                q.add(c);
                visit[c] = true;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int result = 0;

        int n = sc.nextInt();
        parent = new ArrayList<>(n + 1);
        child = new ArrayList<>(n + 1);

        for (int i = 0; i <= n; i++) {
            parent.add(new ArrayList<>());
            child.add(new ArrayList<>());
        }

        visit = new boolean[n + 1];

        int M = sc.nextInt();
        for (int k = 0; k < M; k++) {
            int first = sc.nextInt();
            int second = sc.nextInt();
            child.get(first).add(second);
            parent.get(second).add(first);
        }

        for (int i = 1; i <= n; i++) { // 다 탐색 가능한지 확인
            Arrays.fill(visit, false);
            bfs(i, n);
            boolean check = true;
            for (int j = 1; j <= n; j++) {
                if (!visit[j]) {
                    check = false;
                    break;
                }
            }
            if (check) {
                result++;
            }
        }
        System.out.println(result);
    }
}
