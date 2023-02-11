package ML.Params;

import java.util.ArrayList;
import java.util.Arrays;

public class C extends MLParam{

    @Override
    public void setValues() {
        this.values = new String[]{"Default", "0.1", "0.5", "10"};
    }
    public void setName() {
        this.pararmName = "C";
    }
}
