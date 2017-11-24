package gui;

import java.time.LocalDate;

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
import javafx.stage.Modality;
import javafx.stage.Stage;

public class KonferenceDialog extends Stage {
	private final Controller controller = new Controller();

	/** Note: company is nullable. */
	public KonferenceDialog(String navn, Konference konference) {
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

	private final TextField txfName = new TextField(), txfAdresse = new TextField(), txfDagsPris = new TextField();
	private final Label lblError = new Label();
	private final DatePicker startDatePicker = new DatePicker(), slutDatePicker = new DatePicker();

	private void initContent(GridPane pane) {
		pane.setPadding(new Insets(20, 20, 0, 20));
		pane.setHgap(10);
		pane.setVgap(5);
		pane.setGridLinesVisible(false);
		pane.setPrefHeight(350);

		Label lblNavn = new Label("Konference navn");
		pane.add(lblNavn, 0, 0);

		pane.add(txfName, 0, 1);
		txfName.setPrefWidth(200);

		Label lblAdresse = new Label("Adresse (Post nr., By, Vej, Vej nr.)");
		pane.add(lblAdresse, 0, 2);
		GridPane.setMargin(lblAdresse, new Insets(10, 0, 0, 0));

		pane.add(txfAdresse, 0, 3);

		Label lblStartDato = new Label("Start Dato");
		pane.add(lblStartDato, 0, 4);
		GridPane.setMargin(lblStartDato, new Insets(10, 0, 0, 0));
		startDatePicker.setShowWeekNumbers(true);
		pane.add(startDatePicker, 0, 5);

		Label lblSlutDato = new Label("Slut Dato");
		pane.add(lblSlutDato, 0, 6);
		GridPane.setMargin(lblSlutDato, new Insets(10, 0, 0, 0));
		slutDatePicker.setShowWeekNumbers(true);
		pane.add(slutDatePicker, 0, 7);

		Label lblDagsPris = new Label("Dagspris");
		pane.add(lblDagsPris, 0, 8);
		GridPane.setMargin(lblAdresse, new Insets(10, 0, 0, 0));

		pane.add(txfDagsPris, 0, 9);

		Button btnCancel = new Button("Annuller");
		pane.add(btnCancel, 0, 10);
		GridPane.setHalignment(btnCancel, HPos.LEFT);
		GridPane.setMargin(btnCancel, new Insets(10, 0, 0, 30));
		btnCancel.setOnAction(event -> controller.cancelAction());

		Button btnOK = new Button("OK");
		pane.add(btnOK, 0, 10);
		GridPane.setHalignment(btnOK, HPos.RIGHT);
		GridPane.setMargin(btnOK, new Insets(10, 30, 0, 0));
		btnOK.setOnAction(event -> controller.okAction());

		pane.add(lblError, 0, 11);
		GridPane.setMargin(lblError, new Insets(0, 0, 10, 0));
		lblError.setStyle("-fx-text-fill: red");

		controller.updateControls();
	}

	/**
	 * Return true, if a company is created or updated.
	 */
	public boolean getResult() {
		return controller.result;
	}

	// -------------------------------------------------------------------------

	private class Controller {
		private Konference konference;
		private boolean result = false;

		public void updateControls() {
			if (konference != null) {
				txfName.setText(konference.getNavn());
				txfAdresse.setText("" + konference.getAdresse());
			} else {
				txfName.clear();
				txfAdresse.clear();
			}
			lblError.setText("");
		}

		// Cancel button action
		public void cancelAction() {
			result = false;
			KonferenceDialog.this.hide();
		}

		// OK button action
		public void okAction() {
			LocalDate startDate = startDatePicker.getValue();
			LocalDate slutDate = slutDatePicker.getValue();
			String navn = txfName.getText().trim();
			String adresse = txfAdresse.getText().trim();
			double dagsPris = 0;

			if (txfDagsPris.getText().length() == 0) {
				lblError.setText("Dagsprisen er tom!");
				return;
			}

			else if (Service.validDouble(txfDagsPris.getText())) {
				System.out.println("Hey");
				dagsPris = Double.parseDouble(txfDagsPris.getText());
			}

			else {
				lblError.setText("Dagsprisen skal været et tal");
				return;
			}

			if (navn.length() == 0) {
				lblError.setText("Du mangler at indtaste et navn til konferencen!");
				return;
			}
			if (adresse.length() == 0) {
				lblError.setText("Adresse er tom!");
				return;
			}
			if (startDate == null) {
				lblError.setText("Start dato er tom!");
				return;
			}
			if (slutDate == null) {
				lblError.setText("Slut dato er tom!");
				return;
			}

			// if (konference != null)
			// Service.updateCompany(konference, name, hours);
			// else
			Service.tilføjKonference(navn, startDate, slutDate, adresse, dagsPris);

			result = true;
			KonferenceDialog.this.hide();

		}

	}
}
