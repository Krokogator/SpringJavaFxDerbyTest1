package sample.view.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class AddRuleWindow {
    private SettingWindow window;

    public AddRuleWindow(SettingWindow window){
        this.window = window;
    }

    @FXML
    TextField loginField;

    public void actionEnterPressed(KeyEvent keyEvent) {
        if(keyEvent.getCode().getName()=="Enter"){
            String t;
            if((t = loginField.getText())!=""){
                window.addRule(t);
            }
        }
    }
}
