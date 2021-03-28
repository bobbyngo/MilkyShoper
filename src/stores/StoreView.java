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

import java.awt.image.*;
import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.awt.*;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.Math;
import javax.swing.border.*;
import javax.swing.event.*;

public class StoreView {

    // Declaring the variables
    private StoreManager sm;
    private int cartID;

    // Declaring the frame
    private JFrame frame;

    // Declaring the panels
    private JPanel mainPanel;
    private JPanel headerPanel;             // Declaring the header panel will hold simply the header label
    private JPanel bodyPanel;               // Declaring the body panel will hold the store panel and the cart panel
    private JPanel storePanel;              // Declaring the store panel which will hold the items of the store
    private JPanel cartPanel;               // Declaring the cart panel which will display the items in your cart
//    private JPanel itemInStorePanel;        // Declaring the item panel which will house the information on each item in the store
//    private JPanel itemInCartPanel;         // "" in the cart

    // Declaring the header label, the items list, and the quit button
    private JLabel headerLabel;             // Declaring the header label; "The Milky Way"
    private JLabel storeLabel;              // Declaring the store label; "Store"
    private JLabel cartLabel;               // Declaring the cart label; "Cart"
    private ArrayList<JPanel> itemsList;    // Declaring the panels inside of the body panel may not be needed
    private boolean[] itemOutOfStock;       // Declaring the array of booleans to check if an item is out of stock
    private JButton quitBtn;                // Declaring the button to generate a new palette
    private JButton addItemToCartBtn;       // Declaring the button to add items FROM the store TO the cart
    private JButton addItemToStoreBtn;      // Declaring the button to REMOVE items from the cart TO the store (i.e., add to the store from the cart)
    private JSlider addItemsToCartSld;      // Declaring the slider for how many of a certain item to add to cart
    private JSlider addItemsToStoreSld;     // Declaring the slider for how many of a certain item to remove from the cart

    GridBagConstraints c = new GridBagConstraints();




    /**
     * Constructor for the stores.StoreView
     * @param sm
     * @param cartID
     */
    public StoreView(StoreManager sm, int cartID){
        this.sm = sm;
        this.cartID = cartID;

        storeViewGenerator();
    }

//    ##################################
//    GUI Methods
//    ##################################

    private void storeViewGenerator(){
        this.frame = new JFrame("Milk Store");
        this.mainPanel = new JPanel(new BorderLayout());            // Initializing the main panel; BorderLayout
        this.headerPanel = new JPanel();                            // Initializing the header panel
        this.bodyPanel = new JPanel(new BorderLayout());            // Initializing the body panel
        this.storePanel = new JPanel(new BorderLayout());           // Initializing the store panel
        this.cartPanel = new JPanel(new BorderLayout());            // Initializing the cart panel

        this.headerLabel.setText("The Milky Way");                                // Initializing the header label
        this.headerLabel.setFont(new Font("Serif", Font.BOLD, 24));     // Setting the font of the header label
        this.storeLabel.setText("Store");                                         // Initializing the store label
        this.storeLabel.setFont(new Font("Serif", Font.BOLD, 18));      // Setting the font of the store label
        this.cartLabel.setText("Cart");                                           // Initializing the cart label
        this.cartLabel.setFont(new Font("Serif", Font.BOLD, 18));       // Setting the font of the cart label

        this.quitBtn = new JButton("Quit");
        quitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame.setVisible(false);
                frame.dispose();
            }
        });

    }

    public void displayGUI(){
        this.bodyPanel.setPreferredSize(new Dimension(250, 100));
        this.headerPanel.setPreferredSize(new Dimension(250, 100));

        this.headerPanel.add(this.headerLabel);
        this.storePanel.add(this.storeLabel);
        this.cartPanel.add(this.cartLabel);

        createStorePanels();

    }

    private void createStorePanels(){

        InputStream imageStream = this.getClass().getResourceAsStream("src/stores/milk.jpg"); // Getting the milk image
        BufferedImage image = null;
        try {
            image = ImageIO.read(imageStream);                            // Assigning to image
        } catch (IOException e) {                                         // Catch for I/O exceptions
            e.printStackTrace();                                          // Print error to StackTrace
        }
        JLabel picLabel = new JLabel(new ImageIcon(image));               // Assigning the image to a JLabel
        picLabel.setPreferredSize(new Dimension(30, 30));     // Setting the preferred image dimension

        for (Integer i : sm.getKeySet()) {
            JPanel itemInStorePanel = new JPanel(new GridBagLayout());
            itemInStorePanel.setPreferredSize(new Dimension(80, 100));

            c.gridx = 0;
            c.gridy = 0;
            itemInStorePanel.add(picLabel, c);

            c.gridx = 1;
            c.gridy = 0;
            itemInStorePanel.add(new JLabel(sm.getProduct().get(i).getName() + " at " +
                    sm.getProduct().get(i).getPrice() + "$/unit"), c);

            c.gridx = 0;
            c.gridy = 1;
            JSlider sld = new JSlider(JSlider.HORIZONTAL, 0, sm.getQuantity().get(i), 0);
            // Change listener on the slider
            // When the slider is moved to some integer x, store x in the 'value' variable
            final int[] value = {0};
            sld.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent changeEvent) {
                    JSlider slider = (JSlider) changeEvent.getSource();
                    if (!slider.getValueIsAdjusting()) {    // If the slider is not currently moving carry out the below
                        value[0] = slider.getValue();
                    }
                }
            });
            itemInStorePanel.add(sld, c);   // Adding the slider to the item panel

            c.gridx = 1;
            c.gridy = 1;
            JButton btn = new JButton("Add to cart");
            // Action listener on the "Add to cart" button
            // When an action (i.e., press) is performed on the button remove the 'value' amount (described in the
            // above ChangeListener) of the current product, from the cart with the corresponding cartID.
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    try {
                        sm.removeCartInventory(sm.getProduct().get(i).getId(), value[0], cartID);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            itemInStorePanel.add(btn, c);   // Adding the button to the item panel
        }
    }


