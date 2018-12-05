import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class FileWordAnalyzer {
    FilePartReader fp = new FilePartReader();
    ArrayList<String> wholeList = new ArrayList<>();
    String [] stringArray = new String[0];

    public FileWordAnalyzer(String filepath, int fromLine, int toLine) {
        fp.setup(filepath,fromLine, toLine);
    }

    public ArrayList<String> wordsByABC() {
        try {
            stringArray = fp.readLines().trim().split("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(String s : stringArray) {
            wholeList.addAll(Arrays.asList(s.split(" ")));
        }
        Collections.sort(wholeList);
        return wholeList;
    }

    public ArrayList<String>  wordsContainingSubString(String subString) {
        ArrayList<String> wordsContainingSubstring = new ArrayList<>();
        try {
            stringArray = fp.readLines().trim().split("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(String s : stringArray) {
            wholeList.addAll(Arrays.asList(s.split(" ")));
        }

        for(String s : wholeList) {
            if(s.contains(subString)) {
                wordsContainingSubstring.add(s);
            }
        }
        return wordsContainingSubstring;
    }

    public ArrayList<String> wordsArePalindrome(){
        ArrayList<String> palindromes = new ArrayList<>();

        try {
            stringArray = fp.readLines().trim().split("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(String s : stringArray) {
            wholeList.addAll(Arrays.asList(s.split(" ")));
        }

        for(String s : wholeList) {
            if(s.equals(new StringBuilder(s).reverse().toString())) {
                palindromes.add(s);
            }
        }
        return palindromes;
    }
}
