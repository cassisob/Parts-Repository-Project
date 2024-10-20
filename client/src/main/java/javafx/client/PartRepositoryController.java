package javafx.client;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import server.Part;
import server.PartRepository;
import server.SubPart;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.List;

public class PartRepositoryController {

    @FXML
    private ListView<String> partListView;

    @FXML
    private TextArea partDetails;

    private PartRepository repo;
    private Part currentPart;

    @FXML
    public void initialize() {
        try {
            repo = (PartRepository) Naming.lookup("rmi://localhost/PartRepository");
            loadParts();
        } catch (Exception e) {
            showError("Error connecting to PartRepository: " + e.getMessage());
        }
    }

    private void loadParts() {
        try {
            List<Part> parts = repo.listParts();
            partListView.getItems().clear();
            for (Part part : parts) {
                partListView.getItems().add(part.getCode() + " - " + part.getName());
            }
        } catch (RemoteException e) {
            showError("Error loading parts: " + e.getMessage());
        }
    }

    @FXML
    private void onShowPartDetails() {
        String selectedItem = partListView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            try {
                int code = Integer.parseInt(selectedItem.split(" - ")[0]);
                currentPart = repo.getPart(code);
                if (currentPart != null) {
                    StringBuilder details = new StringBuilder();
                    details.append("Code: ").append(currentPart.getCode()).append("\n");
                    details.append("Name: ").append(currentPart.getName()).append("\n");
                    details.append("Description: ").append(currentPart.getDescription()).append("\n");
                    details.append("Is Primitive: ").append(currentPart.isPrimitive()).append("\n");

                    if (!currentPart.isPrimitive()) {
                        List<SubPart> subParts = currentPart.getSubParts();
                        details.append("Subparts:\n");
                        for (SubPart subPart : subParts) {
                            details.append("  - ").append(subPart.getPart().getName())
                                    .append(" (Quantity: ").append(subPart.getQuantity()).append(")\n");
                        }
                    }

                    partDetails.setText(details.toString());
                } else {
                    showError("Part not found");
                }
            } catch (Exception e) {
                showError("Error showing part details: " + e.getMessage());
            }
        }
    }

    @FXML
    private void onAddNewPart() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/javafx/client/AddPart.fxml"));

            Parent root = loader.load();

            AddPartController controller = loader.getController();
            controller.setRepo(repo);

            Stage stage = new Stage();

            stage.setTitle("Add New Part");
            stage.initModality(Modality.APPLICATION_MODAL);

            Scene scene = new Scene(root);

            scene.getStylesheets().add(getClass().getResource("/javafx/client/style.css").toExternalForm());
            stage.setScene(scene);
            stage.showAndWait();

            loadParts();
        } catch (IOException e) {
            showError("Error opening Add New Part window: " + e.getMessage());
        }
    }


    @FXML
    private void onAddSubPart() throws RemoteException {
        if (currentPart == null || currentPart.isPrimitive()) {
            showError("Please select a non-primitive part to add subparts.");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/javafx/client/AddSubPart.fxml"));
            Parent root = loader.load();

            AddSubPartController controller = loader.getController();
            controller.setPart(currentPart);
            controller.setRepo(repo);

            Stage stage = new Stage();

            stage.setTitle("Add Subpart");
            stage.initModality(Modality.APPLICATION_MODAL);

            Scene scene = new Scene(root);

            scene.getStylesheets().add(getClass().getResource("/javafx/client/style.css").toExternalForm());
            stage.setScene(scene);
            stage.showAndWait();

            onShowPartDetails();
        } catch (IOException e) {
            showError("Error opening Add Subpart window: " + e.getMessage());
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
