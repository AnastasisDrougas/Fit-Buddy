package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FormulaCard extends JPanel{

    private JRadioButton hrButton = new JRadioButton("Advanced Method");
    private JRadioButton metButton = new JRadioButton("Classic Method");
    private ButtonGroup group;

    public FormulaCard(Font desciptionFont, Font labelFont){
        group = new ButtonGroup();
        addRadioButton(this, hrButton, metButton, group, labelFont);
        JLabel infoLabel = new JLabel("<html><body style='width: 400px; padding: 20px;'>" +
                "<b>Description:</b><br><br>" +
                "1. <b>Classic Method:</b> Standard MET values.<br>" +
                "2. <b>Advanced Method:</b> Personalised (Weight, Age, Sex). " +
                "</body></html>");
        infoLabel.setFont(desciptionFont);
        infoLabel.setBorder(new EmptyBorder(0, 40, 60, 0));
        this.add(infoLabel);
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

    public boolean isHeartRateMethodSelected(){
        return hrButton.isSelected();
    }

    public JRadioButton getHrButton() { return hrButton; }

    public JRadioButton getMetButton() { return metButton; }

}
