
import controller.MenuController;
import controller.ProductController;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.Product;

public class Main {

    public static void main(String[] args) {
        ProductController productCon = new ProductController();
        MenuController menu = new MenuController();
        List<Product> productList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        productCon.loadData(productList);
        System.out.println("Welcome to Product Inventory Management. Please select number from 1 to 5:");
        String option;
        do {
            menu.showMenu();
            option = scanner.nextLine();
            switch (option) {
                case "1":
                    productCon.caculateTotalValue(productList);
                    break;
                case "2":
                    System.out.println("The most expensive product is " + productCon.findMostExpensive(productList));
                    break;
                case "3":
                    System.out.println("Input the product you want to find");
                    String findName = scanner.nextLine();
                    boolean result = productCon.isNameExist(findName, productList);
                    if (result){
                        System.out.println(findName + " is existed in the inventory");
                    }
                    else{
                        System.out.println(findName + " is not existed in the inventory");
                    }
                    break;
                case "4":
                    menu.showSubMenu();
                    String chooseTag = scanner.nextLine().trim();
                    switch (chooseTag) {
                        case "1":
                            productCon.ascSortByPrice(productList);
                            productCon.showAllProducts(productList);
                            break;
                        case "2":
                            productCon.descSortByPrice(productList);
                            productCon.showAllProducts(productList);
                            break;
                        case "3":
                            productCon.ascSortByQuantity(productList);
                            productCon.showAllProducts(productList);
                            break;
                        case "4":
                            productCon.descSortByQuantity(productList);
                            productCon.showAllProducts(productList);
                            break;
                        case "5":
                            break;
                        default:
                            System.out.println("Please input only number form 1 to 5");
                    }
                    break;
                    case "5":
                        break;
                default:
                    System.out.println("Please input only number form 1 to 5");

            }
        } while (!option.equals("5"));
        scanner.close();
    }
}
