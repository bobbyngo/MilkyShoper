package storetest;

// GABRIEL BENNI KELLEY EVENSEN
// 101119814
// Ngo Huu Gia Bao
// 101163137
/**
 * A class representing the tests in Inventory.java
 *
 * @author Gabriel Benni Kelley Evensen, 101119814
 * @author Ngo Huu Gia Bao, 101163137
 * @version 1.0
 */

import java.util.*;
import org.junit.jupiter.api.*;
import stores.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InventoryTest {
    private static Inventory inventory;
    private static Set<Integer> availableProductIDs;

    /**
     * Method to initialize required variables.
     */
    @BeforeAll
    public static void init() {
        inventory = new Inventory();
        availableProductIDs = new HashSet<>();
    }

    /**
     * Method to test the getAvailableID() method in Inventory.java
     */
    @Test
    public void testGetAvailableID() {
        availableProductIDs.addAll(Arrays.asList(0, 1, 2, 3, 4));
        assertEquals(availableProductIDs, inventory.getAvailableID(), "The getAvailbleID() method returned unexpected result.");
    }

    /**
     * Method to test the gettingQuantity() method in Inventory.java
     */
    @Test
    public void testGettingQuantity() {
        assertEquals(20, inventory.gettingQuantity(0));
        assertEquals(60, inventory.gettingQuantity(1));
        assertEquals(39, inventory.gettingQuantity(2));
        assertEquals(80, inventory.gettingQuantity(3));
        assertEquals(25, inventory.gettingQuantity(4));
    }

    /**
     * Method to test the removingQuantity() method in Inventory.java
     */
    @Test
    public void testRemovingQuantity() {
        inventory.removingQuantity(0, 10);
        assertEquals(10, inventory.gettingQuantity(0), "The removingQuantity() method returned unexpected result.");
        inventory.removingQuantity(1, 10);
        assertEquals(50, inventory.gettingQuantity(1), "The removingQuantity() method returned unexpected result.");
        inventory.removingQuantity(2, 10);
        assertEquals(29, inventory.gettingQuantity(2), "The removingQuantity() method returned unexpected result.");
        inventory.removingQuantity(3, 10);
        assertEquals(70, inventory.gettingQuantity(3), "The removingQuantity() method returned unexpected result.");
        inventory.removingQuantity(4, 10);
        assertEquals(15, inventory.gettingQuantity(4), "The removingQuantity() method returned unexpected result.");
    }

}
