package gui;

import application.model.Udflugt;
import application.service.Service;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

//gruppe: Anders R.P., Casper L. og Frederik Ø.
public class UdflugtPane extends GridPane {
    private final Controller controller = new Controller();

    public UdflugtPane() {
        this.initControls();
    }

    // -------------------------------------------------------------------------

    private final ListView<String> lvwLedsagere = new ListView<>();
    private final ListView<Udflugt> lvwUdflugter = new ListView<>();

    private void initControls() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        Label lblUdflugter = new Label("Udflugter");
        this.add(lblUdflugter, 0, 0);
        lblUdflugter.setFont(new Font("Serif", 30));

        Label lblLedsagere = new Label("Ledsagere");
        this.add(lblLedsagere, 1, 0);
        lblLedsagere.setFont(new Font("Serif", 30));

        this.add(lvwUdflugter, 0, 1, 1, 5);
        lvwUdflugter.setPrefWidth(300);
        lvwUdflugter.setPrefHeight(300);

        ChangeListener<Udflugt> listener = (ov, o, n) -> controller.selectedKonferenceChanged();
        lvwUdflugter.getSelectionModel().selectedItemProperty().addListener(listener);

        this.add(lvwLedsagere, 1, 1, 1, 5);
        lvwLedsagere.setPrefHeight(300);
        lvwLedsagere.setPrefWidth(300);

        ChangeListener<String> listener2 = (ov, o, n) -> controller.selectedTilmeldingChanged();
        lvwLedsagere.getSelectionModel().selectedItemProperty().addListener(listener2);

        controller.fillLvw();
    }

    // -------------------------------------------------------------------------

    public void updateControls() {
        controller.updateControls();
    }

    // -------------------------------------------------------------------------

    private class Controller {
        private DeltagerDialog createDialog;
        private Udflugt udflugt;
        String tilmelding;

        public void fillLvw() {

            lvwUdflugter.getItems().setAll(Service.getAllUdflugter());
            if (lvwUdflugter.getItems().size() > 0) {
                lvwUdflugter.getSelectionModel().select(0);
                udflugt = lvwUdflugter.getSelectionModel().getSelectedItem();
            }
            if (udflugt != null) {
                lvwLedsagere.getItems().setAll(Service.getLedsager(udflugt));
            }

        }

        public void updateControls() {
            udflugt = lvwUdflugter.getSelectionModel().getSelectedItem();
            tilmelding = lvwLedsagere.getSelectionModel().getSelectedItem();

            if (udflugt != null) {

                lvwLedsagere.getItems().setAll(Service.getLedsager(udflugt));

            } else {
                lvwLedsagere.getItems().clear();
            }
        }

        public void selectedKonferenceChanged() {
            this.updateControls();
        }

        public void selectedTilmeldingChanged() {
            tilmelding = lvwLedsagere.getSelectionModel().getSelectedItem();

        }
    }
}
