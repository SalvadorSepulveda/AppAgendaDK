package es.ieslosmontecillos;

import com.gluonhq.charm.glisten.mvc.View;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Optional;

public class InicioController {
    @FXML
    private View inicio;
    private DataUtil dataUtil;
    private ObservableList olProv;
    private ObservableList olPers;
    private ObservableList<Usuario> olUsers;
    private Pane rootMain = new Pane();
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label labelInfo;

    private Pane getRootMain(){
        return rootMain;
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

    public void setOlUsers(ObservableList olUsers) {
        this.olUsers = olUsers;
    }

    @FXML
    public void onEnterClicked(ActionEvent actionEvent) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        Optional<Usuario> usuarioEncontrado = olUsers.stream().filter(u -> username.equals(u.getUsername()) && password.equals(u.getPassword())).findFirst();

        if (usuarioEncontrado.isPresent()) {
            try{
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/AgendaView.fxml"));
                Pane rootAgendaView = fxmlLoader.load();
                rootMain.getChildren().add(rootAgendaView);
                AgendaViewController agendaViewController = fxmlLoader.getController();
                agendaViewController.setDataUtil(dataUtil);
                agendaViewController.setOlProvincias(olProv);
                agendaViewController.setOlPersonas(olPers);
                agendaViewController.setOlUsers(olUsers);
                agendaViewController.cargarTodasPersonas();
                inicio.setVisible(false);
            } catch (IOException e) {
                System.out.println("IOException: " + e);
            }
        } else {
            labelInfo.setText("El usuario o contraseña no esta bien escrito o no existe.");
        }
    }

    @FXML
    public void onExitClicked(ActionEvent actionEvent) {
        System.out.println("Exit");
        System.exit(0);
    }
}