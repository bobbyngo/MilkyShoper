// GABRIEL BENNI KELLEY EVENSEN
// 101119814
// Ngo Huu Gia Bao
// 101163137

import java.util.*;


public class StoreManager {
    private Inventory inventory = new Inventory();
    private int counterCartID = -1;
    private HashMap<Integer, ShoppingCart> shoppingCart = new HashMap<>();    //cartID,  shopping cart


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
    public void processTransactionOld(Integer[][] product_array){
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
     * This method will add the cartID as a key and the ShoppingCart object as the value to organize.
     * It will increment the counterCartID by 1 and pass it to the cartID inside the ShoppingCart class
     * @return incremented counterCartID
     */
    public int assignNewCartID() {
       counterCartID ++;
        ShoppingCart sc = new ShoppingCart(counterCartID);
        shoppingCart.put(counterCartID, sc);
        return counterCartID;
    }


    /**
     * This method will give the access to the customerCart HashMap inside the ShoppingCart class
     * @param cartID int, the unique cart ID of the customer
     * @return HashMap, the customerCart which stores the id and quantity of the product
     */
    public HashMap<Integer, Integer> getCustomerCart(int cartID) {
        return shoppingCart.get(cartID).getCustomerCart();
    }


    /**
     * This method is called inside the addDisplay of the StoreView. It will check if the id and the amount is valid, then
     * add those information to the customerCart Hashmap in the ShoppingCart class first. Then the method will remove
     * those product from inventory class
     * @param id int, the id of the product that the customer wants to remove from their cart
     * @param quantity int, the quantity that the customer remove from their cart
     * @param cartID int, the unique cart ID of the customer
     */
    public void removeCartInventory (int id, int quantity, int cartID) {

        //Because the addCustomerProduct will check the availability of the product of the inventory class
        //so if we remove the product from the inventory first, we cannot add product to the cart

        //Add quantity of the given id to the customer cart first
        shoppingCart.get(cartID).addCustomerProduct(id, quantity, this.inventory);

        //Remove quantity of the given id from the inventory
        inventory.removingQuantity(id, quantity);
    }


    /**
     * This method is called inside the removeDisplay of the StoreView. It will check if the id and the amount is valid, then
     * remove those information from the customerCart Hashmap and add those back to the inventory class
     * @param id int, the id of the product that the customer wants to remove from their cart
     * @param quantity int, the quantity that the customer remove from their cart
     * @param cartID int, the unique cart ID of the customer
     */
    public void addCartInventory (int id, int quantity, int cartID) {

        //Because the removeCustomerProduct check the product of the customer not the inventory so we could
        //swap other way around

        //Add product with id back the amount to the inventory
        int originalAmount = inventory.getIdQuantity().get(id);
        inventory.getIdQuantity().put(id, originalAmount + quantity);

        //Remove the quantity of the given id from the cart
        shoppingCart.get(cartID).removeCustomerProduct(id, quantity);
    }


    /**
     * This method will print out the receipt of the customer by accessing the customerCart Hashmap inside the Shopping Cart class
     * and the gettingProduct method inside the Inventory class to retrieve product the information
     * @param cartID int, the unique cart ID of the customer
     * @return String which have been formatting as the receipt
     */
    public String processTransaction (int cartID) {

        StringBuilder stringBuilder = new StringBuilder();
        double total = 0;

        for (Integer i: shoppingCart.get(cartID).getCustomerCart().keySet()) {

            //Accessing to the every value (the quantity of the product) of the HashMap in the Shopping Cart with the id i
            stringBuilder.append(shoppingCart.get(cartID).getCustomerCart().get(i))
                    .append(" | ")

                    //get the id of the product given the id i
                    .append(inventory.gettingProduct(i).getId())
                    .append(" | ")

                    //get the name of the product given the id i
                    .append(inventory.gettingProduct(i).getName())
                    .append(" | ")

                    //get the price of the product given the id i and multiply with the quantity
                    .append(inventory.gettingProduct(i).getPrice() * shoppingCart.get(cartID).getCustomerCart().get(i))
                    .append("\n");


            total += inventory.gettingProduct(i).getPrice() * shoppingCart.get(cartID).getCustomerCart().get(i);

        }
        shoppingCart.remove(cartID);
        stringBuilder.append("The total is: ").append(total);
        return stringBuilder.toString();

    }

    /**
     * This method will be called inside the quit method of the StoreView. For the situation that the customer quit the store without
     * checking out. It will loop through the customerCart HashMap which is the customer's cart, the method will remove all of the items
     * and put them back to the inventory
     * @param cartID int, the unique cart ID of the customer
     */
    public void emptyCustomerCart (int cartID) {
        for (int i: shoppingCart.get(cartID).getCustomerCart().keySet()) {

            //The quantity that is inside the customer's cart
            int currentAmount = shoppingCart.get(cartID).getCustomerCart().get(i);

            //Add the quantity back to the IdQuantity HashMap inside the Inventory class
            inventory.getIdQuantity().put(i, currentAmount);

            //Remove the item inside the customerCart HashMap inside the ShoppingCart class
            shoppingCart.get(cartID).getCustomerCart().remove(i);
        }
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
