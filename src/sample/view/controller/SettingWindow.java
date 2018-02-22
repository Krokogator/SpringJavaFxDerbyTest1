package sample.view.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class SettingWindow {
    @FXML
    ListView rulesList;

    //External methods

    public void addRule(String ruleName){
        rulesList.getItems().add(ruleName);
    }

    public void editRule(int index){
        rulesList.edit(index);
    }

    public void deleteRule(int index){
        rulesList.getItems().remove(index);
    }

    //ACTIONS

    public void actionAdd(ActionEvent actionEvent) {
        addRule(displayAddPopup());
    }

    public void actionEdit(ActionEvent actionEvent) {
        editRule(0);
    }

    public void actionDelete(ActionEvent actionEvent) {
        deleteRule(rulesList.getSelectionModel().getSelectedIndex());
    }

    static String newRuleName = "";

    public static String displayAddPopup()
    {
        Stage popupwindow=new Stage();
        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("New rule");
        popupwindow.setIconified(false);

        Label l = new Label();
        l.setText("New rule name:");
        TextField field = new TextField();

        field.setOnKeyPressed(e ->{
            if(e.getCode().getName() == "Enter" && field.getText()!= ""){
                newRuleName = field.getText();
                popupwindow.close();
            }
        });

        HBox layout= new HBox(10);
        layout.getChildren().addAll(l, field);
        layout.setAlignment(Pos.CENTER);
        Scene scene1= new Scene(layout, 300, 50);
        popupwindow.setScene(scene1);
        popupwindow.showAndWait();

        return newRuleName;
    }

}
