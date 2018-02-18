package sample.controller;

import javafx.util.Pair;

import java.util.List;

public class ClickableSlider {
    private static ClickableSlider instance;

    //Return only one instance
    public static ClickableSlider getInstance(){
        if(instance == null){ instance = new ClickableSlider(); }
        return instance;
    }

    //Blocked access to constructor
    private ClickableSlider(){}

    public void add(String name, List<String> path, Pair<Integer, Integer> range){
    }
}
