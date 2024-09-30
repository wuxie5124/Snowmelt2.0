package Model;

import sun.awt.geom.AreaOp;

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

    Double pearson;
    Double Spearman;
    Double Kendall;
    Double mic;

    public Double getCorr(Coefficient coefficient) {
        switch (coefficient) {
            case SPEARMAN:
                return Spearman;
            case KENDALL:
                return Kendall;
            case MIC:
                return mic;
            case PEARSON:
                return pearson;
            default:
                return 0.0;
        }
    }

    public Double getPearson() {
        return pearson;
    }

    public void setPearson(Double pearson) {
        this.pearson = pearson;
    }

    public Double getSpearman() {
        return Spearman;
    }

    public void setSpearman(Double spearman) {
        Spearman = spearman;
    }

    public Double getKendall() {
        return Kendall;
    }

    public void setKendall(Double kendall) {
        Kendall = kendall;
    }

    public Double getMic() {
        return mic;
    }

    public void setMic(Double mic) {
        this.mic = mic;
    }

    public ParamData(Boolean isCheck, String paramName) {
        this.isCheck = isCheck;
        this.paramName = paramName;
    }
}
