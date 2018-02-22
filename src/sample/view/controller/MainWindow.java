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
import javafx.scene.layout.StackPane;
import javafx.scene.web.PopupFeatures;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import org.w3c.dom.Element;
import sample.model.Rule;
import sample.rules.IComponent;
import sample.rules.RuleVisitor;
import sample.rules.Rules;
import sample.rules.clickers.Slider;
import sample.rules.containers.Container;
import sample.rules.containers.Loop;
import sample.rules.containers.RandomContainer;
import sample.serializable.DeserializeFromXML;
import sample.serializable.SerializeToXML;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
        engine.setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");
        System.out.println(engine.getUserAgent());
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

        //initPopupHandler();
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
        //loop.add(new sample.rules.clickers.Button("feed-button"));
        //loop.add(new sample.rules.clickers.Button("boutonPanser"));
        //loop.add(new sample.rules.clickers.Button("boutonCoucher"));

        Rules rulesController = new Rules();
        List rules = new ArrayList();
        Container mainContainer = new Container();

            RandomContainer randomContainer = new RandomContainer();
                Container container1 = new Container();
                container1.add(new sample.rules.clickers.Button("boutonNourrir"));
                container1.add(new Slider("haySlider", "getElementsByClassName('section-fourrage-target')[0]"));
            randomContainer.add(container1);
                Container container2 = new Container();
                container2.add(new sample.rules.clickers.Button("boutonBalade-foret"));
            randomContainer.add(container2);

        mainContainer.add(randomContainer);

        mainContainer.add(new sample.rules.clickers.Button("nav-next"));
        rules.add(mainContainer);
        rulesController.setComponents(rules);

        //rulesController.save("rules");
        //rulesController.load("rules");

        rules = rulesController.getComponents();

        RuleVisitor visitor = new RuleVisitor(engine);

        for (IComponent kompot: (List<IComponent>)rules) {
            new Thread(() -> {
                kompot.acceptVisitor(visitor );
            }).start();
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

    private void initPopupHandler(){
        WebView wv = new WebView();
        wv.getEngine().setCreatePopupHandler(new Callback<PopupFeatures, WebEngine>() {

            @Override
            public WebEngine call(PopupFeatures p) {
                Stage stage = new Stage(StageStyle.UTILITY);
                WebView wv2 = new WebView();
                stage.setScene(new Scene(wv2));
                stage.show();
                return wv2.getEngine();
            }
        });


        StackPane root = new StackPane();
        root.getChildren().add(wv);

        Scene scene = new Scene(root, 300, 250);
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
        wv.getEngine().load("http://www.google.pl");
    }
}
