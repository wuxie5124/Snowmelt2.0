package UI;

import ML.MachineLearn;
import ML.*;
import Model.ParamAndTiff;
import Model.ParamData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class SnowmeltDialog extends JFrame {

    JPanel panelContent;
    JPanel panelMethodConstruct;
    JPanel panelModelParamsSet;
    JPanel panelFeatureSet;

    JPanel panelModelTrain;
    JPanel panelImgSetting;
    JPanel panelCalculate;
    JLabel labelRemind;

    JMenuItem item1;
    JMenuItem item2;
    JMenuItem item3;
    JMenuItem item4;
    JMenuItem item5;
    JMenuItem item6;
    JMenuItem item7;
    JMenuItem item8;
    JMenuItem item9;
    public static MachineLearn[] MACHINE_LEARNS = new MachineLearn[]{new XGBoost(), new GBDT(), new SVM(), new RF(), new KNN()};
    public ArrayList<MachineLearn> machineLearns;
//    private ArrayList<String> checkedParams;
    public ArrayList<ParamData> paramData;
    public ArrayList<ParamAndTiff> paramAndTiffs;
    public String excelFilePath;

    public SnowmeltDialog() {
        initSource();
        initComponent();
        initComponentStatus();
        initLayout();
        removeEvent();
        addEvent();
        this.setSize(600, 500);
        this.setTitle("基于STACKING模型融雪洪灾预测模型");
        this.setVisible(true);
    }

    private void initSource() {
        this.machineLearns = new ArrayList<>();
        this.machineLearns.add(new XGBoost());
        this.machineLearns.add(new GBDT());
        this.machineLearns.add(new KNN());
        this.machineLearns.add(new RF());
        this.machineLearns.add(new SVM());
        this.machineLearns.add(new XGBoost());

        this.paramData = new ArrayList<>();
        this.paramAndTiffs = new ArrayList<>();
    }

    private void initComponent() {
        this.panelContent = new JPanel();
//        this.panelModelParamsSet = new JPanel();
//        this.panelFeatureSet = new JPanel();
//        this.panelCalculate = new JPanel();
        this.labelRemind = new JLabel("欢迎使用");

        this.item1 = new JMenu("预测类型");
        this.item2 = new JMenuItem("方法构建");
        this.item3 = new JMenuItem("模型参数设置");
        this.item4 = new JMenuItem("特征设置");
        this.item5 = new JMenuItem("模型参数选择");
        this.item6 = new JMenuItem("影像选择");
        this.item7 = new JMenuItem("方法执行");
        this.item8 = new JMenuItem("回归");
        this.item9 = new JMenuItem("分类");

        JMenuBar menuBar = new JMenuBar();//创建菜单栏对象
        JMenu menu1 = new JMenu("融雪洪灾点预测");// 创建菜单对象

        menuBar.add(menu1);
        item1.add(item8);
        item1.add(item9);

        menu1.add(item1);
        menu1.addSeparator();//在菜单项中间添加分界线

        menu1.add(item2);
        menu1.add(item3);
        menu1.add(item4);
        menu1.add(item5);
        menu1.add(item6);
        menu1.add(item7);

        //
        item2.setAccelerator(KeyStroke.getKeyStroke('A'));
        item3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_MASK));//添加热键
        this.setJMenuBar(menuBar);
    }

    private void initComponentStatus() {

    }

    private void initLayout() {
        this.setLayout(new GridBagLayout());
        this.add(this.panelContent,
                new myGridBagConstraints(0, 0, 1, 1, 1, 1)
                        .setInset(5, 5, 5, 5)
                        .setAnchor(GridBagConstraints.CENTER)
                        .setFill(GridBagConstraints.BOTH));
        this.panelContent.setLayout(new GridBagLayout());
        this.panelContent.add(this.labelRemind,
                new myGridBagConstraints(0, 0, 1, 1, 0, 0)
                        .setAnchor(GridBagConstraints.EAST));
    }

    private void removeEvent() {

    }

    private void addEvent() {
        item2.addActionListener(menuItemAction);
        item3.addActionListener(menuItemAction);
        item4.addActionListener(menuItemAction);
        item5.addActionListener(menuItemAction);
        item6.addActionListener(menuItemAction);
        item7.addActionListener(menuItemAction);
    }

//    public void setCheckedParams(ArrayList<String> checkedParams) {
//        this.checkedParams = checkedParams;
//    }

    ActionListener menuItemAction = e -> {
        if (e.getSource() == item1) {

        } else if (e.getSource() == item2) {
            this.panelMethodConstruct = new PanelMethodConstruct(machineLearns);
            replace(this.panelContent, this.panelMethodConstruct);
        } else if (e.getSource() == item3) {
            this.panelModelParamsSet = new PanelModelParamsSet(machineLearns);
            replace(this.panelContent, this.panelModelParamsSet);
        } else if (e.getSource() == item4) {
            this.panelFeatureSet = new PanelFeatureSet(this, this.paramData);
            replace(this.panelContent, this.panelFeatureSet);
        } else if(e.getSource() == item5){
            this.panelModelTrain = new PanelModelTrain(machineLearns,this.excelFilePath);
            replace(this.panelContent,this.panelModelTrain);
        } else if (e.getSource() == item6) {
            this.panelImgSetting = new PanelImgSetting(this, this.paramAndTiffs ,this.paramData);
            replace(this.panelContent, this.panelImgSetting);
        }  else if (e.getSource() == item7) {
            this.panelCalculate = new PanelCalculate(this.machineLearns,this.paramAndTiffs,this.excelFilePath);
            replace(this.panelContent, this.panelCalculate);
        }
        this.revalidate();
        this.repaint();
    };

    void replace(JPanel parentPanel, JComponent jComponent) {
        parentPanel.removeAll();
        parentPanel.setLayout(new GridBagLayout());
        parentPanel.add(jComponent,
                new myGridBagConstraints(0, 0, 0, 0, 1, 1)
                        .setAnchor(GridBagConstraints.CENTER));
    }

}
