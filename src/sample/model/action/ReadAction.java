package sample.model.action;

public class ReadAction implements IAction {
    @Override
    public String getActionType() {
        return "read";
    }
}
