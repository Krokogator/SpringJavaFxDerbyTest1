package sample.rules.checkers;

import java.io.Serializable;
import java.util.Random;

public class Value implements ICheckable, Serializable {

    @Override
    public boolean check() {
        Random r = new Random();
        if(r.nextDouble()>0.5){
            return true;
        }
        else {
            return false;
        }
    }
}
