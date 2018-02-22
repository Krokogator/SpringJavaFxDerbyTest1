package sample.rules;

import sample.serializable.DeserializeFromXML;
import sample.serializable.SerializeToXML;

import java.io.Serializable;
import java.util.List;

public class Rules implements Serializable{
    private List components;
    public Rules(){}

    public List getComponents(){return components;}

    public void setComponents(List components){this.components = components;}

    public void save(String filename){
        SerializeToXML.serialize(this, filename);
    }

    public void load(String filename){
        Rules rules = (Rules) DeserializeFromXML.deserialize(filename);
        setComponents(rules.getComponents());
    }
}
