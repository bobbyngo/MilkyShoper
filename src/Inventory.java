// GABRIEL BENNI KELLEY EVENSEN
// 101119814
// Ngo Huu Gia Bao
// 101163137

import java.util.HashMap;

public class Inventory {
    private HashMap<Integer, Product> infoProduct = new HashMap<>(); //(id, Product)
    private HashMap<Integer, Integer> id_quantity = new HashMap<>(); //(id, quantity)


    /**
     * Constructor of Inventory
     */
    public Inventory(){
        Product p1 = new Product("1% milk", 0, 4.30);
        Product p2 = new Product("2% milk", 1, 3.30);
        Product p3 = new Product("3% milk", 2, 5.00);
        /**
         * Initialize the id-product
         */
        infoProduct.put(0,p1);
        infoProduct.put(1,p2);
        infoProduct.put(2,p3);

        /**
         * Initialize the id-quantity
         */
        id_quantity.put(0,1);
        id_quantity.put(1,2);
        id_quantity.put(2,0);
    }

    /**
     * Get the amount of stock for a given Product ID (Note: it is possible the Product does not exist in
     * the Inventory!)
     */
    public int gettingQuantity (int id) {
        for (Integer i : id_quantity.keySet()) {
            if (i == id) {
                return getId_quantity().get(i);
            }
        }
        return -1;
    }

    /**
     * Add a specified amount of stock for a given Product to the inventory (Note: new Products can be
     * added!).
     */
    public void addingQuantity (int id,int amount) {
        if (id_quantity.containsKey(id)) {
                id_quantity.put(id, amount + id_quantity.get(id));
                System.out.println("[" + getInfoProduct().get(id).getName() + "] now has " + amount + " additional wares.");
            } else {
                //New product add
                id_quantity.put(id,amount);
            }
    }

    /**
     * Remove a specified amount of stock for a given Product ID from the inventory (Note: you cannot
     * have negative stock, and you cannot delete Products from the Inventory; if a Product’s stock
     * reaches 0, leave it.).
     */
    public void removingQuantity (int id, int amount) {
        if (id_quantity.containsKey(id)) {
            if (id_quantity.get(id) - amount < 0) {
                System.out.println("\n[" + getInfoProduct().get(id).getName() + "] has insufficient wares. Transaction nullified.");
            } else {
                id_quantity.put(id, id_quantity.get(id) - amount);
            }
        }
    }

    /**
     * Get information on a Product given a Product ID
     */
    public void gettingProduct (int id) {
        for (Integer i : infoProduct.keySet()) {
            if (i.equals(id)) {
                System.out.println("The Product name: " + infoProduct.get(i).getName()
                        + ", ID: " + infoProduct.get(i).getId() + ", price " + infoProduct.get(i).getPrice());
            }
        }
    }


    /**
     * Set and Get methods for the hashmap id and quantity in stock
     */
    public HashMap<Integer, Integer> getId_quantity() {
        return id_quantity;
    }

    /**
     * Set and get methods for the hashmap id and product in stock
     */

    public HashMap<Integer, Product> getInfoProduct() {
        return infoProduct;
    }

}

