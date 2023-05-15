package ML;

import ML.Params.N_neighbors;
import ML.Params.Weights;

import java.util.ArrayList;

public class KNN extends MachineLearn{

    @Override
    void initMLParam() {
        this.mlParams = new ArrayList<>();
        this.mlParams.add(new N_neighbors());
        this.mlParams.add(new Weights());
    }

    @Override
    void initMLName() {
        this.mlName = "KNN";
    }
}
