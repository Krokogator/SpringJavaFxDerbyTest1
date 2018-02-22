package sample.rules.clickers;

import sample.rules.IComponent;
import sample.rules.IVisitor;

import java.io.Serializable;

public class Slider implements IComponent, Serializable{

    private String sliderId;
    private String valuePath;
    private int value;

    public Slider(String sliderId, String valuePath){
        this.sliderId = sliderId;
        this.valuePath = valuePath;
    }

    public Slider(String sliderId, int value){
        this.sliderId = sliderId;
        this.value = value;
    }

    public String getId(){return sliderId;}
    public String getValuePath(){return valuePath;}
    public int getValue(){return value; }

    @Override
    public void add(IComponent component) { }

    @Override
    public void acceptVisitor(IVisitor visitor) {
        visitor.visit(this);
    }
}
