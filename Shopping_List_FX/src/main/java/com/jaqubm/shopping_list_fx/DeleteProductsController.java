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
import java.util.Objects;
import java.util.ResourceBundle;

import static com.jaqubm.shopping_list_fx.ShoppingListApplication.currentProducts;

public class DeleteProductsController implements Initializable {

    @FXML
    private ChoiceBox<String> dpCategoryChoiceBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(CategoryList category : currentProducts) {
            dpCategoryChoiceBox.getItems().add(category.getCategory());
        }
    }

    @FXML
    public void deleteCategory() {
        for(int i=0; i<currentProducts.size(); i++) {
            if(Objects.equals(currentProducts.get(i).getCategory(), dpCategoryChoiceBox.getValue())) {
                currentProducts.remove(i);
                break;
            }
        }

        dpCategoryChoiceBox.getSelectionModel().clearSelection();
        dpCategoryChoiceBox.getItems().clear();
        initialize(null, null);
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
