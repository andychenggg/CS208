package LabAss1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class StableMatching {

    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        //减少扩容
        Map<String, Integer> menMap = new HashMap<>(n + 1, 1.f);
        Map<String, Integer> womenMap = new HashMap<>(n + 1, 1.f);
        String [] menNames = new String[n];
        String [] womenNames = new String[n];
        int [][] menPrefList = new int[n][n];
        int [][] womenPrefList = new int[n][n];
        //men,women的编号：0, ..., n-1
        for(int i = 0; i < n; i++){
            menNames[i] = in.next();
            menMap.put(menNames[i], i);
        }
        for(int i = 0; i < n; i++){
            womenNames[i] = in.next();
            womenMap.put(womenNames[i], i);
        }
        //以women或men编号为列进行排名之后的列表，表中数据是排名，从0开始
        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < n; j++){
                menPrefList[i][womenMap.get(in.next())] = j;

            }
        }
        //print menPrefList
//        for(int i = 0; i < n; i++)
//        {
//            for(int j = 0; j < n; j++){
//                out.print(menPrefList[i][j] + " ");
//            }
//            out.println("");
//        }
        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < n; j++){
                womenPrefList[i][menMap.get(in.next())] = j;
            }
        }
        //print womenPrefList
//        for(int i = 0; i < n; i++)
//        {
//            for(int j = 0; j < n; j++){
//                out.print(womenPrefList[i][j] + " ");
//            }
//            out.println("");
//        }
        //menReverseList,以排名为列标，从0开始，表中数据是女人编号，
        int [][] menRankList = new int[n][n];
        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < n; j++){
                menRankList[i][menPrefList[i][j]] = j;
            }
        }
        //print rankList
//        for(int i = 0; i < n; i++)
//        {
//            for(int j = 0; j < n; j++){
//                out.print(menRankList[i][j] + " ");
//            }
//            out.println("");
//        }
        //match mens and women
        int [] wifeOfMen = new int[n];
        int [] husbandOfWomen = new int[n];
        for(int i = 0; i < n; i++){
            wifeOfMen[i] = -1;
            husbandOfWomen[i] = -1;
        }
        //检测男人求婚求到哪里了
        int [] counters = new int[n];
        //freeQ
        Queue<Integer> freeQ = new ArrayDeque<>(n);
        for(int i = 0; i<n; i++){
            freeQ.add(i);
        }
        while(!freeQ.isEmpty()){
            int menIndex = freeQ.poll();
            int womenIndex = menRankList[menIndex][counters[menIndex]++];
            //out.println(menIndex + " " + womenIndex);
            if(husbandOfWomen[womenIndex] == -1){
                //match
                husbandOfWomen[womenIndex] = menIndex;
                wifeOfMen[menIndex] = womenIndex;
            }
            //这个女人结婚了,如果碰见了一个更优秀的男士
            else if(womenPrefList[womenIndex][menIndex] <
                womenPrefList[womenIndex][husbandOfWomen[womenIndex]]){
                freeQ.add(husbandOfWomen[womenIndex]); //set free
                husbandOfWomen[womenIndex] = menIndex;
                wifeOfMen[menIndex] = womenIndex;
            }
            else{
                //rejected
                freeQ.add(menIndex);
            }
        }
        for(int i = 0; i < n; i++){
            out.println(menNames[i] + " " + womenNames[wifeOfMen[i]]);
        }
        out.close();
    }

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