package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class OutputCard extends JPanel {
    private JTable Table;
    private DefaultTableModel tableModel;
    private JButton loadBtn = new JButton("Load your TCX files");

    public OutputCard(){
        JPanel tableWrapper = new JPanel(new BorderLayout());;
        initTable(tableWrapper);
        this.add(tableWrapper, BorderLayout.CENTER);

        JPanel bottomPanel3 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel3.add(loadBtn);
        this.add(bottomPanel3, BorderLayout.SOUTH);
    }

    public JButton getLoadBtn() { return loadBtn; }

    public DefaultTableModel getTableModel() { return tableModel; }

    public void initTable(JPanel panel) {
        String[] columns = {"Sport", "Distance (m)", "Time (min)", "Avg HR (bpm)","Avg Speed (km/h)", "Calories"};
        tableModel = new DefaultTableModel(columns, 0);
        Table = new JTable(tableModel);

        // Wrap table in a panel with padding
        panel.setBorder(new EmptyBorder(20, 20, 20, 20)); // padding from edges

        JScrollPane scrollPane = new JScrollPane(Table);
        scrollPane.setPreferredSize(new Dimension(500, 300)); // make table smaller if needed
        panel.add(scrollPane, BorderLayout.CENTER);
    }

}
