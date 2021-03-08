// GABRIEL BENNI KELLEY EVENSEN
// 101119814
// Ngo Huu Gia Bao
// 101163137
/**
 * A class representing the Inventory of the store
 *
 * @author Gabriel Benni Kelley Evensen, 101119814
 * @author Ngo Huu Gia Bao, 101163137
 * @version 2.0
 */
import java.util.HashMap;
import java.util.Set;

public class Inventory {
    private HashMap<Integer, Product> infoProduct = new HashMap<>(); //(id, Product)
    private HashMap<Integer, Integer> idQuantity = new HashMap<>(); //(id, quantity)


    /**
     * Constructor of Inventory
     */
    public Inventory(){
        Product p1 = new Product("1% milk", 0, 4.30);
        Product p2 = new Product("2% milk", 1, 3.30);
        Product p3 = new Product("3% milk", 2, 5.00);
        Product p4 = new Product("goat milk", 3, 6.99);
        Product p5 = new Product("camel milk", 4, 30.99);
        /**
         * Initialize the id-product
         */
        infoProduct.put(0,p1);
        infoProduct.put(1,p2);
        infoProduct.put(2,p3);
        infoProduct.put(3,p4);
        infoProduct.put(4,p5);

        /**
         * Initialize the id-quantity
         */
        idQuantity.put(0,20);
        idQuantity.put(1,60);
        idQuantity.put(2,39);
        idQuantity.put(3,80);
        idQuantity.put(4,25);
    }

    /**
     * Get all the ID of the product from the HashMap
     * @return Set of Integer
     */
    public Set<Integer> getAvailableID () {
        return infoProduct.keySet();
    }

    /**
     * Get the amount of stock for a given Product ID (Note: it is possible the Product does not exist in
     * the Inventory!)
     * @param id int, the id that the user wants to get the info
     * @return int, the quantity of the given id
     */
    public int gettingQuantity (int id) {
        for (Integer i : idQuantity.keySet()) {
            if (i == id) {
                return getIdQuantity().get(i);
            }
        }
        return -1;
    }

    /**
     * Remove a specified amount of stock for a given Product ID from the inventory (Note: you cannot
     * have negative stock, and you cannot delete Products from the Inventory; if a Product’s stock
     * reaches 0, leave it).
     * @param id int, the id of the product
     * @param amount, int the amount of the product to remove
     */
    public void removingQuantity (int id, int amount) {
        if (idQuantity.containsKey(id) && idQuantity.get(id) >= amount) {
            idQuantity.put(id, idQuantity.get(id) - amount);
        }else {
            System.out.println("\nInventory: The amount you entered is more than what we have");
        }
    }

    /**
     * Get information on a Product given a Product ID
     * @param id int, the id of the product
     */
    public Product getProduct (int id) {
        return infoProduct.get(id);
    }


    /**
     * Get methods for the hashmap id and quantity in stock
     * @return idQuantity, the HashMap storing the id and quantity in the Inventory class
     */
    public HashMap<Integer, Integer> getIdQuantity() {
        return idQuantity;
    }

    /**
     * Get methods for the hashmap id and product in stock
     * @return infoProduct, the HashMap storing the id and the Product in the Inventory class
     */

    public HashMap<Integer, Product> getInfoProduct() {
        return infoProduct;
    }

}

