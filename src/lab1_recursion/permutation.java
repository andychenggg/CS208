package lab1_recursion;

import java.util.Scanner;

public class permutation {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        per("", s);
    }
    public static void per(String s1, String s2){
        if (s2.equals("")) {
            System.out.println(s1);
        }
        for(int i = 0; i<s2.length(); i++){
            String newS1 = s1 + s2.charAt(i);
            String newS2;
            if(i != s2.length() - 1){
                newS2 = s2.substring(0, i) + s2.substring(i+1);
            }
            else {
                newS2 = s2.substring(0, i);
            }
            per(newS1, newS2);
        }
    }

}
