package ML.Params;

import java.util.ArrayList;
import java.util.Arrays;

public class N_estimators extends MLParam{

    @Override
    public void setValues() {
        this.values = new String[]{"Default", "50", "100", "200", "500", "1000"};
    }

    @Override
    public void setName() {
        this.pararmName = "n_estimators";
    }
}
