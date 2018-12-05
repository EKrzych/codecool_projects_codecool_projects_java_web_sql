import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FilePartReaderTest {
    FilePartReader fp;

    @BeforeEach
    public void createFilePartReader() {
        this.fp = new FilePartReader();
    }


    @Test
    public void testReadLinesBeforeSetup() {

        assertThrows(FileNotFoundException.class, () -> {fp.readLines();});
    }

    @Test
    public void testSetupFromLineLT1() {

        assertThrows(IllegalArgumentException.class, () -> {fp.setup("", -3,4 );});
    }

    @Test
    public void testSetupToLineLTFromLine() {

        assertThrows(IllegalArgumentException.class, () -> {fp.setup("", 5, 3);});
    }

    @Test
    public void testReadLines1_2() throws IOException {
        fp.setup("resources/test_data.txt", 1, 2);

        String result = fp.readLines();

        assertEquals("1a1\n2b 2a", result, "The result was: " + result);
    }

    @Test
    public void testReadLines2_4() {

        fp.setup("resources/test_data.txt", 2, 4);
        String result = null;
        try {
            result = fp.readLines();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals("2b 2a\n3c 3b 3a\n4d 4cr 4bb4 4a", result);
    }

    @Test
    public void testReadLinesAll() {

        fp.setup("resources/test_data.txt", 1, 7);
        String result = null;
        try {
            result = fp.readLines();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertTrue("1a1\n2b 2a\n3c 3b 3a\n4d 4cr 4bb4 4a\n5e 5d 5c 5b 5ax\n6f 6ea 6d 6ca 6bb 6a\n7g 7f 7ea".equals(result));
    }

    @Test
    public void testReadLinesPastEof() {

        fp.setup("resources/test_data.txt", 1, 9);
        String result = null;
        try {
            result = fp.readLines();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertTrue("1a1\n2b 2a\n3c 3b 3a\n4d 4cr 4bb4 4a\n5e 5d 5c 5b 5ax\n6f 6ea 6d 6ca 6bb 6a\n7g 7f 7ea".equals(result));
    }

}