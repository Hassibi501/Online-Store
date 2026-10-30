import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class StoreRepository {

    public static ArrayList<Product> readFile(String fileName){
        ArrayList<Product> products = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            bufferedReader.readLine(); //Skips first line of file - SKU|Product Name|Price|Department
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);

                String[] parts = line.split("\\|");

                Product extractedProducts = new Product(parts[0], parts[1], Double.parseDouble(parts[2]), parts[3]);

                products.add(extractedProducts);
            }
        }
        catch (IOException e) {
            e.getStackTrace();
        }

        return products;
    }
}
