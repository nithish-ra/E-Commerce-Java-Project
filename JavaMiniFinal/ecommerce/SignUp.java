import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.*;

public class SignUp{

    public SignUp(){
        BuyerLogin.loadBuyerData();
        SellerLogin.loadSellerData();
        JFrame frame = new JFrame("Sign up");

        frame.setLayout(new GridBagLayout());
        GridBagConstraints buttonGbc = new GridBagConstraints();
        buttonGbc.insets = new Insets(10, 10, 10, 10); 
        buttonGbc.fill = GridBagConstraints.HORIZONTAL; 

        JLabel usernameLabel = new JLabel("Enter username:");
        JLabel passwordLabel = new JLabel("Enter password:");
        JTextField usernameField = new JTextField(15);
        JPasswordField passwordField = new JPasswordField(15);

        JButton buyerButton = new JButton("Buyer");
        JButton sellerButton = new JButton("Seller");
        JButton backButton = new JButton("<--");

        buttonGbc.gridx = 0;
        buttonGbc.gridy = 1;
        buttonGbc.gridwidth = 1;
        buttonGbc.anchor = GridBagConstraints.WEST;
        frame.add(usernameLabel, buttonGbc);

        buttonGbc.gridx = 1;
        frame.add(usernameField, buttonGbc);

        buttonGbc.gridx = 0;
        buttonGbc.gridy = 2;
        frame.add(passwordLabel, buttonGbc);

        buttonGbc.gridx = 1;
        frame.add(passwordField, buttonGbc);

        buttonGbc.gridx = 0;
        buttonGbc.gridy = 3;
        buttonGbc.gridwidth = 2;
        buttonGbc.anchor = GridBagConstraints.CENTER;
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(buyerButton);
        buttonPanel.add(sellerButton);
        frame.add(buttonPanel, buttonGbc);

        GridBagConstraints backgbc = new GridBagConstraints();
        backgbc.gridx = 0;
        backgbc.gridy = 0;
        backgbc.gridwidth = 2;
        backgbc.anchor = GridBagConstraints.NORTHWEST; 
        frame.add(backButton, backgbc);

        buyerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = usernameField.getText();
                String pwd = new String(passwordField.getPassword());

                if (!BuyerLogin.buyer.containsKey(name)) {
                    BuyerLogin.buyer.put(name, pwd);  
                    BuyerLogin.saveBuyerData();
                    JOptionPane.showMessageDialog(frame, "YOUR BUYER ACCOUNT HAS BEEN REGISTERED");
                    frame.dispose();
                    new MainPage(); 
                } else {
                    JOptionPane.showMessageDialog(frame, "BUYER ALREADY EXISTS, PLEASE TRY ANOTHER USERNAME");
                }
            }
        });

        sellerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = usernameField.getText();
                String pwd = new String(passwordField.getPassword());

                if (!SellerLogin.seller.containsKey(name)) {
                    SellerLogin.seller.put(name, pwd);
                    SellerLogin.saveSellerData();  
                    JOptionPane.showMessageDialog(frame, "YOUR SELLER ACCOUNT HAS BEEN REGISTERED");
                    frame.dispose();
                    new MainPage();
                } else {
                    JOptionPane.showMessageDialog(frame, "SELLER ALREADY EXISTS, PLEASE TRY ANOTHER USERNAME");
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new MainPage();
            }
        });

        frame.setSize(400, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new SignUp();
    }
}
