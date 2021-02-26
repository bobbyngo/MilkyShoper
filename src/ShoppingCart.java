// GABRIEL BENNI KELLEY EVENSEN
// 101119814
// Ngo Huu Gia Bao
// 101163137

import java.util.ArrayList;
import java.util.HashMap;


public class ShoppingCart {
    private int cartID;
    //ArrayList <Integer> cart = new ArrayList<>();
    private HashMap <Integer, Integer> customerCart = new HashMap<>(); //id quantity
    StoreManager sm = new StoreManager();

    public ShoppingCart (int cartID) {
        this.cartID = cartID;
    }

    /**
     * TODO Create methods to add and remove in Store Manager
     */
    public void addCustomerProduct (int id, int amount) {
        //Add product id with the amount to the customer's cart
        customerCart.put(id, amount);

        //Remove the product id with the amount from the inventory
        sm.removeCartInventory(id, amount);
    }

    public void removeCustomerProduct (int id, int amount) {
        //Remove product id from the amount to the customer's cart
        customerCart.remove(id);

        //Add product id back the amount to the inventory
        int originalAmount = sm.getQuantity().get(id);
        sm.getQuantity().put(id, amount + originalAmount);
    }

}
