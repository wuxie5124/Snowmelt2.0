package ML.Params;

import java.util.ArrayList;
import java.util.Arrays;

public class Max_depth extends MLParam{
    @Override
    public void setValues() {
        this.values = new String[]{"Default", "10", "20", "30", "50", "70", "80", "100"};
    }

    @Override
    public void setName() {
        this.pararmName = "max_depth";
    }
}
