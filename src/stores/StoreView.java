package stores;// GABRIEL BENNI KELLEY EVENSEN
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
import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.Math;

public class StoreView {

    private StoreManager sm;
    private int cartID;
    private final JFrame frame;                                     // Declaring the JFrame
    private JLabel headerLabel;                                     // Declaring the header label
    private JPanel mainPanel, headerPanel, bodyPanel, footerPanel;  // Declaring the required panels
    private ArrayList<JPanel> itemsList;                           // Declaring the panels inside of the body panel
    private JButton quitButton;                                     // Declaring the button to generate a new palette

    /**
     * Constructor for the stores.StoreView
     * @param sm
     * @param cartID
     */
    public StoreView(StoreManager sm, int cartID){
        this.sm = sm;
        this.cartID = cartID;

        this.frame = new JFrame("The Milky Way");           // Initializing the JFrame
        this.itemsList = new ArrayList<>();                    // Initializing the body panels ArrayList<JPanel>

        this.headerLabel = new JLabel(
                "Kindly select from one of our milk kinds, below:-"
        );                                                      // Declaring and initializing the label for the header panel

        this.mainPanel = new JPanel(new BorderLayout());        // Initializing the main panel; recommended BorderLayout
        this.headerPanel = new JPanel();                        // Initializing the header panel
        this.bodyPanel = new JPanel(new GridLayout());          // Initializing body panel; recommended GridLayout
        this.footerPanel = new JPanel(new GridBagLayout());     // Initializing footer panel; recommended GridBagLayout

        this.quitButton = new JButton("Quit");                          // Initializing the "Generate Palette" button
        this.quitButton.addActionListener(new ActionListener() {            // Listener attached to the button, i.e., if clicked carry out below code
            @Override
            public void actionPerformed(ActionEvent actionEvent) {          // If the action is performed carry out below code
                frame.setVisible(false);                                    // Setting the frame visibility of the frame to FALSE
                frame.dispose();                                            // Disposing of the frame
            }
        });

    }

    /**
     * Method responsible for managing the GUI.
     * Adding the body, header, and footer panels to the main panel.
     * Setting up the lock panel operation.
     * Packing and adding the main panel to the frame.
     * Setting up frame closing menu, and setting frame visibility to TRUE.
     */
    public void displayGUI(){
        this.headerPanel.setPreferredSize(new Dimension(250, 100)); // Setting dimensions
        this.footerPanel.setPreferredSize(new Dimension(250, 100)); // ""

        this.headerPanel.add(this.headerLabel);                         // Adding header label to the header panel
        this.footerPanel.add(this.quitButton);                          // Adding the "Generate Palette" button to footer panel

        this.mainPanel.add(this.headerPanel, BorderLayout.PAGE_START);  // Adding the header panel to the main panel; at the page start
        this.mainPanel.add(this.bodyPanel, BorderLayout.CENTER);        // Adding the body panel to the main panel; at the page centre
        this.mainPanel.add(this.footerPanel, BorderLayout.PAGE_END);    // Adding the footer panel to the main panel; at the page end

        this.frame.add(this.mainPanel);                                 // Adding the main panel to the frame
        this.frame.pack();                                              // Packing the frame

        this.frame.addWindowListener(new WindowAdapter() {                                          // Listener attached to the frame, i.e., if close clicked carry out below code
            @Override
            public void windowClosing(WindowEvent we) {                                             // When the action is that of a window closing carry out below code
                if (JOptionPane.showConfirmDialog(frame, "Are you sure you want to quit?")  // If the option selected is okay carry out below code
                        == JOptionPane.OK_OPTION) {
                    // close it down!
                    frame.setVisible(false);                                                        // Setting the frame visibility of the frame to FALSE
                    frame.dispose();                                                                // Disposing of the frame
                }
            }
        });
        this.frame.setVisible(true);                                                                // Setting the frame visibility of the frame to TRUE
    }

