
import java.io.FileOutputStream;

public class Sample {
    public static void main(String[] args) throws IOException {
        FileOutputStream output = new FileOutputStream("E:/out.txt");
        String data = "하이";
        output.write(data.getBytes());
        output.close();
    }
    
}