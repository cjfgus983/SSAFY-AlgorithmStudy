import java.util.*;

public class DynamicSegmentTreeSolution {

    // Employee 클래스: 각 직원은 고유 id, 시작 시간(start), 종료 시간(end)을 갖습니다.
    static class Employee {
        int id, start, end;
        public Employee(int id, int start, int end) {
            this.id = id;
            this.start = start;
            this.end = end;
        }
    }

    /**
     * 동적 업데이트를 지원하는 세그먼트 트리 클래스
     *
     * 세그먼트 트리는 정렬된 직원 배열의 특정 구간에 대한 정보를 빠르게 질의(query)하고,
     * 업데이트(update)할 수 있도록 돕습니다.
     *
     * lo, hi: 이 노드가 담당하는 배열의 구간을 의미합니다.
     *         예를 들어, lo=0, hi=5라면 이 노드는 0번 인덱스부터 5번 인덱스까지의 데이터를 포함합니다.
     *
     * endCount: TreeMap을 사용하여 이 구간에 있는 직원들의 종료 시간(end)과 그 빈도수를 저장합니다.
     *           key는 종료 시간, value는 해당 종료 시간을 가진 직원의 개수입니다.
     *
     * left, right: 해당 구간을 반으로 나눈 왼쪽 자식과 오른쪽 자식을 나타냅니다.
     */
    static class SegmentTree {
        int lo, hi;
        SegmentTree left, right;
        TreeMap<Integer, Integer> endCount; // 종료 시간과 그 빈도수를 저장하는 자료구조

        /**
         * 생성자: [lo, hi] 구간에 대해 세그먼트 트리를 구성합니다.
         * employees 배열은 시작 시간 기준으로 정렬된 Employee 배열입니다.
         */
        public SegmentTree(int lo, int hi, Employee[] employees) {
            this.lo = lo;
            this.hi = hi;
            endCount = new TreeMap<>();
            if (lo == hi) {
                // 리프 노드: 구간에 해당하는 단일 직원의 종료 시간을 TreeMap에 저장
                int end = employees[lo].end;
                endCount.put(end, 1);
            } else {
                // 내부 노드: 구간을 두 부분으로 나누어 자식 노드를 생성
                int mid = (lo + hi) / 2;
                left = new SegmentTree(lo, mid, employees);
                right = new SegmentTree(mid + 1, hi, employees);
                // 자식 노드들의 종료 시간 정보를 병합하여 현재 노드의 endCount를 구성
                mergeCounts();
            }
        }

        /**
         * mergeCounts()는 자식 노드(left와 right)의 endCount 정보를 병합합니다.
         * 각 종료 시간에 대해 두 자식에서 나온 빈도수를 합산합니다.
         */
        private void mergeCounts() {
            // 왼쪽 자식의 모든 종료 시간 정보를 추가
            for (Map.Entry<Integer, Integer> entry : left.endCount.entrySet()) {
                endCount.put(entry.getKey(), entry.getValue());
            }
            // 오른쪽 자식의 종료 시간 정보를 병합: 이미 존재하면 빈도수를 누적
            for (Map.Entry<Integer, Integer> entry : right.endCount.entrySet()) {
                endCount.put(entry.getKey(), endCount.getOrDefault(entry.getKey(), 0) + entry.getValue());
            }
        }

        /**
         * query(L, R, threshold) 메서드
         * 주어진 배열 구간 [L, R]에서 종료 시간이 threshold 이상인 직원의 수를 반환합니다.
         *
         * 재귀적으로 구간을 탐색하면서 해당 구간에 포함된 노드의 endCount에서
         * threshold 이상의 종료 시간을 가진 직원들의 빈도수를 이분 탐색(lowerBound)를 통해 빠르게 구합니다.
         */
        public int query(int L, int R, int threshold) {
            // 현재 노드의 구간이 [L, R]와 겹치지 않는 경우 0을 반환
            if (R < lo || hi < L) return 0;
            // 현재 노드의 구간이 완전히 [L, R] 내에 포함되는 경우
            if (L <= lo && hi <= R) {
                int count = 0;
                // tailMap(threshold, true): threshold 이상의 키들을 반환
                for (int cnt : endCount.tailMap(threshold, true).values()) {
                    count += cnt;
                }
                return count;
            }
            // 구간이 일부 겹치는 경우, 왼쪽과 오른쪽 자식에서 결과를 합산
            return left.query(L, R, threshold) + right.query(L, R, threshold);
        }

        /**
         * update(index, end) 메서드
         * 주어진 index에 해당하는 직원의 종료 시간(end) 정보를 세그먼트 트리에서 업데이트합니다.
         * 이는 삭제(remove) 작업에서 사용되며, 해당 종료 시간의 빈도를 감소시킵니다.
         */
        public void update(int index, int end) {
            // 리프 노드에 도달했으며, 해당 인덱스가 맞다면
            if (lo == hi && lo == index) {
                int current = endCount.get(end);
                if (current == 1) {
                    endCount.remove(end);
                } else {
                    endCount.put(end, current - 1);
                }
                return;
            }
            // 현재 노드가 담당하는 구간에서 index가 왼쪽 자식 구간에 속하면
            if (index <= left.hi) {
                left.update(index, end);
            } else {
                // 그렇지 않으면 오른쪽 자식 구간에 속합니다.
                right.update(index, end);
            }
            // 상위 노드에서도 해당 종료 시간의 빈도를 감소시킵니다.
            int current = endCount.get(end);
            if (current == 1) {
                endCount.remove(end);
            } else {
                endCount.put(end, current - 1);
            }
        }
    }

