package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class OutputCard extends JPanel {
    private JTable Table;
    private DefaultTableModel tableModel;
    private JButton loadBtn = new JButton("Load your TCX files");
    private JButton addActivity = new JButton("Add Activity");
    private JButton showDailyAchivement = new JButton("Daily Goal Achievement");
    private JButton showVO2max = new JButton("VO2max");
    private JButton showZone = new JButton("Zone Evaluation");

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
        bottomPanel.add(showZone);
        this.add(bottomPanel, BorderLayout.SOUTH);

    }

    public JButton getShowZone() { return showZone; }

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

    public JTextPane ZonePopUpWindow(String sport, double []timestamps, double calories){
        String report = "___________________________________________________\n\n";

        report += sport + "\n\n";

        report += "Zone 1(Recovery Zone): " + timestamps[0] + "minutes\n";
        report += "Zone 2(Aerobic Zone): " + timestamps[1] + "minutes\n";
        report += "Zone 3(Tempo Zone): " + timestamps[2] + "minutes\n";
        report += "Zone 4(Performance Zone): " + timestamps[3] + "minutes\n";
        report += "Zone 5(Anaerobic Zone): " + timestamps[4] + "minutes\n\n";

        report +="Calories(zone based): " + String.format("%.2f", calories) + "kcal\n";


        JTextPane textPane = new JTextPane();
        textPane.setText(report);
        textPane.setEditable(false);
        textPane.setOpaque(false);

        JScrollPane scrollPane = new JScrollPane(textPane);
        scrollPane.setOpaque(false);
        scrollPane.setPreferredSize(new Dimension(300,200));
        scrollPane.setBorder(null);

        return textPane;
    }

}
