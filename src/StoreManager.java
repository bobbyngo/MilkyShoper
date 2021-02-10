// GABRIEL BENNI KELLEY EVENSEN
// 101119814
// Ngo Huu Gia Bao
// 101163137

import java.util.HashMap;
import java.util.Map;


public class StoreManager {
    private Inventory inventory = new Inventory();

    /**
     * Method for getting stocks
     */
    public void checkStock(int id){
        System.out.println( "Product:- " + inventory.getInfoProduct().get(id).getName() + " | Stock:- " + inventory.gettingQuantity(id) );
    }

    /**
     * Method to process a transaction given an Array of Product information [ [productID, buyQuantity], ...]
     */
    public void processTransaction(Integer[][] product_array){
        double total = 0.00;
        Map<Integer, Integer> product_map = new HashMap<Integer, Integer>(product_array.length);
        for (Integer[] mapping : product_array)
        {
            product_map.put(mapping[0], mapping[1]);

            if (inventory.getId_quantity().get(mapping[0]) - mapping[1] >= 0) {
                total += mapping[1]*inventory.getInfoProduct().get(mapping[0]).getPrice();
            }

            inventory.removingQuantity(mapping[0], mapping[1]);
        }
        System.out.println("\n=============\nReceipt:-\n=============\n Your total is $" + total);
    }

    /**
     * Main method
     * Calling methods and testing Product class and Inventory class will be in here
     * Creating objects in main
     */
    public static void main(String[] args) {
        /**
         * Kindly put your test cases below, dearest TA.
         * I've put some tests below, I hope it might help.
         */

        /**
         * Creating the StoreManager upon starting
         */
        StoreManager store = new StoreManager();

        /**
         * Checking the Product info with id
         */
        System.out.print("\n==================================\nProduct Information with Given ID:\n==================================\n");
        store.inventory.gettingProduct(0);
        store.inventory.gettingProduct(1);
        store.inventory.gettingProduct(2);

        /**
         * Checking the stocks BEFORE transaction
         */
        System.out.print("\n==================================\nWares prior to transaction:\n==================================\n");
        store.checkStock(0);
        store.checkStock(1);
        store.checkStock(2);

        /**
         * Processing transactions
         * Note:- I have intentionally included errors to prove that part works
         */
        Integer[][] product_2d_array = {{0, 2}, {1, 2}, {2, 1}};
        store.processTransaction(product_2d_array);

        /**
         * Checking the stocks AFTER transaction
         */
        System.out.print("\n==================================\nWares after transaction:-\n==================================\n");
        store.checkStock(0);
        store.checkStock(1);
        store.checkStock(2);

        /**
         * Adding products to our inventory. Refilling.
         */
        System.out.print("\n==================================\nWares after refill:-\n==================================\n");

        store.inventory.addingQuantity(0,2);
        store.checkStock(0);
        store.inventory.addingQuantity(1,1);
        store.checkStock(1);
        store.inventory.addingQuantity(2,3);
        store.checkStock(2);
    }
}
