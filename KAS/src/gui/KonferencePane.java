package gui;

import application.model.Hotel;
import application.model.Konference;
import application.model.Udflugt;
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
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class KonferencePane extends GridPane {
    private final Controller controller = new Controller();

    public KonferencePane() {
        this.initContent();
    }

    // -------------------------------------------------------------------------

    private final TextField txfNavn = new TextField(), txfAdresse = new TextField(), txfStartDate = new TextField(),
            txfSlutDate = new TextField(), txfDagsPris = new TextField(), txfUdflugtNavn = new TextField(),
            txfUdflugtAdresse = new TextField(), txfUdflugtPris = new TextField(), txfFrokost = new TextField(),
            txfDato = new TextField(), txfBeskrivelse = new TextField();
    private final ListView<Konference> lvwKonferencer = new ListView<>();
    private final ListView<Udflugt> lvwUdflugter = new ListView<>();
    private final ListView<Hotel> lvwHoteller = new ListView<>();
    private final Button btnAddUdflugt = new Button("Opret Udflugt");

    private void initContent() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        Label lblKonf = new Label("Konferencer");
        this.add(lblKonf, 0, 0);
        lblKonf.setFont(new Font("Serif", 30));

        this.add(lvwKonferencer, 0, 8, 2, 1);
        lvwKonferencer.setPrefWidth(300);
        lvwKonferencer.setPrefHeight(300);

        this.add(lvwHoteller, 4, 8, 2, 1);
        lvwHoteller.setPrefHeight(300);
        lvwHoteller.setPrefWidth(300);

        Label lblHoteller = new Label("Hoteller");
        this.add(lblHoteller, 4, 0);
        lblHoteller.setFont(new Font("Serif", 30));

        ChangeListener<Konference> listener = (ov, o, n) -> controller.selectedKonferenceChanged();
        lvwKonferencer.getSelectionModel().selectedItemProperty().addListener(listener);

        ChangeListener<Udflugt> listener1 = (ov, o, n) -> controller.selectedUdflugtChanged();
        lvwUdflugter.getSelectionModel().selectedItemProperty().addListener(listener1);

        Label lblNavn = new Label("Navn:");
        this.add(lblNavn, 0, 1);

        this.add(txfNavn, 1, 1);
        txfNavn.setEditable(false);

        Label lblAdresse = new Label("Adresse:");
        this.add(lblAdresse, 0, 2);

        this.add(txfAdresse, 1, 2);
        txfAdresse.setEditable(false);

        Label lblStartDate = new Label("Start dato:");
        this.add(lblStartDate, 0, 3);

        this.add(txfStartDate, 1, 3);
        txfStartDate.setEditable(false);

        Label lblSlutDate = new Label("Slut dato:");
        this.add(lblSlutDate, 0, 4);

        this.add(txfSlutDate, 1, 4);
        txfSlutDate.setEditable(false);

        Label lblDagsPris = new Label("Dagspris:");
        this.add(lblDagsPris, 0, 5);

        this.add(txfDagsPris, 1, 5);

        Label lblUdflugtNavn = new Label("Navn:");
        this.add(lblUdflugtNavn, 2, 1);

        this.add(txfUdflugtNavn, 3, 1);

        Label lblUdflugtAdresse = new Label("Adresse:");
        this.add(lblUdflugtAdresse, 2, 2);

        this.add(txfUdflugtAdresse, 3, 2);

        Label lblUdflugtPris = new Label("Pris:");
        this.add(lblUdflugtPris, 2, 3);

        this.add(txfUdflugtPris, 3, 3);

        Label lblFrokost = new Label("Frokost:");
        this.add(lblFrokost, 2, 4);

        this.add(txfFrokost, 3, 4);

        Label lblDato = new Label("Dato:");
        this.add(lblDato, 2, 5);

        this.add(txfDato, 3, 5);
        txfSlutDate.setEditable(false);

        Label lblBeskrivelse = new Label("Beskrivelse:");
        this.add(lblBeskrivelse, 2, 6);

        this.add(txfBeskrivelse, 3, 6);

        HBox hbxButtons = new HBox(40);
        this.add(hbxButtons, 0, 9, 1, 1);
        hbxButtons.setPadding(new Insets(10, 0, 0, 0));
        hbxButtons.setAlignment(Pos.BASELINE_CENTER);

        Button btnAdd = new Button("Tilføj Konference");
        hbxButtons.getChildren().add(btnAdd);
        btnAdd.setOnAction(event -> controller.createAction());

        Button btnRediger = new Button("Rediger");
        hbxButtons.getChildren().add(btnRediger);
        btnRediger.setOnAction(event -> controller.redigerAction());

        Label lblUdflugt = new Label("Udflugter");
        this.add(lblUdflugt, 2, 0);
        lblUdflugt.setFont(new Font("Serif", 30));

        this.add(lvwUdflugter, 2, 8, 2, 1);
        lvwUdflugter.setPrefWidth(300);
        lvwUdflugter.setPrefHeight(300);

        this.add(btnAddUdflugt, 2, 9);
        btnAddUdflugt.setOnAction(event -> controller.createUdflugtAction());
        btnAddUdflugt.setDisable(true);

        controller.fillLvwKonferencer();
        controller.fillLvwUdflugter();
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
        private KonferenceDialog createDialog;
        private UdflugtDialog createUdflugtDialog;
        private Konference konference;
        private Udflugt udflugt;

        public void fillLvwKonferencer() {
            lvwKonferencer.getItems().setAll(Service.getAllkonferencer());
            if (lvwKonferencer.getItems().size() > 0)
                lvwKonferencer.getSelectionModel().select(0);
        }

        public void fillLvwUdflugter() {
            lvwUdflugter.getItems().setAll(Service.getAllUdflugter());
            if (lvwUdflugter.getItems().size() > 0)
                lvwUdflugter.getSelectionModel().select(0);
        }

        public void updateControls() {
            this.konference = lvwKonferencer.getSelectionModel().getSelectedItem();
            if (konference != null) {
                txfNavn.setText(konference.getNavn());
                txfAdresse.setText("" + konference.getAdresse());
                txfStartDate.setText("" + konference.getStart());
                txfSlutDate.setText("" + konference.getSlut());
                lvwUdflugter.getItems().setAll(konference.getUdflugter());
                txfDagsPris.setText("" + konference.getDagsPris());
                btnAddUdflugt.setDisable(false);
            } else {
                txfNavn.clear();
                txfAdresse.clear();
                txfStartDate.clear();
                txfSlutDate.clear();
                lvwUdflugter.getItems().clear();
                btnAddUdflugt.setDisable(true);
            }
            this.udflugt = lvwUdflugter.getSelectionModel().getSelectedItem();
            if (udflugt != null) {
                txfUdflugtNavn.setText(udflugt.getNavn());
                txfUdflugtAdresse.setText("" + udflugt.getSted());
                txfDato.setText("" + udflugt.getDato());
                txfUdflugtPris.setText("" + udflugt.getPris());
                txfFrokost.setText("" + udflugt.getFrokost());
                txfBeskrivelse.setText("" + udflugt.getBeskrivelse());
//                btnAddUdflugt.setDisable(false);
            } else {
                txfUdflugtNavn.clear();
                txfUdflugtAdresse.clear();
                txfDato.clear();
//                btnAddUdflugt.setDisable(true);
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

        public void createUdflugtAction() {
            if (createUdflugtDialog == null) {
                createUdflugtDialog = new UdflugtDialog("Create Udflugt", null, konference);
                Stage stage = (Stage) lvwUdflugter.getScene().getWindow();
                createUdflugtDialog.initOwner(stage);
            }

            createUdflugtDialog.showAndWait();
            // ... wait for the dialog to close

            boolean isCreated = createUdflugtDialog.getResult();
            if (isCreated) {
                lvwUdflugter.getItems().setAll(konference.getUdflugter());
                int index = lvwUdflugter.getItems().size() - 1;
                lvwUdflugter.getSelectionModel().select(index);
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

        public void selectedUdflugtChanged() {
            this.updateControls();
        }
    }

}
