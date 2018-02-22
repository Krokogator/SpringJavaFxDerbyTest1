package sample.rules.containers;

import sample.rules.IComponent;
import sample.rules.IVisitor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Container implements IComponent, Serializable {
    private List<IComponent> components = new ArrayList<>();

    @Override
    public void add(IComponent component) {
        this.components.add(component);
    }

    @Override
    public void acceptVisitor(IVisitor visitor) {
        for (IComponent component : components) {
            component.acceptVisitor(visitor);
        }
    }
}