    // 정렬된 직원 배열, id → 인덱스 매핑, 그리고 세그먼트 트리 객체를 전역 변수로 선언합니다.
    static Employee[] sortedEmployees;
    static SegmentTree segTree;
    static HashMap<Integer, Integer> idToIndex = new HashMap<>();

    public static void main(String[] args) {
        // 예제 직원 데이터 생성: (id, start, end)
        List<Employee> employeesList = new ArrayList<>();
        employeesList.add(new Employee(1, 9, 17));
        employeesList.add(new Employee(2, 8, 15));
        employeesList.add(new Employee(3, 10, 18));
        employeesList.add(new Employee(4, 12, 20));
        employeesList.add(new Employee(5, 7, 14));
        employeesList.add(new Employee(6, 11, 19));

        // 1. 직원 리스트를 시작 시간(start) 기준으로 오름차순 정렬
        employeesList.sort(Comparator.comparingInt(e -> e.start));
        int n = employeesList.size();
        // 정렬된 리스트를 배열로 변환
        sortedEmployees = employeesList.toArray(new Employee[n]);
        // 각 직원의 id와 배열 내 인덱스 매핑 생성 (동적 삭제 시 해당 인덱스를 빠르게 찾기 위함)
        for (int i = 0; i < n; i++) {
            idToIndex.put(sortedEmployees[i].id, i);
        }

        // 2. 정렬된 배열을 기반으로 세그먼트 트리 생성
        segTree = new SegmentTree(0, n - 1, sortedEmployees);

        // 3. 쿼리: 노래 시작 시간(songStart) 이하로 근무하는 직원 중,
        //    종료 시간이 songEnd 이상인 직원의 수를 구합니다.
        int songStart = 10;
        int songEnd = 17;
        // findLastIndexLE()를 사용해 시작 시간이 songStart 이하인 직원들 중 마지막 인덱스를 찾습니다.
        int pos = findLastIndexLE(sortedEmployees, songStart);
        // 만약 조건을 만족하는 직원이 없다면 pos는 -1이 될 수 있으므로, 결과는 0입니다.
        int result = (pos == -1) ? 0 : segTree.query(0, pos, songEnd);
        System.out.println("노래를 처음부터 끝까지 들을 수 있는 직원 수: " + result);

        // 4. remove(id) 호출: 예를 들어 id가 3인 직원을 삭제합니다.
        removeEmployee(3);

        // 삭제 후 다시 동일한 쿼리를 수행합니다.
        pos = findLastIndexLE(sortedEmployees, songStart);
        result = (pos == -1) ? 0 : segTree.query(0, pos, songEnd);
        System.out.println("삭제 후, 노래를 처음부터 끝까지 들을 수 있는 직원 수: " + result);
    }

    /**
     * findLastIndexLE(Employee[] arr, int value)
     * 정렬된 배열에서 employee.start가 value 이하인 직원 중,
     * 가장 마지막(오른쪽) 인덱스를 찾습니다.
     *
     * 만약 employee.start가 value 이하인 직원이 없으면 -1을 반환합니다.
     */
    public static int findLastIndexLE(Employee[] arr, int value) {
        int l = 0, r = arr.length - 1, ans = -1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (arr[mid].start <= value) {
                ans = mid;   // 조건을 만족하는 인덱스 발견
                l = mid + 1; // 더 오른쪽에도 조건을 만족하는 값이 있는지 탐색
            } else {
                r = mid - 1; // 조건을 만족하지 않으므로 왼쪽 구간 탐색
            }
        }
        return ans;
    }

    /**
     * removeEmployee(int id)
     * 주어진 id를 가진 직원을 세그먼트 트리에서 동적으로 제거(업데이트)합니다.
     *
     * 1. idToIndex 맵을 통해 해당 직원이 배열에서 어느 인덱스에 위치하는지 찾습니다.
     * 2. 세그먼트 트리의 update() 메서드를 호출하여 해당 직원의 종료 시간 정보를 업데이트(빈도 감소)합니다.
     * 3. 필요 시, idToIndex에서 해당 id를 제거합니다.
     */
    public static void removeEmployee(int id) {
        if (!idToIndex.containsKey(id)) return; // 이미 삭제된 경우 무시
        int index = idToIndex.get(id);
        Employee emp = sortedEmployees[index];
        // 세그먼트 트리 업데이트: 해당 인덱스에서 emp.end 값을 가진 빈도를 감소시킴
        segTree.update(index, emp.end);
        // 동적 삭제 후 idToIndex에서 해당 id 제거
        idToIndex.remove(id);
        System.out.println("삭제된 직원 id: " + id);
    }
}
