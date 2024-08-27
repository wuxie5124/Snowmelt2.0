package UI;

import javax.swing.*;
import java.awt.*;

public class PanelConsole extends JPanel {
    JTextArea textArea;
    public PanelConsole() {
        this.textArea = new JTextArea();
        this.textArea.setFont(new java.awt.Font("宋体", Font.BOLD, 16));
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(this.textArea);
        scrollPane.createHorizontalScrollBar();
        scrollPane.createVerticalScrollBar();
        this.setLayout(new GridBagLayout());
//        JLabel nameLabel = new JLabel("输出：");
//        this.add(nameLabel,new myGridBagConstraints(0,0,1,1,0,0).setFill(GridBagConstraints.NONE).setInset(5,0,0,0).setAnchor(GridBagConstraints.WEST));
        this.add(scrollPane,new myGridBagConstraints(0,0,1,1,1,1).setFill(GridBagConstraints.BOTH).setInset(0,0,0,0).setAnchor(GridBagConstraints.CENTER));
        this.setBorder(BorderFactory.createTitledBorder("输出"));
    }
    public void addText(String text){
        this.textArea.append("\n" + text);
    }

    public void setText(String text){
        this.textArea.setText(text);
    }

    public void clearText(){
        this.textArea.setText("");
    }
}
