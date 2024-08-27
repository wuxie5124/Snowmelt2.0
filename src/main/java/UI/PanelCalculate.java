package UI;

import ML.MachineLearn;
import Model.ParamAndTiff;
import Tool.PythonUtilities;

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
    /*
       方法执行
     */
     public PanelCalculate(ArrayList<MachineLearn> machineLearns, ArrayList<ParamAndTiff> paramAndTiffs, String excelFilePath) {
        this.machineLearns = machineLearns;
        this.paramAndTiffs = paramAndTiffs;
        this.excelFilePath = excelFilePath;
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
            resultStr = PythonUtilities.runMachineLearn(this.machineLearns, this.paramAndTiffs, this.excelFilePath);
            if (resultStr.size() >0){
                jTextArea.setText("模型训练结果，可以输出结果");
            }else{
                jTextArea.setText("模型训练未完成，请检查");
            }
        } else if (e.getSource() == jButton2) {
            String all = "";
            for (String s : resultStr) {
                all += s + "\n";
            }
            jTextArea.setText(all);
        }
    };
}
