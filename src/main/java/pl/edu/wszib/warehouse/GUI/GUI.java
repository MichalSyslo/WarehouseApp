package pl.edu.wszib.warehouse.GUI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.wszib.warehouse.database.IDataBase;
import pl.edu.wszib.warehouse.model.Product;

import java.util.Scanner;

@Component
public class GUI implements IGUI {
    Scanner scanner = new Scanner(System.in);

    @Autowired
    public IDataBase database;

    @Override
    public void showMainMenu() {
        while(true){
            System.out.println("\n1. Add product");
            System.out.println("2. Remove product");
            System.out.println("3. Show all products");
            System.out.println("4. Exit");


            switch (scanner.nextLine()){
                case "1":
                    addProduct();
                    break;
                case "2":
                    removeProduct();
                    break;
                case "3":
                    showAllProducts();
                    break;
                case "4":
                        System.exit(0);
                    break;
                default:
                    System.out.println("Provided number is incorrect. Please, choose proper option");
            }
        }
    }

    public void addProduct(){
        System.out.println("Provide product ID: ");
        String id = scanner.nextLine();
        System.out.println("Provide name: ");
        String name = scanner.nextLine();
        System.out.println("Provide quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        if(database.addProduct(new Product(name, quantity, id))){
            System.out.println("Product has been added.");
        }else{
            System.out.println("Product can not be added.");
        }

    }

    public void removeProduct(){
        System.out.println("Provide product ID: ");
        String id = scanner.nextLine();
        System.out.println("Provide quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        if(database.removeProduct(id, quantity)){
            System.out.println("Product has been removed.");
        } else{
            System.out.println("Product can not be removed");
        }

    }

    public void showAllProducts(){
        for(Product currentProduct : database.getAllProducts()){
            System.out.println(currentProduct);
        }
    }
}
