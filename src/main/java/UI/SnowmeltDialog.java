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
    JMenuItem itemModelBuild;
    JMenuItem itemResultOutput;
//    JMenuItem item
    JMenuItem itemNorAndGridEx;
    JMenuItem itemMethodConstruct;
    JMenuItem itemModelParamsSe;
    JMenuItem itemFeatureSet;
    JMenuItem itemModelTrain;
    JMenuItem itemImgSetting;
    JMenuItem itemCalculate;
    JMenuItem itemRegressor;
    JMenuItem itemClassifier;
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
        this.setTitle("基于Stacking的气象灾害风险等级预测软件V1.0");
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
        this.itemPreprocess = new JMenu("预处理");
        this.itemModelBuild = new JMenu("模型构建");
        this.itemResultOutput = new JMenu("结果输出");

        this.itemRegressor = new JMenuItem("回归");
        this.itemClassifier = new JMenuItem("分类");

        this.itemNorAndGridEx = new JMenuItem("归一化与栅格值提取");

        this.itemMethodConstruct = new JMenuItem("方法构建");
        this.itemModelParamsSe = new JMenuItem("模型参数设置");
        this.itemFeatureSet = new JMenuItem("特征设置");
        this.itemModelTrain = new JMenuItem("模型参数选择");

        this.itemImgSetting = new JMenuItem("影像选择");
        this.itemCalculate = new JMenuItem("方法执行");

        JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.add(itemClassAndRegressor);
        jMenuBar.add(itemPreprocess);
        jMenuBar.add(itemModelBuild);
        jMenuBar.add(itemResultOutput);

        itemClassAndRegressor.add(itemRegressor);
//        itemClassAndRegressor.add(itemClassifier);

        itemPreprocess.add(itemNorAndGridEx);

        itemModelBuild.add(itemMethodConstruct);
        itemModelBuild.add(itemModelParamsSe);
        itemModelBuild.add(itemFeatureSet);
        itemModelBuild.add(itemModelTrain);

        itemResultOutput.add(itemImgSetting);
        itemResultOutput.add(itemCalculate);

        //
        itemMethodConstruct.setAccelerator(KeyStroke.getKeyStroke('A'));
        itemModelParamsSe.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_MASK));//添加热键
        this.setJMenuBar(jMenuBar);
    }

    private void initComponentStatus() {

    }

    private void initLayout() {
        this.setLayout(new GridBagLayout());
        this.add(this.panelContent,
                new MyGridBagConstraints(0, 0, 1, 1, 1, 3)
                        .setInset(5, 5, 5, 5)
                        .setAnchor(GridBagConstraints.CENTER)
                        .setFill(GridBagConstraints.BOTH));
        this.add(this.panelConsole,
                new MyGridBagConstraints(0, 1, 1, 1, 1, 1)
                        .setInset(5, 5, 5, 5)
                        .setAnchor(GridBagConstraints.SOUTH)
                        .setFill(GridBagConstraints.BOTH)
        );

        this.panelContent.setLayout(new GridBagLayout());
        this.panelContent.add(this.labelRemind,
                new MyGridBagConstraints(0, 0, 1, 1, 0, 0)
                        .setAnchor(GridBagConstraints.EAST));
        this.panelContent.setPreferredSize(new Dimension(600,350));
        this.panelConsole.setPreferredSize(new Dimension(600,100));
    }

    private void removeEvent() {

    }

    private void addEvent() {
        itemMethodConstruct.addActionListener(menuItemAction);
        itemNorAndGridEx.addActionListener(menuItemAction);
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

        }else if(e.getSource() == itemNorAndGridEx){
            this.panelPreprocess = new PanelPreProcess(this);
            replace(this.panelContent,this.panelPreprocess);
        } else if (e.getSource() == itemMethodConstruct) {
            this.panelMethodConstruct = new PanelMethodConstruct(this);
            replace(this.panelContent, this.panelMethodConstruct);
        } else if (e.getSource() == itemModelParamsSe) {
            this.panelModelParamsSet = new PanelModelParamsSet(this);
            replace(this.panelContent, this.panelModelParamsSet);
        } else if (e.getSource() == itemFeatureSet) {
            this.panelFeatureSet = new PanelFeatureSet(this);
            replace(this.panelContent, this.panelFeatureSet);
        } else if(e.getSource() == itemModelTrain){
            this.panelModelTrain = new PanelModelTrain(this);
            replace(this.panelContent,this.panelModelTrain);
        } else if (e.getSource() == itemImgSetting) {
            this.panelImgSetting = new PanelImgSetting(this);
            replace(this.panelContent, this.panelImgSetting);
        }  else if (e.getSource() == itemCalculate) {
            this.panelCalculate = new PanelCalculate(this);
            replace(this.panelContent, this.panelCalculate);
        }
        this.revalidate();
        this.repaint();
    };

    void replace(JPanel parentPanel, JComponent jComponent) {
        parentPanel.removeAll();
        parentPanel.setLayout(new GridBagLayout());
        parentPanel.add(jComponent,
                new MyGridBagConstraints(0, 0, 1, 1, 1, 1)
                        .setAnchor(GridBagConstraints.CENTER));
    }

}
