package com.jaqubm.shopping_list_fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

import static com.jaqubm.shopping_list_fx.ShoppingListApplication.currentProducts;

public class ShoppingListController {

    @FXML
    public void switchToAddProductView(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("add-product-view.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchToShowAllProductsView(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("show-all-products-view.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchToShowProductsView(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("show-products-view.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void deleteAllProducts() {
        currentProducts.clear();
    }

    @FXML
    public void switchToDeleteProductsView(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("delete-products-view.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchToDeleteProductView(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("delete-product-view.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void saveFile() throws IOException {
        File productList_file = new File("shopping_list.txt");
        BufferedWriter productList_writer = new BufferedWriter(new FileWriter(productList_file));

        for(CategoryList category : currentProducts) {
            for(ProductList product : category.getProducts()) {
                productList_writer.write(category.getCategory());
                productList_writer.newLine();
                productList_writer.write(product.getProductName());
                productList_writer.newLine();
                productList_writer.write(product.getProductMeasurements());
                productList_writer.newLine();
                productList_writer.write(String.valueOf(product.getProductNumber()));
                productList_writer.newLine();
            }
        }

        productList_writer.close();
    }
}