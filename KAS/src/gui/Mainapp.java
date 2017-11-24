package gui;

import application.service.Service;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Mainapp extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void init() {
        Service.initStorage();
    }

    @Override
    public void start(Stage stage) {

        stage.setTitle("KAS");
        BorderPane pane = new BorderPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    // -------------------------------------------------------------------------

    private void initContent(BorderPane pane) {
        MenuBar menubar = new MenuBar();
        //this.initMenus(menubar);
        pane.setTop(menubar);

        TabPane tabPane = new TabPane();
        this.initTabPane(tabPane);
        pane.setCenter(tabPane);
    }

//	private void initMenus(MenuBar menubar) {
//		Menu menuPrint = new Menu("Oversigter");
//		menubar.getMenus().add(menuPrint);
//
//		MenuItem deltagere = new MenuItem("Deltagere");
//		menuPrint.getItems().add(deltagere);
//		deltagere.setOnAction(event -> controller.companiesAction());
//
//		MenuItem udflugt = new MenuItem("Udflugter");
//		menuPrint.getItems().add(udflugt);
//		udflugt.setOnAction(event -> controller.employeesAction());
//
//		MenuItem hotel = new MenuItem("Hoteller");
//		menuPrint.getItems().add(hotel);
//		hotel.setOnAction(event -> controller.companiesAction());
//
//		menuPrint.getItems().add(new SeparatorMenuItem());
//
//		MenuItem exit = new MenuItem("Exit");
//		menuPrint.getItems().add(exit);
//		exit.setOnAction(event -> controller.exitAction());
//	}

    private void initTabPane(TabPane tabPane) {
        tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

        Tab tabKonference = new Tab("Konference");
        tabPane.getTabs().add(tabKonference);

        KonferencePane KonferencePane = new KonferencePane();
        tabKonference.setContent(KonferencePane);
        tabKonference.setOnSelectionChanged(event -> KonferencePane.updateControls());

        Tab tabDeltager = new Tab("Deltagere");
        tabPane.getTabs().add(tabDeltager);

        Tab tabUdflugter = new Tab("Udflugter");
        tabPane.getTabs().add(tabUdflugter);

        UdflugtPane udflugtPane = new UdflugtPane();
        tabUdflugter.setContent(udflugtPane);
        tabUdflugter.setOnSelectionChanged(event -> udflugtPane.updateControls());

        DeltagerPane deltagerPane = new DeltagerPane();
        tabDeltager.setContent(deltagerPane);
        tabDeltager.setOnSelectionChanged(event -> deltagerPane.updateControls());
    }
}
