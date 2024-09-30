package Model;

import UI.MyGridBagConstraints;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.ActionListener;

public class MTextField extends JPanel {
    JLabel label;
    JTextField textField;
    JButton button;

    public MTextField(String name) {
        this();
        this.label.setText(name);
    }

    public MTextField() {
        this.label = new JLabel();
        this.textField = new JTextField();
        this.textField.setPreferredSize(new Dimension(120, 25));
        ((AbstractDocument) textField.getDocument()).setDocumentFilter(new NumericDocumentFilter());
        this.button = new JButton();
        this.button.setPreferredSize(new Dimension(80, 25));
        this.setLayout(new GridBagLayout());
        this.add(this.label,new MyGridBagConstraints(0,0,1,1,0,1).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.EAST));
        this.add(this.textField,new MyGridBagConstraints(1,0,1,1,1,1).setFill(GridBagConstraints.HORIZONTAL).setAnchor(GridBagConstraints.EAST).setInset(0,5,0,0));
        this.add(this.button,new MyGridBagConstraints(2,0,1,1,1,0).setFill(GridBagConstraints.HORIZONTAL).setAnchor(GridBagConstraints.EAST).setInset(0,5,0,0) );
    }

    public void setLabelName(String name){
        this.label.setText(name);
    }

    public void setButtonName(String name ){
        this.button.setText(name);
    }
    public String getText() {
        return textField.getText();
    }

    public void setText(String text) {
        this.textField.setText(text);
    }
    public void addBtAcListener(ActionListener actionListener){
        this.button.addActionListener(actionListener);
    }

    class NumericDocumentFilter extends DocumentFilter {
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            if (string != null && isValidInput(fb, offset, string)) {
                super.insertString(fb, offset, string, attr);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            if (text != null && isValidInput(fb, offset, text)) {
                super.replace(fb, offset, length, text, attrs);
            }
        }

        private boolean isValidInput(FilterBypass fb, int offset, String input) throws BadLocationException {
            String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
            String newText = currentText.substring(0, offset) + input + currentText.substring(offset);

            // 检查输入是否符合要求：只允许数字和最多一个小数点
            return newText.matches("\\d*(\\.\\d*)?"); // 允许空输入
        }

        @Override
        public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
            super.remove(fb, offset, length);
        }
    }
}

