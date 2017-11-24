package gui;

import application.model.Konference;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PrintDialog extends Stage {
    private final Controller controller = new Controller();

    /** Note: company is nullable. */
    public PrintDialog(String navn, Konference konference) {
        controller.konference = konference;

        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        this.setTitle(navn);
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);

        Label lblPrintOversigt = new Label("Print oversigt");
        lblPrintOversigt.setFont(new Font("Serif", 30));
        pane.add(lblPrintOversigt, 0, 0);

        Button btnLuk = new Button("Luk");
        pane.add(btnLuk, 0, 2);

    }

    // -------------------------------------------------------------------------

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(20, 20, 0, 20));
        pane.setHgap(10);
        pane.setVgap(5);
        pane.setGridLinesVisible(false);
        pane.setPrefHeight(350);

    }

    /**
     * Return true, if a company is created or updated.
     */

    // -------------------------------------------------------------------------

    private class Controller {
        private Konference konference;

    }
}
