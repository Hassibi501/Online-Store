import java.util.Scanner;

/**
 * Workshop 3: Online Store
 */

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String fileName = "products.csv";



        System.out.println(StoreRepository.readFile(fileName));


//        while(true){
//            System.out.println("""
//                ---------------------------------------------
//                ======Welcome to Hassieb's Online Store======
//                ---------------------------------------------
//                1. Display Products
//                2. Display Cart
//                3. Exit""");
//
//            int userChoice = Integer.parseInt(scanner.nextLine());
//
//            switch (userChoice){
//                case 1:
//                    System.out.println("""
//                            ---------------------------------------------
//                            1. Search/Filter Products by Department
//                            2. Add Product to Cart
//                            3. Go Back to Home Page""");
//                    break;
//                case 2:
//                    System.out.println("""
//                            ---------------------------------------------
//                            1. Check Out
//                            2. Remove Product From Cart
//                            3. Go Back to Home Page""");
//                    break;
//                case 3:
//                    System.out.println("Exiting Store...");
//                    scanner.close();
//                    return;
//                default:
//                    System.out.println("Error: Please enter valid menu option (1-3).");
//            }//switch
//        }//while
//

    }
}
