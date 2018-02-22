package sample.rules.containers;

import sample.rules.IComponent;
import sample.rules.IVisitor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Loop implements IComponent, Serializable {
    private List<IComponent> components = new ArrayList<>();
    private int loops;

    public Loop(int loops) {
        this.loops = loops;
    }

    @Override
    public void add(IComponent component) {
        this.components.add(component);
    }

    @Override
    public void acceptVisitor(IVisitor visitor) {
        for(int i = 0; i < loops; i++){
            for (IComponent component:components) {
                component.acceptVisitor(visitor);
            }
        }
    }
}
