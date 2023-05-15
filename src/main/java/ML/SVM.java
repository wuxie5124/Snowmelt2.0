package ML;

import ML.Params.C;
import ML.Params.Kernel;

import java.util.ArrayList;

public class SVM extends MachineLearn {

    @Override
    void initMLParam() {
        this.mlParams = new ArrayList<>();
        this.mlParams.add(new C());
        this.mlParams.add(new Kernel());
    }

    @Override
    void initMLName() {
        this.mlName = "SVM";
    }
}
