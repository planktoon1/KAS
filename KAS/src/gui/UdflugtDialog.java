package gui;

import java.time.LocalDate;

import application.model.Konference;
import application.model.Udflugt;
import application.service.Service;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class UdflugtDialog extends Stage {
    private final Controller controller = new Controller();

    /** Note: company is nullable. */
    public UdflugtDialog(String navn, Udflugt udflugt, Konference konference) {
        controller.udflugt = udflugt;
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

    private final TextField txfName = new TextField(), txfSted = new TextField(), txfPris = new TextField();
    private final TextArea txaBeskrivelse = new TextArea();
    private final CheckBox cbxFrokost = new CheckBox("Frokost ");
    private final Label lblError = new Label();
    private final DatePicker DatePicker = new DatePicker();

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(20, 20, 0, 20));
        pane.setHgap(10);
        pane.setVgap(5);
        pane.setGridLinesVisible(false);

        Label lblNavn = new Label("Udflugt navn");
        pane.add(lblNavn, 0, 0);

        pane.add(txfName, 0, 1);
        txfName.setPrefWidth(200);

        Label lblAdresse = new Label("Adresse (Post nr., By, Vej, Vej nr.)");
        pane.add(lblAdresse, 0, 2);
        GridPane.setMargin(lblAdresse, new Insets(10, 0, 0, 0));

        pane.add(txfSted, 0, 3);

        Label lblPris = new Label("Pris (kr . øre)");
        pane.add(lblPris, 0, 4);
        GridPane.setMargin(lblPris, new Insets(10, 0, 0, 0));

        pane.add(txfPris, 0, 5);

        pane.add(cbxFrokost, 0, 6);

        Label lblDato = new Label("Dato");
        pane.add(lblDato, 0, 7);
        GridPane.setMargin(lblDato, new Insets(10, 0, 0, 0));
        DatePicker.setShowWeekNumbers(true);
        pane.add(DatePicker, 0, 8);

        Label lblBeskrivelse = new Label("Beskrivelse");
        pane.add(lblBeskrivelse, 0, 9);

        pane.add(txaBeskrivelse, 0, 10);
        txaBeskrivelse.setPrefWidth(200);
        txaBeskrivelse.setPrefHeight(200);

        Button btnCancel = new Button("Annuller");
        pane.add(btnCancel, 0, 11);
        GridPane.setHalignment(btnCancel, HPos.LEFT);
        GridPane.setMargin(btnCancel, new Insets(10, 0, 0, 30));
        btnCancel.setOnAction(event -> controller.cancelAction());

        Button btnOK = new Button("OK");
        pane.add(btnOK, 0, 11);
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
        private Udflugt udflugt;
        private boolean result = false;

        public void updateControls() {
            if (udflugt != null) {
                txfName.setText(udflugt.getNavn());
                txfSted.setText("" + udflugt.getSted());
            } else {
                txfName.clear();
                txfSted.clear();
            }
            lblError.setText("");
        }

        // Cancel button action
        public void cancelAction() {
            result = false;
            UdflugtDialog.this.hide();
        }

        // OK button action
        public void okAction() {
            LocalDate dato = DatePicker.getValue();
            double pris = 0.0;
            if (txfPris.getText().length() > 0 && validDouble(txfPris.getText()) == true) {
                pris = 1.0 * Double.parseDouble(txfPris.getText());
            }
            boolean frokost = cbxFrokost.isSelected();
            String beskrivelse = txaBeskrivelse.getText().trim();
            String navn = txfName.getText().trim();
            String sted = txfSted.getText().trim();

            if (navn.length() == 0) {
                lblError.setText("Name is empty");
                return;
            }
            if (sted.length() == 0) {
                lblError.setText("Adresse er tom");
                return;
            }
            if (dato == null) {
                lblError.setText("Dato er tom");
                return;
            }
            if (pris == 0 || pris < 0) {
                lblError.setText("pris skal være et valid tal");
                return;
            }
            if (beskrivelse == null) {
                lblError.setText("Beskrivelse er tom");
                return;
            }

            Service.tilføjUdflugt(sted, dato, pris, frokost, beskrivelse, navn,
                    konference);

            result = true;
            UdflugtDialog.this.hide();
        }

        public boolean validDouble(String string) {
            boolean result = true;
            boolean dotFound = false;

            for (int i = 0; i < string.length(); i++) {
                if (string.charAt(i) >= '0' && string.charAt(i) <= '9' || string.charAt(i) == '.') {
                    if (string.charAt(i) == '.') {
                        if (dotFound) {
                            result = false;
                            break;
                        }
                        dotFound = true;
                    }
                } else {
                    result = false;
                    break;
                }
            }
            return result;
        }
    }
}
