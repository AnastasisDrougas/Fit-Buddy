package org.example;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;


public class ViewUI extends JFrame {
    private CardLayout cardLayout = new CardLayout();
    private JPanel mainContainer;
    private WelcomCard Card0;
    private InputCard Card1;
    private FormulaCard Card2;
    private OutputCard Card3;

    private JButton StartButton = new JButton("Start");
    private JButton next1 = new JButton("Next");
    private JButton next2 = new JButton("Next");

    private Font labelFont = new Font("SansSerif", Font.BOLD, 20);
    private Font desciptionFont = new Font("SansSerif", Font.PLAIN, 15);
    private Font fieldFont = new Font("SansSerif", Font.PLAIN, 20);


    public ViewUI(){
        setTitle("Fit Buddy");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setMinimumSize(new Dimension(800, 600));

        Image HUAlogo = new ImageIcon(
                getClass().getResource("/logo.png")
        ).getImage();

        Image logo = new ImageIcon(
                getClass().getResource("/FitnessLogo.png")
        ).getImage();

        //main container that swaps views
        mainContainer = new JPanel(cardLayout);

        //CARD 0: Welcoming window
        Card0 = new WelcomCard();
        JPanel inputWrapper = new LogoPanel(logo, true);
        inputWrapper.setLayout(new BorderLayout());

        inputWrapper.add(Card0, BorderLayout.CENTER);
        Card0.setOpaque(false);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(StartButton);
        bottomPanel.setOpaque(false);
        Card0.add(bottomPanel, BorderLayout.SOUTH);


        //CARD 1: User's profile info card
        Card1 = new InputCard(labelFont, fieldFont);
        JPanel inputWrapper1 = new LogoPanel(HUAlogo);
        inputWrapper1.setLayout(new BorderLayout());

        Card1.setOpaque(false);
        inputWrapper1.add(Card1, BorderLayout.CENTER);

        JPanel bottomPanel1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel1.add(next1);
        bottomPanel1.setOpaque(true);
        inputWrapper1.add(bottomPanel1, BorderLayout.SOUTH);


        //CARD 2: formula panel
        Card2 = new FormulaCard(desciptionFont, labelFont);
        JPanel inputWrapper2 = new LogoPanel(HUAlogo);
        inputWrapper2.setLayout(new BorderLayout());

        Card2.setOpaque(false);
        inputWrapper2.add(Card2, BorderLayout.CENTER);

        JPanel bottomPanel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel2.add(next2);
        bottomPanel2.setOpaque(false);
        inputWrapper2.add(bottomPanel2, BorderLayout.SOUTH);


        //CARD 3: results
        Card3 = new OutputCard();
        JPanel inputWrapper3 = new LogoPanel(HUAlogo);
        inputWrapper3.setLayout(new BorderLayout());

        Card3.setOpaque(false);
        inputWrapper3.add(Card3, BorderLayout.CENTER);

        mainContainer.add(inputWrapper, "WELCOME");
        mainContainer.add(inputWrapper1, "PROFILE");
        mainContainer.add(inputWrapper2, "FORMULA");
        mainContainer.add(inputWrapper3, "RESULTS");
        add(mainContainer);

        showWelcome();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public JButton getStartButton() { return StartButton; }

    public JButton getNext1() { return next1; }

    public JButton getNext2() { return next2; }

    private void showWelcome() {cardLayout.show(mainContainer, "WELCOME");}

    public void showProfile() { cardLayout.show(mainContainer, "PROFILE"); }

    public void showFormula() { cardLayout.show(mainContainer, "FORMULA"); }

    public void showResults() { cardLayout.show(mainContainer, "RESULTS"); }

    public InputCard getCard1() { return Card1; }

    public FormulaCard getCard2() { return Card2; }

    public OutputCard getCard3() { return Card3; }
}
