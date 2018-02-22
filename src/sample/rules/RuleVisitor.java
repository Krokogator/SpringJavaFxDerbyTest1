package sample.rules;

import javafx.application.Platform;
import javafx.scene.web.WebEngine;
import sample.rules.clickers.Button;
import sample.rules.clickers.Slider;
import sample.rules.containers.Ifer;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class RuleVisitor implements IVisitor {

    private WebEngine engine;

    public RuleVisitor(WebEngine engine) {
        this.engine = engine;
    }

    @Override
    public void visit(Button button) {
        executeClickableButton(button.getId());
    }

    @Override
    public void visit(Slider slider) {
        if(!(slider.getValuePath()==null)){
            executeClickableSlider(slider.getId(), slider.getValuePath());
        }
        else {
            executeClickableSlider(slider.getId(), slider.getValue());
        }
    }

    @Override
    public void visit(Ifer ifer) {
    }

    private void executeClickableButton(String buttonId) {
        waitRandomTime(500, 2500);
        Platform.runLater(() -> engine.executeScript("document.getElementById('" + buttonId + "').click();"));
    }

    private void executeClickableSlider(String sliderId, int value){
        waitRandomTime(500, 2500);
        Platform.runLater(() ->engine.executeScript("document.getElementById('" + sliderId + "').getElementsByTagName('span')[" + value + "].click();"));
    }

    private void executeClickableSlider(String sliderId, String valuePath) {
        waitRandomTime(500, 2500);
        Platform.runLater(() ->engine.executeScript("document.getElementById('" + sliderId + "').getElementsByTagName('span')[" + getValue(valuePath) + "].click();"));
    }

    private int getValue(String valuePath){
        String str = (String) engine.executeScript("document." + valuePath + ".textContent");
        int res = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') continue;
            res = res * 10 + (c - '0');
        }
        return res;
    }

    private void waitRandomTime(int minMilis, int maxMilis) {
        Random r = new Random();
        try {
            TimeUnit.MILLISECONDS.sleep(minMilis + r.nextInt(maxMilis-minMilis));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
