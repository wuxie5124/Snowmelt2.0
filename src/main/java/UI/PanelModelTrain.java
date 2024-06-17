package UI;

import ML.*;
import ML.Params.MLParam;
import Model.ParamData;
import Tool.JsonUtilities;
import Tool.PythonUtilities;
import com.alibaba.fastjson.JSONObject;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.poi.util.StringUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;

public class PanelModelTrain extends JPanel {
    private ArrayList<MachineLearn> machineLearns;
    private JButton buttonCustom;
    private JButton buttonGridSearch;
    private JButton buttonChooseBest;
    private JButton buttonReadFile;
    private JButton buttonSaveFile;
    private JTextArea jTextField;
    private String excelFilePath;

    private ArrayList<ParamData> paramData;

    private ArrayList<MachineLearn> bestMachineLearn;

    public PanelModelTrain(ArrayList<MachineLearn> machineLearns, String excelFilePath,ArrayList<ParamData> paramData) {
        this.machineLearns = machineLearns;
        this.excelFilePath = excelFilePath;
        this.bestMachineLearn = new ArrayList<>();
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
        buttonSaveFile.addActionListener(actionListener);
    }
    private void initLayout() {
        this.setLayout(new GridBagLayout());
        this.add(jTextField, new myGridBagConstraints(0, 0, 1, 2, 1, 1).setFill(GridBagConstraints.BOTH).setAnchor(GridBagConstraints.CENTER).setInset(2,2,2,2));
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout()) ;
        buttonPanel.add(buttonGridSearch, new myGridBagConstraints(0, 0, 1, 1, 1, 1).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInset(2,2,2,2));
        buttonPanel.add(buttonCustom, new myGridBagConstraints(1, 0, 1, 1, 1, 1).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInset(2,2,2,2));
        buttonPanel.add(buttonChooseBest, new myGridBagConstraints(2, 0, 1, 1, 1, 1).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInset(2,2,2,2));
        buttonPanel.add(buttonReadFile, new myGridBagConstraints(3, 0, 1, 1, 1, 1).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInset(2,2,2,2));
        //        默认保存搜寻结果
        //        buttonPanel.add(buttonSaveFile, new myGridBagConstraints(4, 0, 1, 1, 1, 1).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInset(2,2,2,2));
        this.add(buttonPanel,new myGridBagConstraints(0, 2, 1, 1, 1, 0).setFill(GridBagConstraints.HORIZONTAL).setAnchor(GridBagConstraints.CENTER).setInset(2,2,2,2));
    }

    private void initComponent() {
        this.buttonGridSearch = new JButton("搜寻模型参数");
        this.buttonCustom = new JButton("自定义参数");
        this.buttonChooseBest = new JButton("选择最优参数");
        this.buttonReadFile = new JButton("读取文件");
        this.buttonSaveFile = new JButton("保存文件");

        this.jTextField = new JTextArea();
        this.jTextField.setLineWrap(true);
        this.jTextField.setFont(new java.awt.Font("宋体", Font.BOLD, 16));
    }

    private static String FORMATSTR = "第{0}个方法为{1},选取参数评价分为{2}，网格搜寻活动参数得分为{3} \n";

    String exportStr = "";
    ActionListener actionListener = e -> {
        if (e.getSource() == buttonGridSearch){
//            String jsonFilePath = PythonUtilities.runGridSearch(this.machineLearns, this.paramData, this.excelFilePath);
            String jsonFilePath = "C:\\Users\\zhangjunmin\\Desktop\\11\\gridSearchParam.json";
            JSONObject jsonObject = JsonUtilities.readJsonFile(jsonFilePath);
            this.exportStr = "";
            String formatStr = "";
            if (jsonObject != null){
                for (int i = 0; i < 6; i++) {
                    JSONObject machine = jsonObject.getJSONObject(Integer.toString(i));
                    if (machine == null) continue;
                    String type = machine.getString("type");
                    JSONObject bestObject = machine.getJSONObject("best");
                    JSONObject param = bestObject.getJSONObject("param");
                    MachineLearn machineLearn =null;
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
                    for (MLParam mlParam : machineLearn.getMLParamList()) {
                        String paramName = mlParam.getParamName();
                        String o;
                        if (param.get(paramName) == null){
                            o = "Default";
                        }
                        else if (param.get(paramName) instanceof BigDecimal) {
                            o = param.get(paramName).toString();
                        }else if(param.get(paramName) instanceof Integer){
                            o = String.valueOf(param.get(paramName));
                        }else{
                            o = (String)param.get(paramName);
                        }
                        mlParam.setCurrentValue(o);
                    }
                    BigDecimal scoreBest = bestObject.getBigDecimal("score");
                    BigDecimal scoreDefault = machine.getJSONObject("default").getBigDecimal("score");
                    formatStr = MessageFormat.format(FORMATSTR,i+1,type,scoreBest.toString(),scoreDefault.toString());
                    jTextField.append(formatStr);
                    bestMachineLearn.add(machineLearn);
//                    exportStr+=formatStr;
                }
            }
        }else if (e.getSource() == buttonCustom){

        }else if (e.getSource() == buttonChooseBest){

        }else if (e.getSource() == buttonReadFile){

        }else if (e.getSource() == buttonSaveFile){

        }
    };
}
