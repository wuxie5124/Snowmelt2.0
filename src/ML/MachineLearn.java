package ML;

import ML.Params.MLParam;

import java.util.ArrayList;

//机器学习方法
public abstract class MachineLearn {
    protected String mlName;
    protected ArrayList<MLParam> mlParams;

    ArrayList<MLParam> getMLParamList() {
        return mlParams;
    }

    ArrayList<String> getCurrentParamsValue() {
        ArrayList<String> currentParamsValue = new ArrayList<>();
        for (MLParam mlParam : mlParams) {
            currentParamsValue.add(mlParam.getCurrentValue());
        }
        return currentParamsValue;
    }
    abstract void initMLParam();

    abstract void initMLName();

    public MachineLearn() {
        initMLParam();
        initMLName();
    }


}
