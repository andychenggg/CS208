package LabAss1;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class testcase {

    public static void main(String[] args) {

        int menNum = 10;
        Random r = new Random();
        List<String> menNames = new ArrayList<>(arrangement.generateStringArrangement("ABCD"));
        List<String> womenNames = new ArrayList<>(arrangement.generateStringArrangement("abcd"));
        ArrayList<Integer> rank = new ArrayList<>();
        for(int i = 0; i < menNum; i++){
            rank.add(i);
        }
        List<ArrayList<Integer>> ranks = new ArrayList<>(arrangement.generateIntArrangement(rank));
        System.out.println(menNum);
        for(int i = 0; i<menNum; i++){
            System.out.print(menNames.get(i) + " ");
        }
        System.out.println();
        for(int i = 0; i<menNum; i++){
            System.out.print(womenNames.get(i) + " ");
        }
        System.out.println();
        for(int i = 0; i<menNum; i++){
            ArrayList<Integer> randRank = ranks.get(r.nextInt(ranks.size()));
//            for(int i1: randRank){
//                System.out.println(i1);
//            }
            for(int j = 0; j<menNum; j++){
                System.out.print(womenNames.get(randRank.get(j)) + " ");
            }
            System.out.println();
        }
        for(int i = 0; i<menNum; i++){
            ArrayList<Integer> randRank = ranks.get(r.nextInt(ranks.size()));
            for(int j = 0; j<menNum; j++){
                System.out.print(menNames.get(randRank.get(j)) + " ");
            }
            System.out.println();
        }
    }
}
class arrangement{
    public static Set<String> generateStringArrangement(String s){
        StringBuilder sb = new StringBuilder(s);
        Set<String> result = new HashSet<>();
        permutationStr(new StringBuilder(s), new StringBuilder(), result);
        return result;
    }
    private static void permutationStr(StringBuilder sb, StringBuilder prefix, Set<String> result){
        if(sb.length() == 0){
            result.add(prefix.toString());
        }
        else{
            for(int i = 0; i < sb.length(); i++){
                permutationStr(new StringBuilder(sb.substring(0, i) + sb.substring(i + 1)),
                    new StringBuilder(prefix.toString() + sb.charAt(i)), result);
            }
        }
    }
    public static  Set<ArrayList<Integer>> generateIntArrangement(ArrayList<Integer> list){
        Set<ArrayList<Integer>> result = new HashSet<>();
        permutationInt(list, new ArrayList<>(), result);
        return result;
    }
    private static void permutationInt(ArrayList<Integer> list, ArrayList<Integer> prefix, Set<ArrayList<Integer>> result){
        if(list.size() == 0){
            result.add(prefix);
        }
        else{
            for(int i = 0; i < list.size(); i++){
                int e = list.get(i);
                ArrayList<Integer> newPrefix = new ArrayList<>(prefix);
                newPrefix.add(e);
                ArrayList<Integer> newList = new ArrayList<>(list);
                newList.remove(i);
                permutationInt(newList, newPrefix, result);
            }
        }
    }
}
