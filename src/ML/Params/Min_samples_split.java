package ML.Params;

import java.util.ArrayList;
import java.util.Arrays;

public class Min_samples_split extends MLParam{
    @Override
    public void setValues() {
        this.values =new String[]{"Default", "2", "5", "10", "20", "50", "100"};
    }

    @Override
    public void setName() {
        this.pararmName = "min_samples_split";
    }
}
