import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleUtils {
    public static void displayMainMenu() {
        System.out.println("""
                ---------------------------------------------
                      Welcome to Hassieb's Online Store
                ---------------------------------------------
                1. Display Products
                2. Display Cart
                3. Exit""");
    }

    public static void displayProductsSubMenu(){
        System.out.println("""
             
                1. Search Products by Department
                2. Search Products by Price
                3. Add Product to Cart
                4. Go Back to Home Page""");
    }

    public static void runProductMenu(Scanner scanner, StoreManager manger, ArrayList<Product> dataFromFile){
        manger.displayProducts(dataFromFile);
        while(true) {
            ConsoleUtils.displayProductsSubMenu();
            int menuChoice = Integer.parseInt(getValidInput(scanner, "Enter menu option(1-4):", "[1-4]","Error: Please enter valid menu option (1-4)."));
            switch (menuChoice) {
                case 1:
                    System.out.println("Search by departments...");
                    break;
                case 2:
                    System.out.println("Search by price");
                    break;
                case 3:
                    System.out.println("add to cart");
                    String userInput = getValidInput(scanner, "Enter product SKU to place in Cart:", "^[A-Z]{2}\\d{4}$", "Error: Please enter valid and existing SKU product item.");

                    manger.addToCart(userInput);
                    break;
                case 4:
                    System.out.println("Return to main menu");
                    return;
            }
        }
    }

    public static void displayCartSubMenu(){
        System.out.println("""
                
                1. Check Out
                2. Remove Product(s) From Cart
                3. Go Back to Home Page""");
    }

    public static void runCartMenu(Scanner scanner, StoreManager manger){
        manger.displayCart();
        while(true) {
            displayCartSubMenu();
            int menuChoice = Integer.parseInt(ConsoleUtils.getValidInput(scanner, "Enter menu option(1-3):", "[1-3]","Error: Please enter valid menu option (1-3)."));
            switch (menuChoice) {
                case 1:
                    System.out.println("checking out...");
                    break;
                case 2:
                    System.out.println("removing products...");
                    String userInput = getValidInput(scanner, "Enter product SKU to remove from Cart:", "^[A-Z]{2}\\d{4}$", "Error: Please enter valid and existing SKU product item.");
                    manger.removeFromCart(userInput);
                    System.out.println();
                    manger.displayCart();
                    break;
                case 3:
                    System.out.println("going back to homepage...");
                    return;
            }
        }
    }



    public static String getValidInput(Scanner scanner, String promptMessage, String regex, String errorMessage) {
        while (true) {
            System.out.println(promptMessage);

            String userInput = scanner.nextLine();

            if (userInput == null){
                System.out.println("Error: Input can not be empty. Try again and please enter valid input.");
                continue;
            }

            if (!userInput.matches(regex)){
                System.out.println(errorMessage);
                continue;
            }

            return userInput;
        }
    }


}
