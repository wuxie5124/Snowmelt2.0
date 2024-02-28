package Tool;

import ML.MachineLearn;
import ML.Params.MLParam;
import UI.ParamData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class PythonUtilities {

    private final static String pythonPath = "D:\\Users\\zjm\\anaconda3\\envs\\mlearn\\python.exe";

    public static ArrayList<String> runMachineLearn(ArrayList<MachineLearn> mLearns, ArrayList<ParamData> paramDatas, String excelFilePath) {
        String strMachineLearns = "";
        String strParamData = "";
        ArrayList<String> resultStr = new ArrayList<>();
        for (MachineLearn mLearn : mLearns) {
            strMachineLearns += "#" + mLearn.getMlName();
            for (MLParam mlParam : mLearn.getMLParamList()) {
                String paramName = mlParam.getParamName();
                String currentValue = mlParam.getCurrentValue();
                strMachineLearns += "%" + paramName + "&" + currentValue;
            }
        }
        for (ParamData paramData : paramDatas) {
            if (paramData.getCheck()){
                strParamData+= "#" + paramData.getParamName();
            }
        }
        String[] args1 = new String[]
                {"D:\\Users\\zjm\\anaconda3\\envs\\mlearn\\python.exe", "C:\\Users\\zjm\\.spyder-py3\\mlearn\\getParamFromJAVA.py", strMachineLearns,strParamData,excelFilePath};
        try {
            Process proc = Runtime.getRuntime().exec(args1);
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream(), "GBK"));
            String line;
            while ((line = in.readLine())!=null){
                resultStr.add(line);
            }
            in.close();
            proc.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return resultStr;

    }
}
