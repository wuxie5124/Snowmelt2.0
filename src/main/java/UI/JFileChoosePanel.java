package UI;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;

public class JFileChoosePanel extends JPanel {
    JTextField textField;
    JButton button;
    JFileChooser fileChooser;

    public JFileChoosePanel() {
        initComponent();
        initLayout();
        initListener();
    }

    private void initComponent() {
        this.textField = new JTextField();
        this.button = new JButton("读取");
        this.button.setPreferredSize(new Dimension(120,30));
        this.fileChooser = new JFileChooser();
    }

    private void initLayout() {
        this.setLayout(new GridBagLayout());
        this.add(textField,new myGridBagConstraints(0,0,1,1,1,0).setFill(GridBagConstraints.HORIZONTAL).setInset(0,0,0,0).setAnchor(GridBagConstraints.WEST));
        this.add(button,new myGridBagConstraints(1,0,1,1,1,0).setFill(GridBagConstraints.NONE).setInset(0,5,0,0).setAnchor(GridBagConstraints.EAST));
    }

    public void setButtonName(String name){
        button.setText(name);
    }

    public void setFileChooseType(int mode){
        fileChooser.setFileSelectionMode(mode);
    }

    public void setFilter(FileFilter fileFilter){
        fileChooser.setFileFilter(fileFilter);
    }
    private void initListener() {
        button.addActionListener(actionListener);
    }
    ActionListener actionListener = e -> {
        if (e.getSource() == button) {
            if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                String path = selectedFile.getPath();
                this.textField.setText(path);
            }
        }
    };

    public String getPath(){
        return textField.getText();
    }
}
