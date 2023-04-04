package LabAss2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SimplicityFavorsRegularities {

    /**
     * When we separate the nodes into in-nodes and out-nodes, we have 2 observations:<br>
     * 1. Every edge connect an in-node and an out-node. Thus, any circle in this undirected graph meet the requirement in the problem.
     *    Then we just want to find the smallest circle. Nodes number should be even.<br>
     * 2. The Max(layer difference) for revisited nodes in the BFS is at most 1;
     *
     * @param args command line argument
     */

    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int T = in.nextInt();
        for(int i = 0; i < T; i++){
            int n = in.nextInt(), m = in.nextInt();
            Node1 [] nodesIn = new Node1[n];
            Node1 [] nodesOut = new Node1[n];
            for(int j = 0; j < n; j++){
                nodesOut[j] = new Node1();
                nodesIn[j] = new Node1();
            }
            for(int j = 0; j < m; j++){
                int v1 = in.nextInt() - 1, v2 = in.nextInt() - 1;
                nodesOut[v1].ns.add(nodesIn[v2]);
                nodesIn[v2].ns.add(nodesOut[v1]);
            }
            int min = m + 1;
            int p1 = 0, p2 = 0;
            for(Node1 u: nodesOut){
                //BFS in u
                Queue<Node1> q = new ArrayDeque<>();
                q.add(u);
                u.isVisit = true;
                tab:
                while(!q.isEmpty()){
                    Node1 v = q.poll();
                    for(Node1 n1: v.ns){
//                        out.println("p1;"+p1++);
                        if(!n1.isVisit){
                            n1.isVisit = true;
                            n1.layer = v.layer + 1;
                            n1.father = v;
                            q.add(n1);
                        }
                        else if(n1 != v.father){
                            min = Math.min(min, n1.layer+v.layer+1);
//                            out.println(nodesOut[2].layer);
//                            out.println("p2;"+p2+++""+min);
//                            if(min == 2){
////                                System.out.println(""+n1.layer+" "+v.layer);
//                            }
                            break tab;
                        }
                    }
                }
                // clean the nodes
                for(Node1 v: nodesOut){
                    v.layer = 0;
                    v.isVisit = false;
                    v.father = null;
                }
                for(Node1 v: nodesIn){
                    v.layer = 0;
                    v.isVisit = false;
                    v.father = null;
                }
            }
            for(Node1 u: nodesOut){
                //BFS in u
                Queue<Node1> q = new ArrayDeque<>();
                q.add(u);
                u.isVisit = true;
                tab:
                while(!q.isEmpty()){
                    Node1 v = q.poll();
                    for(Node1 n1: v.ns){
                        if(!n1.isVisit){
                            n1.isVisit = true;
                            n1.layer = v.layer + 1;
                            n1.father = v;
                            q.add(n1);
                        }
                        else if(n1 != v.father){
                            min = Math.min(min, n1.layer+v.layer+1);
                            break tab;
                        }
                    }
                }
                // clean the nodes
                for(Node1 v: nodesOut){
                    v.layer = 0;
                    v.isVisit = false;
                    v.father = null;
                }
                for(Node1 v: nodesIn){
                    v.layer = 0;
                    v.isVisit = false;
                    v.father = null;
                }
            }
            out.println(min == m + 1 ? -1 : min);
        }
        out.close();
    }
}
class Node1{
    Node1 father;
    int layer = 0;
    boolean isVisit = false;
    ArrayList<Node1> ns= new ArrayList<>();
}
class QReader {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private StringTokenizer tokenizer = new StringTokenizer("");

    private String innerNextLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    public boolean hasNext() {
        while (!tokenizer.hasMoreTokens()) {
            String nextLine = innerNextLine();
            if (nextLine == null) {
                return false;
            }
            tokenizer = new StringTokenizer(nextLine);
        }
        return true;
    }

    public String nextLine() {
        tokenizer = new StringTokenizer("");
        return innerNextLine();
    }

    public String next() {
        hasNext();
        return tokenizer.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public long nextLong() {
        return Long.parseLong(next());
    }
}

class QWriter implements Closeable {
    private BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public void print(Object object) {
        try {
            writer.write(object.toString());
        } catch (IOException e) {
            return;
        }
    }

    public void println(Object object) {
        try {
            writer.write(object.toString());
            writer.write("\n");
        } catch (IOException e) {
            return;
        }
    }

    @Override
    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            return;
        }
    }
}
