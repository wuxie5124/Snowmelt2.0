package UI;

import ML.*;
import ML.Params.MLParam;
import Model.ChooseState;
import Model.FeatureTableModel;
import Model.FileChoose;
import Model.ParamData;
import Tool.JsonUtilities;
import Tool.PythonUtilities;
import com.alibaba.fastjson.JSONObject;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;

import static Tool.ExcelUtilities.getExcelHeader;

public class PanelModelTrain extends JPanel {
    private ArrayList<MachineLearn> machineLearns;
    private JButton buttonCustom;
    private JButton buttonGridSearch;
    private JButton buttonChooseBest;
    private JButton buttonReadFile;
//    private JButton buttonSaveFile;
    private JTextArea jTextField;
    private String excelFilePath;

    private ArrayList<ParamData> paramData;

    private ArrayList<MachineLearn> bestMachineLearn;
    private ArrayList<MachineLearn> defaultMachineLearn;

    public PanelModelTrain(ArrayList<MachineLearn> machineLearns, String excelFilePath,ArrayList<ParamData> paramData) {
        this.machineLearns = machineLearns;
        this.excelFilePath = excelFilePath;
        this.bestMachineLearn = new ArrayList<>();
        this.defaultMachineLearn = new ArrayList<>();
        this.defaultMachineLearn.addAll(machineLearns);
        this.paramData = paramData;
        initComponent();
        initLayout();
        initListener();
    }

    private void initListener() {
        buttonGridSearch.addActionListener(actionListener);
        buttonCustom.addActionListener(actionListener);
        buttonChooseBest.addActionListener(actionListener);
        buttonReadFile.addActionListener(actionListener);
    }
    private void initLayout() {
        this.setLayout(new GridBagLayout());
        this.add(jTextField, new myGridBagConstraints(0, 0, 1, 2, 1, 1).setFill(GridBagConstraints.BOTH).setAnchor(GridBagConstraints.CENTER).setInset(2,2,2,2));
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout()) ;
        buttonPanel.add(buttonGridSearch, new myGridBagConstraints(0, 0, 1, 1, 1, 1).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInset(2,2,2,2));
        buttonPanel.add(buttonCustom, new myGridBagConstraints(1, 0, 1, 1, 1, 1).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInset(2,2,2,2));
        buttonPanel.add(buttonChooseBest, new myGridBagConstraints(2, 0, 1, 1, 1, 1).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInset(2,2,2,2));
//        buttonPanel.add(buttonReadFile, new myGridBagConstraints(3, 0, 1, 1, 1, 1).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInset(2,2,2,2));
        this.add(buttonPanel,new myGridBagConstraints(0, 2, 1, 1, 1, 0).setFill(GridBagConstraints.HORIZONTAL).setAnchor(GridBagConstraints.CENTER).setInset(2,2,2,2));
    }

    private void initComponent() {
        this.buttonGridSearch = new JButton("搜寻模型参数");
        this.buttonCustom = new JButton("选择自选参数");
        this.buttonChooseBest = new JButton("选择最优参数");
        this.buttonReadFile = new JButton("读取文件");
        this.jTextField = new JTextArea();
        this.jTextField.setLineWrap(true);
        this.jTextField.setFont(new java.awt.Font("宋体", Font.BOLD, 16));
    }

    private static String FORMATSTR = "第{0}个方法为{1},用户选取参数为,{2}，评分为{3};网格搜寻参数为,{4}，评分为{5} \n";
    private void outputFile(String filepath) {
        JSONObject jsonObject = JsonUtilities.readJsonFile(filepath);
        String exportStr = "";
        String formatStr = "";
        if (jsonObject != null) {
            for (int i = 0; i < 6; i++) {
                JSONObject machine = jsonObject.getJSONObject(Integer.toString(i));
                if (machine == null) continue;
                String type = machine.getString("type");
                JSONObject bestObject = machine.getJSONObject("best");
                JSONObject paramBest = bestObject.getJSONObject("param");
                MachineLearn machineLearn = null;
                if (type.equals("xgb")) {
                    machineLearn = new XGBoost();
                } else if (type.equals("gdbt")) {
                    machineLearn = new GBDT();
                } else if (type.equals("knn")) {
                    machineLearn = new KNN();
                } else if (type.equals("rf")) {
                    machineLearn = new RF();
                } else if (type.equals("svm")) {
                    machineLearn = new SVM();
                }
                String paramDestStr = "";
                String paramDefaultStr = "";
                for (MLParam mlParam : machineLearn.getMLParamList()) {
                    String paramName = mlParam.getParamName();
                    String o;
                    if (paramBest.get(paramName) == null) {
                        o = "Default";
                    } else if (paramBest.get(paramName) instanceof BigDecimal) {
                        BigDecimal o1 = (BigDecimal) paramBest.get(paramName);
                        o1.setScale(3);
                        o = o1.toString();
                    } else if (paramBest.get(paramName) instanceof Integer) {
                        o = String.valueOf(paramBest.get(paramName));
                    } else {
                        o = (String) paramBest.get(paramName);
                    }
                    paramDestStr += paramName + ":" + o + ";";
                    mlParam.setCurrentValue(o);
                }
                for (MLParam mlParam : this.machineLearns.get(i).getMLParamList()) {
                    String paramName = mlParam.getParamName();
                    String paramValue = mlParam.getCurrentValue();
                    paramDefaultStr += paramName + ":" + paramValue + ";";
                }

                BigDecimal scoreBest = bestObject.getBigDecimal("score");
                BigDecimal scoreDefault = machine.getJSONObject("default").getBigDecimal("score");

                formatStr = MessageFormat.format(FORMATSTR, i + 1, type, paramDefaultStr, scoreDefault.toString(), paramDestStr, scoreBest.toString());
                jTextField.append(formatStr);
                bestMachineLearn.add(machineLearn);
            }
        }
    }
    ActionListener actionListener = e -> {
        if (e.getSource() == buttonGridSearch){
            String jsonFilePath = PythonUtilities.runGridSearch(this.machineLearns, this.paramData, this.excelFilePath);
//            String filepath = "C:\\Users\\zhangjunmin\\Desktop\\11\\gridSearchParam.json";
            outputFile(jsonFilePath);
        }else if (e.getSource() == buttonCustom){
            this.machineLearns.clear();
            this.machineLearns.addAll(defaultMachineLearn);
        }else if (e.getSource() == buttonChooseBest){
            this.machineLearns.clear();
            this.machineLearns.addAll(bestMachineLearn);
        }else if (e.getSource() == buttonReadFile) {
            FileChoose fileChoose = new FileChoose();
            fileChoose.setFileFilter(new FileNameExtensionFilter("*.json","json"));
            if (fileChoose.getSTATE() == ChooseState.OK) {
                File selectedFile = fileChoose.getSelectedFile();
                outputFile(selectedFile.getPath());
            }
        }
    };
}
