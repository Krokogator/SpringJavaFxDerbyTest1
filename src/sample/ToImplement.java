package sample;

import javafx.scene.web.WebEngine;

public class ToImplement {
    WebEngine engine;
    public void feed(int value){
        engine.executeScript("document.getElementById('haySlider').getElementsByTagName('span')["+value+"].click();");
    }

    public void play(int value){
        engine.executeScript("document.getElementById('centerPlaySlider').getElementsByTagName('span')["+value+"].click();");
    }

    public void otherStuff(){

        //System.out.println("Udało się: "+engine.executeScript("document.getElementById(\"centerPlaySlider\").getElementsByTagName(\"span\")[10].textContent"));
        //System.out.println("Udało się: "+engine.executeScript("document.getElementsByClassName('section-fourrage-target')[0].textContent"));

        //System.out.println(getBetweenTagsText("energie"));
        //System.out.println(getBetweenTagsText("sante"));
        //System.out.println(getBetweenTagsText("moral"));
    }
}
