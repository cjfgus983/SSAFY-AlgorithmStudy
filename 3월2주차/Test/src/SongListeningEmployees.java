import java.util.*;

public class SongListeningEmployees {

    static class Employee {
        int start, end;
        Employee(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee(9, 17),
                new Employee(8, 15),
                new Employee(10, 18),
                new Employee(12, 20),
                new Employee(7, 14),
                new Employee(11, 19),
                new Employee(3,16),
                new Employee(0,20),
                new Employee(10,17),
                new Employee(10,12)
        );

        int songStart = 10;
        int songEnd = 17;
        System.out.println(countEmployees(employees, songStart, songEnd));  // 예상 출력: 2
    }

    public static int countEmployees(List<Employee> employees, int songStart, int songEnd) {
        int n = employees.size();
        int[] starts = new int[n];
        int[] ends = new int[n];

        // 각각의 배열에 직원의 시작시간과 종료시간 저장
        for (int i = 0; i < n; i++) {
            starts[i] = employees.get(i).start;
            ends[i] = employees.get(i).end;
        }

        // 두 배열을 오름차순 정렬
        Arrays.sort(starts);
        Arrays.sort(ends);

        // 시작시간이 songStart 이하인 직원 수
        int countStarts = upperBound(starts, songStart);
        // 종료시간이 songEnd 미만인 직원 수 (즉, 노래가 끝나기 전에 퇴근하는 직원 수)
        int countEnds = upperBound(ends, songEnd - 1);

        return countStarts - countEnds;
    }

    /**
     * upperBound: 정렬된 배열에서 value 이하의 원소 개수를 반환.
     * (즉, 첫 원소가 value보다 큰 인덱스를 찾음)
     */
    public static int upperBound(int[] arr, int value) {
        int lo = 0, hi = arr.length;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (arr[mid] <= value) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }
}
