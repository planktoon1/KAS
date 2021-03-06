package gui;

import java.time.LocalDate;
import java.util.ArrayList;

import application.model.Hotel;
import application.model.Konference;
import application.service.Service;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

//gruppe: Anders R.P., Casper L. og Frederik Ø.
public class HotelDialog extends Stage {
    private final Controller controller = new Controller();

    /** Note: company is nullable. */
    public HotelDialog(String navn, Hotel hotel, Konference konference) {
        controller.hotel = hotel;
        controller.konference = konference;

        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        this.setTitle(navn);
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    // -------------------------------------------------------------------------

    private final TextField txfName = new TextField(), txfSted = new TextField(), txfPris = new TextField(),
            txfPris1 = new TextField();
    private final Label lblError = new Label();
    private final DatePicker DatePicker = new DatePicker();
    private final VBox vBox = new VBox(10);
    private final Button btnTilføjTillæg = new Button("Tilføj tillæg");

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(20, 20, 0, 20));
        pane.setHgap(10);
        pane.setVgap(5);
        pane.setGridLinesVisible(false);
        pane.setPrefHeight(500);

        Label lblNavn = new Label("Navn");
        pane.add(lblNavn, 0, 0);

        pane.add(txfName, 0, 1);
        txfName.setPrefWidth(200);

        Label lblAdresse = new Label("Adresse (Post nr., By, Vej, Vej nr.)");
        pane.add(lblAdresse, 0, 2);
        GridPane.setMargin(lblAdresse, new Insets(10, 0, 0, 0));

        pane.add(txfSted, 0, 3);

        Label lblPris = new Label("Pris for enkeltmands værelse (kr . øre)");
        pane.add(lblPris, 0, 4);
        GridPane.setMargin(lblPris, new Insets(10, 0, 0, 0));

        pane.add(txfPris1, 0, 5);

        Label lblPris1 = new Label("Pris for to personers værelse (kr . øre)");
        pane.add(lblPris1, 0, 6);
        GridPane.setMargin(lblPris, new Insets(10, 0, 0, 0));

        pane.add(txfPris, 0, 7);

        Label lblTillæg = new Label("Tillæg");
        pane.add(lblTillæg, 0, 8);
        GridPane.setMargin(lblTillæg, new Insets(10, 0, 0, 0));

        pane.add(vBox, 0, 9);
        vBox.getChildren().add(btnTilføjTillæg);
        btnTilføjTillæg.setOnAction(event -> controller.tilføjTillæg());

        Button btnCancel = new Button("Annuller");
        pane.add(btnCancel, 0, 12);
        GridPane.setHalignment(btnCancel, HPos.LEFT);
        GridPane.setMargin(btnCancel, new Insets(10, 0, 0, 30));
        btnCancel.setOnAction(event -> controller.cancelAction());

        Button btnOK = new Button("OK");
        pane.add(btnOK, 0, 12);
        GridPane.setHalignment(btnOK, HPos.RIGHT);
        GridPane.setMargin(btnOK, new Insets(10, 30, 0, 0));
        btnOK.setOnAction(event -> controller.okAction());

        pane.add(lblError, 0, 12);
        GridPane.setMargin(lblError, new Insets(0, 0, 10, 0));
        lblError.setStyle("-fx-text-fill: red");

        controller.updateControls();
    }

    public boolean getResult() {
        return controller.result;
    }

    // -------------------------------------------------------------------------

    private class Controller {
        private Konference konference;
        private Hotel hotel;
        private boolean result = false;
        private ArrayList<TextField> tillægNavn = new ArrayList<>();
        private ArrayList<TextField> tillægPris = new ArrayList<>();

        public void updateControls() {
            if (hotel != null) {
                txfName.setText(hotel.getNavn());
                txfSted.setText("" + hotel.getAdresse());
            } else {
                txfName.clear();
                txfSted.clear();
            }
            lblError.setText("");
        }

        // Cancel button action
        public void cancelAction() {
            result = false;
            HotelDialog.this.hide();
        }

        // OK button action
        public void okAction() {
            LocalDate dato = DatePicker.getValue();
            double pris = 0.0;
            if (txfPris.getText().length() > 0 && Service.validDouble(txfPris.getText()) == true) {
                pris = 1.0 * Double.parseDouble(txfPris.getText());
            }
            String navn = txfName.getText().trim();
            String sted = txfSted.getText().trim();
            String pris1 = txfPris.getText().trim();
            String pris2 = txfPris1.getText().trim();

            if (navn.length() == 0) {
                lblError.setText("Name is empty");
                return;
            }
            if (sted.length() == 0) {
                lblError.setText("Adresse er tom");
                return;
            }

            if (!Service.validDouble(pris1) || !Service.validDouble(pris2)) {
                lblError.setText("Pris skal være et valid tal");
                return;
            }
            if (pris1.length() == 0) {
                lblError.setText("Prisen er tom");
                return;
            }

            if (pris2.length() == 0) {
                lblError.setText("Prisen er tom");
                return;
            }

            Hotel h = Service.tilføjHotel(navn, sted, Double.parseDouble(pris2), Double.parseDouble(pris1), konference);

            int i = 0;
            for (TextField tf : tillægNavn) {
                if (Service.validDouble(tillægPris.get(i).getText()))
                    h.createHoteltillæg(tillægNavn.get(i).getText(),
                            Double.parseDouble(tillægPris.get(i).getText()));
                else {
                    lblError.setText("Pris skal være tal");
                    return;
                }
                i++;
            }

            result = true;
            HotelDialog.this.hide();
        }

        public void tilføjTillæg() {
            HBox hBox = new HBox(5);
            vBox.getChildren().add(hBox);
            TextField txfNavn = new TextField();
            TextField txfPris = new TextField();
            hBox.getChildren().add(txfNavn);
            hBox.getChildren().add(txfPris);
            tillægNavn.add(txfNavn);
            tillægPris.add(txfPris);

        }
    }
}
