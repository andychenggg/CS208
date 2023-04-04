package LabAss2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class NewYearCall {

    public static void main(String[] args) {
        QReader in = new QReader();
        int n = in.nextInt(), m = in.nextInt(), p = in.nextInt(), q = in.nextInt();
        node [] nodes = new node[n];
        for(int i = 0; i < n; i++){
            nodes[i] = new node();
        }
        for(int i = 0; i < m; i++){
            int n1 = in.nextInt() - 1;
            int n2 = in.nextInt() - 1;
            nodes[n1].connect.add(nodes[n2]);
            nodes[n2].connect.add(nodes[n1]);
        }
        int [] questions = new int[q];
        for(int i = 0; i< q; i++){
            questions[i] = in.nextInt();
        }
        int max = Arrays.stream(questions).max().orElse(Integer.MIN_VALUE);
        //System.out.println(max);
        int [] numberOfVillages = new int[max+1]; // including day 0;
        Queue<node> queue = new ArrayDeque<>();
        queue.add(nodes[p - 1]);
        nodes[p - 1].isVisit = true;
        while(!queue.isEmpty()){
            node n1 = queue.poll();
            if(n1.time >= max+1){
                // never query that day
                break;
            }
            // count in the numberOfVillages
            numberOfVillages[n1.time]++;
            for(node u: n1.connect){
                if(!u.isVisit){
                    u.time = n1.time+1;
                    //System.out.println("time" + u.time);
                    u.isVisit = true;
                    queue.add(u);
                }
            }
        }
        for(int i = 0; i< max; i++){
            numberOfVillages[i+1] += numberOfVillages[i];
        }
        QWriter out = new QWriter();
        for(int i = 0; i< q; i++){
            out.print(numberOfVillages[questions[i]]+" ");
        }
        out.close();
    }
}
class node{
    int time = 0;
    boolean isVisit = false;
    ArrayList<node> connect = new ArrayList<>();
}
//class QReader {
//    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//    private StringTokenizer tokenizer = new StringTokenizer("");
//
//    private String innerNextLine() {
//        try {
//            return reader.readLine();
//        } catch (IOException e) {
//            return null;
//        }
//    }
//
//    public boolean hasNext() {
//        while (!tokenizer.hasMoreTokens()) {
//            String nextLine = innerNextLine();
//            if (nextLine == null) {
//                return false;
//            }
//            tokenizer = new StringTokenizer(nextLine);
//        }
//        return true;
//    }
//
//    public String nextLine() {
//        tokenizer = new StringTokenizer("");
//        return innerNextLine();
//    }
//
//    public String next() {
//        hasNext();
//        return tokenizer.nextToken();
//    }
//
//    public int nextInt() {
//        return Integer.parseInt(next());
//    }
//
//    public long nextLong() {
//        return Long.parseLong(next());
//    }
//}
//
//class QWriter implements Closeable {
//    private BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
//
//    public void print(Object object) {
//        try {
//            writer.write(object.toString());
//        } catch (IOException e) {
//            return;
//        }
//    }
//
//    public void println(Object object) {
//        try {
//            writer.write(object.toString());
//            writer.write("\n");
//        } catch (IOException e) {
//            return;
//        }
//    }
//
//    @Override
//    public void close() {
//        try {
//            writer.close();
//        } catch (IOException e) {
//            return;
//        }
//    }
//}
