package UI;

import ML.MachineLearn;
import ML.Params.MLParam;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanelCalculate extends JPanel {
    private ArrayList<MachineLearn> machineLearns;
    JButton jButton1;
    JButton jButton2;
    private JTextField jTextField;
    private ArrayList<String> checkedParams;
    public PanelCalculate(ArrayList<MachineLearn> machineLearns,ArrayList<String> checkedParams) {
        this.machineLearns = machineLearns;
        this.checkedParams =checkedParams;
        initComponent();
        initLayout();
        initListener();
    }

    private void initComponent() {
        jButton1 = new JButton("训练模型");
        jButton2 = new JButton("输出结果");
        this.jTextField = new JTextField();
    }

    private void initLayout() {
        this.setLayout(new GridBagLayout());
        this.add(jButton1, new myGridBagConstraints(0, 0, 1, 1, 1, 1).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.CENTER));
        this.add(jButton2, new myGridBagConstraints(0, 1, 1, 1, 1, 1).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.CENTER));
        this.add(jTextField, new myGridBagConstraints(1, 0, 1, 2, 1, 1).setFill(GridBagConstraints.BOTH).setAnchor(GridBagConstraints.CENTER));
    }
    private void initListener() {
        jButton1.addActionListener(actionListener);
        jButton2.addActionListener(actionListener);
    }

    ActionListener actionListener = e -> {
        if (e.getSource() == jButton1) {
            for (MachineLearn machineLearn : this.machineLearns) {
                for (MLParam mlParam : machineLearn.getMLParamList()) {
                    System.out.println(mlParam.getParamName());
                }
            }
            for (String checkedParam : this.checkedParams) {
                System.out.println(checkedParam);
            }


        } else if (e.getSource() == jButton2) {

        }
    };
}
