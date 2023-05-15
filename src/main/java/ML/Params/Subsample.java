package ML.Params;

import java.util.ArrayList;
import java.util.Arrays;

public class Subsample extends MLParam {

    @Override
    public void setValues() {
        this.values = new String[]{"Default", "0.1", "0.2", "0.4", "0.5", "0.7", "0.8"};
    }
    public void setName() {
        this.pararmName = "subsample";
    }
}
