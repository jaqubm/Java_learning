package com.jaqubm.shopping_list_fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.jaqubm.shopping_list_fx.ShoppingListApplication.currentProducts;

public class DeleteProductController implements Initializable {

    @FXML
    private ChoiceBox<String> dpCategoryChoiceBox;

    @FXML
    private ChoiceBox<String> dpProductChoiceBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(CategoryList category : currentProducts) {
            dpCategoryChoiceBox.getItems().add(category.getCategory());
        }
        dpCategoryChoiceBox.setOnAction(this::initializeProducts);
    }

    @FXML
    public void initializeProducts(ActionEvent event) {
        selectionClear(dpProductChoiceBox, currentProducts, dpCategoryChoiceBox);
    }

    static void selectionClear(ChoiceBox<String> ProductChoiceBox, ArrayList<CategoryList> currentProducts, ChoiceBox<String> dpCategoryChoiceBox) {
        ProductChoiceBox.getSelectionModel().clearSelection();
        ProductChoiceBox.getItems().clear();

        for(CategoryList category : currentProducts) {
            if(Objects.equals(category.getCategory(), dpCategoryChoiceBox.getValue())) {
                for(ProductList product : category.getProducts()) {
                    ProductChoiceBox.getItems().add((product.getProductName()));
                }
                break;
            }
        }
    }

    @FXML
    public void deleteProduct() {
        if(!Objects.equals(dpCategoryChoiceBox.getValue(), "") && !Objects.equals(dpProductChoiceBox.getValue(), "")) {
            for(CategoryList category : currentProducts) {
                if(Objects.equals(category.getCategory(), dpCategoryChoiceBox.getValue())) {
                    for(int i=0; i<category.getProducts().size(); i++) {
                        if(Objects.equals(category.getProducts().get(i).getProductName(), dpProductChoiceBox.getValue())) {
                            category.getProducts().remove(i);
                            break;
                        }
                    }
                }
            }

            for(int i=0; i<currentProducts.size(); i++) {
                if(currentProducts.get(i).getProducts().size() == 0) {
                    currentProducts.remove(i);
                    break;
                }
            }

            dpProductChoiceBox.getSelectionModel().clearSelection();
            dpProductChoiceBox.getItems().clear();

            dpCategoryChoiceBox.getSelectionModel().clearSelection();
            dpCategoryChoiceBox.getItems().clear();
            initialize(null, null);
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
