package es.ieslosmontecillos;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class LoginController {
    private DataUtil dataUtil;
    private ObservableList olProv;
    private ObservableList olPers;
    private Pane rootMain = new Pane();

    @javafx.fxml.FXML
    private TextField nameField;
    @javafx.fxml.FXML
    private PasswordField passwordField;

    @javafx.fxml.FXML
    public void onEnterClicked(ActionEvent actionEvent) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/login.fxml"));
            Pane rootAgendaView = fxmlLoader.load();
            rootMain.getChildren().add(rootAgendaView);
//            AgendaViewController agendaViewController = fxmlLoader.getController();
//            agendaViewController.setDataUtil(dataUtil);
//            agendaViewController.setOlProvincias(olProv);
//            agendaViewController.setOlPersonas(olPers);
//            agendaViewController.cargarTodasPersonas();
            System.out.println("Click");
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }

    @javafx.fxml.FXML
    public void onExitClicked(ActionEvent actionEvent) {
    }

    public void setRootMain(Pane rootMain) {
        this.rootMain = rootMain;
    }

    public void setDataUtil(DataUtil dataUtil) {
        this.dataUtil = dataUtil;
    }

    public void setOlProv(ObservableList olProv) {
        this.olProv = olProv;
    }

    public void setOlPers(ObservableList olPers) {
        this.olPers = olPers;
    }
}
