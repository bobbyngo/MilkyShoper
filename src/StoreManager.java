import java.util.HashMap;
import java.util.ArrayList;

public class StoreManager {
    public int getAmount(Inventory product_inv){
        product_inv.getId_quantity();
    }



//    Inventory inventory1 = new Inventory(GoT, );

    /**
     * Main method
     * Calling methods and testing Product class and Inventory class will be in here
     * Creating objects in main
     */
    public static void main(String[] args) {
        Product GoT = new Product("Game of Thrones: A Game of Thrones", 001, 22.00);

        HashMap<Integer, Product> info_product = new HashMap<>()
        {{
            put(001, GoT);
        }};

        HashMap<Integer, Integer> ID_quantity = new HashMap<>()
        {{
            put(001, 1);
        }};

        Inventory product_inv = new Inventory(info_product, ID_quantity);

//        Functionality to check the amount of Product (GoT) is in Inventory (product_inv)


    }
}
