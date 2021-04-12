package stores;
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

public class ShoppingCart implements ProductStockContainer{

    private int cartID;
    private HashMap<Integer, Integer> customerCart = new HashMap<>(); //id quantity
    private HashMap<Integer, Product> infoProduct = new HashMap<>(); //id quantity

    /**
     * The constructor of the stores.ShoppingCart class
     *
     * @param cartID int, the cartID of the stores.ShoppingCart class
     */
    public ShoppingCart(int cartID) {

        this.cartID = cartID;
        this.customerCart.put(0, 0);
        this.customerCart.put(1, 0);
        this.customerCart.put(2, 0);
        this.customerCart.put(3, 0);
        this.customerCart.put(4, 0);

    }

    /**
     * The getter method for the HashMap customerCart
     *
     * @return customerCart, HashMap storing the id and the amount of the product of the customer's cart
     */
    public HashMap<Integer, Integer> getCustomerCart() {
        return customerCart;
    }


    /**
     *                                         MILESTONE 5
     */


    /**
     * The implemented method which will return the quantity of product inside the ShoppingCart
     * that associates with the given Product
     * @param product Product
     * @return int, the quantity of product inside the ShoppingCart
     */
    @Override
    public int getProductQuantity(Product product) {
        if (customerCart.containsKey(product.getId())) {
            return customerCart.get(product.getId());
        }
        return -1;
    }

    /**
     * The implemented method which will add the quantity of product to the ShoppingCart
     * that associates with the given Product
     * @param product Product
     * @param amount the quantity the ShoppingCart that wants to add
     */
    @Override
    public void addProductQuantity(Product product, int amount) {
        customerCart.put(product.getId(), customerCart.get(product.getId()) + amount);
    }

    /**
     * The implemented method which will remove the quantity of product from the ShoppingCart
     * that associates with the given Product
     * @param product Product
     * @param amount the quantity the ShoppingCart that wants to remove
     */
    @Override
    public void removeProductQuantity(Product product, int amount) {
        customerCart.put(product.getId(), customerCart.get(product.getId()) - amount);
    }

    /**
     * The implemented method will return the number of product that is available inside
     * the ShoppingCart
     * @return int, the number of available products inside the ShoppingCart
     */
    @Override
    public int getNumOfProducts() {
        return customerCart.size();
    }
}
