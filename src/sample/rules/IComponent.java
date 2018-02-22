package sample.rules;

public interface IComponent {
    void add(IComponent IComponent);
    void acceptVisitor(IVisitor visitor);
}
