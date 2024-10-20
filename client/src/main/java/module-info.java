module client.repository.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.rmi;


    opens javafx.client to javafx.fxml;
    exports javafx.client;
}