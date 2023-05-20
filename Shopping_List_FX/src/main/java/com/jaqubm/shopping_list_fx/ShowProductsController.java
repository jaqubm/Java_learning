package com.jaqubm.shopping_list_fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.jaqubm.shopping_list_fx.ShoppingListApplication.currentProducts;

public class ShowProductsController implements Initializable {

    @FXML
    private ChoiceBox<String> spChoiceBox;

    @FXML
    private ListView<String> spListView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(CategoryList category : currentProducts) {
            spChoiceBox.getItems().add(category.getCategory());
        }
        spChoiceBox.setOnAction(this::initializeProducts);
    }

    @FXML
    public void initializeProducts(ActionEvent event) {
        spListView.getItems().clear();

        for(CategoryList category : currentProducts) {
            if(Objects.equals(category.getCategory(), spChoiceBox.getValue())) {
                for(ProductList product : category.getProducts()) {
                    spListView.getItems().add(product.getProductName() + ": - " + product.getProductNumber() + " " + product.getProductMeasurements());
                }
                break;
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
