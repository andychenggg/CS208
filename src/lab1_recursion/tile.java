package lab1_recursion;

import java.util.Scanner;

public class tile {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int [] arr = new int [n + 1];
        System.out.println(computingWays(arr, 0, n));
    }

    public static int computingWays(int [] arr, int i, int n){
        //计算宽度为i时的方法数
        if(i == n){
            return 1;
        }
        if(i > n){
            return 0;
        }
        if(arr[i] > 0){
            return arr[i];
        }
        arr[i] = computingWays(arr, i + 1, n) + computingWays(arr, i + 2, n);
        return arr[i];
    }

}
