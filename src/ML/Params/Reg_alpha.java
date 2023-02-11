package ML.Params;

import java.util.ArrayList;
import java.util.Arrays;

public class Reg_alpha extends MLParam{

    @Override
    public void setValues() {
        this.values =  new String[]{"Default", "0.1", "0.2", "0.3", "0.4"};
    }

    @Override
    public void setName() {
        this.pararmName = "reg_alpha";
    }
}
