import java.util.ArrayList;
import java.util.Scanner;

/**
 * Workshop 3: Online Store
 */

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String fileName = "products.csv";

        ArrayList<Product> dataFromFile = StoreManager.extractFileData(fileName);
        StoreManager manager = new StoreManager(dataFromFile);


        while(true){
            ConsoleUtils.displayMainMenu();

            int menuChoice = Integer.parseInt(ConsoleUtils.getValidInput(scanner, "Enter menu option(1-3):", "[1-3]","Error: Please enter valid menu option (1-3)."));

            switch (menuChoice){
                case 1:
                    manager.displayProducts(dataFromFile);
                    ConsoleUtils.displayProductsSubMenu();


                    break;
                case 2:
                    ConsoleUtils.displayCartSubMenu();
                    break;
                case 3:
                    System.out.println("Exiting Store...");
                    scanner.close();
                    return;
            }//switch
        }//while


    }
}
