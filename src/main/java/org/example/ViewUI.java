package org.example;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;


public class ViewUI extends JFrame {
    private CardLayout cardLayout = new CardLayout();
    private JPanel mainContainer;
    private InputCard Card1;
    private FormulaCard Card2;
    private OutputCard Card3;

    private JButton next1 = new JButton("Next");
    private JButton next2 = new JButton("Next");
    private JButton addActivity = new JButton("Add Activity");

    private Font labelFont = new Font("SansSerif", Font.BOLD, 20);
    private Font desciptionFont = new Font("SansSerif", Font.PLAIN, 15);
    private Font fieldFont = new Font("SansSerif", Font.PLAIN, 20);


    public ViewUI(){
        setTitle("Fitness Buddy ULTRA ProMax Edition");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 500);
        setMinimumSize(new Dimension(600, 500));

        Image logo = new ImageIcon(
                getClass().getResource("/logo.png")
        ).getImage();

        //main container that swaps views
        mainContainer = new JPanel(cardLayout);


        //CARD 1: User's profile info card
        Card1 = new InputCard(labelFont, fieldFont);
        JPanel inputWrapper = new LogoPanel(logo);
        inputWrapper.setLayout(new BorderLayout());

        inputWrapper.add(Card1, BorderLayout.CENTER);
        Card1.setOpaque(false);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(next1);
        bottomPanel.setOpaque(true);
        inputWrapper.add(bottomPanel, BorderLayout.SOUTH);


        //CARD 2: formula panel
        Card2 = new FormulaCard(desciptionFont, labelFont);
        JPanel inputWrapper2 = new JPanel(new BorderLayout());
        inputWrapper2.add(Card2, BorderLayout.CENTER);

        JPanel bottomPanel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel2.add(next2);
        inputWrapper2.add(bottomPanel2, BorderLayout.SOUTH);


        //CARD 3: results
        Card3 = new OutputCard();
        JPanel inputWrapper3 = new JPanel(new BorderLayout());
        inputWrapper3.add(Card3, BorderLayout.CENTER);

        JPanel bottomPanel4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel4.add(addActivity);
        inputWrapper3.add(bottomPanel4, BorderLayout.SOUTH);


        mainContainer.add(inputWrapper, "PROFILE");
        mainContainer.add(inputWrapper2, "FORMULA");
        mainContainer.add(inputWrapper3, "RESULTS");
        add(mainContainer);

        showProfile();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public JButton getAddActivity() { return addActivity; }

    public JButton getNext1() { return next1; }

    public JButton getNext2() { return next2; }

    public void showProfile() { cardLayout.show(mainContainer, "PROFILE"); }

    public void showFormula() { cardLayout.show(mainContainer, "FORMULA"); }

    public void showResults() { cardLayout.show(mainContainer, "RESULTS"); }

    public InputCard getCard1() { return Card1; }

    public FormulaCard getCard2() { return Card2; }

    public OutputCard getCard3() { return Card3; }
}
