package gui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

import application.model.Deltager;
import application.model.Hotel;
import application.model.HotelTillæg;
import application.model.Konference;
import application.model.Udflugt;
import application.service.Service;
import javafx.beans.value.ChangeListener;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DeltagerDialog extends Stage {
    private final Controller controller = new Controller();

    /** Note: Deltager is nullable. */
    public DeltagerDialog(String title, Konference konference) {
        controller.konference = konference;

        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        this.setTitle(title);
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    // -------------------------------------------------------------------------

    private final TextField txfNavn = new TextField(), txfLedsager = new TextField(), txfAdresse = new TextField(),
            txfTlf = new TextField(), txfFirmaNavn = new TextField(), txfFirmaTlf = new TextField();
    // private final ComboBox<Konference> cbbKonferencer = new ComboBox<>();
    private final ComboBox<Hotel> cbbHoteller = new ComboBox<>();
    private final Label lblError = new Label();
    private final CheckBox cbxFordrag = new CheckBox(), cbxFirma = new CheckBox(),
            cbxHotel = new CheckBox("Hotel:");
    private final DatePicker startDatePicker = new DatePicker(), slutDatePicker = new DatePicker();
    private final CheckBox[] cbxUdflugter = new CheckBox[Service.getAllUdflugter().size()];
    private final VBox vbxHotelTillæg = new VBox(5);
    private final ArrayList<CheckBox> cbxHotelTillæg = new ArrayList<CheckBox>(Collections.nCopies(20, new CheckBox()));

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(20, 20, 0, 20));
        pane.setHgap(10);
        pane.setVgap(5);
        pane.setGridLinesVisible(false);
        pane.setPrefHeight(600);
        pane.setPrefWidth(400);

        Label lblNavn = new Label("Navn");
        pane.add(lblNavn, 0, 0);

        pane.add(txfNavn, 0, 1);
        txfNavn.setPrefWidth(200);

        Label lblAdresse = new Label("Adresse");
        pane.add(lblAdresse, 0, 2);

        pane.add(txfAdresse, 0, 3);

        Label lblTlf = new Label("Tlf. nr");
        pane.add(lblTlf, 0, 4);

        pane.add(txfTlf, 0, 5);

        pane.add(cbxFordrag, 1, 1);
        cbxFordrag.setText("Fordragsholder");

        Label lblLedsager = new Label("Ledsager");
        pane.add(lblLedsager, 0, 7);
        GridPane.setMargin(lblLedsager, new Insets(10, 0, 0, 0));

        pane.add(txfLedsager, 0, 8);

        Label lblUdflugt = new Label("Udflugt(er):");
        pane.add(lblUdflugt, 1, 7);

        VBox vbxUdflugter = new VBox(5);
        pane.add(vbxUdflugter, 1, 8, 1, 5);

        int i = 0;
        for (Udflugt u : controller.konference.getUdflugter()) {
            HBox hbx = new HBox(5);
            Label lblkonfUdflugt = new Label(u.getNavn());

            hbx.getChildren().add(lblkonfUdflugt);
            cbxUdflugter[i] = new CheckBox();
            hbx.getChildren().add(cbxUdflugter[i]);
            vbxUdflugter.getChildren().add(hbx);
            i++;
        }

        Label lblStartDate = new Label("Start dato");
        pane.add(lblStartDate, 0, 11);

        pane.add(startDatePicker, 0, 12);

        Label lblSlutDate = new Label("Slut dato");
        pane.add(lblSlutDate, 0, 13);

        pane.add(slutDatePicker, 0, 14);

        pane.add(cbxHotel, 0, 15);

        cbbHoteller.setDisable(true);
        pane.add(cbbHoteller, 0, 16);

        ChangeListener<Boolean> listener1 = (ov, o, n) -> controller.HotelCheckmarkChanged(n);
        cbxHotel.selectedProperty().addListener(listener1);

        Label lblHotelTillæg = new Label("Hotel tillæg:");
        pane.add(lblHotelTillæg, 1, 15);
        pane.add(vbxHotelTillæg, 1, 16, 1, 3);
        vbxHotelTillæg.setMinWidth(200);
        cbbHoteller.setOnAction(event -> controller.fillHotelTillæg());

        pane.add(cbxFirma, 0, 17);
        cbxFirma.setText("Firma");
        Label lblFirmaNavn = new Label("Firma navn");
        pane.add(lblFirmaNavn, 0, 18);
        pane.add(txfFirmaNavn, 0, 19);
        Label lblFirmaTlf = new Label("Firma tlf.");
        pane.add(lblFirmaTlf, 1, 18);
        pane.add(txfFirmaTlf, 1, 19);

        ChangeListener<Boolean> listener = (ov, o, n) -> controller.firmaCheckmarkChanged(n);
        cbxFirma.selectedProperty().addListener(listener);

        txfFirmaNavn.setDisable(true);
        txfFirmaTlf.setDisable(true);

        Button btnCancel = new Button("Annuller");
        pane.add(btnCancel, 0, 20);
        GridPane.setHalignment(btnCancel, HPos.LEFT);
        GridPane.setMargin(btnCancel, new Insets(10, 0, 0, 30));
        btnCancel.setOnAction(event -> controller.cancelAction());

        Button btnOK = new Button("OK");
        pane.add(btnOK, 0, 20);
        GridPane.setHalignment(btnOK, HPos.RIGHT);
        GridPane.setMargin(btnOK, new Insets(10, 30, 0, 0));
        btnOK.setOnAction(event -> controller.okAction());

        pane.add(lblError, 0, 13);
        GridPane.setMargin(lblError, new Insets(0, 0, 10, 0));
        lblError.setStyle("-fx-text-fill: red");

        controller.fillComboBox();
    }

    public boolean getResult() {
        return controller.result;
    }

    // -------------------------------------------------------------------------

    private class Controller {
        public Konference konference;
        private boolean result = false;

        public void fillComboBox() {
            cbbHoteller.getItems().setAll(konference.getHoteller());
            if (cbbHoteller.getItems().size() > 0) {
                cbbHoteller.getSelectionModel().select(0);
            }

            if (cbbHoteller.getItems().size() > 0) {
                cbbHoteller.getSelectionModel().select(0);
            }
        }

        // Cancel button action
        public void cancelAction() {
            result = false;
            DeltagerDialog.this.hide();
        }

        // OK Button action
        public void okAction() {
            String name = txfNavn.getText().trim();
            if (name.length() == 0) {
                lblError.setText("Name is empty");
                return;
            }
            String adresse = txfAdresse.getText().trim();
            if (adresse.length() == 0) {
                lblError.setText("Adresse is empty");
                return;
            }
            String tlf = txfTlf.getText().trim();
            if (tlf.length() == 0) {
                lblError.setText("Tlf is empty");
                return;
            }

            Deltager deltager = null;
            if (cbxFirma.isSelected()) {
                String firmaNavn = txfFirmaNavn.getText().trim();
                if (firmaNavn.length() == 0) {
                    lblError.setText("FirmaNavn is empty");
                    return;
                }
                String firmaTlf = txfFirmaTlf.getText().trim();
                if (firmaTlf.length() == 0) {
                    lblError.setText("FirmaTlf is empty");
                    return;
                }
                deltager = new Deltager(name, adresse, tlf, firmaNavn, firmaTlf);
            } else {
                deltager = new Deltager(name, adresse, tlf);
            }

            String ledsager = txfLedsager.getText().trim();

            boolean erFordragsholder = false;
            if (cbxFordrag.isSelected()) {
                erFordragsholder = true;
            }

            LocalDate startDate = startDatePicker.getValue();
            LocalDate slutDate = slutDatePicker.getValue();

            if (startDate == null) {
                lblError.setText("Start dato er tom!");
                return;
            }
            if (slutDate == null) {
                lblError.setText("Slut dato er tom!");
                return;
            }

            ArrayList<Udflugt> udflugter = new ArrayList<Udflugt>();
            for (int i = 0; i < cbxUdflugter.length; i++) {
                if (cbxUdflugter[i].isSelected()) {
                    udflugter.add(controller.konference.getUdflugter().get(i));
                }
            }
            Hotel hotel = null;
            ArrayList<HotelTillæg> hotelTillæg = null;
            if (cbxHotel.isSelected()) {
                hotel = cbbHoteller.getValue();
                hotelTillæg = new ArrayList<HotelTillæg>();
                for (int i = 0; i < cbxHotelTillæg.size(); i++) {
                    if (cbxHotelTillæg.get(i).isSelected()) {
                        hotelTillæg.add(cbbHoteller.getValue().getHotelTillæg().get(i));
                    }
                }
            }

            Service.tilføjTilmelding(erFordragsholder, startDate, slutDate, ledsager, hotel, hotelTillæg, udflugter,
                    deltager, konference);
            result = true;
            DeltagerDialog.this.hide();

        }

        public void fillHotelTillæg() {
            vbxHotelTillæg.getChildren().clear();

            int i = 0;
            for (HotelTillæg ht : cbbHoteller.getValue().getHotelTillæg()) { //
                HBox hbx = new HBox(5);
                Label lblHotelTillæg1 = new Label(ht.toString());

                hbx.getChildren().add(lblHotelTillæg1);
                cbxHotelTillæg.set(i, new CheckBox());
                hbx.getChildren().add(cbxHotelTillæg.get(i));
                vbxHotelTillæg.getChildren().add(hbx);
                i++;
            }
        }

        public void firmaCheckmarkChanged(boolean checked) {
            txfFirmaNavn.setDisable(!checked);
            txfFirmaTlf.setDisable(!checked);
        }

        public void HotelCheckmarkChanged(boolean checked) {
            cbbHoteller.setDisable(!checked);
            if (checked) {
                fillHotelTillæg();
            } else {
                vbxHotelTillæg.getChildren().clear();
            }
        }
    }
}
