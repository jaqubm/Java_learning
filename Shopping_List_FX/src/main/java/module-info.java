module com.jaqubm.shopping_list_fx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.jaqubm.shopping_list_fx to javafx.fxml;
    exports com.jaqubm.shopping_list_fx;
}