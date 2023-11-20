package es.ieslosmontecillos;

import com.gluonhq.charm.glisten.mvc.View;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AppAgendaDK extends Application {
    private DataUtil dataUtil;
    private InicioView inicioView = new InicioView();
    private InicioController inicioController;

    @Override
    public void start(Stage primaryStage) throws IOException {
        StackPane rootMain = new StackPane();

        View inicio = inicioView.getView();
        rootMain.getChildren().add(inicio);
        inicioController = inicioView.getInicioController();
        inicioController.setRootMain(rootMain);

        dataUtil = new DataUtil();
        dataUtil.obtenerTodasProvincias();
        ObservableList<Provincia> olProv = dataUtil.getOlProvincias();
        dataUtil.obtenerTodasPersonas();
        ObservableList<Persona> olPers = dataUtil.getOlPersonas();

        inicioController.setDataUtil(dataUtil);
        inicioController.setOlProv(olProv);
        inicioController.setOlPers(olPers);
        inicioController.setRootMain(rootMain);

        Scene scene = new Scene(rootMain,800,800);
        primaryStage.setTitle("App Agenda DK Sepu");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    @Override
    public void stop() {
        System.out.println("Stop: Se cerró la app");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
