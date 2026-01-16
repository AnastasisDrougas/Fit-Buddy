package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class InputCard extends JPanel {

    private  JTextField weightField = new JTextField(5);
    private  JTextField ageField = new JTextField(5);
    private  JTextField goalField = new JTextField(5);

    private JRadioButton male = new JRadioButton("Male");
    private JRadioButton female = new JRadioButton("Female");
    private ButtonGroup group2;

    public InputCard(Font labelFont, Font fieldFont){
        this.setLayout(new GridLayout(0, 2, 10, 10));

        JLabel sexLabel = new JLabel("Sex:");
        sexLabel.setFont(labelFont);
        sexLabel.setBorder(new EmptyBorder(0, 40, 60, 0));
        this.add(sexLabel);
        group2 = new ButtonGroup();
        addRadioButton(this, male, female, group2, labelFont);

        addInputRow(this, "Weight:", weightField, 5, labelFont, fieldFont);
        addInputRow(this, "Age:", ageField, 5, labelFont, fieldFont);
        addInputRow(this, "Set Daily Goal:", goalField, 5, labelFont, fieldFont);

    }

    private void addInputRow(JPanel panel, String labelText, JTextField field, int columns, Font labelFont, Font fieldFont) {
        JLabel label = new JLabel(labelText);
        label.setFont(labelFont);

        field.setFont(fieldFont);
        field.setColumns(columns);

        panel.add(label);

        label.setBorder(new EmptyBorder(0, 40, 60, 0));

        JPanel fieldWrapper = new JPanel(new FlowLayout(FlowLayout.LEFT));
        fieldWrapper.add(field);
        fieldWrapper.setOpaque(false);

        panel.add(fieldWrapper);
    }

    private void addRadioButton(JPanel panel, JRadioButton btn1, JRadioButton btn2, ButtonGroup group, Font font){
        btn1.setFont(font);
        btn2.setFont(font);
        btn1.setOpaque(false);
        btn2.setOpaque(false);

        group.add(btn1);
        group.add(btn2);

        JPanel wrapper = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 0));
        wrapper.setBorder(new EmptyBorder(0, 0, 0, 50));
        wrapper.setOpaque(false);
        wrapper.add(btn1);
        wrapper.add(btn2);

        panel.add(wrapper);
    }

    public JTextField getWeightField() { return weightField; }

    public String getSexField() {
        if(male.isSelected()){
            return "m";
        } else {
            return "f";
        }
    }

    public JTextField getAgeField() { return ageField; }

    public JTextField getGoalField() { return goalField; }

    public JRadioButton getMale() { return male; }

    public JRadioButton getFemale() { return female; }
}
