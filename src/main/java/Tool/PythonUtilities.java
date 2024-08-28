package Tool;

import ML.MachineLearn;
import ML.Params.MLParam;
import Model.ParamAndTiff;
import Model.ParamData;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PythonUtilities {
    private  static  boolean isAbsolutePath = false;
    @Deprecated
    public static ArrayList<String> runMachineLearn1(ArrayList<MachineLearn> mLearns, ArrayList<ParamData> paramDatas, String excelFilePath) {
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
            if (paramData.getCheck()) {
                strParamData += "#" + paramData.getParamName();
            }
        }
//        String pythonPath = System.getProperty("user.dir");

//        String pythonPath = "C:\\Users\\zhangjunmin\\Desktop\\snow";
        String pythonPath = "D:\\Users\\zjm\\anaconda3\\envs\\";
        JOptionPane.showMessageDialog(null, pythonPath);
        //方法是下面那个，这个已弃用
        String[] args1 = new String[]
                {pythonPath + "mlearn\\python.exe", pythonPath + "\\python\\getParamFromJAVA.py", strMachineLearns, strParamData, excelFilePath};
        try {
            Process proc = Runtime.getRuntime().exec(args1);
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream(), "GBK"));
            String line;
            while ((line = in.readLine()) != null) {
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

    public static ArrayList<String> runMachineLearn(ArrayList<MachineLearn> mLearns, ArrayList<ParamAndTiff> paramAndTiffs, String excelFilePath) {
        String strMachineLearns = "";
        String strParamData = "";
        String strTifPath = "";
        ArrayList<String> resultStr = new ArrayList<>();
        for (MachineLearn mLearn : mLearns) {
            strMachineLearns += "#" + mLearn.getMlName();
            for (MLParam mlParam : mLearn.getMLParamList()) {
                String paramName = mlParam.getParamName();
                String currentValue = mlParam.getCurrentValue();
                strMachineLearns += "%" + paramName + "&" + currentValue;
            }
        }
        for (ParamAndTiff paramAndTiff : paramAndTiffs) {
            paramAndTiff.getTiffName();
            strParamData += "#" + paramAndTiff.getParamName();
            strTifPath += "#%" + paramAndTiff.getTiffPath();
        }
        String pythonPath = System.getProperty("user.dir");
        if (isAbsolutePath) {
            pythonPath = "E:\\TeacherLiu\\snow";
        }

//        JOptionPane.showMessageDialog(null, pythonPath);
        String[] args1 = new String[]
                {pythonPath + "\\python\\Miniconda3\\python.exe", pythonPath + "\\python\\calculateTifFromJavaR.py", strMachineLearns, strParamData, strTifPath, excelFilePath};
        try {
            Process proc = Runtime.getRuntime().exec(args1);
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream(), "GBK"));
            String line;
            while ((line = in.readLine()) != null) {
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

    public static String runGridSearch(ArrayList<MachineLearn> mLearns, ArrayList<ParamData> paramDatas, String excelFilePath) {
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
            if (paramData.getCheck()) {
                strParamData += "#" + paramData.getParamName();
            }
        }
        String pythonPath = System.getProperty("user.dir");
        if (isAbsolutePath) {
            pythonPath = "E:\\TeacherLiu\\snow";
        }
        String jsonFilePath = pythonPath + "\\paramFile\\gridSearchParam.json";
//        String jsonFilePath = pythonPath + "\\paramFile\\gridSearchParam.json";
        String[] args1 = new String[]
                {pythonPath + "\\python\\Miniconda3\\python.exe", pythonPath + "\\python\\getGridSearchResultR.py", strMachineLearns, strParamData, excelFilePath, jsonFilePath};
        try {
            Process proc = Runtime.getRuntime().exec(args1);
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream(), "GBK"));
            String line;
            while ((line = in.readLine()) != null) {
                resultStr.add(line);
            }
            in.close();
            proc.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (resultStr.size() == 0){
            jsonFilePath = "";
        }
        return jsonFilePath;
    }

    public static void runPreprocess(String inputTifPath,String outTifPath,String xyPath,String extractPath) {
        ArrayList<String> resultStr = new ArrayList<>();
        String pythonPath = System.getProperty("user.dir");
        if (isAbsolutePath) {
            pythonPath = "E:\\TeacherLiu\\snow";
        }
        String[] args1 = new String[]
                {pythonPath + "\\python\\Miniconda3\\python.exe", pythonPath + "\\python\\getFeatureTable.py", inputTifPath, outTifPath, xyPath, extractPath};
        try {
            Process proc = Runtime.getRuntime().exec(args1);
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream(), "GBK"));
            String line;
            while ((line = in.readLine()) != null) {
                resultStr.add(line);
            }
            in.close();
            proc.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static Map runCorr(String extractPath) {
        String pythonPath = System.getProperty("user.dir");
        if (isAbsolutePath) {
            pythonPath = "E:\\TeacherLiu\\snow";
        }
        Map<String ,Double> corrMap = new HashMap<>();
        String[] args1 = new String[]
                {pythonPath + "\\python\\Miniconda3\\python.exe", pythonPath + "\\python\\corr.py",extractPath};
        try {
            Process proc = Runtime.getRuntime().exec(args1);
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream(), "GBK"));
            String line;
            while ((line = in.readLine()) != null) {
                String[] split = line.split("#");
                for (int i = 1; i < split.length; i++) {
                    String[] featureAndCorr = split[i].split("&");
                    if (featureAndCorr[1].equals("nan")){
                        continue;
                    }
                    corrMap.put(featureAndCorr[0],Double.valueOf(featureAndCorr[1]));
                }
            }
            in.close();
            proc.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return corrMap;
    }
}
