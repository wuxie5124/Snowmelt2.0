package UI;

public class ParamData {
    public Boolean getCheck() {
        return isCheck;
    }

    public void setCheck(Boolean check) {
        isCheck = check;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    Boolean isCheck;
    String paramName;

    public ParamData(Boolean isCheck, String paramName) {
        this.isCheck = isCheck;
        this.paramName = paramName;
    }
}
