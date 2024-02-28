package Tool;

import ML.MachineLearn;
import ML.Params.MLParam;
import UI.ParamData;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class PythonUtilities {
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
        String pythonPath = System.getProperty("user.dir");

//        String pythonPath = "C:\\Users\\zhangjunmin\\Desktop\\snow";
//        JOptionPane.showMessageDialog(null, pythonPath);
        String[] args1 = new String[]
                {pythonPath + "\\python\\mlearn\\python.exe", pythonPath + "\\python\\getParamFromJAVA.py", strMachineLearns,strParamData,excelFilePath};
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
