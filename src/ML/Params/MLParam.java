package ML.Params;

//机器学习方法的参数
public abstract class MLParam {

    protected String pararmName;

    protected String[] values;

    public void setCurrentValue(String currentValue) {
        this.currentValue = currentValue;
    }

    public String getParamName() {
        return pararmName;
    }

    protected String currentValue;

    public String[] getValues() {
        return values;
    }

    public String getCurrentValue() {
        return currentValue;
    }

    public abstract void setValues();

    public abstract void setName();

    public MLParam() {
        setValues();
        setName();
        initValues();
    }

    private void initValues() {
        this.currentValue = values[0];
    }
}
