import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.swing.*;
import x.notepad;

public class BuyerHomePage extends JFrame {
    public static List<Product> buyerproducts;
    private List<Product> cart;
    private JList<String> productList;
    private DefaultListModel<String> productListModel;
    private JList<String> cartList;
    private DefaultListModel<String> cartListModel;

    public BuyerHomePage() {
        this.buyerproducts = BuyerLogin.fetchAvailablebProducts();
        this.cart = new ArrayList<>();
        this.productListModel = new DefaultListModel<>();
        this.cartListModel = new DefaultListModel<>();
        
        setTitle("Buyer Home Page");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel productPanel = new JPanel(new BorderLayout());
        JLabel productLabel = new JLabel("Available Products:");
        productList = new JList<>(productListModel);
        JScrollPane productScrollPane = new JScrollPane(productList);
        productPanel.add(productLabel, BorderLayout.NORTH);
        productPanel.add(productScrollPane, BorderLayout.CENTER);
        loadProductList();
        
        JPanel buttonPanel = new JPanel();
        JButton addToCartButton = new JButton("Add to Cart");
        JButton sortByPriceButton = new JButton("Sort by Price");
        JButton sortByRatingButton = new JButton("Sort by Rating");
        JButton delButton = new JButton("Delete from Cart");
        JButton logoutButton = new JButton("Logout");
        JButton checkoutButton = new JButton("Checkout");

        buttonPanel.add(addToCartButton);
        buttonPanel.add(sortByPriceButton);
        buttonPanel.add(sortByRatingButton);
        buttonPanel.add(delButton);
        buttonPanel.add(logoutButton); 
        buttonPanel.add(checkoutButton);
        JButton wishButton = new JButton("Wishlist");
        buttonPanel.add(wishButton);

        JPanel cartPanel = new JPanel(new BorderLayout());
        JLabel cartLabel = new JLabel("Your Cart:");
        cartList = new JList<>(cartListModel);
        JScrollPane cartScrollPane = new JScrollPane(cartList);
        cartPanel.add(cartLabel, BorderLayout.NORTH);
        cartPanel.add(cartScrollPane, BorderLayout.CENTER);
        
        add(productPanel, BorderLayout.WEST);
        add(buttonPanel, BorderLayout.CENTER);
        add(cartPanel, BorderLayout.EAST);

        addToCartButton.addActionListener(e -> addProductToCart());

        sortByPriceButton.addActionListener(e -> {
            sortProductsByPrice();
            loadProductList();
        });

        sortByRatingButton.addActionListener(e -> {
            sortProductsByRating();
            loadProductList();
        });

        delButton.addActionListener(e -> {
            delProductToCart();
            loadCart();
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BuyerLogin();
                dispose(); 
            }
        });

        checkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if((cart.size()) != 0)
                    checkoutCart();
                else
                    JOptionPane.showMessageDialog(BuyerHomePage.this, "Please add item to cart before checking out");
            }   
        });

        wishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notepad n=new notepad();
                n.mainfn();
            }
        });

        setVisible(true);
    }

    private void loadProductList() {
        productListModel.clear();
        buyerproducts = BuyerLogin.fetchAvailablebProducts();
        for (Product product : buyerproducts) {
            productListModel.addElement("ID: " + product.getId() + ", Name: " + product.getName() + ", Price: " + product.getPrice() + ", Rating: " + product.getRating() + ", Available Quantity: " + product.getAvailableQuantity());
        }
    }

    private void loadCart() {
        cartListModel.clear();
        for (Product product : cart) {
            cartListModel.addElement("ID: " + product.getId() + ", Name: " + product.getName() + ", Price: " + product.getPrice());
        }
    }

    private void addProductToCart() {
        int selectedIndex = productList.getSelectedIndex();
        if (selectedIndex != -1) {
            Product selectedProduct = buyerproducts.get(selectedIndex);
            if((selectedProduct.getAvailableQuantity())!=0){
                cart.add(selectedProduct);
                JOptionPane.showMessageDialog(this, "Product added to cart: " + selectedProduct.getName());
                loadCart();
            }
            else   
                JOptionPane.showMessageDialog(this,"Product out of stock, please try later : "+selectedProduct.getName());
        } else {
            JOptionPane.showMessageDialog(this, "Please select a product to add to the cart.");
        }
    }

    private void sortProductsByPrice() {
        buyerproducts.sort(Comparator.comparingDouble(Product::getPrice));
    }

    private void sortProductsByRating() {
        buyerproducts.sort(Comparator.comparingDouble(Product::getRating).reversed());
    }

    private void delProductToCart() {
        int selectedIndex = cartList.getSelectedIndex();
        if (selectedIndex != -1) {
            Product selectedProduct = cart.get(selectedIndex);
            cart.remove(selectedProduct);
            JOptionPane.showMessageDialog(this, "Product deleted from the cart: " + selectedProduct.getName());
            loadCart();
            cartList.setModel(cartListModel);
        } 
        else {
            JOptionPane.showMessageDialog(this, "Please select a product to delete from the cart.");
        }
    }

    private void checkoutCart(){
        double totalPrice = 0;
        for(Product product : cart){
            product.setSoldQuantity(product.getSoldQuantity()+1);
            product.setAvailableQuantity(product.getAvailableQuantity()-1);
            totalPrice += product.getPrice();
        }
        cart.clear();
        cartListModel.clear();
        BuyerLogin.saveAvailablebProducts();
        buyerproducts = BuyerLogin.fetchAvailablebProducts();
        productListModel.clear();
        loadProductList();
        JOptionPane.showMessageDialog(this, "Total Price : " + totalPrice);
    }
}
