package UI;

import ML.MachineLearn;
import Model.ParamAndTiff;
import Tool.PythonUtilities;
import Tool.StringUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanelCalculate extends JPanel {
    private ArrayList<MachineLearn> machineLearns;
    JButton jButton1;
    JButton jButton2;
    private JTextArea jTextArea;
    private ArrayList<ParamAndTiff> paramAndTiffs;
    private String excelFilePath;
    private ArrayList<String> resultStr;
    SnowmeltDialog dialog;

    /*
       方法执行
     */
    public PanelCalculate(SnowmeltDialog dialog) {
        this.dialog = dialog;
        this.machineLearns = dialog.machineLearns;
        this.paramAndTiffs = dialog.paramAndTiffs;
        this.excelFilePath = dialog.excelFilePath;
        initComponent();
        initLayout();
        initListener();
        this.setBorder(BorderFactory.createTitledBorder("方法执行"));
    }

    private void initComponent() {
        jButton1 = new JButton("训练模型");
        jButton2 = new JButton("输出结果");
        this.jTextArea = new JTextArea();
        this.jTextArea.setLineWrap(true);
        this.jTextArea.setFont(new java.awt.Font("宋体", Font.BOLD, 16));
    }

    private void initLayout() {
        this.setLayout(new GridBagLayout());
        this.add(jTextArea, new myGridBagConstraints(0, 0, 1, 1, 1, 1).setFill(GridBagConstraints.BOTH).setAnchor(GridBagConstraints.CENTER).setInset(10, 10, 0, 10));
        JPanel jPanelSub = new JPanel();
        jPanelSub.setLayout(new GridBagLayout());
        this.add(jPanelSub, new myGridBagConstraints(0, 1, 1, 1, 1, 0).setFill(GridBagConstraints.BOTH).setAnchor(GridBagConstraints.CENTER).setInset(10, 10, 0, 10));
        jPanelSub.add(jButton1, new myGridBagConstraints(0, 0, 1, 1, 1, 1).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.CENTER));
        jPanelSub.add(jButton2, new myGridBagConstraints(1, 0, 1, 1, 1, 1).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.CENTER));
//        jTextField.setPreferredSize(new Dimension(300, 300));
    }

    private void initListener() {
        jButton1.addActionListener(actionListener);
        jButton2.addActionListener(actionListener);
    }

    ActionListener actionListener = e -> {
        if (e.getSource() == jButton1) {
            this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
            if (this.machineLearns.size() > 0
                    && this.paramAndTiffs.size() > 0 && !StringUtilities.isEmptyOrNull(this.excelFilePath)) {
                dialog.panelConsole.addText("开始计算结果!");
                resultStr = PythonUtilities.runMachineLearn(this.machineLearns, this.paramAndTiffs, this.excelFilePath);
                if (resultStr.size() > 0) {
                    jTextArea.setText("模型训练结果，可以输出结果");
                } else {
                    jTextArea.setText("模型训练未完成，请检查");
                }
                dialog.panelConsole.addText("计算完成！");
            }else{
                if (this.machineLearns.size() == 0) {
                    dialog.panelConsole.addText("未选择机器学习方法！");
                }
                if(this.paramAndTiffs.size() == 0){
                    dialog.panelConsole.addText("未选择影像文件！");
                }
                if(StringUtilities.isEmptyOrNull(this.excelFilePath)){
                    dialog.panelConsole.addText("未选择特征文件！");
                }

            }
            this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        } else if (e.getSource() == jButton2) {
            String all = "";
            for (String s : resultStr) {
                all += s + "\n";
            }
            jTextArea.setText(all);
        }
    };
}
