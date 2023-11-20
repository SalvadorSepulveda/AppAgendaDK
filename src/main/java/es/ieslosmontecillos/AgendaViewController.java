package es.ieslosmontecillos;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AgendaViewController implements Initializable {
    private DataUtil dataUtil;
    private ObservableList<Provincia> olProvincias =
            FXCollections.observableArrayList();
    private ObservableList<Persona> olPersonas =
            FXCollections.observableArrayList();
    @FXML
    private TableView<Persona> tableViewAgenda;
    @FXML
    private TableColumn<Persona,String> columnNombre;
    @FXML
    private TableColumn<Persona,String> columnApellido;
    @FXML
    private TableColumn<Persona,String> columnEmail;
    @FXML
    private TableColumn<Persona,String> columnProvincia;
    @FXML
    private TextField textFieldNombre;
    @FXML
    private TextField textFieldApellido;

    private Persona personaSeleccionada;
    @FXML

    private Pane rootAgendaView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnApellido.setCellValueFactory(new
                PropertyValueFactory<>("apellidos"));
        columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        columnProvincia.setCellValueFactory(
                cellData->{
                    SimpleStringProperty property=new SimpleStringProperty();
                    if (cellData.getValue().getProvincia().getId() != 0){
                        property.setValue(cellData.getValue().getProvincia().getNombre());
                    }
                    return property;
                });
        tableViewAgenda.getSelectionModel().selectedItemProperty().addListener(
                (observable,oldValue,newValue)->{
                    personaSeleccionada=newValue;
                    if (personaSeleccionada != null){
                        textFieldNombre.setText(personaSeleccionada.getNombre());
                        textFieldApellido.setText(personaSeleccionada.getApellidos());
                    } else {
                        textFieldNombre.setText("");
                        textFieldApellido.setText("");
                    }
                });

    }

    public void setRootAgendaView(Pane rootAgendaView){
        this.rootAgendaView = rootAgendaView;
    }

    public void setDataUtil(DataUtil dataUtil) {
        this.dataUtil = dataUtil;
    }

    public void setOlProvincias(ObservableList<Provincia> olProvincias) {
        this.olProvincias = olProvincias;
    }

    public void setOlPersonas(ObservableList<Persona> olPersonas) {
        this.olPersonas = olPersonas;
    }



    public void cargarTodasPersonas() {
        tableViewAgenda.setItems(FXCollections.observableArrayList(olPersonas));
    }

    public void onActionButtonGuardar(ActionEvent actionEvent) {
        if (personaSeleccionada != null) {
            personaSeleccionada.setNombre(textFieldNombre.getText());
            personaSeleccionada.setApellidos(textFieldApellido.getText());
            dataUtil.actualizarPersona(personaSeleccionada);
            int numFilaSeleccionada = tableViewAgenda.getSelectionModel().getSelectedIndex();
            tableViewAgenda.getItems().set(numFilaSeleccionada, personaSeleccionada);
            TablePosition pos = new TablePosition(tableViewAgenda, numFilaSeleccionada, null);
            tableViewAgenda.getFocusModel().focus(pos);
            tableViewAgenda.requestFocus();
        }
    }

    public void onActionButtonNuevo(ActionEvent event){
        try{
            FXMLLoader fxmlLoader = new
                    FXMLLoader(getClass().getResource("fxml/PersonaDetalleView.fxml"));
            Parent rootDetalleView=fxmlLoader.load();

            PersonaDetalleViewController personaDetalleViewController =
                    (PersonaDetalleViewController) fxmlLoader.getController();
            personaDetalleViewController.setRootAgendaView(rootAgendaView);

            rootAgendaView.setVisible(false);

            StackPane rootMain =
                    (StackPane) rootAgendaView.getScene().getRoot();
            rootMain.getChildren().add(rootDetalleView);
            personaSeleccionada = new Persona();
            personaDetalleViewController.setPersona(personaSeleccionada,true);
            personaDetalleViewController.mostrarDatos();
        } catch (IOException ex){
            System.out.println("Error volcado"+ex);
        }
    }


    public void onActionButtonEditar(ActionEvent actionEvent) {
        try{
            FXMLLoader fxmlLoader = new
                    FXMLLoader(getClass().getResource("fxml/PersonaDetalleView.fxml"));
            Parent rootDetalleView=fxmlLoader.load();

            PersonaDetalleViewController personaDetalleViewController =
                    (PersonaDetalleViewController) fxmlLoader.getController();
            personaDetalleViewController.setRootAgendaView(rootAgendaView);

            rootAgendaView.setVisible(false);

            StackPane rootMain =
                    (StackPane) rootAgendaView.getScene().getRoot();
            rootMain.getChildren().add(rootDetalleView);
            personaDetalleViewController.setPersona(personaSeleccionada,false);
            personaDetalleViewController.mostrarDatos();
        } catch (IOException ex){
            System.out.println("Error volcado"+ex);
        }
    }


    public void onActionButtonSuprimir(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar");
        alert.setHeaderText("¿Desea suprimir el siguiente registro?");
        alert.setContentText(personaSeleccionada.getNombre() + " "
                + personaSeleccionada.getApellidos());
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            // Acciones a realizar si el usuario acepta
            dataUtil.eliminarPersona(personaSeleccionada);
            tableViewAgenda.getItems().remove(personaSeleccionada);
            tableViewAgenda.getFocusModel().focus(null);
            tableViewAgenda.requestFocus();
        } else {
            // Acciones a realizar si el usuario cancela
            int numFilaSeleccionada=
                    tableViewAgenda.getSelectionModel().getSelectedIndex();
            tableViewAgenda.getItems().set(numFilaSeleccionada,personaSeleccionada);
            TablePosition pos = new TablePosition(tableViewAgenda,
                    numFilaSeleccionada,null);
            tableViewAgenda.getFocusModel().focus(pos);
            tableViewAgenda.requestFocus();
        }

    }
}