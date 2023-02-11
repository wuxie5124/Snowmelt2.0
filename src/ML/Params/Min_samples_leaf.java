package ML.Params;


import java.util.ArrayList;
import java.util.Arrays;

public class Min_samples_leaf extends MLParam {

    @Override
    public void setValues() {
        this.values = new String[]{"Default", "1", "5", "10", "20", "50", "100"};
    }

    @Override
    public void setName() {
        this.pararmName = "min_samples_leaf";
    }
}
