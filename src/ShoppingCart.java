// GABRIEL BENNI KELLEY EVENSEN
// 101119814
// Ngo Huu Gia Bao
// 101163137

import java.util.ArrayList;
import java.util.HashMap;


public class ShoppingCart {

    private static int shopId = 0;
    private int cartID;
    private HashMap <Integer, Integer> customerCart = new HashMap<>(); //id quantity
    //private HashMap<Integer, Product> customerCartInfo = new HashMap<>(); //(id, Product)

    public ShoppingCart () {
        this.cartID = shopId++;
    }

    /**
     * TODO Create methods to add and remove in Store Manager
     */
    public void addCustomerProduct (int id, int amount, Inventory inventory) {
        if (amount <= inventory.getIdQuantity().get(id) && inventory.getIdQuantity().containsKey(id)) {
            //Add product id with the amount to the customer's cart
            customerCart.put(id, amount);
        }
    }

    public void removeCustomerProduct (int id, int amount) {
        if (amount <= customerCart.get(id) && customerCart.containsKey(id)) {
            //Remove product with id from the amount to the customer's cart
            customerCart.put(id, customerCart.get(id) - amount);

            //Remove products that have 0 quantity in the customer cart
            if (customerCart.get(id) == 0) {
                customerCart.remove(id);
            }
        }else {
            System.out.println("\nInventory: The amount you want to remove exceeding the amount in your cart");
        }
    }

    public int getCartID() {
        return cartID;
    }

    public HashMap<Integer, Integer> getCustomerCart() {
        return customerCart;
    }
}
