import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class BuyerLogin extends JFrame{
    public static final String buyerfile = "buyerCred.txt";
    public static final String productfile = "productfile.txt";
    public static HashMap<String, String> buyer = new HashMap<>();

    public BuyerLogin() {
        loadBuyerData();
        JFrame frame = new JFrame("Buyer Login");
        frame.setLayout(new GridBagLayout());
        GridBagConstraints buttonGbc = new GridBagConstraints();
        buttonGbc.insets = new Insets(10, 10, 10, 10); 

        JButton backButton = new JButton("<--");
        GridBagConstraints backButtonGbc = new GridBagConstraints();
        backButtonGbc.gridx = 0;
        backButtonGbc.gridy = 0;
        backButtonGbc.gridwidth = 2;
        backButtonGbc.anchor = GridBagConstraints.NORTHWEST;
        frame.add(backButton, backButtonGbc);

        JLabel usernameLabel = new JLabel("USERNAME");
        JTextField usernameField = new JTextField(15);

        buttonGbc.gridx = 0;
        buttonGbc.gridy = 1;
        buttonGbc.anchor = GridBagConstraints.WEST;
        frame.add(usernameLabel, buttonGbc);

        buttonGbc.gridx = 1;
        frame.add(usernameField, buttonGbc);

        JLabel passwordLabel = new JLabel("PASSWORD");
        JPasswordField passwordField = new JPasswordField(15);

        buttonGbc.gridx = 0;
        buttonGbc.gridy = 2;
        frame.add(passwordLabel, buttonGbc);

        buttonGbc.gridx = 1;
        frame.add(passwordField, buttonGbc);

        JButton loginButton = new JButton("Login");
        JButton forgotPWButton = new JButton("Forgot Password");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(loginButton);
        buttonPanel.add(forgotPWButton);

        buttonGbc.gridx = 0;
        buttonGbc.gridy = 3;
        buttonGbc.gridwidth = 2;
        frame.add(buttonPanel, buttonGbc);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String uname = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (buyer.containsKey(uname) && buyer.get(uname).equals(password)) {
                    JOptionPane.showMessageDialog(frame, "LOGIN SUCCESSFUL");
                    new BuyerHomePage();
                } 
                else {
                    JOptionPane.showMessageDialog(frame, "INVALID LOGIN");
                }
            }
        });

        forgotPWButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new BuyerPWDReset();
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new MainPage();
            }
        });

        saveBuyerData();
        frame.setSize(400, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void loadBuyerData(){
        try(BufferedReader breader = new BufferedReader(new FileReader(buyerfile))){
            String line;
            while((line=breader.readLine())!=null){
                String[] parts = line.split(",");
                if(parts.length==2){
                    buyer.put(parts[0],parts[1]);
                }
            }
        } catch(IOException e){
            System.out.println("Error reading data : " + e.getMessage());
        }
    }

    public static void saveBuyerData(){
        try(BufferedWriter bwriter = new BufferedWriter(new FileWriter(buyerfile))){
            for(Map.Entry<String,String> entry : buyer.entrySet()){
                bwriter.write(entry.getKey()+","+entry.getValue());
                bwriter.newLine();
            }
        } catch(IOException e){
            System.out.println("Error updating data : "+ e.getMessage());
        }
    }

    public static java.util.List<Product> fetchAvailablebProducts() {
        java.util.List<Product> products = new ArrayList<>();
        try(BufferedReader prodReader = new BufferedReader(new FileReader(productfile))){
            String prod;
            while((prod=prodReader.readLine())!=null){
                String[] details = prod.split(",");
                if(details.length==6){
                    int id = Integer.parseInt(details[0]);
                    double price = Double.parseDouble(details[2]);
                    double rating = Double.parseDouble(details[3]);
                    int availqty = Integer.parseInt(details[4]);
                    int soldqty = Integer.parseInt(details[5]);
                    products.add(new Product(id,details[1],price,rating,availqty,soldqty));
                }
            }
        }catch(IOException e){
            System.out.println("Error reading file : "+e.getMessage());
        }
        /*products.add(new Product(1, "Wireless Earbuds", 59.99, 4.2, 50, 25));
        products.add(new Product(2, "Smartphone Stand", 15.99, 4.7, 150, 60));
        products.add(new Product(3, "Portable Charger", 29.99, 4.5, 100, 40));
        products.add(new Product(4, "Bluetooth Speaker", 79.99, 4.3, 30, 20));
        products.add(new Product(5, "LED Desk Lamp", 22.49, 4.6, 75, 55));
        products.add(new Product(6, "USB-C Cable", 8.99, 4.1, 200, 120));
        products.add(new Product(7, "Laptop Cooling Pad", 25.99, 4.0, 45, 30));
        products.add(new Product(8, "Smart Watch", 199.99, 4.8, 20, 15));
        products.add(new Product(9, "Wireless Keyboard", 34.99, 4.4, 60, 35));
        products.add(new Product(10, "Gaming Mouse", 49.99, 4.9, 40, 28));
        */
        return products;
        
    }

    public static void saveAvailablebProducts(){
        try(BufferedWriter prodWriter = new BufferedWriter(new FileWriter(productfile))){
            for(Product product : BuyerHomePage.buyerproducts){
                int id = product.getId();
                String name = product.getName();
                double price = product.getPrice();
                double rating = product.getRating();
                int availqty = product.getAvailableQuantity();
                int soldqty = product.getSoldQuantity();
                prodWriter.write(id+","+name+","+price+","+rating+","+availqty+","+soldqty);
                prodWriter.newLine();
            }
        }catch(IOException e){
            System.out.println("Error occured : "+e.getMessage());
        }
    }


    public static void main(String[] args) {
        new BuyerLogin();
    }
}
