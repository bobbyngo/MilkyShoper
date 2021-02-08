import java.sql.Array;
import java.util.HashMap;
import java.util.ArrayList;

public class StoreManager {
    private Inventory product_inventory = new Inventory();

    /**
     * Method to check how much stock of a given Product is in the Inventory
     */
    public void getProductAmount(Product product){
        System.out.println(product_inventory.gettingQuantity(product.getId()));
    }

    /**
     * Method to process a transaction given an Array of Product information [ [productID, buyQuantity], ...]
     */
    public void processTransaction(Integer arr[][]){
        for (Integer i = 0; i < arr.length; i++){
            if (arr[i][1] > product_inventory.gettingQuantity(arr[i][0])){
                new IllegalArgumentException("Product:" + arr[i][0] + " insufficient stock. (" + product_inventory.gettingQuantity(arr[i][0]) + ")" );
                continue;
            } else {
                product_inventory.removingQuantity(arr[i][0], arr[i][1]);
            }
        }
    }

    /**
     * Main method
     * Calling methods and testing Product class and Inventory class will be in here
     * Creating objects in main
     */
    public static void main(String[] args) {
        /**
         * Kindly put your test cases below, dearest TA.
         */
        StoreManager store1 = new StoreManager();

        System.out.println(store1.product_inventory.getId_quantity());
//        Integer arr[][] = new Integer[2][1];
//        store1.processTransaction();
    }
}
