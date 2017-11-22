package gui;

import java.time.LocalDate;

import application.model.Deltager;
import application.model.Firma;
import application.model.Hotel;
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
//    private final ComboBox<Konference> cbbKonferencer = new ComboBox<>();
    private final ComboBox<Hotel> cbbHoteller = new ComboBox<>();
    private final ComboBox<Udflugt> cbbUdflugter = new ComboBox<>();
    private final ComboBox<Firma> cbbFirma = new ComboBox<>();
    private final Label lblError = new Label();
    private final CheckBox cbxFordrag = new CheckBox(), cbxFirma = new CheckBox();
    private final DatePicker startDatePicker = new DatePicker(), slutDatePicker = new DatePicker();
    private final CheckBox[] cbxUdflugter = new CheckBox[Service.getAllUdflugter().size()];

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
        pane.add(vbxUdflugter, 1, 8);
        
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

        Label lblHotel = new Label("Hotel");
        pane.add(lblHotel, 0, 15);

        pane.add(cbbHoteller, 0, 16);


        pane.add(cbxFirma, 0, 17);
        cbxFirma.setText("Firma");
        Label lblFirmaNavn = new Label("Firma navn");
        pane.add(lblFirmaNavn, 0, 18);
        pane.add(txfFirmaNavn, 0, 19);
        Label lblFirmaTlf = new Label("Firma tlf.");
        pane.add(lblFirmaTlf, 1, 18);
        pane.add(txfFirmaTlf, 1, 19);

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

        ChangeListener<Boolean> listener = (ov, o, n) -> controller.firmaCbxChange(n);
        cbxFirma.selectedProperty().addListener(listener);

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

            cbbUdflugter.getItems().setAll(Service.getAllUdflugter());
            if (cbbUdflugter.getItems().size() > 0) {
                cbbUdflugter.getSelectionModel().select(0);
            }

//            cbbFirma.getItems().setAll(Service.getAllHoteller());
            if (cbbHoteller.getItems().size() > 0) {
                cbbHoteller.getSelectionModel().select(0);
            }
        }

        // public void updateControls() {
        // if (Deltager != null) {
        // txfName.setText(Deltager.getName());
        // txfWage.setText("" + Deltager.getWage());
        // if (Deltager.getCompany() != null) {
        // cbxCompany.setSelected(true);
        // cbbCompany.getSelectionModel().select(Deltager.getCompany());
        // cbbCompany.setDisable(false);
        // } else {
        // cbbCompany.getSelectionModel().select(0);
        // cbbCompany.setDisable(true);
        // }
        // } else {
        // txfName.clear();
        // txfWage.clear();
        // cbxCompany.setSelected(false);
        // cbbCompany.getSelectionModel().select(0);
        // cbbCompany.setDisable(true);
        // }
        // lblError.setText("");
        // }

        // ---------------------------------------------------------------------

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

            result = true;
            DeltagerDialog.this.hide();

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


        }

        public void firmaCbxChange(Boolean checked) {
            txfFirmaNavn.setDisable(!checked);
            txfFirmaTlf.setDisable(!checked);
            txfFirmaNavn.clear();
            txfFirmaTlf.clear();
        }

    }
}
