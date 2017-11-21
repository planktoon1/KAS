package gui;

import application.model.Konference;
import application.service.Service;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class DeltagerPane extends GridPane {
    private final Controller controller = new Controller();

    public DeltagerPane() {
        this.initControls();
    }

    //-------------------------------------------------------------------------

    private final TextField txfName = new TextField(), txfWage = new TextField(),
            txfCompany = new TextField(), txfSalary = new TextField();
    private final ListView<Konference> lvwKonferences = new ListView<>();

    private void initControls() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        Label lblComp = new Label("Konferences");
        this.add(lblComp, 0, 0);

        this.add(lvwKonferences, 0, 1, 1, 5);
        lvwKonferences.setPrefWidth(200);
        lvwKonferences.setPrefHeight(200);

        ChangeListener<Konference> listener = (ov, o, n) -> controller.selectedKonferenceChanged();
        lvwKonferences.getSelectionModel().selectedItemProperty().addListener(listener);

        Label lblName = new Label("Name:");
        this.add(lblName, 1, 1);

        this.add(txfName, 2, 1);
        txfName.setPrefWidth(200);
        txfName.setEditable(false);

        Label lblWage = new Label("Hourly Wage:");
        this.add(lblWage, 1, 2);

        this.add(txfWage, 2, 2);
        txfWage.setEditable(false);

        Label lblCompany = new Label("Company:");
        this.add(lblCompany, 1, 3);

        this.add(txfCompany, 2, 3);
        txfCompany.setEditable(false);

        Label lblSalary = new Label("Weekly Salary:");
        this.add(lblSalary, 1, 4);

        this.add(txfSalary, 2, 4);
        txfSalary.setEditable(false);

        HBox hbxButtons = new HBox(40);
        this.add(hbxButtons, 0, 6, 3, 1);
        hbxButtons.setPadding(new Insets(10, 0, 0, 0));
        hbxButtons.setAlignment(Pos.BASELINE_CENTER);

        Button btnCreate = new Button("Create");
        hbxButtons.getChildren().add(btnCreate);
        btnCreate.setOnAction(event -> controller.createAction());

        Button btnUpdate = new Button("Update");
        hbxButtons.getChildren().add(btnUpdate);
        btnUpdate.setOnAction(event -> controller.updateAction());

        Button btnDelete = new Button("Delete");
        hbxButtons.getChildren().add(btnDelete);
        btnDelete.setOnAction(event -> controller.deleteAction());

        controller.fillLvwKonferences();
    }

    // -------------------------------------------------------------------------

    public void updateControls() {
        controller.updateControls();
    }

    // -------------------------------------------------------------------------

    private class Controller {
        private DeltagerDialog createDialog, updateDialog;

        public void fillLvwKonferences() {
            lvwKonferences.getItems().setAll(Service.getAllkonferencer());
            if (lvwKonferences.getItems().size() > 0)
                lvwKonferences.getSelectionModel().select(0);
        }

        public void updateControls() {
//            Konference Konference = lvwKonferences.getSelectionModel().getSelectedItem();
//            if (Konference != null) {
//                txfName.setText(Konference.getName());
//                txfWage.setText("kr " + Konference.getWage());
//                if (Konference.getCompany() != null) {
//                    txfCompany.setText("" + Konference.getCompany());
//                    txfSalary.setText("kr " + Konference.weeklySalary());
//                } else {
//                    txfCompany.clear();
//                    txfSalary.clear();
//                }
//            } else {
//                txfName.clear();
//                txfWage.clear();
//                txfCompany.clear();
//                txfSalary.clear();
//            }
        }

        // --------------------------------------------------------------------

        // Create button action
        public void createAction() {
            if (createDialog == null) {
                createDialog = new DeltagerDialog("Opret deltager", null);
                Stage stage = (Stage) lvwKonferences.getScene().getWindow();
                createDialog.initOwner(stage);
            }

            createDialog.showAndWait();
            // ... wait for the dialog to close

            boolean isCreated = createDialog.getResult();
            if (isCreated) {
                lvwKonferences.getItems().setAll(Service.getAllkonferencer());
                int index = lvwKonferences.getItems().size() - 1;
                lvwKonferences.getSelectionModel().select(index);
            }
            this.updateControls();
        }

        // Update button action
        public void updateAction() {
//            Konference Konference = lvwKonferences.getSelectionModel().getSelectedItem();
//            if (Konference == null)
//                return;
//
////            if (updateDialog == null) {
////                updateDialog = new KonferenceDialog("Update Konference", Konference);
////                Stage stage = (Stage) lvwKonferences.getScene().getWindow();
////                updateDialog.initOwner(stage);
//            }
//
//            updateDialog.showAndWait();
//            // ... wait for the dialog to close
//
//            boolean isUpdated = updateDialog.getResult();
//            if (isUpdated) {
//                int selectedIndex = lvwKonferences.getSelectionModel().getSelectedIndex();
//                lvwKonferences.getItems().setAll(Service.getAllkonferencer());
//                lvwKonferences.getSelectionModel().select(selectedIndex);
//            }
        }

        // Delete button action
        public void deleteAction() {
//            Konference Konference = lvwKonferences.getSelectionModel().getSelectedItem();
//            if (Konference == null)
//                return;
//
//            Alert deleteAlert = new Alert(AlertType.CONFIRMATION);
//            deleteAlert.setTitle("Confirmation");
//            deleteAlert.setHeaderText("Delete the Konference?");
//            deleteAlert.setContentText("Deletion can't be undone");
//            Stage stage = (Stage) lvwKonferences.getScene().getWindow();
//            deleteAlert.initOwner(stage);
//
//            Optional<ButtonType> result = deleteAlert.showAndWait();
//            // ... wait for the dialog to close
//
//            if (result.isPresent() && result.get() == ButtonType.OK) {
//                Service.deleteKonference(Konference);
//                lvwKonferences.getItems().setAll(Service.getAllkonferencer());
//                this.updateControls();
//            }
        }

        // Selected item in lvwKonferences changed
        public void selectedKonferenceChanged() {
            this.updateControls();
        }
    }
}
