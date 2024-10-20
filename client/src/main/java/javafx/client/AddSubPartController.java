package javafx.client;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import server.Part;
import server.PartRepository;

public class AddSubPartController {

    @FXML
    private TextField partCodeField;

    @FXML
    private TextField quantityField;

    private Part currentPart;
    private PartRepository repo;

    public void setPart(Part currentPart) {
        this.currentPart = currentPart;
    }

    public void setRepo(PartRepository repo) {
        this.repo = repo;
    }

    @FXML
    private void onAddSubPart() {
        try {
            int partCode = Integer.parseInt(partCodeField.getText());
            int quantity = Integer.parseInt(quantityField.getText());

            Part subPart = repo.getPart(partCode);
            if (subPart != null) {
                currentPart.addSubPart(subPart, quantity);
                Stage stage = (Stage) partCodeField.getScene().getWindow();
                stage.close();
            } else {
                System.out.println("Subpart not found");
            }
        } catch (Exception e) {
            System.out.println("Error adding subpart: " + e.getMessage());
        }
    }

    @FXML
    private void onCancel() {
        Stage stage = (Stage) partCodeField.getScene().getWindow();
        stage.close();
    }
}
