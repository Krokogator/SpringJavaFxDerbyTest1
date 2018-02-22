package sample.rules;

import sample.rules.clickers.Button;
import sample.rules.clickers.Slider;
import sample.rules.containers.Ifer;

public interface IVisitor {
    void visit(Button button);
    void visit(Slider slider);
    void visit(Ifer ifer);
}
