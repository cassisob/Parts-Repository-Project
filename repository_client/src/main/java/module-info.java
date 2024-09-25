module client.repository_client {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens client.repository_client to javafx.fxml;
    exports client.repository_client;
}