import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Utility {
    public static void readFile(String fileName){

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
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
