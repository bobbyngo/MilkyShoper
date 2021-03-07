// GABRIEL BENNI KELLEY EVENSEN
// 101119814
// Ngo Huu Gia Bao
// 101163137

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class StoreView {

    private StoreManager sm;
    private int cartID;

    /**
     * Constructor
     * @param sm
     * @param cartID
     */
    public StoreView(StoreManager sm, int cartID){
        this.sm = sm;
        this.cartID = cartID;
    }

    private void helpDisplay(){
        System.out.println("browse | add | remove | checkout | quit");
    }

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
     * TODO: add and remove method doesn't work perfectly, after adding or removing, the inventory class does not display correctly
     * BUT the function work
     *
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

            sm.removeCartInventory(id, quantity);

        }else {
            System.out.println("\nYour ID is not available " +
                                "\nPlease choose the available ID");
        }
    }

    private void removeDisplay() {
        if (sm.getCustomerCart().size() == 0){
            System.out.println("Your cart is empty, nothing to remove");

        } else {
            System.out.println("|----------THE COURSE STORE----------|");
            System.out.println("\\---------------REMOVE---------------/\n");
            System.out.println("Stock | ID | Product Name | Unit Price ");

            for (Integer i : sm.getCustomerCart().keySet()) {
                System.out.println(sm.getCustomerCart().get(i) + " | "
                        + sm.getProduct().get(i).getId() + " | "
                        + sm.getProduct().get(i).getName() + " | "
                        + "$" + sm.getCustomerCart().get(i) * sm.getProduct().get(i).getPrice());
            }

            System.out.println("\nPlease choose the ID of the Product to remove");
            Scanner myObj = new Scanner(System.in);
            int id = myObj.nextInt();

            if (sm.getCustomerCart().containsKey(id)) {
                System.out.println("\nPlease choose the amount want to remove");
                myObj = new Scanner(System.in);
                int quantity = myObj.nextInt();
                sm.addCartInventory(id, quantity);
            }else {
                System.out.println("\nYour ID is not available " +
                        "\nPlease choose the available ID");
            }
        }

    }

    private void checkOutDisplay() {
        System.out.println("|----------THE COURSE STORE----------|");
        System.out.println("\\-------------CHECK OUT-------------/\n");
        System.out.println("\\--------------YOUR CART--------------/\n");
        if (sm.getCustomerCart().size() == 0){
            System.out.println("Your cart is empty");

        }else {
            System.out.println("Amount | ID | Product Name | Unit Price");

            for (Integer i : sm.getCustomerCart().keySet()) {
                System.out.println(sm.getCustomerCart().get(i) + " | "
                        + sm.getProduct().get(i).getId() + " | "
                        + sm.getProduct().get(i).getName() + " | "
                        + "$" + sm.getCustomerCart().get(i) * sm.getProduct().get(i).getPrice());
            }
        }
    }

    public boolean displayUI() {
        //Create scanner object
        Scanner myObj = new Scanner(System.in);
        System.out.println("\nType help for the list of commands\nEnter command: ");
        //Read user input
        String demand = myObj.nextLine();

        while (!demand.equals("quit")) {

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
            }

            if (!demand.equals("quit")){
                myObj = new Scanner(System.in);
                System.out.println("\nType help for the list of commands \nEnter command: ");
                demand = myObj.nextLine();
            }
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
                        System.out.print("GO TO ANOTHER STOREVIEW? (y) >>> ");
                        chooseAnother = sc.next();
                    }
                }
            }else {
                System.out.println("Please choose number in the range [0, " + (users.length - 1) + "]");
            }
        }
    }
}
