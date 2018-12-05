import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class FileWordAnalyzerTest {
    FileWordAnalyzer fwa;

    @BeforeEach
    public void prepareFileWordAnalyzer() {
        this.fwa = new FileWordAnalyzer("resources/test_data.txt", 1, 7);
    }

    @Test
    public void testArrayLengthFromWordsByABC() {

        assertEquals(24, fwa.wordsByABC().size());
    }
    @Test
    public void testWordsByABC() {
        ArrayList<String> expected = fwa.wordsByABC();
        Collections.sort(expected, String.CASE_INSENSITIVE_ORDER);
        assertEquals(expected, fwa.wordsByABC());
    }

    @Test
    public void testLengthwordsContainingSubString_ZZZ() {
        assertEquals(0, fwa.wordsContainingSubString("ZZZ").size());
    }

    @Test
    public void testLengthwordsContainingSubString_1() {

        assertEquals(1, fwa.wordsContainingSubString("1").size());
    }

    @Test
    public void testLengthwordsContainingSubString_6() {

        assertEquals(6, fwa.wordsContainingSubString("6").size());
    }

    @Test
    public void testLengthwordsContainingSubString_a() {

        assertEquals(9, fwa.wordsContainingSubString("a").size());
    }

    @Test
    public void testLengthwordsContainingSubString_A() {

        assertEquals(0, fwa.wordsContainingSubString("A").size());
    }

    @Test
    public void testLengthWordsArePalindrome() {

        assertEquals(2, fwa.wordsArePalindrome().size());
    }

    @Test
    public void testWordsArePalindrome() {

        ArrayList<String> palindromes = fwa.wordsArePalindrome();
        for(String s : palindromes) {
            assertEquals(s, (new StringBuilder(s).reverse().toString()));
        }
    }

}