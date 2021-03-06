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

//gruppe: Anders R.P., Casper L. og Frederik Ø.
public class KonferencePane extends GridPane {
    private final Controller controller = new Controller();

    public KonferencePane() {
        this.initContent();
    }

    // -------------------------------------------------------------------------

    private final TextField txfNavn = new TextField(), txfAdresse = new TextField(), txfStartDate = new TextField(),
            txfSlutDate = new TextField(), txfDagsPris = new TextField(), txfUdflugtNavn = new TextField(),
            txfUdflugtAdresse = new TextField(), txfUdflugtPris = new TextField(), txfFrokost = new TextField(),
            txfDato = new TextField(), txfBeskrivelse = new TextField(), txfHotelNavn = new TextField(),
            txfHotelAdresse = new TextField(), txfHotelPrisPerNat1 = new TextField(),
            txfHotelPrisPerNat2 = new TextField(), txfHotelTillæg = new TextField();
    private final ListView<Konference> lvwKonferencer = new ListView<>();
    private final ListView<Udflugt> lvwUdflugter = new ListView<>();
    private final ListView<Hotel> lvwHoteller = new ListView<>();
    private final Button btnAddUdflugt = new Button("Tilføj Udflugt");
    private final Button btnPrintDeltagere = new Button("Print deltageroversigt");
    private final Button btnPrintUdflugter = new Button("Print udflugtoversigt");
    private final Button btnPrintHoteller = new Button("Print hoteloversigt");
    private final Button btnAddHotel = new Button("Tilføj hotel");

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

        ChangeListener<Hotel> listener2 = (ov, o, n) -> controller.selectedHotelChanged();
        lvwHoteller.getSelectionModel().selectedItemProperty().addListener(listener2);

        // KONFERENCER - KONFERENCER - KONFERENCER - KONFERENCER - KONFERENCER -
        // KONFERENCER - KONFERENCER - KONFERENCER //

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

        // UDFLUGTER - UDFLUGTER - UDFLUGTER - UDFLUGTER - UDFLUGTER - UDFLUGTER -
        // UDFLUGTER - UDFLUGTER - UDFLUGTER - UDFLUGTER - //

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

        // HOTELLER - HOTELLER - HOTELLER - HOTELLER - HOTELLER - HOTELLER - HOTELLER -
        // HOTELLER - HOTELLER - HOTELLER //

        Label lblHotelNavn = new Label("Navn:");
        this.add(lblHotelNavn, 4, 1);

        this.add(txfHotelNavn, 5, 1);
        txfHotelNavn.setEditable(false);

        Label lblHotelAdresse = new Label("Adresse:");
        this.add(lblHotelAdresse, 4, 2);

        this.add(txfHotelAdresse, 5, 2);
        txfHotelAdresse.setEditable(false);

        Label lblHotelPrisPerNat1 = new Label("Pris per nat (én person):");
        this.add(lblHotelPrisPerNat1, 4, 3);

        this.add(txfHotelPrisPerNat1, 5, 3);
        txfHotelPrisPerNat1.setEditable(false);

        Label lblHotelPrisPerNat2 = new Label("Pris per nat (to personer):");
        this.add(lblHotelPrisPerNat2, 4, 4);

        this.add(txfHotelPrisPerNat2, 5, 4);
        txfHotelPrisPerNat2.setEditable(false);

        Label lblHotelTillæg = new Label("Tillæg:");
        this.add(lblHotelTillæg, 4, 5);

        this.add(txfHotelTillæg, 5, 5);
        txfHotelTillæg.setEditable(false);

        HBox hbxButtons = new HBox(40);
        this.add(hbxButtons, 0, 9, 1, 1);
        hbxButtons.setAlignment(Pos.BASELINE_CENTER);

        Button btnAdd = new Button("Tilføj Konference");
        hbxButtons.getChildren().add(btnAdd);
        btnAdd.setOnAction(event -> controller.createAction());

        Button btnRediger = new Button("Rediger");
        hbxButtons.getChildren().add(btnPrintDeltagere);
        btnPrintDeltagere.setOnAction(event -> controller.printDeltagere());

        Label lblUdflugt = new Label("Udflugter");
        this.add(lblUdflugt, 2, 0);
        lblUdflugt.setFont(new Font("Serif", 30));

        this.add(lvwUdflugter, 2, 8, 2, 1);
        lvwUdflugter.setPrefWidth(300);
        lvwUdflugter.setPrefHeight(300);

        HBox hbox2 = new HBox(40);
        this.add(hbox2, 4, 9);
        hbox2.getChildren().add(btnAddHotel);
        hbox2.getChildren().add(btnPrintHoteller);
        btnPrintHoteller.setOnAction(event -> controller.printHoteller());
        btnAddHotel.setOnAction(event -> controller.createHotelAction());

        HBox hbox1 = new HBox(40);
        this.add(hbox1, 2, 9);
        hbox1.getChildren().add(btnAddUdflugt);
        hbox1.getChildren().add(btnPrintUdflugter);

