package gui;

import application.model.Deltager;
import application.model.Hotel;
import application.model.Konference;
import application.model.Udflugt;
import application.service.Service;
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
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DeltagerDialog extends Stage {
    private final Controller controller = new Controller();

    /** Note: Deltager is nullable. */
    public DeltagerDialog(String title, Deltager deltager) {
        controller.Deltager = deltager;

        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        this.setTitle(title);
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    // -------------------------------------------------------------------------

    private final TextField txfNavn = new TextField(), txfWage = new TextField(), txfLedsager = new TextField();
    private final ComboBox<Konference> cbbKonferencer = new ComboBox<>();
    private final ComboBox<Hotel> cbbHoteller = new ComboBox<>();
    private final ComboBox<Udflugt> cbbUdflugter = new ComboBox<>();
    private final Label lblError = new Label();
    private final CheckBox cbxFordrag = new CheckBox();
    private final DatePicker startDatePicker = new DatePicker(), slutDatePicker = new DatePicker();

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(20, 20, 0, 20));
        pane.setHgap(10);
        pane.setVgap(5);
        pane.setGridLinesVisible(false);

        Label lblName = new Label("Name");
        pane.add(lblName, 0, 0);

        pane.add(txfNavn, 0, 1);
        txfNavn.setPrefWidth(200);

        Label lblWage = new Label("Hourly Wage");
        pane.add(lblWage, 0, 2);
        GridPane.setMargin(lblWage, new Insets(10, 0, 0, 0));

        pane.add(txfWage, 0, 3);

        pane.add(cbbKonferencer, 0, 5);

        Button btnCancel = new Button("Cancel");
        pane.add(btnCancel, 0, 6);
        GridPane.setHalignment(btnCancel, HPos.LEFT);
        GridPane.setMargin(btnCancel, new Insets(10, 0, 0, 30));
        btnCancel.setOnAction(event -> controller.cancelAction());

        Button btnOK = new Button("OK");
        pane.add(btnOK, 0, 6);
        GridPane.setHalignment(btnOK, HPos.RIGHT);
        GridPane.setMargin(btnOK, new Insets(10, 30, 0, 0));
        btnOK.setOnAction(event -> controller.okAction());

        pane.add(lblError, 0, 7);
        GridPane.setMargin(lblError, new Insets(0, 0, 10, 0));
        lblError.setStyle("-fx-text-fill: red");

        controller.fillCompanyComboBox();
//         controller.updateControls();
    }

    public boolean getResult() {
        return controller.result;
    }

    // -------------------------------------------------------------------------

    private class Controller {
        private Deltager Deltager;
        private boolean result = false;

        public void fillCompanyComboBox() {
            cbbKonferencer.getItems().setAll(Service.getAllkonferencer());
            if (cbbKonferencer.getItems().size() > 0) {
                cbbKonferencer.getSelectionModel().select(0);
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

            int wage = -1;
            try {
                wage = Integer.parseInt(txfWage.getText().trim());
            } catch (NumberFormatException ex) {
                // do nothing
            }
            if (wage < 0) {
                lblError.setText("Wage is not a positive number");
                return;
            }

            result = true;
            DeltagerDialog.this.hide();
        }

    }
}
