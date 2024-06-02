package UI;

import ML.MachineLearn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
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

    public PanelModelTrain(ArrayList<MachineLearn> machineLearns, String excelFilePath) {
        this.machineLearns = machineLearns;
        this.excelFilePath = excelFilePath;
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
        buttonPanel.add(buttonSaveFile, new myGridBagConstraints(4, 0, 1, 1, 1, 1).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInset(2,2,2,2));
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

    ActionListener actionListener = e -> {
        if (e.getSource() == buttonGridSearch){

        }else if (e.getSource() == buttonCustom){

        }else if (e.getSource() == buttonChooseBest){

        }else if (e.getSource() == buttonReadFile){

        }else if (e.getSource() == buttonSaveFile){

        }
    };
}