        btnAddUdflugt.setOnAction(event -> controller.createUdflugtAction());
        btnPrintUdflugter.setOnAction(event -> controller.printUdflugter());
        btnAddUdflugt.setDisable(true);

        controller.fillLvwKonferencer();
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
        private Hotel hotel;
        private PrintDialog printDialog;
        private HotelDialog createHotelDialog;

        public void fillLvwKonferencer() {
            lvwKonferencer.getItems().setAll(Service.getAllkonferencer());
            if (lvwKonferencer.getItems().size() > 0)
                lvwKonferencer.getSelectionModel().select(0);
        }

        public void updateControls() {
            this.konference = lvwKonferencer.getSelectionModel().getSelectedItem();
            if (konference != null) {
                txfNavn.setText(konference.getNavn());
                txfAdresse.setText("" + konference.getAdresse());
                txfStartDate.setText("" + konference.getStart());
                txfSlutDate.setText("" + konference.getSlut());
                lvwUdflugter.getItems().setAll(konference.getUdflugter());
                lvwHoteller.getItems().setAll(konference.getHoteller());
                txfDagsPris.setText("" + konference.getDagsPris());
                btnAddUdflugt.setDisable(false);
                if (lvwHoteller.getItems().size() > 0)
                    lvwHoteller.getSelectionModel().select(0);
                if (lvwUdflugter.getItems().size() > 0)
                    lvwUdflugter.getSelectionModel().select(0);

            } else {
                txfNavn.clear();
                txfAdresse.clear();
                txfStartDate.clear();
                txfSlutDate.clear();
                lvwUdflugter.getItems().clear();
                btnAddUdflugt.setDisable(true);
            }

        }

        // --------------------------------------------------------------------

        // Create button action
        public void createAction() {
            if (createDialog == null) {
                createDialog = new KonferenceDialog("Opret Konference", null);
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
                createUdflugtDialog = new UdflugtDialog("Opret Udflugt", null, konference);
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
        public void printDeltagere() {
            printDialog = new PrintDialog(Service.deltagerOversigt(konference));
            Stage stage = (Stage) lvwKonferencer.getScene().getWindow();
            printDialog.initOwner(stage);
            printDialog.showAndWait();
        }

        public void printUdflugter() {
            printDialog = new PrintDialog(Service.udflugtOversigt(konference));
            Stage stage = (Stage) lvwUdflugter.getScene().getWindow();
            printDialog.initOwner(stage);

            printDialog.showAndWait();
        }

        public void printHoteller() {
            printDialog = new PrintDialog(Service.hotelOversigt(konference));
            Stage stage = (Stage) lvwHoteller.getScene().getWindow();
            printDialog.initOwner(stage);

            printDialog.showAndWait();
        }

        // Selected item in lvwCompanies changed
        public void selectedKonferenceChanged() {
            this.updateControls();
        }

        public void selectedUdflugtChanged() {

            this.udflugt = lvwUdflugter.getSelectionModel().getSelectedItem();
            if (udflugt != null) {
                txfUdflugtNavn.setText(udflugt.getNavn());
                txfUdflugtAdresse.setText("" + udflugt.getSted());
                txfDato.setText("" + udflugt.getDato());
                txfUdflugtPris.setText("" + udflugt.getPris());
                txfFrokost.setText("" + udflugt.getFrokost());
                txfBeskrivelse.setText("" + udflugt.getBeskrivelse());
                // btnAddUdflugt.setDisable(false);
            } else {
                txfUdflugtNavn.clear();
                txfUdflugtAdresse.clear();
                txfDato.clear();
                // btnAddUdflugt.setDisable(true);
            }
        }

        public void selectedHotelChanged() {

            this.hotel = lvwHoteller.getSelectionModel().getSelectedItem();
            if (hotel != null) {
                txfHotelNavn.setText(hotel.getNavn());
                txfHotelAdresse.setText("" + hotel.getAdresse());
                txfHotelPrisPerNat1.setText("" + hotel.getPrisPrNat1());
                txfHotelPrisPerNat2.setText("" + hotel.getPrisPrNat2());
                txfHotelTillæg.setText("" + hotel.getHotelTillæg());
            } else {
                txfHotelNavn.clear();
                txfHotelAdresse.clear();
                txfHotelPrisPerNat1.clear();
                txfHotelPrisPerNat2.clear();
                txfHotelTillæg.clear();
            }
        }

        public void createHotelAction() {
            createHotelDialog = new HotelDialog("Opret hotel", null, konference);
            Stage stage = (Stage) lvwHoteller.getScene().getWindow();
            createHotelDialog.initOwner(stage);

            createHotelDialog.showAndWait();
            // ... wait for the dialog to close

            boolean isCreated = createHotelDialog.getResult();
            if (isCreated) {
                lvwHoteller.getItems().setAll(konference.getHoteller());
                int index = lvwHoteller.getItems().size() - 1;
                lvwHoteller.getSelectionModel().select(index);
            }
        }

    }

}
