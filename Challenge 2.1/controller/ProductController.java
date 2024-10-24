package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import model.Product;

public class ProductController{
    public void loadData(List<Product> productList){
        productList.add(new Product("Laptop", 999.99, 5));
        productList.add(new Product("SmartPhone", 499.99, 10));
        productList.add(new Product("Tablet", 299.99, 0));
        productList.add(new Product("SmartWatch", 199.99, 3));
    }

    public void showAllProducts(List<Product> productList){
        for (Product product : productList){
            System.out.println(product.toString());
        }
    }

    public void caculateTotalValue(List<Product> productList){
        double total = 0;
        for (Product product : productList){
            total += product.getPrice() * product.getQuantity();
        }
        System.out.println("Total value in the inventory is: " + String.format("%.2f", total));
    }

    public boolean isNameExist(String name, List<Product> productList){
        for (Product product : productList){
            if (name.equalsIgnoreCase(product.getName())){
                return true;
            }
        }
        return false;
    }

    public void ascSortByPrice(List<Product> productList){
        Collections.sort(productList, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return Double.compare(p1.getPrice(), p2.getPrice());
            }
        });
    } 

    public void descSortByPrice(List<Product> productList){
        Collections.sort(productList, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return Double.compare(p2.getPrice(), p1.getPrice());
            }
        });
    }

    public void ascSortByQuantity(List<Product> productList){
        Collections.sort(productList, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return Integer.compare(p1.getQuantity(), p2.getQuantity());
            }
        });
    }

    public void descSortByQuantity(List<Product> productList){
        Collections.sort(productList, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return Integer.compare(p2.getQuantity(), p1.getQuantity());
            }
        });
    }
    public String findMostExpensive(List<Product> productList){
        List<Product> copiedList = (List<Product>) ((ArrayList<Product>) productList).clone();
        descSortByPrice(copiedList);    
        String expensiveProdName = copiedList.get(0).getName();
        return expensiveProdName;
    }
}