// GABRIEL BENNI KELLEY EVENSEN
// 101119814
// Ngo Huu Gia Bao
// 101163137
/**
 * A class representing the view of the store
 *
 * @author Gabriel Benni Kelley Evensen, 101119814
 * @author Ngo Huu Gia Bao, 101163137
 * @version 1.0
 */

import java.util.Scanner;

public class StoreView {

    private StoreManager sm;
    private int cartID;

    /**
     * Constructor for the StoreView
     * @param sm
     * @param cartID
     */
    public StoreView(StoreManager sm, int cartID){
        this.sm = sm;
        this.cartID = cartID;
    }

    /**
     * The helpDisplay method will give the information about the command lines
     */
    private void helpDisplay(){
        System.out.println("browse | add | remove | checkout | quit");
    }

    /**
     * The browseDisplay will print out all the information about the products that exist in the inventory
     * with the give format
     */
    private void browseDisplay() {
        System.out.println("|----------THE COURSE STORE----------|");
        System.out.println("\\---------------BROWSE---------------/\n");
        System.out.println("Stock | ID | Product Name | Unit Price");

        for (Integer i : sm.getKeySet()) {
            System.out.println(sm.getQuantity().get(i) + " | "
                    + sm.getProduct().get(i).getId() + " | "
                    + sm.getProduct().get(i).getName() + " | "
                    + "$" + sm.getProduct().get(i).getPrice());
        }
    }

    /**
     * The addDisplay will be called when the user want to add the items into their cart. This methods will called the removeCartInventory
     * method in the StoreManager class which will add the items into their cart and remove the items from the Inventory.
     */
    private void addDisplay() {
        System.out.println("|----------THE COURSE STORE----------|");
        System.out.println("\\----------------ADD----------------/\n");
        System.out.println("Stock | ID | Product Name | Unit Price ");

        for (Integer i : sm.getKeySet()) {
            System.out.println(sm.getQuantity().get(i) + " | "
                    + sm.getProduct().get(i).getId() + " | "
                    + sm.getProduct().get(i).getName() + " | "
                    + "$" + sm.getProduct().get(i).getPrice());
        }
        System.out.println("\nPlease choose the ID of the Product to add");
        Scanner myObj = new Scanner(System.in);
        int id = myObj.nextInt();


        if (sm.getQuantity().containsKey(id)) {
            System.out.println("\nPlease choose the amount want to add");
            myObj = new Scanner(System.in);
            int quantity = myObj.nextInt();

            sm.removeCartInventory(id, quantity, cartID);

        }else {
            System.out.println("\nYour ID is not available " +
                                "\nPlease choose the available ID");
        }
    }

    /**
     * The removeDisplay will be called when the user want to remove items from their cart. This methods will called the addCartInventory
     * method in the StoreManager class which will remove the items from their cart and add the items back to the Inventory.
     */
    private void removeDisplay() {
        if (sm.getCustomerCart(cartID).size() == 0){
            System.out.println("Your cart is empty, nothing to remove");

        } else {
            System.out.println("|----------THE COURSE STORE----------|");
            System.out.println("\\---------------REMOVE---------------/\n");
            System.out.println("Stock | ID | Product Name | Unit Price ");

            for (Integer i : sm.getCustomerCart(cartID).keySet()) {
                System.out.println(sm.getCustomerCart(cartID).get(i) + " | "
                        + sm.getProduct().get(i).getId() + " | "
                        + sm.getProduct().get(i).getName() + " | "
                        + "$" + sm.getCustomerCart(cartID).get(i) * sm.getProduct().get(i).getPrice());
            }

            System.out.println("\nPlease choose the ID of the Product to remove");
            Scanner myObj = new Scanner(System.in);
            int id = myObj.nextInt();

            if (sm.getCustomerCart(cartID).containsKey(id)) {
                System.out.println("\nPlease choose the amount want to remove");
                myObj = new Scanner(System.in);
                int quantity = myObj.nextInt();

                sm.addCartInventory(id, quantity, cartID);

            }else {
                System.out.println("\nYour ID is not available " +
                        "\nPlease choose the available ID");
            }
        }

    }

    /**
     * The checkOutDisplay will print out the information of the products in the customer's cart with the total price
     */
    private void checkOutDisplay() {
        System.out.println("|----------THE COURSE STORE----------|");
        System.out.println("\\-------------CHECK OUT-------------/\n");
        System.out.println("\\-------------YOUR CART------------/\n");
        if (sm.getCustomerCart(cartID).size() == 0){
            System.out.println("Your cart is empty");

        }else {
            System.out.println("Amount | ID | Product Name | Unit Price");
            //Display the total
            System.out.println(sm.processTransaction(cartID));
        }
    }

    /**
     * This method will display the user interface, it will display all the functions that the StoreView has such as add items,
     * remove items, help, browse the store, check out and quit.
     * @return true if the user enters quit, otherwise false
     */
    public boolean displayUI() {
        //Create scanner object
        Scanner myObj = new Scanner(System.in);
        System.out.println("\nType help for the list of commands\nEnter command: ");
        //Read user input
        String demand = myObj.nextLine();

        //while (!demand.equals("quit")) {

        if (demand.equals("help")) {
            helpDisplay();
        }

        if (demand.equals("browse")){
            browseDisplay();
        }

        if (demand.equals("add")){
            addDisplay();
        }

        if (demand.equals("remove")){
            removeDisplay();
        }

        if (demand.equals("checkout")){
            checkOutDisplay();
            System.out.println("Good bye");
            return true;
        }

        if (demand.equals("quit")){             //if quit without checkout the product will be added back to the inventory
            sm.emptyCustomerCart(cartID);
            System.out.println("Good bye");
            return true;

        }
        return false;
    }



    public static void main (String args[]) {

        StoreManager sm = new StoreManager();
        StoreView sv1 = new StoreView(sm, sm.assignNewCartID());
        StoreView sv2 = new StoreView(sm, sm.assignNewCartID());
        StoreView sv3 = new StoreView(sm, sm.assignNewCartID());
        StoreView[] users = {sv1, sv2, sv3};
        int activeSV = users.length;
        Scanner sc = new Scanner(System.in);
        while (activeSV > 0) {
            System.out.print("CHOOSE YOUR STOREVIEW >>> ");
            int choice = sc.nextInt();
            if (choice < users.length && choice >= 0) {
                if (users[choice] != null) {
                    String chooseAnother = "";
                    while (!chooseAnother.equals("y") && !chooseAnother.equals("Y")) {
                        // this implementation of displayGUI waits for input and displays the page
                        // corresponding to the user's input. it does this once, and then returns
                        // true if the user entered 'checkout' or 'quit'.
                        if (users[choice].displayUI()) {
                            users[choice] = null;
                            activeSV--;
                            break;
                        }
                        System.out.println("GO TO ANOTHER STOREVIEW? (y) ");
                        System.out.println("IF NOT YOU CAN ENTER ANYTHING ");
                        chooseAnother = sc.next();
                    }
                } else {
                    System.out.println("MAIN > ERROR > BAD CHOICE\nTHAT STOREVIEW WAS DEACTIVATED");
                }
            } else {
                System.out.println(
                        String.format("MAIN > ERROR > BAD CHOICE\nPLEASE CHOOSE IN RANGE [%d, %d]",
                                0, users.length - 1)
                );
            }
        }
        System.out.println("ALL STOREVIEWS DEACTIVATED");
    }
}
