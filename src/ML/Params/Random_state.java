package ML.Params;

import java.util.ArrayList;
import java.util.Arrays;

public class Random_state extends MLParam {

    @Override
    public void setValues() {
        this.values = new String[]{"Default", "1", "3", "5"};
    }

    @Override
    public void setName() {
        this.pararmName = "random_state";
    }
}
