package sample.rules.clickers;

import sample.rules.*;

import java.io.Serializable;

public class Button implements IComponent, Serializable {

    private String id;

    public Button(String id) { this.id = id; }

    public String getId() { return id; }

    public void acceptVisitor(IVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void add(IComponent component) { }
}
