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


public interface ProductStockContainer {

    /**
     * The abstract method which will return the quantity of product inside the Inventory or ShoppingCart
     * that associates with the given Product
     * @param product Product
     * @return int, the quantity of product inside the Inventory or ShoppingCart
     */
    public int getProductQuantity (Product product);

    /**
     * The abstract method which will add the quantity of product to the Inventory or ShoppingCart
     * that associates with the given Product
     * @param product Product
     * @param amount the quantity the Inventory or ShoppingCart that wants to add
     */
    public void addProductQuantity (Product product, int amount);

    /**
     * The abstract method which will remove the quantity of product from the Inventory or ShoppingCart
     * that associates with the given Product
     * @param product Product
     * @param amount the quantity the Inventory or ShoppingCart that wants to remove
     */
    public void removeProductQuantity (Product product, int amount);

    /**
     * The abstract method will return the number of product that is available inside
     * the Inventory or ShoppingCart
     * @return int, the number of available products
     */
    public int getNumOfProducts();

}
