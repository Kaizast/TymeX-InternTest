package controller;

public class MenuController {

    public void showMenu() {
        System.out.println("");
        System.out.println("1. Calculate the total inventory value.");
        System.out.println("2. Find the most expensive product");
        System.out.println("3. Check if a product is in stock");
        System.out.println("4. Sort product");
        System.out.println("5. Exit");
    }

    public void showSubMenu() {
        System.out.println("");
        System.out.println("Sort by:");
        System.out.println("1.Ascending Price");
        System.out.println("2.Descending Price");
        System.out.println("3.Ascending Quantity");
        System.out.println("4.Descending Quantity");
        System.out.println("5.Back");
    }

}
