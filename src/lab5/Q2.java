package lab5;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Q2 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<interval> l = new ArrayList<>();
        for(int i = 0; i < 8; i++){
            l.add(new interval(in.next(), in.nextInt(), in.nextInt()));
        }
        l.sort(Comparator.comparingInt(interval::getEnd));
        ArrayList<String> s = new ArrayList<>();
        while(l.size() != 0){
            interval i = l.get(0);
            s.add(i.name);
            while(l.size() != 0){
                if(l.get(0).begin < i.end)
                    l.remove(0);
                else break;
            }
        }
        s.forEach(s1 -> System.out.print(s1+" "));
    }
}
class interval{
    String name;
    int begin;
    int end;
    interval(String n, int b, int e){
        name = n;
        begin = b;
        end = e;
    }

    public String getName() {
        return name;
    }

    public int getBegin() {
        return begin;
    }

    public int getEnd() {
        return end;
    }
}
