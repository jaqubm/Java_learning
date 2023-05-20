package com.jaqubm.shopping_list_fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class ShoppingListApplication extends Application {

    public static ArrayList<CategoryList> availableProducts;
    public static ArrayList<CategoryList> currentProducts;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ShoppingListApplication.class.getResource("shopping-list-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 640);
        stage.setTitle("Shopping_List_FX");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws FileNotFoundException {
        availableProducts = new ArrayList<>();
        currentProducts = new ArrayList<>();

        loadingData(availableProducts, "product_list.txt");
        loadingData(currentProducts, "shopping_list.txt");

        launch();
    }

    /**
     * Loading available products and categories from file to categoryLists
     * @param categoryLists ArrayList of CategoryList classes containing products
     * @param fileName Name of the file data is loaded from
     */
    private static void loadingData(ArrayList<CategoryList> categoryLists, String fileName) throws FileNotFoundException {
        File productList_file = new File(fileName);
        Scanner productList_reader = new Scanner(productList_file);

        while(productList_reader.hasNextLine()) {
            String category = productList_reader.nextLine();
            String productName = productList_reader.nextLine();
            String productMeasurements = productList_reader.nextLine();
            float productNumber = Float.parseFloat(productList_reader.nextLine());

            boolean categoryExists = false;

            for(CategoryList item : categoryLists) {
                if(Objects.equals(item.getCategory(), category)) {
                    categoryExists = true;
                    item.addProduct(productName, productMeasurements, productNumber);
                    break;
                }
            }

            if(!categoryExists) {
                categoryLists.add(new CategoryList(category));
                categoryLists.get(categoryLists.size()-1).addProduct(productName, productMeasurements, productNumber);
            }
        }
        productList_reader.close();
    }
}