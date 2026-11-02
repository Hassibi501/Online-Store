import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class StoreManager {
    private final ArrayList<Product> inventory;
    private final ArrayList<Product> cart;

    public StoreManager(ArrayList<Product> productItems){
        this.inventory = productItems;
        this.cart = new ArrayList<>();
    }



    public void displayProducts(ArrayList<Product> productItems){
        System.out.println("------------------------------------------------------------------------------------------");
        for(Product items: productItems){
            System.out.printf("%-8s %-50s $%-12.2f %-20s\n", items.getSKU(), items.getProductName(), items.getPrice(), items.getDepartment());
        }
        System.out.println("------------------------------------------------------------------------------------------");
    }

    public void displayCart(){
        System.out.println("------------------------------------------------------------------------------------------");
        if(this.cart.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }
        for(Product items: this.cart){
            System.out.printf("%-8s %-50s $%-12.2f %-20s\n", items.getSKU(), items.getProductName(), items.getPrice(), items.getDepartment());
        }
        System.out.println("------------------------------------------------------------------------------------------");
    }



    private Product searchInventoryBySKU(String SKU) {
        for(Product items: this.inventory){
            if(Objects.equals(items.getSKU(), SKU)){
                return items;
            }
        }
        System.out.println("Error: Item does not exist or invalid SKU input.");
        return null;
    }

    public void addToCart(String SKU){
        Product item = searchInventoryBySKU(SKU);

        if(item != null) this.cart.add(item);
    }

    private Product searchCartBySKU(String SKU) {
        for(Product items: this.cart){
            if(Objects.equals(items.getSKU(), SKU)){
                return items;
            }
        }
        System.out.println("Error: Item does not exist or invalid SKU input.");
        return null;
    }

    public void removeFromCart(String SKU){
        Product item = searchCartBySKU(SKU);

        if(item != null) this.cart.remove(item);
    }

//    public void batchRemoveProducts(Product products, String[] _itemsToRemove) {
//        for(String items: _itemsToRemove) {
//            removeFromCart(items);
//        }
//    }


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

}
