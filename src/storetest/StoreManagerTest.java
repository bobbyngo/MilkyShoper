package storetest;

// GABRIEL BENNI KELLEY EVENSEN
// 101119814
// Ngo Huu Gia Bao
// 101163137
/**
 * A class representing the tests in StoreManager.java
 *
 * @author Gabriel Benni Kelley Evensen, 101119814
 * @author Ngo Huu Gia Bao, 101163137
 * @version 1.0
 */

import java.util.*;
import org.junit.jupiter.api.*;
import stores.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import stores.*;

public class StoreManagerTest {
    private static StoreManager sm;
    private static ShoppingCart sc1;
    private static ShoppingCart sc2;
    private static Inventory inventory;

    /**
     * Method to initialize required variables
     */
    @BeforeAll
    public static void init() {
        sm = new StoreManager();
        sc1 = new ShoppingCart(sm.assignNewCartID());
        inventory = new Inventory();
    }

    /**
     * Method to test the assignNewCartID() method in StoreManager.java
     */
    @Test
    public void testAssignNewCartID() {
        assertEquals(0, sm.assignNewCartID(), "The assignNewCartID() method returned unexpected result.");
    }

    /**
     * Method to test the removeCartInventory() method in StoreManager.java
     */
    @Test
    public void testRemoveCartInventory() {
        sc1.addCustomerProduct(0, 20);
        sm.removeCartInventory(0, 10, sc1.getCartID());
        assertEquals(10, sm.getShoppingCart().get(sc1.getCartID()).getCustomerCart().get(0), "The removeCartInventory() method returned unexpected result.");
    }

    /*
    * Method to test the addCartInventory() method in StoreManager.java
    */
    @Test
    public void testAddCartInventory() {
//        sc1.addCustomerProduct(0, 10);
//        sc1.removeCustomerProduct(0, 5);
        sm.addCartInventory(0, 5, sc1.getCartID());
        assertEquals(5, sm.getShoppingCart().get(sc1.getCartID()).getCustomerCart().get(0), "The addCartInventory() method returned unexpected result.");
    }


//    /**
//     * Method to test the processTransaction method in StoreManager.java
//     */
//    @Test
//    public void testProcessTransaction() {
//
//        assertEquals(
//                "10 | 0 | 1% milk | 43.0\n" +
//                "The total is: 43.0\n", sm.getInventory().getIdQuantity().get(0));
//    }

    /*
    * Method to test the emptyCustomerCart method in Storemanager.java
    * */
    @Test
    public void testEmptyCustomerCart () {
        sm.emptyCustomerCart(sc1.getCartID());
        assertEquals(null, sm.getShoppingCart().get(sc1.getCartID()).getCustomerCart().get(0), "The addCartInventory() method returned unexpected result." );

    }
}
