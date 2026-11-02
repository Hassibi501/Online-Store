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
        String userInput;
        manger.displayProducts(dataFromFile);
        while(true) {
            ConsoleUtils.displayProductsSubMenu();
            int menuChoice = Integer.parseInt(getValidInput(scanner, "Enter menu option(1-4):", "[1-4]","Error: Please enter valid menu option (1-4)."));
            switch (menuChoice) {
                case 1:
                    userInput = getValidInput(scanner, "Enter department name:", "^[a-zA-z]+(\\s?[a-zA-z]+)*$", "Error: Please enter valid department name. Make sure there are only letters and single space between each word.");
                    System.out.println();
                    manger.displayInventoryByDepartment(userInput);
                    break;
                case 2:
                    double maxPrice = Double.parseDouble(getValidInput(scanner, "Enter maximum price range:", "^\\d+(\\.?\\d\\d?)?$", "Error: Enter a valid price (e.g. 123.45)."));
                    double minPrice = Double.parseDouble(getValidInput(scanner, "Enter minimum price range:", "^\\d+(\\.?\\d\\d?)?$", "Error: Enter a valid price (e.g. 123.45)."));
                    System.out.println();
                    manger.sortInventoryBYPrice(maxPrice, minPrice);
                    break;
                case 3:
                    userInput = getValidInput(scanner, "Enter product SKU to place in Cart:", "^[A-Z]{2}\\d{4}$", "Error: Please enter valid and existing SKU product item.");
                    manger.addToCart(userInput);
                    break;
                case 4:
                    System.out.println("Returning to main menu...");
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
        String userInput;
        manger.displayCart();
        while(true) {
            displayCartSubMenu();
            int menuChoice = Integer.parseInt(ConsoleUtils.getValidInput(scanner, "Enter menu option(1-3):", "[1-3]","Error: Please enter valid menu option (1-3)."));
            switch (menuChoice) {
                case 1:
                    if(manger.isCartEmpty()) return;

                    manger.checkOut();
                    System.out.println();
                    userInput = getValidInput(scanner, " Please confirm final payment(yes/no):", "^(?i)(yes|no)$", "Error: Please enter yes or no (not case sensitive).");
                    if(userInput.equalsIgnoreCase("yes")){
                        System.out.println("\nThank you for shopping at Hassieb's Store. Good Bye! Please come again!");
                        System.exit(1);
                    }
                    break;
                case 2:
                    userInput = getValidInput(scanner, "Enter product SKU to remove from Cart:", "^[A-Z]{2}\\d{4}$", "Error: Please enter valid and existing SKU product item.");
                    manger.removeFromCart(userInput);
                    System.out.println();
                    manger.displayCart();
                    break;
                case 3:
                    System.out.println("Returning to main menu...");
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
