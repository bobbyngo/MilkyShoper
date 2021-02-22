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

    public void helpDisplay(){
        System.out.println("browse | addToCart | removeItems | checkout | quit");
    }

    public void browseDisplay() {
        System.out.println("|----------THE COURSE STORE----------|");
        System.out.println("\\---------------BROWSE---------------/\n");
        System.out.println("Stock | Product Name | Unit Price");

        for (Integer i : sm.getKeySet()) {
            System.out.println(sm.getQuantity().get(i) + " | "
                    + sm.getProduct().get(i).getName() + " | "
                    + sm.getProduct().get(i).getPrice());
        }
    }


    public void addDisplay() {
        System.out.println("|----------THE COURSE STORE----------|");
        System.out.println("\\----------------ADD----------------/\n");
    }

    public void removeDisplay() {
        System.out.println("|----------THE COURSE STORE----------|");
        System.out.println("\\---------------REMOVE---------------/\n");
    }

    public void checkOutDisplay() {
        System.out.println("|----------THE COURSE STORE----------|");
        System.out.println("\\--------------CHECKOUT--------------/\n");
    }

    public static void main (String args[]) {
        StoreManager sm1 = new StoreManager();
        StoreView sv1 = new StoreView(sm1,1);

        //Create scanner object
        Scanner myObj = new Scanner(System.in);
        System.out.println("\nType help for the list of commands\nEnter command: ");
        //Read user input
        String demand = myObj.nextLine();

        while (!demand.equals("quit")) {

            if (demand.equals("help")) {
                sv1.helpDisplay();
            }

            if (demand.equals("browse")){
                sv1.browseDisplay();
            }

            if (demand.equals("addToCart")){
                sv1.addDisplay();
            }

            if (demand.equals("removeItems")){
                sv1.removeDisplay();
            }

            if (demand.equals("checkout")){
                sv1.checkOutDisplay();
            }

            if (!demand.equals("quit")){
                myObj = new Scanner(System.in);
                System.out.println("\nType help for the list of commands \nEnter command: ");
                demand = myObj.nextLine();
            }
        }

    }
}
