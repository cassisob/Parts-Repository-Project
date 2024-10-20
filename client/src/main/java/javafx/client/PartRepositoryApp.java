package javafx.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class PartRepositoryApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("PartRepositoryView.fxml"));
        Parent root = loader.load();


        primaryStage.setTitle("Part Repository");

        primaryStage.setWidth(800);
        primaryStage.setHeight(700);

        Scene scene = new Scene(root, 600, 400);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
