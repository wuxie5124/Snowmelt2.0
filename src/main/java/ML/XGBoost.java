package ML;

import ML.Params.*;

import java.util.ArrayList;

public class XGBoost extends MachineLearn {

    @Override
    void initMLParam() {
        this.mlParams = new ArrayList<>();
        this.mlParams.add(new Learning_rate());
        this.mlParams.add(new N_estimators());
        this.mlParams.add(new Max_depth());
        this.mlParams.add(new Min_child_weight());
        this.mlParams.add(new Subsample());
        this.mlParams.add(new Colsample_bytree());
        this.mlParams.add(new Reg_alpha());
        this.mlParams.add(new Reg_lambda());
    }
    @Override
    void initMLName() {
        this.mlName = "XGBoost";
    }
}
