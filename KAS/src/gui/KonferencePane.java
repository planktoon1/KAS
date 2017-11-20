package gui;

import application.model.Company;
import application.model.Employee;
import application.service.Service;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
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

	private final TextField txfNavn = new TextField(), txfHours = new TextField();
	private final TextArea txaEmps = new TextArea();
	private final ListView<Company> lvwKonferencer = new ListView<>();

	private void initContent() {
		this.setPadding(new Insets(20));
		this.setHgap(20);
		this.setVgap(10);
		this.setGridLinesVisible(false);

		Label lblKonf = new Label("Konferencer");
		this.add(lblKonf, 0, 0);

		this.add(lvwKonferencer, 0, 1, 1, 3);
		lvwKonferencer.setPrefWidth(200);
		lvwKonferencer.setPrefHeight(200);

		ChangeListener<Company> listener = (ov, o, n) -> controller.selectedKonferenceChanged();
		lvwKonferencer.getSelectionModel().selectedItemProperty().addListener(listener);

		Label lblNavn = new Label("Navn:");
		this.add(lblNavn, 1, 1);

		this.add(txfNavn, 2, 1);
		txfNavn.setEditable(false);

		Label lblHours = new Label("Weekly Hours:");
		this.add(lblHours, 1, 2);

		this.add(txfHours, 2, 2);
		txfHours.setEditable(false);

		Label lblEmps = new Label("Employees:");
		this.add(lblEmps, 1, 3);
		GridPane.setValignment(lblEmps, VPos.BASELINE);
		lblEmps.setPadding(new Insets(4, 0, 4, 0));

		this.add(txaEmps, 2, 3);
		txaEmps.setPrefWidth(200);
		txaEmps.setPrefHeight(100);
		txaEmps.setEditable(false);

		HBox hbxButtons = new HBox(40);
		this.add(hbxButtons, 0, 4, 3, 1);
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
			lvwKonferencer.getItems().setAll(Service.getAllCompanies());
			if (lvwKonferencer.getItems().size() > 0)
				lvwKonferencer.getSelectionModel().select(0);
		}

		public void updateControls() {
			Company company = lvwKonferencer.getSelectionModel().getSelectedItem();
			if (company != null) {
				txfNavn.setText(company.getName());
				txfHours.setText("" + company.getHours());
				StringBuilder sb = new StringBuilder();
				for (Employee emp : company.getEmployees()) {
					sb.append(emp + "\n");
				}
				txaEmps.setText(sb.toString());
			} else {
				txfNavn.clear();
				txfHours.clear();
				txaEmps.clear();
			}
		}

		// --------------------------------------------------------------------

		// Create button action
		public void createAction() {
			if (createDialog == null) {
				createDialog = new KonferenceDialog("Create Company", null);
				Stage stage = (Stage) lvwKonferencer.getScene().getWindow();
				createDialog.initOwner(stage);
			}

			createDialog.showAndWait();
			// ... wait for the dialog to close

			boolean isCreated = createDialog.getResult();
			if (isCreated) {
				lvwKonferencer.getItems().setAll(Service.getAllCompanies());
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
