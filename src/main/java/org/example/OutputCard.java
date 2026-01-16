package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class OutputCard extends JPanel {
    private JTable Table;
    private DefaultTableModel tableModel;
    private JButton loadBtn = new JButton("Load your TCX files");
    private JButton addActivity = new JButton("Add Activity");
    private JButton showDailyAchivement = new JButton("Daily Goal Achievement");
    private JButton showVO2max = new JButton("VO2max");

    public OutputCard(){
        this.setLayout(new BorderLayout());
        JPanel tableWrapper = new JPanel(new BorderLayout());;
        initTable(tableWrapper);
        this.add(tableWrapper, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(loadBtn);
        bottomPanel.add(addActivity);
        bottomPanel.add(showDailyAchivement);
        bottomPanel.add(showVO2max);
        this.add(bottomPanel, BorderLayout.SOUTH);

    }

    public JButton getShowVO2max() { return showVO2max; }

    public JButton getShowDailyAchivement() { return showDailyAchivement; }

    public JButton getAddActivity() { return addActivity; }

    public JButton getLoadBtn() { return loadBtn; }

    public DefaultTableModel getTableModel() { return tableModel; }

    public void initTable(JPanel panel) {
        String[] columns = {"Sport", "Distance(m)", "Time(min)", "Avg HR(bpm)","Avg Speed(km/h)","Avg Pace(min/km)","Date", "Calories"};
        tableModel = new DefaultTableModel(columns, 0);
        Table = new JTable(tableModel);

        // Wrap table in a panel with padding
        panel.setBorder(new EmptyBorder(20, 20, 20, 20)); // padding from edges

        JScrollPane scrollPane = new JScrollPane(Table);
        scrollPane.setPreferredSize(new Dimension(600, 300)); // make table smaller
        panel.add(scrollPane, BorderLayout.CENTER);
    }

}
