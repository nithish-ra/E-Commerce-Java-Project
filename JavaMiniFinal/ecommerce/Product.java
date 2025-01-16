public class Product {
    private int id;
    private String name;
    private double price;
    private double rating;
    private int availableQuantity;
    private int soldQuantity;

    public Product(int id, String name, double price, double rating, int availableQuantity, int soldQuantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.rating = rating;
        this.availableQuantity = availableQuantity;
        this.soldQuantity = soldQuantity;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public double getRating() { return rating; }
    public int getAvailableQuantity() { return availableQuantity; }
    public int getSoldQuantity() { return soldQuantity; }

    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
    public void setRating(double rating) { this.rating = rating; }
    public void setAvailableQuantity(int availableQuantity) { this.availableQuantity = availableQuantity; }
    public void setSoldQuantity(int soldQuantity) { this.soldQuantity = soldQuantity; }
}
