package lab5;

import java.util.ArrayList;
import java.util.Scanner;

public class Q1 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), m = in.nextInt();
        node [] nodes = new node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new node(i+1);
        }
        for(int i = 0; i < m; i++){
            int v1 = in.nextInt() - 1, v2 = in.nextInt() - 1;
            nodes[v1].ns.add(nodes[v2]);
            nodes[v2].incoming++;
        }
        queue q = new queue(n);
        int [] result = new int [n];
        int counter = 0;
        for(int i = 0; i < n; i++){
            if(nodes[i].incoming == 0){
                q.enqueue(nodes[i]);
            }
        }
        while (!q.isEmpty()){
            node u = q.dequeue();
            result[counter++] = u.val;
            for(node n1: u.ns){
                n1.incoming--;
                if(n1.incoming == 0){
                    q.enqueue(n1);
                }
            }
        }
        if (counter == n) {
            for (int i : result) {
                System.out.print(i + " ");
            }
        } else {
            System.out.print("impossible");
        }
    }
}
class node{
    int val;
    int layer = 0;
    int incoming;
    node(int v){
        val = v;
    }
    ArrayList<node> ns = new ArrayList<>();
}
class queue{
    int front = 0;
    int rear = 0;
    node [] ns;
    queue(int size){
        ns = new node[size];
    }
    void enqueue(node n){
        ns[rear++] = n;
        for(int i = rear - 1; i > front; i--){
            if(ns[i].layer == ns[i - 1].layer && ns[i].val < ns[i-1].val){
                node n1 = ns[i];
                ns[i] = ns[i-1];
                ns[i-1] = n1;
            }
            else break;
        }
    }
    node dequeue(){
        return ns[front++];
    }
    boolean isEmpty(){
        return front==rear;
    }
}
