package org.example;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;


public class ViewUI extends JFrame {

    //Fields.
    public JButton loadBtn = new JButton("Load your TCX files");
    private JRadioButton hrButton = new JRadioButton("HR Method");;
    private JRadioButton metButton = new JRadioButton("MET Method");;
    private ButtonGroup group;

    public  JTextField weightField = new JTextField(5);
    public  JTextField ageField = new JTextField(5);
    public  JTextField sexField = new JTextField(5);
    public  JTextField goalField = new JTextField(5);

    //public JComboBox<String> calcMethod = new JComboBox<>(new String[]{"Classic Method (MET-Based)", "Advanced Method (HeartRate-Based)"});
    public JTextArea displayArea = new JTextArea(14, 40);

    private JTable Table;
    private DefaultTableModel tableModel;

    public ViewUI(){
        setTitle("Fitness Coach ULTRA ProMax Edition");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        initTable();

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Weight:"));
        inputPanel.add(weightField);

        inputPanel.add(new JLabel("Age:"));
        inputPanel.add(ageField);

        inputPanel.add(new JLabel("Sex:"));
        inputPanel.add(sexField);

        inputPanel.add(new JLabel("Daily Goal:"));
        inputPanel.add(goalField);

        group = new ButtonGroup();
        group.add(hrButton);
        group.add(metButton);
        JPanel calcPanel = new JPanel();
        calcPanel.add(hrButton);
        calcPanel.add(metButton);
        this.add(calcPanel, BorderLayout.SOUTH);

        //inputPanel.add(calcMethod);
        inputPanel.add(loadBtn);

        add(inputPanel, BorderLayout.NORTH);

        //add(new JScrollPane(displayArea), BorderLayout.CENTER);
        pack();
    }

    public boolean isHeartRateMethodSelected(){
        return hrButton.isSelected();
    }

    public void initTable() {
        String[] columns = {"Sport", "Distance (m)", "Time (min)", "Avg HR", "Calories"};
        tableModel = new DefaultTableModel(columns, 0);
        Table = new JTable(tableModel);
        add(new JScrollPane(Table), BorderLayout.CENTER);
    }

    public JButton getLoadBtn() {
        return loadBtn;
    }

    public JTextField getWeightField() {
        return weightField;
    }

    public JTextField getSexField() {
        return sexField;
    }

    public JTextField getAgeField() {
        return ageField;
    }

    public JTextField getGoalField() {
        return goalField;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }
}
