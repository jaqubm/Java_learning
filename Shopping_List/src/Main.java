import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

class ProductList {
    private final String category;
    final ArrayList<String> products;

    ProductList(String data) {
        category = data;
        products = new ArrayList<>();
    }

    void new_product(String data) { products.add(data); }

    String get_category() { return category; }

    String get_product(int index) { return products.get(index); }
}

public class Main {
    public static void main(String[] args) throws IOException {

        ArrayList<ProductList> productList = new ArrayList<>();
        ArrayList<ProductList> shoppingList = new ArrayList<>();

        //Loading products and categories from file to productList
        try {
            File productList_file = new File("product_list.txt");

            Scanner productList_reader = new Scanner(productList_file);

            while(productList_reader.hasNextLine()) {
                String data = productList_reader.nextLine();

                if(data.startsWith(" ")) {
                    productList.get(productList.size()-1).new_product(data);
                }
                else {
                    ProductList addingCategory = new ProductList(data);
                    productList.add(addingCategory);
                }
            }

            System.out.println("Product list loaded\n");

            productList_reader.close();
        } catch(FileNotFoundException f) {
            f.printStackTrace();
        }

        //Printing all loaded products and categories to check if it worked properly for product_list
        System.out.println("Product list:");
        for(ProductList item : productList) {
            System.out.println(item.get_category());
            for(int j = 0; j < item.products.size(); j++) {
                System.out.println(item.get_product(j));
            }
        }

        //Loading products and categories from file to shoppingList
        try {
            File shoppingList_file = new File("shopping_list.txt");

            Scanner shoppingList_reader = new Scanner(shoppingList_file);

            while(shoppingList_reader.hasNextLine()) {
                String data = shoppingList_reader.nextLine();

                if(data.startsWith(" ")) {
                    shoppingList.get(shoppingList.size()-1).new_product(data);
                }
                else {
                    ProductList addingCategory = new ProductList(data);
                    shoppingList.add(addingCategory);
                }
            }

            System.out.println("\nShopping list loaded\n");

            shoppingList_reader.close();
        } catch(FileNotFoundException f) {
            f.printStackTrace();
        }

        //Printing all loaded products and categories to check if it worked properly for shopping_list
        System.out.println("Shopping list:");
        for(ProductList value : shoppingList) {
            System.out.println(value.get_category());
            for(int j = 0; j < value.products.size(); j++) {
                System.out.println(value.get_product(j));
            }
        }

        Scanner scan = new Scanner(System.in);

        while(true) {
            System.out.println("\nWhat do you want to do?");
            System.out.println("1 - Add another product");
            System.out.println("2 - Show all products on shopping list");
            System.out.println("3 - Show products on shopping list from category of your choice");
            System.out.println("4 - Delete all products on shopping list");
            System.out.println("5 - Delete products on shopping list from category of your choice");
            System.out.println("6 - Delete product of your choice");
            System.out.println("7 - Save your shopping list");
            System.out.println("8 - End program");
            System.out.print("Enter value: ");

            int choice = scan.nextInt();
            System.out.print("\n");

            if(choice == 8)
                break;

            switch(choice) {
                case 1:
                    for(int i=0; i<productList.size(); i++) {
                        System.out.println(i + " - " + productList.get(i).get_category());
                    }
                    System.out.println("\nChoose category: ");

                    int category = scan.nextInt();

                    for(int i=0; i<productList.get(category).products.size(); i++) {
                        System.out.println(i + " - " + productList.get(category).get_product(i));
                    }
                    System.out.println("\nChoose product: ");

                    int product = scan.nextInt();
                    boolean found = false;

                    for(ProductList list : shoppingList) {
                        if(Objects.equals(list.get_category(), productList.get(category).get_category())) {
                            list.new_product(productList.get(category).get_product(product));
                            found = true;
                            break;
                        }
                    }

                    if(!found) {
                        ProductList addingCategory = new ProductList(productList.get(category).get_category());
                        addingCategory.new_product(productList.get(category).get_product(product));
                        shoppingList.add(addingCategory);
                    }

                    break;
                case 2:
                    System.out.println("Shopping list:");
                    for(ProductList list : shoppingList) {
                        System.out.println(list.get_category());
                        for(int j = 0; j < list.products.size(); j++) {
                            System.out.println(list.get_product(j));
                        }
                    }

                    break;
                case 3:
                    for(int i=0; i<shoppingList.size(); i++) {
                        System.out.println(i + " - " + shoppingList.get(i).get_category());
                    }
                    System.out.println("\nChoose category: ");

                    category = scan.nextInt();

                    System.out.println("All products from category of your choice:\n" + shoppingList.get(category).get_category());
                    for(int j=0; j<shoppingList.get(category).products.size(); j++) {
                        System.out.println(shoppingList.get(category).get_product(j));
                    }

                    break;
                case 4:
                    for(ProductList list : shoppingList) {
                        list.products.clear();
                    }

                    shoppingList.clear();

                    System.out.println("All products has been deleted from shopping list!");

                    break;
                case 5:
                    for(int i=0; i<shoppingList.size(); i++) {
                        System.out.println(i + " - " + shoppingList.get(i).get_category());
                    }
                    System.out.println("\nChoose category: ");

                    category = scan.nextInt();

                    shoppingList.get(category).products.clear();
                    shoppingList.remove(category);

                    System.out.println("Products from category of your choice has been removed!");

                    break;
                case 6:
                    for(int i=0; i<shoppingList.size(); i++) {
                        System.out.println(i + " - " + shoppingList.get(i).get_category());
                    }
                    System.out.println("\nChoose category: ");

                    category = scan.nextInt();

                    for(int i=0; i<shoppingList.get(category).products.size(); i++) {
                        System.out.println(i + " - " + shoppingList.get(category).get_product(i));
                    }
                    System.out.println("\nChoose product: ");

                    product = scan.nextInt();

                    shoppingList.get(category).products.remove(product);

                    if(shoppingList.get(category).products.isEmpty()) {
                        shoppingList.remove(category);
                    }

                    System.out.println("Product has been removed from shopping list!");

                    break;
                case 7:
                    try {
                        FileWriter saveFile = new FileWriter("shopping_list.txt");

                        for(ProductList list : shoppingList) {
                            saveFile.write(list.get_category() + "\n");
                            for(int j = 0; j < list.products.size(); j++) {
                                saveFile.write(list.get_product(j) + "\n");
                            }
                        }

                        saveFile.close();
                    }
                    catch(Exception f) {
                        f.getStackTrace();
                    }

                    System.out.println("Shopping list has been saved!");

                    break;
            }

            System.out.println("\n-----------------------");
        }

        scan.close();
    }
}