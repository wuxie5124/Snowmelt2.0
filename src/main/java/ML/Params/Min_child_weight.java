package ML.Params;

import java.util.ArrayList;
import java.util.Arrays;

public class Min_child_weight extends MLParam{

    @Override
    public void setValues() {
        this.values = new String[]{"Default", "1", "2", "3", "4", "5", "6"};
    }

    @Override
    public void setName() {
        this.pararmName = "min_child_weight";
    }
}
