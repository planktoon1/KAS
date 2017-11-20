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

public class KonferencePane extends GridPane {
    private final Controller controller = new Controller();

    public KonferencePane() {
        this.initContent();
    }

    // -------------------------------------------------------------------------

    private final TextField txfNavn = new TextField(), txfAdresse = new TextField(), txfStartDate = new TextField(),
            txfSlutDate = new TextField();
    private final ListView<Konference> lvwKonferencer = new ListView<>();

    private void initContent() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        Label lblKonf = new Label("Konferencer");
        this.add(lblKonf, 0, 0);

        this.add(lvwKonferencer, 0, 1, 1, 5);
        lvwKonferencer.setPrefWidth(200);
        lvwKonferencer.setPrefHeight(200);

        ChangeListener<Konference> listener = (ov, o, n) -> controller.selectedKonferenceChanged();
        lvwKonferencer.getSelectionModel().selectedItemProperty().addListener(listener);

        Label lblNavn = new Label("Navn:");
        this.add(lblNavn, 1, 1);

        this.add(txfNavn, 2, 1);
        txfNavn.setEditable(false);

        Label lblAdresse = new Label("Adresse:");
        this.add(lblAdresse, 1, 2);

        this.add(txfAdresse, 2, 2);
        txfAdresse.setEditable(false);

        Label lblStartDate = new Label("Start dato:");
        this.add(lblStartDate, 1, 3);

        this.add(txfStartDate, 2, 3);
        txfStartDate.setEditable(false);

        Label lblSlutDate = new Label("Slut dato:");
        this.add(lblSlutDate, 1, 4);
//gvu
        this.add(txfSlutDate, 2, 4);
        txfSlutDate.setEditable(false);

        HBox hbxButtons = new HBox(40);
        this.add(hbxButtons, 0, 6, 3, 1);
        hbxButtons.setPadding(new Insets(10, 0, 0, 0));
        hbxButtons.setAlignment(Pos.BASELINE_CENTER);

        Button btnAdd = new Button("Tilføj Konference");
        hbxButtons.getChildren().add(btnAdd);
        btnAdd.setOnAction(event -> controller.createAction());

        Button btnRediger = new Button("Rediger");
        hbxButtons.getChildren().add(btnRediger);
        btnRediger.setOnAction(event -> controller.redigerAction());

        controller.fillLvwCompanies();
    }

    // -------------------------------------------------------------------------

    /**
     * Updates the content of controls in this pane.
     */
    public void updateControls() {
        controller.updateControls();
    }

    // -------------------------------------------------------------------------

    private class Controller {
        private KonferenceDialog createDialog, updateDialog;

        public void fillLvwCompanies() {
            lvwKonferencer.getItems().setAll(Service.getAllkonferencer());
            if (lvwKonferencer.getItems().size() > 0)
                lvwKonferencer.getSelectionModel().select(0);
        }

        public void updateControls() {
            Konference konference = lvwKonferencer.getSelectionModel().getSelectedItem();
            if (konference != null) {
                txfNavn.setText(konference.getNavn());
                txfAdresse.setText("" + konference.getAdresse());
                txfStartDate.setText("" + konference.getStart());
                txfSlutDate.setText("" + konference.getSlut());
            } else {
                txfNavn.clear();
                txfAdresse.clear();
                txfStartDate.clear();
                txfSlutDate.clear();
            }
        }

        // --------------------------------------------------------------------

        // Create button action
        public void createAction() {
            if (createDialog == null) {
                createDialog = new KonferenceDialog("Create Konference", null);
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
        }

        // Update button action
        public void redigerAction() {
            // Company company = lvwKonferencer.getSelectionModel().getSelectedItem();
            // if (company == null)
            // return;
            //
            // if (updateDialog == null) {
            // updateDialog = new CompanyDialog("Update Company", company);
            // Stage stage = (Stage) lvwKonferencer.getScene().getWindow();
            // updateDialog.initOwner(stage);
            // }
            //
            // updateDialog.showAndWait();
            // // ... wait for the dialog to close
            //
            // boolean isUpdated = updateDialog.getResult();
            // if (isUpdated) {
            // int selectIndex = lvwKonferencer.getSelectionModel().getSelectedIndex();
            // lvwKonferencer.getItems().setAll(Service.getAllCompanies());
            // lvwKonferencer.getSelectionModel().select(selectIndex);
            // }
        }

        // Selected item in lvwCompanies changed
        public void selectedKonferenceChanged() {
            this.updateControls();
        }
    }

}
