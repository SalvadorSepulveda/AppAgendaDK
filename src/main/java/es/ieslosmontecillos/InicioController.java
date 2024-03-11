package es.ieslosmontecillos;

import com.gluonhq.charm.glisten.mvc.View;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import java.io.IOException;

public class InicioController {
    @FXML
    private Label label;
    private Pane rootMain = new Pane();
    private Pane getRootMain(){
        return rootMain;
    }

    @FXML
    public void iniciaApp(MouseEvent mouseEvent) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/login.fxml"));
            Pane rootAgendaView = fxmlLoader.load();
            rootMain.getChildren().add(rootAgendaView);
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }

    public void setRootMain(Pane rootMain) {
        this.rootMain = rootMain;
    }
}