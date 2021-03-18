package stores;// GABRIEL BENNI KELLEY EVENSEN
// 101119814
// Ngo Huu Gia Bao
// 101163137
/**
 * A class representing a stores.Product
 *
 * @author Gabriel Benni Kelley Evensen, 101119814
 * @author Ngo Huu Gia Bao, 101163137
 * @version 2.0
 */
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
        * Get the name of the stores.Product
     */
    public String getName() {
        return name;
    }

    /**
        * Get the ID of the stores.Product
     */
    public int getId() {
        return id;
    }

    /**
        * Get the Price of the stores.Product
     */
    public double getPrice() {
        return price;
    }

}