    /*
     * Method reponsible for managing the buttons that lock the body panels; i.e., the colour changing panels
     * */
    public void storeAddRemoveOperation(){
        
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
        System.out.println("Stock | ID | stores.Product Name | Unit Price");

        for (Integer i : sm.getKeySet()) {
            System.out.println(sm.getQuantity().get(i) + " | "
                    + sm.getProduct().get(i).getId() + " | "
                    + sm.getProduct().get(i).getName() + " | "
                    + "$" + sm.getProduct().get(i).getPrice());
        }
    }

    /**
     * The addDisplay will be called when the user want to add the items into their cart. This methods will called the removeCartInventory
     * method in the stores.StoreManager class which will add the items into their cart and remove the items from the stores.Inventory.
     */
    private void addDisplay() {
        System.out.println("|----------THE COURSE STORE----------|");
        System.out.println("\\----------------ADD----------------/\n");
        System.out.println("Stock | ID | stores.Product Name | Unit Price ");

        for (Integer i : sm.getKeySet()) {
            System.out.println(sm.getQuantity().get(i) + " | "
                    + sm.getProduct().get(i).getId() + " | "
                    + sm.getProduct().get(i).getName() + " | "
                    + "$" + sm.getProduct().get(i).getPrice());
        }
        System.out.println("\nPlease choose the ID of the stores.Product to add");
        Scanner myObj = new Scanner(System.in);

        try {
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
        }catch (Exception e) {
            System.out.println(e);
            System.out.println("Please enter valid choice \n");
        }

    }

    /**
     * The removeDisplay will be called when the user want to remove items from their cart. This methods will called the addCartInventory
     * method in the stores.StoreManager class which will remove the items from their cart and add the items back to the stores.Inventory.
     */
    private void removeDisplay() {
        if (sm.getCustomerCart(cartID).size() == 0){
            System.out.println("Your cart is empty, nothing to remove\n");

        } else {
            System.out.println("|----------THE COURSE STORE----------|");
            System.out.println("\\---------------REMOVE---------------/\n");
            System.out.println("Stock | ID | stores.Product Name | Unit Price ");

            for (Integer i : sm.getCustomerCart(cartID).keySet()) {
                System.out.println(sm.getCustomerCart(cartID).get(i) + " | "
                        + sm.getProduct().get(i).getId() + " | "
                        + sm.getProduct().get(i).getName() + " | "
                        + "$" + sm.getCustomerCart(cartID).get(i) * sm.getProduct().get(i).getPrice());
            }

            System.out.println("\nPlease choose the ID of the stores.Product to remove");
            Scanner myObj = new Scanner(System.in);

            try {
                int id = myObj.nextInt();

                if (sm.getCustomerCart(cartID).containsKey(id)) {
                    System.out.println("\nPlease choose the amount want to remove");
                    myObj = new Scanner(System.in);
                    int quantity = myObj.nextInt();

                    sm.addCartInventory(id, quantity, cartID);

                } else {
                    System.out.println("\nYour ID is not available " +
                            "\nPlease choose the available ID");
                }
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("Please enter valid choice \n");
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
            System.out.println("Your cart is empty, The amount to pay is $0\n");

        }else {
            System.out.println("Amount | ID | stores.Product Name | Unit Price");
            //Display the total
            System.out.println(sm.processTransaction(cartID));
        }
    }

    /**
     * This method will display the user interface, it will display all the functions that the stores.StoreView has such as add items,
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

        if ((!demand.equals("help") && !demand.equals("browse") && !demand.equals("add") &&
        !demand.equals("remove") && !demand.equals("checkout") && !demand.equals("quit"))) {
            System.out.println("Please enter the valid command");
            System.out.println("Type help for the list of commands\n");
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

            int choice;

            //Try-catch Exception block, if the user input invalid storeview number, the error will be displayed
            try {
                choice = sc.nextInt();
            }catch (Exception e){
                System.out.println(e);
                System.out.println("Your store view choosing is not valid");
                System.out.println("Please try again\n");
                break;
            }

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
