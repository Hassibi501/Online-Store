import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String fileName = "products.csv";

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader (fileName))) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        }
        catch (IOException e) {
            e.getStackTrace();
        }
    }
}
