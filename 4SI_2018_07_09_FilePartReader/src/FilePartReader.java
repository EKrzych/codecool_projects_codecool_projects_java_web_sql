import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FilePartReader {
    private String filePath;
    private Integer fromLine;
    private Integer toLine;

    public FilePartReader() {
        this.filePath = "";
        this.fromLine = 0;
        this.toLine = 0;
    }

    public void setup(String filePath, Integer fromLine, Integer toLine) {
        this.filePath = filePath;
        this.fromLine = fromLine;
        this.toLine = toLine;
        if(toLine < fromLine) {
            throw new IllegalArgumentException();
        }
        if(fromLine < 1) {
            throw new IllegalArgumentException();
        }
    }

    private String read(String filePath) throws IOException {
        StringBuilder sb = new StringBuilder();
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine();
            while(line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
        }
        return sb.toString().trim();
    }

    public String readLines() throws IOException {
        StringBuilder sb = new StringBuilder();
        String [] stringArray;
            stringArray = read(filePath).split(System.lineSeparator());
        for(int i = 1; i <= stringArray.length; i++) {
            if(i <= toLine && i>=fromLine) {
                sb.append(stringArray[i - 1]);
                sb.append("\n");
            }
        }
        return sb.toString().trim();
    }

    public static void main(String[] args) {
        FilePartReader fp = new FilePartReader();
        fp.setup("resources/test_data.txt", 2, 4);
        try {
            System.out.println(fp.readLines());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
