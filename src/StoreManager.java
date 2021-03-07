// GABRIEL BENNI KELLEY EVENSEN
// 101119814
// Ngo Huu Gia Bao
// 101163137

import java.lang.reflect.Array;
import java.util.*;


public class StoreManager {
    private Inventory inventory = new Inventory();
    private ShoppingCart sc = new ShoppingCart();
    //private int cartID = -1;
    //private ArrayList<ShoppingCart>shoppingCart = new ArrayList<>();


    /**
     *                                         MILESTONE 1
     */

    /**
     * Method for getting stocks
     */
    public void checkStock(int id){
        System.out.println( "Product:- " + inventory.getInfoProduct().get(id).getName() + " | Stock:- " + inventory.gettingQuantity(id) );
    }

    /***
     * Method to process a transaction given an Array of Product information [ [productID, buyQuantity], ...]
     */
    public void processTransaction(Integer[][] product_array){
        double total = 0.00;
        Map<Integer, Integer> product_map = new HashMap<Integer, Integer>(product_array.length);
        for (Integer[] mapping : product_array)
        {
            product_map.put(mapping[0], mapping[1]);

            if (inventory.getIdQuantity().get(mapping[0]) - mapping[1] >= 0) {
                total += mapping[1]*inventory.getInfoProduct().get(mapping[0]).getPrice();
            }

            inventory.removingQuantity(mapping[0], mapping[1]);
        }
        System.out.println("\n=============\nReceipt:-\n=============\n Your total is $" + total);
    }


    /**
     *                                         MILESTONE 2
     */


    /**
     * This method retrieve the quantity of the Product to give the access to the StoreView
     * through the hashmap in the Inventory class
     * @return hashmap with ID and Quantity
     */
    public HashMap<Integer, Integer> getQuantity() {
        return inventory.getIdQuantity();
    }

    /**
     * This method retrieve the information of the Product to give the access to the StoreView
     * through the hashmap in the Inventory class
     * @return HashMap with ID and Product
     */
    public HashMap<Integer, Product> getProduct() {
        return inventory.getInfoProduct();
    }

    /**
     * This method retrieve the ID of of the Product to give the access to the StoreView
     * through the gettingProduct method in the Inventory class
     * @return Set of Integer
     */
    public Set<Integer> getKeySet () {
        return inventory.getAvailableID();
    }

    /**
     * This method will return number distinctively
     * TODO: Change to hashmap
     * TODO: THIS FUNCTION MAY CAUSE THE REASON WHY THE HASHMAP OF THE INVENTORY DID NOT UPDATE
     */

    public int assignNewCartID() {
/*        this.cartID ++;
        ShoppingCart sc = new ShoppingCart(cartID);
        //sc.cart.add(this.cartID);
        shoppingCart.add(sc);
        return this.cartID;*/

        return sc.getCartID();
    }

    public HashMap<Integer, Integer> getCustomerCart() {
        return sc.getCustomerCart();
    }


    public void removeCartInventory (int id, int quantity) {

        //Because the addCustomerProduct will check the availability of the product of the inventory class
        // so if we remove the product from the inventory first, we cannot add product to the cart

        //Add quantity of the given id to the customer cart first
        sc.addCustomerProduct(id, quantity, this.inventory);

        //Remove quantity of the given id from the inventory
        inventory.removingQuantity(id, quantity);

    }

    /**
     * TODO : need to be fixed
     * @param id
     * @param quantity
     */
    public void addCartInventory (int id, int quantity) {

        //Because the removeCustomerProduct check the product of the customer not the inventory so we could
        //swap other way around

        //Add product with id back the amount to the inventory
        int originalAmount = inventory.getIdQuantity().get(id);
        inventory.getIdQuantity().put(id, originalAmount + quantity);

        //Remove the quantity of the given id from the cart
        sc.removeCustomerProduct(id, quantity);

    }



    /**
     * Main method
     * Calling methods and testing Product class and Inventory class will be in here
     * Creating objects in main
     */
//    public static void main(String[] args) {
//        /**
//         * Kindly put your test cases below, dearest TA.
//         * I've put some tests below, I hope it might help.
//         */
//
//        /**
//         * Creating the StoreManager upon starting
//         */
//        StoreManager store = new StoreManager();
//
//        /**
//         * Checking the Product info with id
//         */
//        System.out.print("\n==================================\nProduct Information with Given ID:\n==================================\n");
//        System.out.println("Name: "+ store.inventory.gettingProduct(0).getName() +
//                ", price: " + store.inventory.gettingProduct(0).getPrice() +
//                ", id: " + store.inventory.gettingProduct(0).getId());
//
//        System.out.println("Name: "+ store.inventory.gettingProduct(1).getName() +
//                ", price: " + store.inventory.gettingProduct(1).getPrice() +
//                ", id: " + store.inventory.gettingProduct(1).getId());
//
//        System.out.println("Name: "+ store.inventory.gettingProduct(2).getName() +
//                ", price: " + store.inventory.gettingProduct(2).getPrice() +
//                ", id: " + store.inventory.gettingProduct(2).getId());
//
//        /**
//         * Checking the stocks BEFORE transaction
//         */
//        System.out.print("\n==================================\nWares prior to transaction:\n==================================\n");
//        store.checkStock(0);
//        store.checkStock(1);
//        store.checkStock(2);
//
//        /**
//         * Processing transactions
//         * Note:- I have intentionally included errors to prove that part works
//         */
//        Integer[][] product_2d_array = {{0, 2}, {1, 2}, {2, 1}};
//        store.processTransaction(product_2d_array);
//
//        /**
//         * Checking the stocks AFTER transaction
//         */
//        System.out.print("\n==================================\nWares after transaction:-\n==================================\n");
//        store.checkStock(0);
//        store.checkStock(1);
//        store.checkStock(2);
//
//        /**
//         * Adding products to our inventory. Refilling.
//         */
//        System.out.print("\n==================================\nWares after refill:-\n==================================\n");
//
//        Product p1 = new Product("1% milk", 0, 4.30);
//        Product p2 = new Product("2% milk", 1, 3.30);
//        Product p3 = new Product("3% milk", 2, 5.00);
//
//        store.inventory.addingQuantity(p1,2);
//        store.checkStock(0);
//        store.inventory.addingQuantity(p2,1);
//        store.checkStock(1);
//        store.inventory.addingQuantity(p3,3);
//        store.checkStock(2);
    //   }
}
