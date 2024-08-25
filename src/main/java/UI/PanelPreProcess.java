package UI;

import Tool.PythonUtilities;
import Tool.StringUtilities;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

public class PanelPreProcess extends JPanel {
    JFileChoosePanel inputTifFileChoosePanel;
    JFileChoosePanel outputTifFileChoosePanel;
    JFileChoosePanel xyFileChoosePanel;
    JFileChoosePanel extractPanel;
    JLabel nameLabel;
    JTextField textField;
    JButton button;

    public PanelPreProcess() {
        initComponent();
        initLayout();
        initListener();
    }

    private void initComponent() {
        this.inputTifFileChoosePanel = new JFileChoosePanel();
        this.inputTifFileChoosePanel.setButtonName("原始影像路径");
        this.inputTifFileChoosePanel.setFileChooseType(JFileChooser.DIRECTORIES_ONLY);

        this.outputTifFileChoosePanel = new JFileChoosePanel();
        this.outputTifFileChoosePanel.setButtonName("处理影像路径");
        this.outputTifFileChoosePanel.setFileChooseType(JFileChooser.DIRECTORIES_ONLY);

        this.xyFileChoosePanel = new JFileChoosePanel();
        this.xyFileChoosePanel.setButtonName("经纬度表格");
        this.xyFileChoosePanel.setFileChooseType(JFileChooser.FILES_ONLY);
        this.xyFileChoosePanel.setFilter(new FileNameExtensionFilter("*.xls,*.xlsx","xls","xlsx"));

        this.extractPanel = new JFileChoosePanel();
        this.extractPanel.setButtonName("输出表格路径");
        this.extractPanel.setFileChooseType(JFileChooser.DIRECTORIES_ONLY);

        this.nameLabel = new JLabel("名称:");
        this.textField = new JTextField();
        this.button = new JButton("数据预处理");
        this.button.setPreferredSize(new Dimension(120,30));
    }

    private void initLayout() {
        this.setLayout(new GridBagLayout());
        this.add(inputTifFileChoosePanel,new myGridBagConstraints(0,0,2,1,1,1).setFill(GridBagConstraints.BOTH).setInset(5,5,0,5));
        this.add(outputTifFileChoosePanel,new myGridBagConstraints(0,1,2,1,1,1).setFill(GridBagConstraints.BOTH).setInset(5,5,0,5));
        this.add(xyFileChoosePanel,new myGridBagConstraints(0,2,2,1,1,1).setFill(GridBagConstraints.BOTH).setInset(5,5,0,5));
        this.add(extractPanel,new myGridBagConstraints(0,3,2,1,1,1).setFill(GridBagConstraints.BOTH).setInset(5,5,0,5));
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new GridBagLayout());
        namePanel.add(nameLabel,new myGridBagConstraints(0,0,1,1,0,1).setFill(GridBagConstraints.NONE).setInset(0,0,0,0));
        namePanel.add(textField,new myGridBagConstraints(1,0,1,1,1,1).setFill(GridBagConstraints.HORIZONTAL).setInset(0,5,0,0));
        this.add(namePanel,new myGridBagConstraints(0,4,1,1,1,1).setFill(GridBagConstraints.BOTH).setInset(5,5,0,0));
        this.add(button,new myGridBagConstraints(1,4,1,0,1,1).setFill(GridBagConstraints.NONE).setInset(5,5,0,5));
    }

    private void initListener() {
        this.button.addActionListener(e->{
            String inputTifFileDirPath = inputTifFileChoosePanel.getPath();
            String outputTifFileDirPath = outputTifFileChoosePanel.getPath();
            String xypath = xyFileChoosePanel.getPath();
            String path = extractPanel.getPath();
            String name = textField.getText();
            if (StringUtilities.isEmptyOrNull(inputTifFileDirPath) ||
                    StringUtilities.isEmptyOrNull(outputTifFileDirPath) ||
                    StringUtilities.isEmptyOrNull(xypath)||
                    StringUtilities.isEmptyOrNull(path) ||
                    StringUtilities.isEmptyOrNull(name)){
                PythonUtilities.runPreprocess(inputTifFileDirPath,outputTifFileDirPath,xypath,path + File.separator + ".xlsx");
            }
        });
    }
}
