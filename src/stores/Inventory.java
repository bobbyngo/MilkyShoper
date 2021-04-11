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
     *
     * @return Set of Integer
     */
    public Set<Integer> getAvailableID() {
        return infoProduct.keySet();
    }

    /**
     * Get the amount of stock for a given stores.Product ID (Note: it is possible the stores.Product does not exist in
     * the stores.Inventory!)
     *
     * @param id int, the id that the user wants to get the info
     * @return int, the quantity of the given id
     */
    public int gettingQuantity(int id) {
        for (Integer i : idQuantity.keySet()) {
            if (i == id) {
                return getIdQuantity().get(i);
            }
        }
        return -1;
    }


    /**
     * Add a specified amount of stock for a given stores.Product ID from the inventory
     * @param id      int, the id of the product
     * @param amount, int the amount of the product to add
     */
    public void addProductQuantity(int id, int amount){
        int originalAmount = idQuantity.get(id);
        idQuantity.put(id, originalAmount + amount);
    }

    /**
     * Remove a specified amount of stock for a given stores.Product ID from the inventory (Note: you cannot
     * have negative stock, and you cannot delete Products from the stores.Inventory; if a stores.Product’s stock
     * reaches 0, leave it).
     *
     * @param id      int, the id of the product
     * @param amount, int the amount of the product to remove
     */
    public void removeProductQuantity(int id, int amount) {
        idQuantity.put(id, idQuantity.get(id) - amount);
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
     * Get methods for the hashmap id and quantity in stock
     *
     * @return idQuantity, the HashMap storing the id and quantity in the stores.Inventory class
     */
    public HashMap<Integer, Integer> getIdQuantity() {
        return idQuantity;
    }

    /**
     * Get methods for the hashmap id and product in stock
     *
     * @return infoProduct, the HashMap storing the id and the stores.Product in the stores.Inventory class
     */

    public HashMap<Integer, Product> getInfoProduct() {
        return infoProduct;
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
        return idQuantity.get(product.getId());
    }

    /**
     * The implemented method which will add the quantity of product to the Inventory
     * that associates with the given Product
     * @param product Product
     * @param amount the quantity the Inventory that wants to add
     */
    @Override
    public void addProductQuantity(Product product, int amount) {
        addProductQuantity(product.getId(), amount);
    }

    /**
     * The implemented method which will remove the quantity of product from the Inventory
     * that associates with the given Product
     * @param product Product
     * @param amount the quantity the Inventory that wants to remove
     */
    @Override
    public void removeProductQuantity(Product product, int amount) {
        removeProductQuantity(product.getId(), amount);
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

