package gui;

import application.model.Deltager;
import application.model.Konference;
import application.service.Service;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PrintDialog extends Stage {
    private final Controller controller = new Controller();

    public PrintDialog(Stage owner) {
        this.initOwner(owner);
        this.setX(owner.getX() + 100);
        this.setY(owner.getY() + 50);

        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        this.setTitle("Print");
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    // -------------------------------------------------------------------------

    private final Label lbl = new Label();
    private final TextArea txa = new TextArea();

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        pane.add(lbl, 0, 0);

        pane.add(txa, 0, 1);
        txa.setPrefWidth(250);
        txa.setPrefRowCount(10);
        txa.setEditable(false);
        txa.setFocusTraversable(false);

        Button btnClose = new Button("Close");
        pane.add(btnClose, 0, 2);
        GridPane.setHalignment(btnClose, HPos.RIGHT);
        btnClose.setOnAction(event -> controller.closeAction());
    }

    // -------------------------------------------------------------------------

    public void showDeltagere() {
        controller.showEmployees();
    }

    public void showCompanies() {
        controller.showCompanies();
    }

    // -------------------------------------------------------------------------

    private class Controller {

        public void showEmployees() {
            lbl.setText("All Employees:");

            StringBuilder sb = new StringBuilder();
            for (Deltager del : Service.getAllDeltagere()) {
                Konference konference = del.getKonference();
                if (konference != null)
                    sb.append(emp + " - at " + konference.getName() + "\n");
                else
                    sb.append(emp + "\n");
            }
            txa.setText(sb.toString());
        }

        public void showCompanies() {
            lbl.setText("All Companies:");

            StringBuilder sb = new StringBuilder();
            for (Konference konference : Service.getAllkonferencer())
                sb.append(konference + "\n");
            txa.setText(sb.toString());
        }

        public void closeAction() {
            PrintDialog.this.hide();
        }
    }
}
