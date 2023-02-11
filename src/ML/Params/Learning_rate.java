package ML.Params;

import java.util.ArrayList;
import java.util.Arrays;

public class Learning_rate extends MLParam{
    @Override
    public void setValues() {
        this.values = new String[]{"Default", "0.1", "0.2", "0.3", "0.5", "0.7", "0.9", "1"};
    }

    @Override
    public void setName() {
        this.pararmName = "learning_rate";
    }
}
