import java.util.HashMap;
import java.util.*;


public class Inventory {
    private String type;
    private int quantity;
    private HashMap<Integer, Integer> id_quantity = new HashMap<>(); //(id, quantity)
    private HashMap<Integer, Product> infoProduct = new HashMap<>(); //(id, Product)


    /**
     * Constructor of Inventory
     */
    public Inventory(String type, int quantity, HashMap id_quantity){
        this.type = type;
        this.quantity = quantity;
        this.id_quantity = id_quantity;
    }

    /**
     * Get the amount of stock for a given Product ID (Note: it is possible the Product does not exist in
     * the Inventory!)
     */
    public int gettingQuantity (int id) {
        for (Integer i : id_quantity.keySet() ){
            if (id_quantity.containsKey(id) ){
                return id_quantity.get(id);
            }
        }
        return -1;
    }

    /**
     * Add a specified amount of stock for a given Product to the inventory (Note: new Products can be
     * added!).
     */
    public int addingQuantity (Product p,int amount) {
        return quantity += amount;
    }

    /**
     * Remove a specified amount of stock for a given Product ID from the inventory (Note: you cannot
     * have negative stock, and you cannot delete Products from the Inventory; if a Product’s stock
     * reaches 0, leave it.).
     */
    public int removingQuantity (Product p,int amount) {
        if (quantity == 0){
            return quantity;
        }if ( quantity - amount < 0) {
            return -1;
        }

        return quantity -= amount;
    }

    /**
     * Get information on a Product given a Product ID
     */
    public Product gettingProduct (int id) {
        for (Integer i : infoProduct.keySet()) {
            return infoProduct.get(id);
        }
        return ;
    }

    /**
     * Get the type of the product in stock
     */
    public String getType() {
        return type;
    }
    /**
     * Set the type of the product in stock
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Get the amount of the product in stock
     */
    public int getQuantity() {
        return quantity;
    }
    /**
     * Set the amount of the product in stock
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Get the hashmap id and quantity in stock
     */
    public HashMap<Integer, Integer> getId_quantity() {
        return id_quantity;
    }

    /**
     * Set the hashmap id and quantity in stock
     */
    public void setId_quantity(HashMap<Integer, Integer> id_quantity) {
        this.id_quantity = id_quantity;
    }

    public HashMap<Integer, Product> getInfoProduct() {
        return infoProduct;
    }

    public void setInfoProduct(HashMap<Integer, Product> infoProduct) {
        this.infoProduct = infoProduct;
    }

    /**
     * ID QUANTITY Hashmap
     */
    public void creatingIdQuantity (Integer id, Integer quantity){
        id_quantity.put(id, quantity);
    }

    /**
     * ID Product Hashmap
     */
    public void creatingIdProduct (Integer id, Product product){
        infoProduct.put(id, product);
    }
}

