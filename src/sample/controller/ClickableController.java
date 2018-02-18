package sample.controller;

import sample.model.Clickable;

import java.util.List;

public class ClickableController {
    private static ClickableController instance;

    //Return only one instance
    public static ClickableController getInstance(){
        if(instance == null){ instance = new ClickableController(); }
        return instance;
    }

    //Blocked access to constructor
    private ClickableController(){}

    public void add(String name, List<String> path){

    }
}
