import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class StoreManager {
    private final ArrayList<Product> inventory;

    public StoreManager(ArrayList<Product> productItems){
        this.inventory = productItems;
    }

    public StoreManager(ArrayList<Product> productItems, String[] _itemsToRemove){
        this.inventory = productItems;
    }

    public static ArrayList<Product> extractFileData(String fileName){
        ArrayList<Product> products = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            bufferedReader.readLine(); //Skips first line of file - SKU|Product Name|Price|Department
            while ((line = bufferedReader.readLine()) != null) {
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

    public void displayProducts(ArrayList<Product> productItems){
        for(Product items: productItems){
            System.out.printf("%-8s %-50s $%-12.2f %-20s\n", items.getSKU(), items.getProductName(), items.getPrice(), items.getDepartment());
        }
    }

    public void addProduct(Product products) {
        this.inventory.add(products);
    }

    public void removeProduct(Product products) {
        this.inventory.remove(products);
    }

    public void batchRemoveProducts(Product products, String[] _itemsToRemove) {
        for(String items: _itemsToRemove) {
            removeProduct(products);
        }
    }
}
