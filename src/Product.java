// GABRIEL BENNI KELLEY EVENSEN
// 101119814
// Ngo Huu Gia Bao
// 101163137
public class Product {
    private final String name;
    private final int id;
    private final double price;

    public Product (String name, int id, double price) {
        this.name = name;
        this.id = id;
        this.price = price;
    }

    /**
        * Get the name of the Product
     */
    public String getName() {
        return name;
    }

    /**
        * Get the ID of the Product
     */
    public int getId() {
        return id;
    }

    /**
        * Get the Price of the Product
     */
    public double getPrice() {
        return price;
    }

}
