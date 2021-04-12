package stores;
// GABRIEL BENNI KELLEY EVENSEN
// 101119814
// Ngo Huu Gia Bao
// 101163137

/**
 * A class representing the managing system of the store
 *
 * @author Gabriel Benni Kelley Evensen, 101119814
 * @author Ngo Huu Gia Bao, 101163137
 * @version 2.0
 */

import java.util.*;

public class StoreManager {
    private Inventory inventory = new Inventory();
    private int counterCartID = -1;
    private HashMap<Integer, ShoppingCart> shoppingCart = new HashMap<>();    //cartID,  shopping cart



    /**
     *                                         MILESTONE 2
     */

    /**
     * This method retrieve the ID of of the stores.Product to give the access to the stores.StoreView
     * through the gettingProduct method in the stores.Inventory class
     *
     * @return Set of Product
     */
    public Set<Product> getAvailableProduct() {
        return this.inventory.getAvailableProduct();
    }

    /**
     * This method will add the cartID as a key and the stores.ShoppingCart object as the value to organize.
     * It will increment the counterCartID by 1 and pass it to the cartID inside the stores.ShoppingCart class
     * @return incremented counterCartID
     */
    public int assignNewCartID() {
        counterCartID++;
        ShoppingCart sc = new ShoppingCart(counterCartID);
        shoppingCart.put(counterCartID, sc);
        return counterCartID;
    }


    /**
     * This method will give the access to the customerCart HashMap inside the stores.ShoppingCart class
     *
     * @param cartID int, the unique cart ID of the customer
     * @return HashMap, the customerCart which stores the id and quantity of the product
     */
    public HashMap<Integer, Integer> getCustomerCart(int cartID) {
        return shoppingCart.get(cartID).getCustomerCart();
    }


    /**
     * This method is called inside the addDisplay of the stores.StoreView. It will check if the id and the amount is valid, then
     * add those information to the customerCart Hashmap in the stores.ShoppingCart class first. Then the method will remove
     * those product from inventory class
     *
     * @param product  Product, the product that the customer wants to remove from their cart
     * @param quantity int, the quantity that the customer remove from their cart
     * @param cartID   int, the unique cart ID of the customer
     */
    public void removeCartInventory(Product product, int quantity, int cartID) {

        //Because the addCustomerProduct will check the availability of the product of the inventory class
        //so if we remove the product from the inventory first, we cannot add product to the cart

        //Add quantity of the given id to the customer cart first

        //If enough stock i inventory:
        // Add product to the cart

        if (quantity <= inventory.getProductQuantity(product)) {
            shoppingCart.get(cartID).addProductQuantity(product, quantity);

            //Remove quantity of the given id from the inventory
            inventory.removeProductQuantity(product, quantity);
        } else {
            System.out.println("StoreManager: The amount you entered is more than what we have in the Inventory\n");
        }
    }


    /**
     * This method is called inside the removeDisplay of the stores.StoreView. It will check if the id and the amount is valid, then
     * remove those information from the customerCart Hashmap and add those back to the inventory class
     *
     * @param product   Product, the product that the customer wants to remove from their cart
     * @param quantity int, the quantity that the customer remove from their cart
     * @param cartID   int, the unique cart ID of the customer
     */
    public void addCartInventory(Product product, int quantity, int cartID) {
        //Access to the shoppingCart hashmap to get the ShoppingCart value based on the key cartID, then accessing to the
        //customerCart hashmap inside the ShoppingCart class,
        //then get the quantity value of the customerCart hashmap by using the key id

        if (quantity <= shoppingCart.get(cartID).getProductQuantity(product)) {
            //Because the removeCustomerProduct check the product of the customer not the inventory so we could
            //swap other way around

            //Remove the quantity of the given id from the cart
            shoppingCart.get(cartID).removeProductQuantity(product, quantity);

            //Add product with id back the amount to the inventory
            inventory.addProductQuantity(product, quantity);

        } else {
            System.out.println("StoreManager: The amount you want to remove exceeding the amount in your cart\n");
        }
    }


    /**
     * This method will print out the receipt of the customer by accessing the customerCart Hashmap inside the Shopping Cart class
     * and the gettingProduct method inside the stores.Inventory class to retrieve product the information
     *
     * @param cartID int, the unique cart ID of the customer
     * @return String which have been formatting as the receipt
     */
    public String processTransaction(int cartID) {

        StringBuilder stringBuilder = new StringBuilder();
        double total = 0;

        for (Integer i : shoppingCart.get(cartID).getCustomerCart().keySet()) {

            //Accessing to the every value (the quantity of the product) of the HashMap in the Shopping Cart with the id i
            stringBuilder.append(shoppingCart.get(cartID).getCustomerCart().get(i))
                    .append(" | ")

                    //get the id of the product given the id i
                    .append(inventory.getProduct(i).getId())
                    .append(" | ")

                    //get the name of the product given the id i
                    .append(inventory.getProduct(i).getName())
                    .append(" | ")

                    //get the price of the product given the id i and multiply with the quantity
                    .append(inventory.getProduct(i).getPrice() * shoppingCart.get(cartID).getCustomerCart().get(i))
                    .append("\n");


            total += inventory.getProduct(i).getPrice() * shoppingCart.get(cartID).getCustomerCart().get(i);

        }
        shoppingCart.remove(cartID);
        stringBuilder.append("The total is: ").append(total);
        return stringBuilder.toString();
    }

    /**
     * This method will return the quantity of the given Product in the Inventory class
     * @param product
     * @return the quantity of the Product
     */
    public int getProductQuantity(Product product){
        return inventory.getProductQuantity(product);
    }

    /**
     * This method will return the quantity of the given Product in the ShoppinCart class
     * @param product , Product
     * @param cartID , int
     * @return the quantity of the Product
     */
    public int getProductQuantityCart(Product product, int cartID) {
        return shoppingCart.get(cartID).getProductQuantity(product);
    }
}
