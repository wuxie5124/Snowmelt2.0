package Model;

public class ParamAndTiff {
    String paramName;

    String tiffName;

    String tiffPath;

    public ParamAndTiff(String paramName, String tiffName,String tiffPath) {
        this.paramName = paramName;
        this.tiffName = tiffName;
        this.tiffPath = tiffPath;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getTiffName() {
        return tiffName;
    }

    public void setTiffName(String tiffName) {
        this.tiffName = tiffName;
    }

    public String getTiffPath() {
        return tiffPath;
    }

    public void setTiffPath(String tiffPath) {
        this.tiffPath = tiffPath;
    }
}
