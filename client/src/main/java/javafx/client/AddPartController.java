package javafx.client;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import server.Part;
import server.PartRepository;

public class AddPartController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField descriptionField;

    @FXML
    private TextField isPrimitiveField;

    private PartRepository repo;

    public void setRepo(PartRepository repo) {
        this.repo = repo;
    }

    @FXML
    private void onAddPart() {
        String name = nameField.getText();
        String description = descriptionField.getText();
        boolean isPrimitive = Boolean.parseBoolean(isPrimitiveField.getText());

        try {
            Part newPart = repo.createPart(name, description, isPrimitive);
            Stage stage = (Stage) nameField.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            System.out.println("Error adding new part: " + e.getMessage());
        }
    }

    @FXML
    private void onCancel() {
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }
}
