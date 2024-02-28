package UI;

import ML.MachineLearn;
import Tool.PythonUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanelCalculate extends JPanel {
    private ArrayList<MachineLearn> machineLearns;
    JButton jButton1;
    JButton jButton2;
    private JTextArea jTextField;
    private ArrayList<ParamData> featureParams;
    private String excelFilePath;
    private ArrayList<String> resultStr;
     public PanelCalculate(ArrayList<MachineLearn> machineLearns, ArrayList<ParamData> featureParams, String excelFilePath) {
        this.machineLearns = machineLearns;
        this.featureParams =featureParams;
        this.excelFilePath = excelFilePath;
        initComponent();
        initLayout();
        initListener();
    }

    private void initComponent() {
        jButton1 = new JButton("训练模型");
        jButton2 = new JButton("输出结果");
        this.jTextField = new JTextArea();
        this.jTextField.setLineWrap(true);
        this.jTextField.setFont(new java.awt.Font("宋体", Font.BOLD, 16));
    }

    private void initLayout() {
        this.setLayout(new GridBagLayout());
        this.add(jButton1, new myGridBagConstraints(0, 0, 1, 1, 1, 1).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.CENTER));
        this.add(jButton2, new myGridBagConstraints(0, 1, 1, 1, 1, 1).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.CENTER));
        this.add(jTextField, new myGridBagConstraints(1, 0, 1, 2, 1, 1).setFill(GridBagConstraints.BOTH).setAnchor(GridBagConstraints.CENTER).setInset(2,2,2,2));
    }

    private void initListener() {
        jButton1.addActionListener(actionListener);
        jButton2.addActionListener(actionListener);
    }

    ActionListener actionListener = e -> {
        if (e.getSource() == jButton1) {
            resultStr = PythonUtilities.runMachineLearn(this.machineLearns, this.featureParams, this.excelFilePath);
            jTextField.setText("模型训练完成，可以输出结果");
        } else if (e.getSource() == jButton2) {
            String all = "";
            for (String s : resultStr) {
                all += s + "\n";
            }
            jTextField.setText(all);
        }
    };
}
