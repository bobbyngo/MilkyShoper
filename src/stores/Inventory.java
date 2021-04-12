package stores;
// GABRIEL BENNI KELLEY EVENSEN
// 101119814
// Ngo Huu Gia Bao
// 101163137
/**
 * A class representing the stores.Inventory of the store
 *
 * @author Gabriel Benni Kelley Evensen, 101119814
 * @author Ngo Huu Gia Bao, 101163137
 * @version 2.0
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Inventory implements ProductStockContainer{

    private HashMap<Integer, Product> infoProduct = new HashMap<>(); //(id, Product)
    private HashMap<Integer, Integer> idQuantity = new HashMap<>(); //(id, quantity)


    /**
     * Constructor of stores.Inventory
     */
    public Inventory() {
        Product p1 = new Product("2% milk", 0, 4.30);
        Product p2 = new Product("cow milk", 1, 3.30);
        Product p3 = new Product("buffalo milk", 2, 5.00);
        Product p4 = new Product("goat milk", 3, 6.99);
        Product p5 = new Product("camel milk", 4, 30.99);
        /**
         * Initialize the id-product
         */
        infoProduct.put(0, p1);
        infoProduct.put(1, p2);
        infoProduct.put(2, p3);
        infoProduct.put(3, p4);
        infoProduct.put(4, p5);

        /**
         * Initialize the id-quantity
         */
        idQuantity.put(0, 20);
        idQuantity.put(1, 60);
        idQuantity.put(2, 39);
        idQuantity.put(3, 80);
        idQuantity.put(4, 25);
    }

    /**
     * Get all the ID of the product from the HashMap
     * @return Set of Product
     */
    public Set<Product> getAvailableProduct() {
        return new HashSet<Product> (infoProduct.values());
    }


    /**
     * Get information on a stores.Product given a stores.Product ID
     *
     * @param id int, the id of the product
     */
    public Product getProduct(int id) {
        return infoProduct.get(id);
    }


    /**
     *                                         MILESTONE 5
     */

    /**
     * The implemented method which will return the quantity of product inside the Inventory
     * that associates with the given Product
     * @param product Product
     * @return int, the quantity of product inside the ShoppingCart
     */
    @Override
    public int getProductQuantity(Product product) {
        if (idQuantity.containsKey(product.getId())) {
            return idQuantity.get(product.getId());
        }
        return -1;
    }

    /**
     * The implemented method which will add the quantity of product to the Inventory
     * that associates with the given Product
     * @param product Product
     * @param amount the quantity the Inventory that wants to add
     */
    @Override
    public void addProductQuantity(Product product, int amount) {
        int originalAmount = idQuantity.get(product.getId());
        idQuantity.put(product.getId(), originalAmount + amount);
    }

    /**
     * The implemented method which will remove the quantity of product from the Inventory
     * that associates with the given Product
     * @param product Product
     * @param amount the quantity the Inventory that wants to remove
     */
    @Override
    public void removeProductQuantity(Product product, int amount) {
        idQuantity.put(product.getId(), idQuantity.get(product.getId()) - amount);
    }


    /**
     * The implemented method will return the number of product that is available inside
     * the Inventory
     * @return int, the number of available products inside the Inventory
     */
    @Override
    public int getNumOfProducts() {
        return idQuantity.size();
    }
}

