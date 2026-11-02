import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
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
        if(this.inventory.isEmpty()) {
            System.out.println("Store Inventory is empty.");
            System.out.println("------------------------------------------------------------------------------------------");

            return;
        }
        for(Product items: productItems){
            System.out.printf("%-8s %-50s $%-12.2f %-20s\n", items.getSKU(), items.getProductName(), items.getPrice(), items.getDepartment());
        }
        System.out.println("------------------------------------------------------------------------------------------");
    }

    public void displayCart(){
        System.out.println("------------------------------------------------------------------------------------------");
        if(this.cart.isEmpty()) {
            System.out.println("Cart is empty.");
            System.out.println("------------------------------------------------------------------------------------------");
            return;
        }
        for(Product items: this.cart){
            System.out.printf("%-8s %-50s $%-12.2f %-20s\n", items.getSKU(), items.getProductName(), items.getPrice(), items.getDepartment());
        }
        System.out.println("------------------------------------------------------------------------------------------");
    }


    // Checking if department user wants to filter by exits.
    private Product searchInventoryByDepartment(String department){
        for(Product items: this.inventory){
            if(Objects.equals(items.getDepartment().toLowerCase(Locale.ROOT), department.toLowerCase())) {
                return items;
            }
        }
        System.out.println("Error: Department does not exist or invalid department input.");
        return null;
    }

    // If department does exist print all instances of department.
    public void displayInventoryByDepartment(String department) {
        System.out.println("------------------------------------------------------------------------------------------");
        Product itemsDepartment = searchInventoryByDepartment(department);
        for(Product items: this.inventory){
            if(itemsDepartment != null && Objects.equals(items.getDepartment().toLowerCase(Locale.ROOT), department.toLowerCase())){
                System.out.printf("%-8s %-50s $%-12.2f %-20s\n", items.getSKU(), items.getProductName(), items.getPrice(), items.getDepartment());
            }
        }
        System.out.println("------------------------------------------------------------------------------------------");
    }

    public void sortInventoryBYPrice(double maxPrice, double minPrice) {
        System.out.println("------------------------------------------------------------------------------------------");
        for(Product items: this.inventory){
            if(items != null && (items.getPrice() <= maxPrice && items.getPrice() >= minPrice)) {
                System.out.printf("%-8s %-50s $%-12.2f %-20s\n", items.getSKU(), items.getProductName(), items.getPrice(), items.getDepartment());
            }
        }
        System.out.println("------------------------------------------------------------------------------------------");
    }

    // Searching for all existing SKU before adding to cart.
    // Checks if the product the user wants to add to the cart exists.
    private Product searchInventoryBySKU(String SKU) {
        for(Product items: this.inventory){
            if(Objects.equals(items.getSKU(), SKU)){
                return items;
            }
        }
        System.out.println("Error: Item does not exist or invalid SKU input.");
        return null;
    }

    // Once SKUs has been it adds them to the cart array list
    public void addToCart(String SKU){
        Product item = searchInventoryBySKU(SKU);

        if(item != null) this.cart.add(item);
    }

    // Same logic as searchInventoryBySKU method but for cart list
    // Checks if the product the user wants to remove from the cart exists.
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

    public void checkOut(){
        double totalCost = 0;
        System.out.println("------------------------------------------------------------------------------------------");
        if(this.cart.isEmpty()) {
            System.out.println("Cart is empty.");
            System.out.println("------------------------------------------------------------------------------------------");
            return;
        }
        for(Product items: this.cart){
            totalCost += items.getPrice();
            System.out.printf("%-8s %-50s $%-12.2f %-20s\n", items.getSKU(), items.getProductName(), items.getPrice(), items.getDepartment());
        }
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.printf("Total Cost: $%.2f\n", totalCost);
        System.out.println("------------------------------------------------------------------------------------------");
    }

    public boolean isCartEmpty(){
        if(this.cart.isEmpty()) {
            System.out.println("\nCart is empty. Please add products to cart.");
            return true;
        }
        return false;
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

}
