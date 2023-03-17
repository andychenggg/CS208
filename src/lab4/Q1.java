package lab4;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Queue;
import java.util.Scanner;

public class Q1 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        node [] nodes = new node[n];
        Queue<node> q = new ArrayDeque<>();
        int [] function = new int[n];
        for(int i = 0; i< n; i++){
            nodes[i] = new node(i);
        }
        for(int i = 0; i< n; i++){
            function[i] = in.nextInt() - 1;
        }
        q.add(nodes[0]);
        while(!q.isEmpty()){
            node n1 = q.poll();
            if(n1.index + 1 < n && !nodes[n1.index + 1].isVisit){
                nodes[n1.index + 1].checkTime = n1.checkTime + 1;
                q.add(nodes[n1.index + 1]);
                nodes[n1.index + 1].isVisit = true;
            }
            if(!nodes[function[n1.index]].isVisit){
                nodes[function[n1.index]].checkTime = n1.checkTime + 1;
                q.add(nodes[function[n1.index]]);
                nodes[function[n1.index]].isVisit = true;
            }
        }
        for(node t: nodes){
            System.out.print(t.checkTime + " ");
        }

    }

}
class node{
    boolean isVisit = false;
    int checkTime = 0;
    int index;
    node(int i){
        index = i;
    }
}


/*
第二题思路：
1. 他一定能移动到旁边一格 => 一定有解
2. 从自己所在的点开始进行bfs，每一层代表的是，在考虑边界范围的情况下，下一次能走到的点
3. 直到这些点第一次出现目标点，完成查找
*/