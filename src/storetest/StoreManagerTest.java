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

    /**
     * Method to initialize required variables
     */
    @BeforeAll
    public static void init() {
        sm = new StoreManager();
        sc1 = new ShoppingCart(sm.assignNewCartID());

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
//    @Test
//    public void testRemoveCartInventory() {
//        sm.addCartInventory(0, 20, sc1.getCartID());
//        sm.removeCartInventory(0, 10, sc1.getCartID());
//        assertEquals(10, sm.getShoppingCart().get(sc1.getCartID()).getCustomerCart().get(0), "The removeCartInventory() method returned unexpected result.");
//    }
//
//    @Test
//    public void testAddCartInventory() {
//
//    }


//    /**
//     * Method to test the processTransaction method in StoreManager.java
//     */
//    @Test
//    public void testProcessTransaction() {
//
//        assertEquals(
//                "10 | 0 | 1% milk | 43.0\n" +
//                "The total is: 43.0\n" +
//                "Good bye", sm.getInventory().getIdQuantity().get(0));
//    }
//
    @Test
    public void testEmptyCustomerCart () {
        sm.emptyCustomerCart(sc1.getCartID());
//        assertEquals();
    }
}
