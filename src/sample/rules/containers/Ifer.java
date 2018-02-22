package sample.rules.containers;

import sample.rules.IComponent;
import sample.rules.IVisitor;
import sample.rules.checkers.ICheckable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Ifer implements IComponent, Serializable {
    private List<IComponent> IComponents= new ArrayList<>();
    private ICheckable checker;

    public Ifer(ICheckable checker){
        this.checker = checker;
    }

    @Override
    public void add(IComponent IComponent) {
        this.IComponents.add(IComponent);
    }

    @Override
    public void acceptVisitor(IVisitor visitor) {
        visitor.visit(this);
    }
}
