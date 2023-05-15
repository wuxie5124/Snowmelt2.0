package ML.Params;

import java.util.ArrayList;
import java.util.Arrays;

public class Colsample_bytree extends MLParam{

    @Override
    public void setValues() {
        this.values = new String[]{"Default", "0.2", "0.4", "0.6", "0.7", "0.8", "0.9", "1"};
    }

    @Override
    public void setName() {
        this.pararmName = "colsample_bytree";
    }
}
