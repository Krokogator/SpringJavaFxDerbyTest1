package sample.view.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.w3c.dom.Element;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MainWindow implements Initializable {

    String htLink = "http://www.howrse.pl";

    @FXML
    TextField loginField,
            passwordField;
    @FXML
    WebView webPanel;

    @FXML
    ComboBox strategyComboBox;

    @FXML
    Button buttonLogin;
    @FXML
    AnchorPane anchor;

    WebEngine engine;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        engine = webPanel.getEngine();
        engine.load(htLink);

        engine.getLoadWorker().stateProperty().addListener(
                (ov, oldState, newState) -> {

                    if (newState == Worker.State.SUCCEEDED) {
                        System.out.println("Page loaded event");

                        if(engine.getLocation().equals("https://www.howrse.pl/") || engine.getLocation().equals("https://www.howrse.pl/site/logIn?publicite=1")){
                            tryDisableCookiesNotification();
                            setLoginDisabled(false);
                        }
                        System.out.println(engine.getLocation());
                    }
                });


        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "Oporządzaj",
                        "BLUPuj",
                        "Trenuj"
                );
        strategyComboBox.setItems(options);
    }

    public void login(){
        Element login = engine.getDocument().getElementById("login");
        login.setAttribute("value",loginField.getText());
        Element password = engine.getDocument().getElementById("password");
        password.setAttribute("value", passwordField.getText());
        engine.executeScript("document.getElementById('authentificationSubmit').click();");
        setLoginDisabled(true);
    }

    public void setLoginDisabled(boolean value){
        loginField.setDisable(value);
        passwordField.setDisable(value);
        buttonLogin.setDisable(value);
    }

    public void tryDisableCookiesNotification(){
        try {
            //Get rid of cookies notification
            clickButtonByClass("button button-style-4 grid-cell");
        } catch (Exception e){}
    }

    public void actionLogin(ActionEvent actionEvent) {
        login();
    }

    public void setTouchDisabled(boolean value){ webPanel.setDisable(value);}

    public String getBetweenTagsText(String elementId) throws NullPointerException{
        return engine.getDocument().getElementById(elementId).getTextContent();
    }

    public void clickSliderById(String sliderID, int value){
        engine.executeScript("document.getElementById('"+sliderID+"').getElementsByTagName('span')["+value+"].click();");
    }

    public void clickButtonById(String buttonId){
        engine.executeScript("document.getElementById('"+buttonId+"').click();");
    }

    public void clickButtonByClass(String buttonId){
        engine.executeScript("document.getElementsByClassName('"+buttonId+"')[0].click();");
    }

    public void actionTest(ActionEvent actionEvent) {
        try{

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void actionEnterPressed(KeyEvent keyEvent) {
        if(keyEvent.getCode().getName()=="Enter"){ login(); }
    }

    public void actionSettings(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/sample/view/fxml/settingWindow.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = new Stage();
            stage.setTitle("Settings");
            stage.setResizable(false);

            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)actionEvent.getTarget()).getScene().getWindow());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
        }
    }
}
