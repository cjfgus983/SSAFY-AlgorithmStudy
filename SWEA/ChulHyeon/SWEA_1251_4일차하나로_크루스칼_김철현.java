//import java.io.*;
//import java.util.*;
//
///*
//*
//* 엣지의 가중치만 문제의 계산식을 적용해서 리스트를 생성한다.
//*
//* 1. 각 섬의 길이 * 환경부담금 해서 엣지의 가중치를 하고 리스트에 넣음
//* 2. find : 해당 노드의 부모를 찾아서 리턴
//* 3. union : 만약 같은 그룹이면 false를 리턴
//* 3-1 : 다른 그룹이라면 그룹 합치는 과정
//*
//* 4. 모든 엣지를 돈다.
//* 4-1 : 다른 그룹일 경우 mst코스트를 합치기
//*
//* */
//
//public class SWEA_1251_4일차하나로_크루스칼_김철현 {
//
//    static int[] parent; // 각 노드의 직계 부모
//    static int[] rank;   // 각 집합의 서열
//
//    // 유니온 파인드: 노드의 대표(루트)를 찾는 함수 (경로 압축 적용)
//    static int find(int node) {
//        if(parent[node] == node) return node;
//        return parent[node] = find(parent[node]); // 직계 부모를 최고 조상으로 바꾸면서 재귀 줄이기
//    }
//
//    // 두 노드를 같은 집합으로 합치고, 합쳐졌으면 true, 이미 같은 집합이면 false 반환
//    static boolean union(int nodeA, int nodeB) {
//        int rootA = find(nodeA);
//        int rootB = find(nodeB);
//        if(rootA == rootB) return false;
//        if(rank[rootA] < rank[rootB]) {
//            parent[rootA] = rootB;
//        } else if(rank[rootA] > rank[rootB]) {
//            parent[rootB] = rootA;
//        } else {
//            parent[rootB] = rootA;
//            rank[rootA]++;
//        }
//        return true;
//    }
//
//    // 간선 정보를 저장할 클래스
//    static class Edge implements Comparable<Edge> {
//        int nodeA, nodeB;
//        double weight;
//
//        public Edge(int nodeA, int nodeB, double weight) {
//            this.nodeA = nodeA;
//            this.nodeB = nodeB;
//            this.weight = weight;
//        }
//
//        @Override
//        public int compareTo(Edge otherEdge) {
//            return Double.compare(this.weight, otherEdge.weight);
//        }
//    }
//
//    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringBuilder sb = new StringBuilder();
//
//        // 전체 테스트 케이스 수
//        int T = Integer.parseInt(br.readLine().trim());
//        for(int testCase = 1; testCase <= T; testCase++) {
//            // 섬의 개수 (노드 수)
//            int islandCount = Integer.parseInt(br.readLine().trim());
//            int[] xCoordinates = new int[islandCount];
//            int[] yCoordinates = new int[islandCount];
//
//            // X좌표 입력
//            StringTokenizer tokenizer = new StringTokenizer(br.readLine().trim());
//            for(int nodeIdx = 0; nodeIdx < islandCount; nodeIdx++) {
//                xCoordinates[nodeIdx] = Integer.parseInt(tokenizer.nextToken());
//            }
//
//            // Y좌표 입력
//            tokenizer = new StringTokenizer(br.readLine().trim());
//            for(int nodeIdx = 0; nodeIdx < islandCount; nodeIdx++) {
//                yCoordinates[nodeIdx] = Integer.parseInt(tokenizer.nextToken());
//            }
//
//            // 환경 부담 세율 E 입력 (실수)
//            double tax = Double.parseDouble(br.readLine().trim());
//
//            // 모든 섬(노드) 쌍에 대한 간선 생성 (간선의 가중치는 E * (거리 제곱))
//            ArrayList<Edge> edgeList = new ArrayList<>();
//            for(int nodeIdx = 0; nodeIdx < islandCount; nodeIdx++) {
//                for(int otherIdx = nodeIdx + 1; otherIdx < islandCount; otherIdx++) {
//                    long deltaX = xCoordinates[nodeIdx] - xCoordinates[otherIdx];
//                    long deltaY = yCoordinates[nodeIdx] - yCoordinates[otherIdx];
//                    long distanceSquared = deltaX * deltaX + deltaY * deltaY;
//                    double edgeWeight = tax * distanceSquared; // 이걸 엣지의 가중치로함
//                    edgeList.add(new Edge(nodeIdx, otherIdx, edgeWeight));
//                }
//            }
//
//            // 간선 가중치 기준 오름차순 정렬
//            Collections.sort(edgeList);
//
//            // 유니온 파인드 초기화
//            parent = new int[islandCount];
//            rank = new int[islandCount];
//            for(int nodeIdx = 0; nodeIdx < islandCount; nodeIdx++) {
//                parent[nodeIdx] = nodeIdx;
//                rank[nodeIdx] = 0;
//            }
//
//            // 크루스칼 알고리즘을 이용하여 최소 신장 트리(MST) 구성
//            double totalMstCost = 0;
//            int usedEdgeCount = 0;
//            for(Edge currentEdge : edgeList) {
//                // 같은 두 노드를 보고 같은 그룹이 아닐 때
//                if(union(currentEdge.nodeA, currentEdge.nodeB)) {
//                    totalMstCost += currentEdge.weight;
//                    usedEdgeCount++;
//                    if(usedEdgeCount == islandCount - 1) break;
//                }
//            }
//
//            // 최종 비용을 소수 첫째 자리에서 반올림하여 정수로 출력
//            long finalCost = Math.round(totalMstCost);
//            sb.append("#").append(testCase).append(" ").append(finalCost).append("\n");
//        }
//
//        System.out.print(sb);
//    }
//}
