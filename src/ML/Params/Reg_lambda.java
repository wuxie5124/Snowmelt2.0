package ML.Params;

import java.util.ArrayList;
import java.util.Arrays;

public class Reg_lambda extends MLParam{

    @Override
    public void setValues() {
        this.values = new String[]{"Default", "1", "2", "4", "5", "7", "9"};
    }

    @Override
    public void setName() {
        this.pararmName = "reg_lambda";
    }
}
