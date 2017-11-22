package gui;

import application.model.HotelTillæg;
import application.model.Konference;
import application.model.Tilmelding;
import application.model.Udflugt;
import application.service.Service;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class DeltagerPane extends GridPane {
	private final Controller controller = new Controller();

	public DeltagerPane() {
		this.initControls();
	}

	// -------------------------------------------------------------------------

	private final TextField txfNavn = new TextField(), txfLedsager = new TextField(), txfUdflugt = new TextField(),
			txfStartDate = new TextField(), txfSlutDate = new TextField(), txfHotel = new TextField(),
			txfSamletPris = new TextField(), txfFordrag = new TextField();
	private final ListView<Konference> lvwKonferencer = new ListView<>();
	private final CheckBox cbxFordrag = new CheckBox();
	private final ListView<Tilmelding> lvwTilmelding = new ListView<>();
	private final ListView<Udflugt> lvwUdflugt = new ListView<>();
	private final ListView<HotelTillæg> lvwHotelTillæg = new ListView<>();

	private void initControls() {
		this.setPadding(new Insets(20));
		this.setHgap(20);
		this.setVgap(10);
		this.setGridLinesVisible(false);

		Label lblComp = new Label("Konferencer:");
		this.add(lblComp, 0, 0);

		Label lblDeltagere = new Label("Tilmelding");
		this.add(lblDeltagere, 2, 0);

		this.add(lvwKonferencer, 0, 1, 1, 5);
		lvwKonferencer.setPrefWidth(200);
		lvwKonferencer.setPrefHeight(200);

		ChangeListener<Konference> listener = (ov, o, n) -> controller.selectedKonferenceChanged();
		lvwKonferencer.getSelectionModel().selectedItemProperty().addListener(listener);

		Label lblTilmelding = new Label("Deltagere:");
		this.add(lblTilmelding, 3, 0);

		this.add(lvwTilmelding, 3, 1, 1, 5);
		lvwTilmelding.setPrefHeight(200);
		lvwTilmelding.setPrefWidth(200);

		ChangeListener<Tilmelding> listener2 = (ov, o, n) -> controller.selectedTilmeldingChanged();
		lvwTilmelding.getSelectionModel().selectedItemProperty().addListener(listener2);

		Label lblNavn = new Label("Navn:");
		this.add(lblNavn, 1, 1);

		this.add(txfNavn, 2, 1);
		txfNavn.setPrefWidth(200);
		txfNavn.setEditable(false);

		Label lblFordrag = new Label("Fordragsholder:");
		this.add(lblFordrag, 1, 2);

		this.add(txfFordrag, 2, 2);

		Label lblLedsager = new Label("Ledsager:");
		this.add(lblLedsager, 1, 3);

		this.add(txfLedsager, 2, 3);
		txfLedsager.setEditable(false);

		Label lblUdflugt = new Label("Udflugt(er):");
		this.add(lblUdflugt, 1, 4);
		this.add(lvwUdflugt, 2, 4);
		lvwUdflugt.setMaxHeight(50);
		//
		// this.add(txfUdflugt, 2, 4);
		// txfUdflugt.setEditable(false);

		Label lblStartDate = new Label("Start dato:");
		this.add(lblStartDate, 1, 5);

		this.add(txfStartDate, 2, 5);

		Label lblSlutDate = new Label("Slut dato:");
		this.add(lblSlutDate, 1, 6);

		this.add(txfSlutDate, 2, 6);

		Label lblHotel = new Label("Hotel:");
		this.add(lblHotel, 1, 7);

		this.add(txfHotel, 2, 7);

		Label lblSamletpris = new Label("Samlet pris:");
		this.add(lblSamletpris, 1, 9);

		this.add(txfSamletPris, 2, 9);
		txfSamletPris.setEditable(false);
		txfFordrag.setEditable(false);
		txfHotel.setEditable(false);

		Label lblHoteltillæg = new Label("Hotel tillæg:");
		this.add(lblHoteltillæg, 1, 8);
		this.add(lvwHotelTillæg, 2, 8);
		lvwHotelTillæg.setMaxHeight(50);

		HBox hbxButtons = new HBox(40);
		this.add(hbxButtons, 0, 10, 3, 1);
		hbxButtons.setPadding(new Insets(10, 0, 0, 0));
		hbxButtons.setAlignment(Pos.BASELINE_CENTER);

		Button btnCreate = new Button("Opret");
		hbxButtons.getChildren().add(btnCreate);
		btnCreate.setOnAction(event -> controller.createAction());

		Button btnUpdate = new Button("Update");
		hbxButtons.getChildren().add(btnUpdate);
		btnUpdate.setOnAction(event -> controller.updateAction());

		controller.fillLvw();
	}

	// -------------------------------------------------------------------------

	public void updateControls() {
		controller.updateControls();
	}

	// -------------------------------------------------------------------------

	private class Controller {
		private DeltagerDialog createDialog;// updateDialog;
		private Konference konference;
		Tilmelding tilmelding;

		public void fillLvw() {

			lvwKonferencer.getItems().setAll(Service.getAllkonferencer());
			if (lvwKonferencer.getItems().size() > 0) {
				lvwKonferencer.getSelectionModel().select(0);
				konference = lvwKonferencer.getSelectionModel().getSelectedItem();
			}
			if (konference != null) {
				lvwTilmelding.getItems().setAll(konference.getTilmeldinger());
			}

		}

		public void updateControls() {
			konference = lvwKonferencer.getSelectionModel().getSelectedItem();
			tilmelding = lvwTilmelding.getSelectionModel().getSelectedItem();

			if (konference != null) {

				lvwTilmelding.getItems().setAll(konference.getTilmeldinger());

			} else {
				lvwTilmelding.getItems().clear();
			}
		}

		// --------------------------------------------------------------------

		// Create button action
		public void createAction() {
			createDialog = new DeltagerDialog("Opret deltager", konference);
			Stage stage = (Stage) lvwKonferencer.getScene().getWindow();
			createDialog.initOwner(stage);
			createDialog.showAndWait();
			// ... wait for the dialog to close

			boolean isCreated = createDialog.getResult();
			if (isCreated) {
				lvwKonferencer.getItems().setAll(Service.getAllkonferencer());
				int index = lvwKonferencer.getItems().size() - 1;
				lvwKonferencer.getSelectionModel().select(index);
			}
			this.updateControls();
		}

		// Update button action
		public void updateAction() {

		}

		// Selected item in lvwKonferences changed
		public void selectedKonferenceChanged() {
			this.updateControls();
		}

		public void selectedTilmeldingChanged() {
			tilmelding = lvwTilmelding.getSelectionModel().getSelectedItem();
			if (tilmelding != null) {
				txfStartDate.setText("" + tilmelding.getAnkomstdato());
				txfSlutDate.setText("" + tilmelding.getAfrejsedato());
				txfHotel.setText("" + tilmelding.getHotel());
				txfNavn.setText(tilmelding.getDeltager().getNavn());
				if (tilmelding.isErFordragsholder()) {
					txfFordrag.setText("JA");
				} else {
					txfFordrag.setText("NEJ");
				}
				if (tilmelding.getLedsager() == null) {
					txfLedsager.setText("Ingen");
				} else {
					txfLedsager.setText(tilmelding.getLedsager());
				}

				if (tilmelding.getUdflugter() != null) {
					lvwUdflugt.getItems().setAll(tilmelding.getUdflugter());
				} else {
					lvwUdflugt.getItems().clear();
				}

				if (tilmelding.getHotel() != null && tilmelding.getHotel().getHotelTillæg() != null) {
					lvwHotelTillæg.getItems().setAll(tilmelding.getHotel().getHotelTillæg());
				} else {
					lvwHotelTillæg.getItems().clear();
				}

				txfSamletPris.setText(String.valueOf(tilmelding.getSamletPris()));

			} else {
				txfNavn.clear();
				cbxFordrag.setSelected(false);
				txfStartDate.clear();
				txfSlutDate.clear();
			}
		}
	}
}
