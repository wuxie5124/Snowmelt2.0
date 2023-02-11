package ML.Params;

import java.util.ArrayList;
import java.util.Arrays;

public class Max_features extends MLParam{


    @Override
    public void setValues() {
        this.values = new String[]{"Default", "sqrt", "log2", "1", "3", "5", "7", "9", "11"};
    }

    @Override
    public void setName() {
        this.pararmName = "max_features";
    }
}
