package ML.Params;

import java.util.ArrayList;
import java.util.Arrays;

public class N_estimators extends MLParam {

    @Override
    public void setValues() {
        this.values = new String[]{"Default", "20", "50", "100", "120", "150", "180","200"};
    }

    @Override
    public void setName() {
        this.pararmName = "n_estimators";
    }
}
