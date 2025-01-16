import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.swing.*;

public class SellerHomePage extends JFrame {
    public static List<Product> sproducts;
    private DefaultListModel<String> productListModel;
    private JList<String> productList;

    public SellerHomePage(List<Product> sproducts) {
        this.sproducts = sproducts;
        this.productListModel = new DefaultListModel<>();
        
        setTitle("Seller Home Page");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel productPanel = new JPanel(new BorderLayout());
        JLabel productLabel = new JLabel("Your Products:");
        productList = new JList<>(productListModel);
        JScrollPane productScrollPane = new JScrollPane(productList);
        productPanel.add(productLabel, BorderLayout.NORTH);
        productPanel.add(productScrollPane, BorderLayout.CENTER);
        
        JPanel controlPanel = new JPanel(new GridLayout(5, 2));
        controlPanel.add(new JLabel("ID:"));
        JTextField idField = new JTextField();
        controlPanel.add(idField);

        controlPanel.add(new JLabel("Name:"));
        JTextField nameField = new JTextField();
        controlPanel.add(nameField);

        controlPanel.add(new JLabel("Price:"));
        JTextField priceField = new JTextField();
        controlPanel.add(priceField);

        controlPanel.add(new JLabel("Rating:"));
        JTextField ratingField = new JTextField();
        controlPanel.add(ratingField);

        controlPanel.add(new JLabel("Available Quantity:"));
        JTextField quantityField = new JTextField();
        controlPanel.add(quantityField);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Product");
        JButton deleteButton = new JButton("Delete Product");
        JButton updateButton = new JButton("Update Product");
        JButton sortBySoldButton = new JButton("Sort by Most Sold");
        JButton logoutButton = new JButton("Logout");
        
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(sortBySoldButton);
        buttonPanel.add(logoutButton);

        add(productPanel, BorderLayout.WEST);
        add(controlPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> addProduct(idField, nameField, priceField, ratingField, quantityField));
        deleteButton.addActionListener(e -> deleteProduct(idField));
        updateButton.addActionListener(e -> updateProduct(idField, nameField, priceField, ratingField, quantityField));
        sortBySoldButton.addActionListener(e -> {
            sortProductsByMostSold();
            loadProductList();
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SellerLogin(); 
                dispose(); 
            }
        });

        setVisible(true);
    }

    private void loadProductList() {
        productListModel.clear();
        for (Product product : sproducts) {
            productListModel.addElement("ID: " + product.getId() + ", Name: " + product.getName() + ", Price: " + product.getPrice() + ", Sold: " + product.getSoldQuantity());
        }
    }

    private void addProduct(JTextField idField, JTextField nameField, JTextField priceField, JTextField ratingField, JTextField quantityField) {
        int id = Integer.parseInt(idField.getText());
        String name = nameField.getText();
        double price = Double.parseDouble(priceField.getText());
        double rating = Double.parseDouble(ratingField.getText());
        int quantity = Integer.parseInt(quantityField.getText());

        sproducts.add(new Product(id, name, price, rating, quantity, 0));
        SellerLogin.saveAvailablebProducts();
        loadProductList();
        JOptionPane.showMessageDialog(this, "Product added: " + name);
    }

    private void deleteProduct(JTextField idField) {
        int id = Integer.parseInt(idField.getText());
        sproducts.removeIf(product -> product.getId() == id);
        SellerLogin.saveAvailablebProducts();
        loadProductList();
        JOptionPane.showMessageDialog(this, "Product deleted with ID: " + id);
    }

    private void updateProduct(JTextField idField, JTextField nameField, JTextField priceField, JTextField ratingField, JTextField quantityField) {
        int id = Integer.parseInt(idField.getText());
        for (Product product : sproducts) {
            if (product.getId() == id) {
                product.setName(nameField.getText());
                product.setPrice(Double.parseDouble(priceField.getText()));
                product.setRating(Double.parseDouble(ratingField.getText()));
                product.setAvailableQuantity(Integer.parseInt(quantityField.getText()));
                SellerLogin.saveAvailablebProducts();
                loadProductList();
                JOptionPane.showMessageDialog(this, "Product updated: " + product.getName());
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Product not found.");
    }

    private void sortProductsByMostSold() {
        sproducts.sort(Comparator.comparingInt(Product::getSoldQuantity).reversed());
    }
}
