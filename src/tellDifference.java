import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;

public class tellDifference {

    public static void main(String[] args) {
        tellStrDiff("abcde\nabcde", "abcde\nabce");
    }

    public static void tellFileDiff(String fileName1, String fileName2) throws IOException {
        File file1 = new File(fileName1);
        File file2 = new File(fileName2);

        BufferedReader reader1 = new BufferedReader(new FileReader(file1));
        BufferedReader reader2 = new BufferedReader(new FileReader(file2));

        ArrayList<String> list1 = new ArrayList<String>();
        ArrayList<String> list2 = new ArrayList<String>();

        String line1 = reader1.readLine();
        String line2 = reader2.readLine();

        while (line1 != null) {
            list1.add(line1);
            line1 = reader1.readLine();
        }

        while (line2 != null) {
            list2.add(line2);
            line2 = reader2.readLine();
        }
        if (list1.equals(list2)) {
            System.out.println("The two files are identical.");
        } else {
            System.out.println("The two files are not identical.");
            for (int i = 0; i < list1.size(); i++) {
                if (!list1.get(i).equals(list2.get(i))) {
                    System.err.println("Difference found at line " + (i+1) + ":\nFile 1: " + list1.get(i) + "\nFile 2: " + list2.get(i));
                }
            }
        }

    }

    public static void tellStrDiff(String s1, String s2){
        int counter = 0;
        CharacterIterator it1 = new StringCharacterIterator(s1);
        CharacterIterator it2 = new StringCharacterIterator(s2);
        for (char ch1 = it1.first(), ch2 = it2.first();
            ch1 != CharacterIterator.DONE && ch2 != CharacterIterator.DONE;
            ch1 = it1.next(), ch2 = it2.next()) {
            if(ch1 == '\n') counter++;
            if(ch1 != ch2){
                System.err.println("Difference occurs!");
                System.err.println("In line "+ (counter + 1) + "," + " difference: "+ ch1 + " " + ch2);
            }
        }
    }
}


