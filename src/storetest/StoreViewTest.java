package storetest;

// GABRIEL BENNI KELLEY EVENSEN
// 101119814
// Ngo Huu Gia Bao
// 101163137
/**
 * A class representing the tests in StoreView.java
 *
 * @author Gabriel Benni Kelley Evensen, 101119814
 * @author Ngo Huu Gia Bao, 101163137
 * @version 1.0
 */

import org.junit.jupiter.api.*;
import stores.*;

public class StoreViewTest {
    private static StoreView sv;
    private static StoreManager sm;

    @BeforeAll
    public static void init() {
        sm = new StoreManager();
        sv = new StoreView(sm, sm.assignNewCartID());
    }

    @Test
    public void testAssignNewCartID() {

    }

    @Test
    public void testRemoveCartInventory() {

    }

    @Test
    public void testAddCartInventory() {

    }


    @Test
    public void testProcessTransaction() {

    }

    @Test
    public void testEmptyCustomerCart () {

    }
}
