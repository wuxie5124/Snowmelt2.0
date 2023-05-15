package ML.Params;

import java.util.ArrayList;
import java.util.Arrays;

public class Kernel extends MLParam{
    @Override
    public void setValues() {
        this.values = new String[]{"rbf", "linear", "poly", "sigmoid", "precomputed"};
    }

    @Override
    public void setName() {
        this.pararmName= "kernel";
    }
}
