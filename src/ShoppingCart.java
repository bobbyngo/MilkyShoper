// GABRIEL BENNI KELLEY EVENSEN
// 101119814
// Ngo Huu Gia Bao
// 101163137
/**
 * A class representing a shopping cart
 *
 * @author Gabriel Benni Kelley Evensen, 101119814
 * @author Ngo Huu Gia Bao, 101163137
 * @version 2.0
 */
import java.util.HashMap;


public class ShoppingCart {

    private int cartID;
    private HashMap <Integer, Integer> customerCart = new HashMap<>(); //id quantity

    /**
     * The constructor of the ShoppingCart class
     * @param cartID int, the cartID of the ShoppingCart class
     */
    public ShoppingCart (int cartID) {
        this.cartID = cartID;
    }

    /**
     * The addCustomerProduct will check if the input quantity is smaller or equal to the quantity in the idQuantity HashMap (Inventory class)
     * and the input id is contained on the Inventory class or not. If yes, the method will add the input id
     * and quantity to the customerCart HashMap.
     * @param id int, the id of the product
     * @param amount int, the amount or the quantity that the customer wants to add
     * @param inventory Inventory, the inventory object that we are referring to
     */
    public void addCustomerProduct (int id, int amount, Inventory inventory) {
        if (amount <= inventory.getIdQuantity().get(id) && inventory.getIdQuantity().containsKey(id)) {
            //Add product id with the amount to the customer's cart
            customerCart.put(id, amount);
        }
    }

    /**
     * The removeCustomerProduct will check if the input quantity is smaller or equal to the quantity in the customerCart HashMap
     * and the input id is contained on the HashMap or not. If yes, the method will remove the input id
     * and quantity from customerCart HashMap.
     * @param id int, the id of the product
     * @param amount int, the amount or the quantity that the customer wants to remove
     */
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

    /**
     * The getter method for the cartID attribute
     * @return cartID, the cart ID of the customer, each customer has unique ID
     */
    public int getCartID() {
        return cartID;
    }

    /**
     * The getter method for the HashMap customerCart
     * @return customerCart, HashMap storing the id and the amount of the product of the customer's cart
     */
    public HashMap<Integer, Integer> getCustomerCart() {
        return customerCart;
    }
}
