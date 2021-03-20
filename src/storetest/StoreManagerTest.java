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
    @Test
    public void testRemoveCartInventory() {

/*        sm.removeCartInventory(0, 5, sc1.getCartID());

        //Test customer cart is added or not
        assertEquals(5, sm.getShoppingCart().get(sc1.getCartID()).getCustomerCart().get(0),
                "The customerCart hashmap in the Shopping Cart class is not added \n");

        //Test inventory is removed or not
        assertEquals(15, sm.getInventory().getIdQuantity().get(0),
                "The idQuantity hashmap in the Inventory class is not removed\n");*/
        System.out.println("BOBBY'S TEST");


    }

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
//                "The total is: 43.0\n", sm.getInventory().getIdQuantity().get(0));
//    }
//
    @Test
    public void testEmptyCustomerCart () {
        sc1.addCustomerProduct(0, 20);
        sm.emptyCustomerCart(sc1.getCartID());
        assertEquals(0, sm.getInventory().getIdQuantity().get(0));
    }
}
