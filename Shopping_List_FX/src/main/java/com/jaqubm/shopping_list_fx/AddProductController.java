package com.jaqubm.shopping_list_fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.jaqubm.shopping_list_fx.ShoppingListApplication.availableProducts;
import static com.jaqubm.shopping_list_fx.ShoppingListApplication.currentProducts;

public class AddProductController implements Initializable {

    @FXML
    private ChoiceBox<String> apcCategoryChoiceBox;

    @FXML
    private ChoiceBox<String> apcProductChoiceBox;

    @FXML
    private TextField apcProductNumber;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(CategoryList category : availableProducts) {
            apcCategoryChoiceBox.getItems().add(category.getCategory());
        }
        apcCategoryChoiceBox.setOnAction(this::initializeProducts);
    }

    @FXML
    public void initializeProducts(ActionEvent event) {
        DeleteProductController.selectionClear(apcProductChoiceBox, availableProducts, apcCategoryChoiceBox);
    }

    @FXML
    public void addProduct() {
        if(!Objects.equals(apcCategoryChoiceBox.getValue(), "") && !Objects.equals(apcProductChoiceBox.getValue(), "") && !Objects.equals(apcProductNumber.getText(), "")) {
            boolean categoryExist = false;
            boolean productExist = false;
            for(CategoryList category : currentProducts) {
                if(Objects.equals(category.getCategory(), apcCategoryChoiceBox.getValue())) {
                    categoryExist = true;
                    for(ProductList product : category.getProducts()) {
                        if(Objects.equals(product.getProductName(), apcProductChoiceBox.getValue())) {
                            productExist = true;
                            product.increaseProductNumber(Float.parseFloat(apcProductNumber.getText()));
                            break;
                        }
                    }
                    if(!productExist) {
                        for(CategoryList category_2 : availableProducts) {
                            if(Objects.equals(category_2.getCategory(), apcCategoryChoiceBox.getValue())) {
                                for(ProductList product : category_2.getProducts()) {
                                    if(Objects.equals(product.getProductName(), apcProductChoiceBox.getValue())) {
                                        category.addProduct(product.getProductName(), product.getProductMeasurements(), Float.parseFloat(apcProductNumber.getText()));
                                    }
                                }
                            }
                        }
                    }
                }
                if(categoryExist)
                    break;
            }

            if(!categoryExist) {
                currentProducts.add(new CategoryList(apcCategoryChoiceBox.getValue()));
                for(CategoryList category : availableProducts) {
                    if(Objects.equals(category.getCategory(), apcCategoryChoiceBox.getValue())) {
                        for(ProductList product : category.getProducts()) {
                            if(Objects.equals(product.getProductName(), apcProductChoiceBox.getValue())) {
                                currentProducts.get(currentProducts.size()-1).addProduct(product.getProductName(), product.getProductMeasurements(), Float.parseFloat(apcProductNumber.getText()));
                            }
                        }
                    }
                }
            }
        }
    }

    @FXML
    public void switchToShoppingListView(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("shopping-list-view.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
