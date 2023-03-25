package lab6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Q1 {
    /**
     * @Algorithm Greedy algorithm
     * 1. sort the intervals by their finish time in ascending order
     * 2. In every loop, take the first interval and place a point at the finish time of it.
     * 3. delete all intervals that contain this timestamp.
     * 4. repeat until the intervals set become an empty set
     *
     * @Proof After sorting the intervals, for an optimal solution S*
     * 1. The first interval should contain at least 1 point, let pi be the rightest point.
     * 2. When pi is not at the right bound of interval 1, when we move this point to the right bound, it doesn't
     *    miss some original intervals because interval 1 finish first. That is, for a point in the interval 1, place
     *    as right as possible.
     * 3. If there are other points during the interval 1, they cannot reach some intervals that the right most point cannot
     *    reach. That is, points except the rightest one are redundant.
     * 4. In each iteration, this greedy algorithm yields a solution that at least as good as any other solutions.
     *    Therefore, it's the optimal solution.
     *
     * @param args command line argument
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        List<interval> l = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            l.add(new interval(in.nextInt(), in.nextInt()));
        }
        l.sort(Comparator.comparingInt(i -> i.end));
        int count = 0;
        while(l.size() != 0){
            interval i = l.get(0);
            count++;
            l = l.stream().filter(inter -> inter.begin > i.end).toList();
        }
        System.out.println(count);
    }
}
class interval{
    int begin;
    int end;
    interval(int b, int e){
        begin = b;
        end = e;
    }
}