//    ##################################
//    Console Methods
//    ##################################

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
//    private void browseDisplay() {
//        System.out.println("|----------THE COURSE STORE----------|");
//        System.out.println("\\---------------BROWSE---------------/\n");
//        System.out.println("Stock | ID | stores.Product Name | Unit Price");
//
//        for (Integer i : sm.getKeySet()) {
//            System.out.println(sm.getQuantity().get(i) + " | "
//                    + sm.getProduct().get(i).getId() + " | "
//                    + sm.getProduct().get(i).getName() + " | "
//                    + "$" + sm.getProduct().get(i).getPrice());
//        }
//    }

    /**
     * The addDisplay will be called when the user want to add the items into their cart. This methods will called the removeCartInventory
     * method in the stores.StoreManager class which will add the items into their cart and remove the items from the stores.Inventory.
     */
//    private void addDisplay() {
//        System.out.println("|----------THE COURSE STORE----------|");
//        System.out.println("\\----------------ADD----------------/\n");
//        System.out.println("Stock | ID | stores.Product Name | Unit Price ");
//
//        for (Integer i : sm.getKeySet()) {
//            System.out.println(sm.getQuantity().get(i) + " | "
//                    + sm.getProduct().get(i).getId() + " | "
//                    + sm.getProduct().get(i).getName() + " | "
//                    + "$" + sm.getProduct().get(i).getPrice());
//        }
//        System.out.println("\nPlease choose the ID of the stores.Product to add");
//        Scanner myObj = new Scanner(System.in);
//
//        try {
//            int id = myObj.nextInt();
//            if (sm.getQuantity().containsKey(id)) {
//                System.out.println("\nPlease choose the amount want to add");
//                myObj = new Scanner(System.in);
//                int quantity = myObj.nextInt();
//
//                sm.removeCartInventory(id, quantity, cartID);
//
//            }else {
//                System.out.println("\nYour ID is not available " +
//                        "\nPlease choose the available ID");
//            }
//        }catch (Exception e) {
//            System.out.println(e);
//            System.out.println("Please enter valid choice \n");
//        }
//
//    }

    /** YET TO IMPLEMENT
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

    /** YET TO IMPLEMENT
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
//            browseDisplay();
        }

        if (demand.equals("add")){
//            addDisplay();
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
