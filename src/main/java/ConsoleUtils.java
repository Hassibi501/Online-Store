import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
                ---------------------------------------------
                1. Search/Filter Products by Department
                2. Add Product to Cart
                3. Go Back to Home Page""");
    }

    public static void displayCartSubMenu(){
        System.out.println("""
                ---------------------------------------------
                1. Check Out
                2. Remove Product(s) From Cart
                3. Go Back to Home Page""");
    }



    public static String getValidInput(Scanner scanner, String promptMessage, String regex, String errorMessage) {
        while (true) {
            System.out.println(promptMessage);

            String userInput = scanner.nextLine();

            if (userInput.equals(null)){
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
