package lab1_recursion;

public class hanoi {
    public static void main(String[] args) {
        move(3, 'A', 'C', 'B');
    }

    public static void move(int plates, char colBegin, char colEnd, char colAux)
    {
        if(plates == 1){
            System.out.println("level " + 1 + " from " + colBegin + " to " + colEnd);
            return;
        }
        move(plates - 1, colBegin, colAux, colEnd);
        System.out.println("level " + plates + " from " + colBegin + " to " + colEnd);
        move(plates - 1, colAux, colEnd, colBegin);
    }
}
