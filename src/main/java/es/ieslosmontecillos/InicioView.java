package es.ieslosmontecillos;

import com.gluonhq.charm.glisten.mvc.View;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
public class InicioView {
    private InicioController inicioController = new InicioController();
    public View getView() {
        try {
            FXMLLoader loader = new
                    FXMLLoader(InicioView.class.getResource("fxml/inicio.fxml"));
            View view = loader.load();
            inicioController = loader.getController();
            return view;
        } catch (IOException e) {
            System.out.println("IOException: " + e);
            return new View();
        }
    }
    public InicioController getInicioController() {
        return inicioController;
    }

}