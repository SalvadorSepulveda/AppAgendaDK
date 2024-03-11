package es.ieslosmontecillos;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import com.gluonhq.charm.glisten.mvc.View;
import java.io.IOException;
public class AppAgendaDK extends Application {
    private DataUtil dataUtil;
    private InicioView inicioView = new InicioView();
    private InicioController inicioController;
    private LoginController loginController;

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
//        inicioController.setDataUtil(dataUtil);
//        inicioController.setOlProv(olProv);
//        inicioController.setOlPers(olPers);
        inicioController.setRootMain(rootMain);
        Scene scene = new Scene(rootMain,800,800);
        Image icon = new Image(getClass().getResourceAsStream("img/agenda.png"));
        primaryStage.getIcons().add(icon);
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
