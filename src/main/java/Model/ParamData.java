package Model;

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

    Double corr;

    public Double getCorr() {
        return corr;
    }

    public void setCorr(Double corr) {
        this.corr = corr;
    }

    public ParamData(Boolean isCheck, String paramName) {
        this.isCheck = isCheck;
        this.paramName = paramName;
    }
}
