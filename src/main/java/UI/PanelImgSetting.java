package UI;

import Model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class PanelImgSetting extends JPanel {
    private SnowmeltDialog dialog;
    ArrayList<ParamAndTiff> paramAndTiffs;
    ArrayList<ParamData> paramDatas;
    JTable tifTable;
    JScrollPane jScrollPane;
    DataChooseTableModel dataChooseTableModel;
    JButton jButtonRead;
    JButton jButtonOK;
    JButton jButtonClear;
    JPanel jPanelSub;

    HashMap<String, String> paramMap;

    public PanelImgSetting(SnowmeltDialog dialog) {
        this.dialog = dialog;
        this.paramAndTiffs = dialog.paramAndTiffs;
        this.paramDatas = dialog.paramData;
        this.paramMap = new HashMap<>();
        initHashMap();
        initComponent();
        initRenderAndEditor();
        initLayout();
        initActionListener();
        this.setBorder(BorderFactory.createTitledBorder("影像选择"));
    }

    private void initHashMap() {
        this.paramMap.put("Agricultur", "Agricultur");
        this.paramMap.put("Curve_numb", "Curve_numb");
        this.paramMap.put("Distance_f", "Distance_f");
        this.paramMap.put("Elevation", "Elevation");
        this.paramMap.put("GDP", "GDP");
        this.paramMap.put("landuse200", "landuse2000");
        this.paramMap.put("Maximum_sn", "Maximum_snow_depth");
        this.paramMap.put("Particle_s", "Particle_s");
        this.paramMap.put("Population", "Population");
        this.paramMap.put("Relative_E", "Relative_E");
        this.paramMap.put("Road_densi", "Road_densi");
        this.paramMap.put("Runoff_CV", "Runoff_CV");
        this.paramMap.put("SCDavg", "SCDavg");
        this.paramMap.put("SCDchanger", "SCDchangerate");
        this.paramMap.put("SCMchanger", "SCMchangerate5");
        this.paramMap.put("SCSchanger", "SCSchangerate5");
        this.paramMap.put("SDSameMont", "SDSameMonthMean01");
        this.paramMap.put("SDSameMo_1", "SDSameMonthMean02");
        this.paramMap.put("SDSameMo_2", "SDSameMonthMean03");
        this.paramMap.put("SDSameMo_3", "SDSameMonthStd01");
        this.paramMap.put("SDSameMo_4", "SDSameMonthStd02");
        this.paramMap.put("SDSameMo_5", "SDSameMonthStd03");
        this.paramMap.put("Slope", "Slope");
        this.paramMap.put("Variance_c", "Variance_c");
        this.paramMap.put("Vegetation", "Vegetation");
        this.paramMap.put("XJAVHRR_av", "XJAVHRR_av");
        this.paramMap.put("XJmonthcgr", "XJmonthcgrt1");
        this.paramMap.put("XJmonthc_1", "XJmonthcgrt2");
        this.paramMap.put("XJmonthc_2", "XJmonthcgrt3");
        this.paramMap.put("yearchange", "yearchangerate");
    }


    private void initActionListener() {
        jButtonRead.addActionListener(actionListenerPage4);
        jButtonOK.addActionListener(actionListenerPage4);
        jButtonClear.addActionListener(actionListenerPage4);
    }

    private void initLayout() {
        GridBagLayout gridBagLayout = new GridBagLayout();
        this.setLayout(gridBagLayout);
        jPanelSub.setLayout(new GridBagLayout());
        this.add(jScrollPane, new myGridBagConstraints(0, 0, 1, 4, 1, 1).setFill(GridBagConstraints.BOTH).setAnchor(GridBagConstraints.CENTER).setInset(10, 10, 0, 10));
        this.add(jPanelSub, new myGridBagConstraints(0, 4, 1, 1, 1, 1).setFill(GridBagConstraints.BOTH).setAnchor(GridBagConstraints.CENTER).setInset(10, 10, 0, 10));
        jPanelSub.add(jButtonRead, new myGridBagConstraints(0, 0, 1, 1, 1, 1).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.CENTER));
//        jPanelSub.add(jButtonOK, new myGridBagConstraints(1, 0, 1, 1, 1, 1).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.CENTER));
        jPanelSub.add(jButtonClear, new myGridBagConstraints(1, 0, 1, 1, 1, 1).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.CENTER));

    }

    private void initRenderAndEditor() {
        DefaultTableCellRenderer defaultTableCellRenderer1 = new DefaultTableCellRenderer();
        defaultTableCellRenderer1.setHorizontalAlignment(0);
        tifTable.getColumnModel().getColumn(0).setCellRenderer(defaultTableCellRenderer1);
        tifTable.getColumnModel().getColumn(1).setCellRenderer(defaultTableCellRenderer1);
        tifTable.getColumnModel().getColumn(2).setCellRenderer(defaultTableCellRenderer1);
    }

    private void initComponent() {
        jButtonRead = new JButton("读取影像文件");
        jButtonOK = new JButton("确定");
        jButtonClear = new JButton("清除选择");
        jPanelSub = new JPanel();

        tifTable = new JTable();
        jScrollPane = new JScrollPane();
        dataChooseTableModel = new DataChooseTableModel();
        dataChooseTableModel.setModelData(paramAndTiffs);
        tifTable.setModel(dataChooseTableModel);
        tifTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        tifTable.getColumnModel().getColumn(1).setPreferredWidth(200);
        tifTable.getColumnModel().getColumn(2).setPreferredWidth(200);
        jScrollPane.setViewportView(tifTable);
        jScrollPane.setPreferredSize(new Dimension(300, 300));
    }

    ActionListener actionListenerPage4 = e -> {
        if (e.getSource() == jButtonRead) {
            JFileChooser fileChoose = new JFileChooser();
            fileChoose.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            if (fileChoose.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChoose.getSelectedFile();
                String path = selectedFile.getPath();
                paramAndTiffs.clear();
                for (ParamData paramData : paramDatas) {
                    if (paramData.getCheck()) {
                        String tifName = paramMap.get(paramData.getParamName());
                        File subfile = new File(path + File.separator + tifName + ".tif");
                        if (subfile.exists()) {
                            paramAndTiffs.add(new ParamAndTiff(paramData.getParamName(), subfile.getName(), subfile.getPath()));
                        }
                    }
                }
                this.dataChooseTableModel.fireTableDataChanged();
                this.dialog.panelConsole.addText("已读取影像数据!");
            }
        } else if (e.getSource() == jButtonClear) {
            paramAndTiffs.clear();
            this.dataChooseTableModel.fireTableDataChanged();
            this.dialog.panelConsole.addText("已清除特征数据!");
        }
    };
}
