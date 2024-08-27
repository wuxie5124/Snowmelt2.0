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
    PanelConsole panelConsole;
    JPanel panelMethodConstruct;
    JPanel panelPreprocess;
    JPanel panelModelParamsSet;
    JPanel panelFeatureSet;

    JPanel panelModelTrain;
    JPanel panelImgSetting;
    JPanel panelCalculate;
    JLabel labelRemind;

    JMenuItem itemClassAndRegressor;
    JMenuItem itemPreprocess;
    JMenuItem itemMethodConstruct;
    JMenuItem itemModelParamsSe;
    JMenuItem itemFeatureSet;
    JMenuItem itemModelTrain;
    JMenuItem itemImgSetting;
    JMenuItem itemCalculate;
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
        this.setSize(650, 600);
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
        this.panelConsole = new PanelConsole();
        this.labelRemind = new JLabel("欢迎使用");
        this.itemClassAndRegressor = new JMenu("预测类型");
        this.itemPreprocess = new JMenuItem("预处理");
        this.itemMethodConstruct = new JMenuItem("方法构建");
        this.itemModelParamsSe = new JMenuItem("模型参数设置");
        this.itemFeatureSet = new JMenuItem("特征设置");
        this.itemModelTrain = new JMenuItem("模型参数选择");
        this.itemImgSetting = new JMenuItem("影像选择");
        this.itemCalculate = new JMenuItem("方法执行");
        this.item8 = new JMenuItem("回归");
        this.item9 = new JMenuItem("分类");

        JMenuBar menuBar = new JMenuBar();//创建菜单栏对象
        JMenu menu1 = new JMenu("融雪洪灾点预测");// 创建菜单对象

        menuBar.add(menu1);
        itemClassAndRegressor.add(item8);
        itemClassAndRegressor.add(item9);

        menu1.add(itemClassAndRegressor);
        menu1.addSeparator();//在菜单项中间添加分界线

        menu1.add(itemPreprocess);
        menu1.add(itemMethodConstruct);
        menu1.add(itemModelParamsSe);
        menu1.add(itemFeatureSet);
        menu1.add(itemModelTrain);
        menu1.add(itemImgSetting);
        menu1.add(itemCalculate);

        //
        itemMethodConstruct.setAccelerator(KeyStroke.getKeyStroke('A'));
        itemModelParamsSe.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_MASK));//添加热键
        this.setJMenuBar(   menuBar);
    }

    private void initComponentStatus() {

    }

    private void initLayout() {
        this.setLayout(new GridBagLayout());
        this.add(this.panelContent,
                new myGridBagConstraints(0, 0, 1, 1, 1, 3)
                        .setInset(5, 5, 5, 5)
                        .setAnchor(GridBagConstraints.CENTER)
                        .setFill(GridBagConstraints.BOTH));
        this.add(this.panelConsole,
                new myGridBagConstraints(0, 1, 1, 1, 1, 1)
                        .setInset(5, 5, 5, 5)
                        .setAnchor(GridBagConstraints.SOUTH)
                        .setFill(GridBagConstraints.BOTH)
        );

        this.panelContent.setLayout(new GridBagLayout());
        this.panelContent.add(this.labelRemind,
                new myGridBagConstraints(0, 0, 1, 1, 0, 0)
                        .setAnchor(GridBagConstraints.EAST));
        this.panelContent.setPreferredSize(new Dimension(600,400));
        this.panelContent.setPreferredSize(new Dimension(600,200));
    }

    private void removeEvent() {

    }

    private void addEvent() {
        itemMethodConstruct.addActionListener(menuItemAction);
        itemPreprocess.addActionListener(menuItemAction);
        itemModelParamsSe.addActionListener(menuItemAction);
        itemFeatureSet.addActionListener(menuItemAction);
        itemModelTrain.addActionListener(menuItemAction);
        itemImgSetting.addActionListener(menuItemAction);
        itemCalculate.addActionListener(menuItemAction);
    }

//    public void setCheckedParams(ArrayList<String> checkedParams) {
//        this.checkedParams = checkedParams;
//    }

    ActionListener menuItemAction = e -> {
        if (e.getSource() == itemClassAndRegressor) {

        }else if(e.getSource() == itemPreprocess){
            this.panelPreprocess = new PanelPreProcess();
            replace(this.panelContent,this.panelPreprocess);
        } else if (e.getSource() == itemMethodConstruct) {
            this.panelMethodConstruct = new PanelMethodConstruct(machineLearns);
            replace(this.panelContent, this.panelMethodConstruct);
        } else if (e.getSource() == itemModelParamsSe) {
            this.panelModelParamsSet = new PanelModelParamsSet(machineLearns);
            replace(this.panelContent, this.panelModelParamsSet);
        } else if (e.getSource() == itemFeatureSet) {
            this.panelFeatureSet = new PanelFeatureSet(this, this.paramData);
            replace(this.panelContent, this.panelFeatureSet);
        } else if(e.getSource() == itemModelTrain){
            this.panelModelTrain = new PanelModelTrain(machineLearns,this.excelFilePath,this.paramData);
            replace(this.panelContent,this.panelModelTrain);
        } else if (e.getSource() == itemImgSetting) {
            this.panelImgSetting = new PanelImgSetting(this, this.paramAndTiffs ,this.paramData);
            replace(this.panelContent, this.panelImgSetting);
        }  else if (e.getSource() == itemCalculate) {
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
                new myGridBagConstraints(0, 0, 1, 1, 1, 1)
                        .setAnchor(GridBagConstraints.CENTER));
    }

}
