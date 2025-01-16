import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.HashMap;

public class BuyerPWDReset {

    public BuyerPWDReset() {
        JFrame frame = new JFrame("Password Reset");
        frame.setLayout(new GridBagLayout());
        GridBagConstraints buttonGbc = new GridBagConstraints();
        buttonGbc.insets = new Insets(10, 10, 10, 10);  

        JButton backButton = new JButton("<--");
        GridBagConstraints backButtonGbc = new GridBagConstraints();
        backButtonGbc.gridx = 0;
        backButtonGbc.gridy = 0;
        backButtonGbc.gridwidth = 1;
        backButtonGbc.anchor = GridBagConstraints.NORTHWEST;
        backButtonGbc.insets = new Insets(10, 10, 0, 0);
        frame.add(backButton, backButtonGbc);

        JLabel usernameLabel = new JLabel("Enter username:");
        buttonGbc.gridx = 0;
        buttonGbc.gridy = 1;
        buttonGbc.anchor = GridBagConstraints.WEST;  
        frame.add(usernameLabel, buttonGbc);

        JTextField usernameField = new JTextField(15); 
        buttonGbc.gridx = 1;
        buttonGbc.gridy = 1;
        buttonGbc.anchor = GridBagConstraints.CENTER; 
        frame.add(usernameField, buttonGbc);

        JLabel passwordLabel = new JLabel("Enter new password:");
        buttonGbc.gridx = 0;
        buttonGbc.gridy = 2;
        buttonGbc.anchor = GridBagConstraints.WEST;
        frame.add(passwordLabel, buttonGbc);

        JPasswordField passwordField = new JPasswordField(15);
        buttonGbc.gridx = 1;
        buttonGbc.gridy = 2;
        frame.add(passwordField, buttonGbc);

        JButton resetButton = new JButton("Reset");
        buttonGbc.gridx = 0;
        buttonGbc.gridy = 3;
        buttonGbc.gridwidth = 2; 
        buttonGbc.anchor = GridBagConstraints.CENTER;
        frame.add(resetButton, buttonGbc);

        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = usernameField.getText();
                String pwd = new String(passwordField.getPassword());

                if (BuyerLogin.buyer.containsKey(name)) {
                    BuyerLogin.buyer.put(name, pwd);
                    BuyerLogin.saveBuyerData();  
                    JOptionPane.showMessageDialog(frame, "PASSWORD RESET");
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(frame, "USERNAME NOT FOUND");
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new BuyerLogin();
            }
        });

        frame.setSize(400, 200);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new BuyerPWDReset();
    }
}
