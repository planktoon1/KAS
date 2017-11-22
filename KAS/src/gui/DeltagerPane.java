package gui;

import application.model.Konference;
import application.model.Tilmelding;
import application.service.Service;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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

    private final TextField txfNavn = new TextField(),
            txfLedsager = new TextField(), txfUdflugt = new TextField(), txfStartDate = new TextField(),
            txfSlutDate = new TextField(), txfHotel = new TextField();
    private final ListView<Konference> lvwKonferencer = new ListView<>();
    private final CheckBox cbxFordrag = new CheckBox();
    private final ListView<Tilmelding> lvwTilmelding = new ListView<>();

    private void initControls() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        Label lblComp = new Label("Konferencer");
        this.add(lblComp, 0, 0);

        this.add(lvwKonferencer, 0, 1, 1, 5);
        lvwKonferencer.setPrefWidth(200);
        lvwKonferencer.setPrefHeight(200);

        Label lblTilmelding = new Label("Deltagere");
        this.add(lblTilmelding, 3, 0);

        this.add(lvwTilmelding, 3, 1, 1, 5);
        lvwTilmelding.setPrefHeight(200);
        lvwTilmelding.setPrefWidth(200);

        ChangeListener<Konference> listener = (ov, o, n) -> controller.selectedKonferenceChanged();
        lvwKonferencer.getSelectionModel().selectedItemProperty().addListener(listener);

        Label lblNavn = new Label("Navn:");
        this.add(lblNavn, 1, 1);

        this.add(txfNavn, 2, 1);
        txfNavn.setPrefWidth(200);
        txfNavn.setEditable(false);

        Label lblFordrag = new Label("Fordragsholder:");
        this.add(lblFordrag, 1, 2);

        this.add(cbxFordrag, 2, 2);

        Label lblLedsager = new Label("Ledsager:");
        this.add(lblLedsager, 1, 3);

        this.add(txfLedsager, 2, 3);
        txfLedsager.setEditable(false);

        Label lblUdflugt = new Label("Udflugt(er):");
        this.add(lblUdflugt, 1, 4);

        this.add(txfUdflugt, 2, 4);
        txfUdflugt.setEditable(false);

        Label lblStartDate = new Label("Start dato:");
        this.add(lblStartDate, 1, 5);

        this.add(txfStartDate, 2, 5);

        Label lblSlutDate = new Label("Slut dato:");
        this.add(lblSlutDate, 1, 6);

        this.add(txfSlutDate, 2, 6);

        Label lblHotel = new Label("Hotel:");
        this.add(lblHotel, 1, 7);

        this.add(txfHotel, 2, 7);

        HBox hbxButtons = new HBox(40);
        this.add(hbxButtons, 0, 8, 3, 1);
        hbxButtons.setPadding(new Insets(10, 0, 0, 0));
        hbxButtons.setAlignment(Pos.BASELINE_CENTER);

        Button btnCreate = new Button("Opret");
        hbxButtons.getChildren().add(btnCreate);
        btnCreate.setOnAction(event -> controller.createAction());

//        Button btnUpdate = new Button("Update");
//        hbxButtons.getChildren().add(btnUpdate);
//        btnUpdate.setOnAction(event -> controller.updateAction());

        Button btnDelete = new Button("Rediger");
        hbxButtons.getChildren().add(btnDelete);
        btnDelete.setOnAction(event -> controller.deleteAction());

        controller.fillLvw();

    }

    // -------------------------------------------------------------------------

    public void updateControls() {
        controller.updateControls();
    }

    // -------------------------------------------------------------------------

    private class Controller {
        private DeltagerDialog createDialog, updateDialog;
        private Konference konference;

        public void fillLvw() {

            lvwKonferencer.getItems().setAll(Service.getAllkonferencer());
            if (lvwKonferencer.getItems().size() > 0)
                lvwKonferencer.getSelectionModel().select(0);

            if (konference != null) {
                lvwTilmelding.getItems().setAll(konference.getTilmeldinger());
            }

        }

        public void updateControls() {
            controller.fillLvw();
            konference = lvwKonferencer.getSelectionModel().getSelectedItem();
            Tilmelding tilmelding = lvwTilmelding.getSelectionModel().getSelectedItem();
            konference = lvwKonferencer.getSelectionModel().getSelectedItem();
            if (konference != null && tilmelding != null) {
//                txfNavn.setText(konference.getNavn());
//                txfFordrag.setText("" + konference.getAdresse());
                txfLedsager.setText("" + tilmelding.getLedsager());

//                txfUdflugt.setText("" + konference.getUdflugter());
//                txfStartDate.setText("" + konference.getStart());
//                txfSlutDate.setText("" + konference.getSlut());
//                lvwUdflugter.getItems().setAll(konference.getUdflugter());
            } else {
                txfNavn.clear();
//                txfFordrag.clear();
//                txfStartDate.clear();
//                txfSlutDate.clear();
//                lvwUdflugter.getItems().clear();
            }
        }

        // --------------------------------------------------------------------

        // Create button action
        public void createAction() {
            if (createDialog == null) {
                createDialog = new DeltagerDialog("Opret deltager", null);
                Stage stage = (Stage) lvwKonferencer.getScene().getWindow();
                createDialog.initOwner(stage);
            }

            createDialog.showAndWait();
            // ... wait for the dialog to close

            boolean isCreated = createDialog.getResult();
            if (isCreated) {
                lvwKonferencer.getItems().setAll(Service.getAllkonferencer());
                int index = lvwKonferencer.getItems().size() - 1;
                lvwKonferencer.getSelectionModel().select(index);
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
