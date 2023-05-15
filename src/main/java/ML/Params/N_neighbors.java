package ML.Params;

import java.util.ArrayList;
import java.util.Arrays;

public class N_neighbors extends MLParam {

    @Override
    public void setValues() {
        this.values = new String[]{"Default", "1", "3", "5", "7", "10"};
    }

    @Override
    public void setName() {
        this.pararmName = "n_neighbors";
    }
}
