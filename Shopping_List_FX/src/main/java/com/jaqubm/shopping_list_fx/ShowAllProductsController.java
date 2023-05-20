package com.jaqubm.shopping_list_fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.jaqubm.shopping_list_fx.ShoppingListApplication.currentProducts;

public class ShowAllProductsController implements Initializable {

    @FXML
    private ListView<String> sapListView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(CategoryList category : currentProducts) {
            for(ProductList product : category.getProducts()) {
                sapListView.getItems().add(product.getProductName() + ": " + product.getProductNumber() + " " + product.getProductMeasurements());
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
