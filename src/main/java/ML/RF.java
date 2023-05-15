package ML;

import ML.Params.*;

import java.util.ArrayList;

public class RF extends MachineLearn {

    @Override
    void initMLParam() {
        this.mlParams = new ArrayList<>();
        this.mlParams.add(new N_estimators());
        this.mlParams.add(new Min_samples_split());
        this.mlParams.add(new Min_samples_leaf());
        this.mlParams.add(new Max_depth());
        this.mlParams.add(new Max_features());
        this.mlParams.add(new Random_state());}

    @Override
    void initMLName() {
        this.mlName ="RandomForest";
    }
}
