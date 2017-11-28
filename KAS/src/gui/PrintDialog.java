package gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

//gruppe: Anders R.P., Casper L. og Frederik Ø.
public class PrintDialog extends Stage {
    private final Controller controller = new Controller();
    private final TextArea texten = new TextArea();

    /** Note: company is nullable. */
    public PrintDialog(String content) {
        controller.content = content;

        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        this.setTitle("Print");
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);

        Label lblPrintOversigt = new Label("Print oversigt");
        lblPrintOversigt.setFont(new Font("Serif", 30));
        pane.add(lblPrintOversigt, 0, 0);

        pane.add(texten, 0, 1);

        Button btnLuk = new Button("Luk");
        pane.add(btnLuk, 0, 2);
        btnLuk.setOnAction(event -> controller.lukAction());

    }

    // -------------------------------------------------------------------------

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(20, 20, 0, 20));
        pane.setHgap(10);
        pane.setVgap(5);
        pane.setGridLinesVisible(false);
        pane.setPrefHeight(350);

        controller.setTexten();
    }

    private class Controller {
        private String content;

        public void setTexten() {
            texten.setText(content);
        }

        public void lukAction() {
            PrintDialog.this.hide();
        }

    }
}
