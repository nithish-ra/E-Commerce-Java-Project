import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MainPage {

    public MainPage() {
        JFrame frame = new JFrame("Main Page");
        frame.setLayout(new GridBagLayout());
        GridBagConstraints buttonGbc = new GridBagConstraints();
        buttonGbc.insets = new Insets(10, 10, 10, 10);

        JLabel l1 = new JLabel("WELCOME TO E-COMMERCE SHOPPING APP");
        buttonGbc.gridx = 0;
        buttonGbc.gridy = 1;
        buttonGbc.gridwidth = 2;
        buttonGbc.anchor = GridBagConstraints.CENTER;
        frame.add(l1, buttonGbc);

        JButton buyerButton = new JButton("Buyer");
        JButton sellerButton = new JButton("Seller");
        JButton signUpButton = new JButton("Sign up");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(buyerButton);
        buttonPanel.add(sellerButton);
        buttonPanel.add(signUpButton);

        buttonGbc.gridy = 2;
        frame.add(buttonPanel, buttonGbc);

        buyerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new BuyerLogin();
            }
        });

        sellerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new SellerLogin();
            }
        });

        signUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new SignUp();
            }
        });

        frame.setSize(400, 300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new MainPage();
    }
}
