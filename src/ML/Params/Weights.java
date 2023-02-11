package ML.Params;

import java.util.ArrayList;
import java.util.Arrays;

public class Weights extends MLParam{

    @Override
    public void setValues() {
        this.values =new String[]{"uniform", "distance"};
    }
    public void setName() {
        this.pararmName = "weights";
    }
}
