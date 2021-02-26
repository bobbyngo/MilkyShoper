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
        System.out.println("browse | addToCart | removeItems | checkout | quit");
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


    private void addDisplay() {
        System.out.println("|----------THE COURSE STORE----------|");
        System.out.println("\\----------------ADD----------------/\n");
        System.out.println("Stock | ID | Product Name | Unit Price | Option");

        for (Integer i : sm.getKeySet()) {
            System.out.println(sm.getQuantity().get(i) + " | "
                    + sm.getProduct().get(i).getId() + " | "
                    + sm.getProduct().get(i).getName() + " | "
                    + "$" + sm.getProduct().get(i).getPrice()
                    + i );
        }
    }

    private void removeDisplay() {
        System.out.println("|----------THE COURSE STORE----------|");
        System.out.println("\\---------------REMOVE---------------/\n");
    }

    private void checkOutDisplay() {
        System.out.println("|----------THE COURSE STORE----------|");
        System.out.println("\\--------------CHECKOUT--------------/\n");
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

            if (demand.equals("addToCart")){
                addDisplay();
            }

            if (demand.equals("removeItems")){
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
