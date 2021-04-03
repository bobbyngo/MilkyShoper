package stores;
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

import org.junit.jupiter.api.extension.ExtensionContext;

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

    private final HashMap<Integer, String> IDIMAGE = new HashMap<>() {{
        put(0, "2%milk.png");
        put(1, "cowmilk.png");
        put(2, "buffalomilk.jpg");
        put(3, "goatmilk.jpg");
        put(4, "camelmilk.jpg");
    }};

    // Declaring the variables
    private StoreManager sm;
    private int cartID;

    // Declaring the frame
    private JFrame frame;

    // Declaring the panels
    private JPanel mainPanel;
    private JPanel headerPanel;                     // Declaring the header panel will hold simply the header label
    private JPanel bodyPanel;                        // Declaring the body panel will hold the store panel and the cart panel

    private JPanel storePanel;                      // Declaring the store panel which will hold the storePanelHeader and storePanelBody
    private JPanel storePanelHeader;                // Declaring the store header panel which will display the label
    private JPanel storePanelBody;                  // Declaring the store body panel which will hold the items of the store

    private JPanel cartPanel;                       // Declaring the cart panel which will hold the cartPanelHeader and cartPanelBody
    private JPanel cartPanelHeader;                 // Declaring the cart header panel which will display the label
    private JPanel cartPanelBody;                   // Declaring the cart body panel which will display the items in your cart

    private JPanel footerPanel;                     // Declaring the footerPanel where will have the check out button

    private JLabel headerLabel;                     // Declaring the header label; "The Milky Way"
    private JLabel storeLabel;                      // Declaring the store label; "Store"
    private JLabel cartLabel;                       // Declaring the cart label; "Cart"

    private boolean[] itemOutOfStock;               // Declaring the array of booleans to check if an item is out of stock

    GridBagConstraints c = new GridBagConstraints();


    /**
     * Constructor for the stores.StoreView
     *
     * @param sm StoreManager reference
     * @param cartID unique cart ID
     */
    public StoreView(StoreManager sm, int cartID) {
        this.sm = sm;
        this.cartID = cartID;

        //We have 4 layouts: main panel (border) -> panel body (gird layout) -> store, cart (box layout) -> product layout (grid bag layout)

        this.frame = new JFrame("Milk Store");
        this.mainPanel = new JPanel(new BorderLayout());                // Initializing the main panel; BorderLayout
        this.headerPanel = new JPanel();                                // Initializing the header panel
        this.bodyPanel = new JPanel(new GridLayout());                  // Initializing the body panel

        this.storePanel = new JPanel(new BorderLayout());               // Initializing the store panel
        this.storePanelHeader = new JPanel(new FlowLayout());
        this.storePanelBody = new JPanel();
        this.storePanelBody.setLayout(new BoxLayout(storePanelBody, BoxLayout.Y_AXIS));


        this.cartPanel = new JPanel(new BorderLayout());                // Initializing the cart panel
        this.cartPanelHeader = new JPanel(new FlowLayout());
        this.cartPanelBody = new JPanel();
        this.cartPanelBody.setLayout(new BoxLayout(cartPanelBody, BoxLayout.Y_AXIS));


        this.footerPanel = new JPanel(new GridBagLayout());

        this.headerLabel = new JLabel();
        this.storeLabel = new JLabel();
        this.cartLabel = new JLabel();

        //Initializing for product panel


        this.headerLabel.setText("The Milky Way");                                // Initializing the header label
        this.headerLabel.setFont(new Font("Serif", Font.BOLD, 24));     // Setting the font of the header label
        this.storeLabel.setText("Store");                                         // Initializing the store label
        this.storeLabel.setFont(new Font("Serif", Font.BOLD, 18));      // Setting the font of the store label
        this.cartLabel.setText("Cart");                                           // Initializing the cart label
        this.cartLabel.setFont(new Font("Serif", Font.BOLD, 18));       // Setting the font of the cart label


    }


    /**
     * This method will display the product information of the Inventory class and put it to the new JLabel
     * @param id the id of the product
     * @return JLabel, the JLabel which has the information of the product of the Inventory class
     */
    private JLabel displayProduct(int id) {
        JLabel updatedLabel = new JLabel();
        updatedLabel.setText("<html>" + "Name: " + sm.getProduct().get(id).getName() +
                "<br>Price: " + sm.getProduct().get(id).getPrice() + "$/unit <br> " + "Quantity: " + sm.getQuantity().get(id) + "</html>");
        return updatedLabel;
    }

    private JLabel displayCartItem(int id) {
        JLabel updatedLabel = new JLabel();
        updatedLabel.setText("<html>" + "Name: " + sm.getProduct().get(id).getName() +
                "<br>Price: " + sm.getProduct().get(id).getPrice() + "$/unit <br> " + "Quantity: " + sm.getCustomerCart(cartID).get(id) + "</html>");
        return updatedLabel;
    }

    /**
     * This method will display the image corresponding to the id of the product with the width 150 and height 150
     * and add the image to the new JLabel
     * @param id the id of the product
     * @return JLabel which has the image
     * @throws IOException
     */
    private JLabel imageMapping(int id) throws IOException {
        JLabel imagePanel = null;
        if (IDIMAGE.containsKey(id)) {
            InputStream in = getClass().getResourceAsStream(IDIMAGE.get(id));
            BufferedImage image = ImageIO.read(in);

            Image resizeImage = image.getScaledInstance(150, 150, Image.SCALE_SMOOTH);

            imagePanel = new JLabel(new ImageIcon(resizeImage));
        }
        return imagePanel;
    }

    /**
     * This method will loop through all the products in the Inventory and create the GidBagLayout each time for each product,
     * This method called the imageMapping and the displayProduct method to give the information of the product
     * The Add to cart button will call the removeCartInventory from the StoreManger inside its actionListener method, so that the
     * users can add the product to their cart
     * @throws IOException
     */
    private void productDisplay() throws IOException {

        for (Integer id : sm.getKeySet()) {

            JPanel itemInStorePanel = new JPanel(new GridBagLayout());
            itemInStorePanel.setPreferredSize(new Dimension(200, 250));
            itemInStorePanel.setBackground(new Color(166, 223, 242));
            Border blackline = BorderFactory.createLineBorder(Color.black);
            itemInStorePanel.setBorder(blackline);

            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 0;
            itemInStorePanel.add(imageMapping(id), c);

            c.gridx = 1;
            c.gridy = 0;
            JLabel productLabel = displayProduct(id);
            itemInStorePanel.add(productLabel, c);

            c.gridx = 0;
            c.gridy = 1;
            JSlider sld = new JSlider(JSlider.HORIZONTAL, 0, sm.getQuantity().get(id), 0);

            //Display number of product in the slider
            sld.setMinorTickSpacing(5);
            sld.setMajorTickSpacing(10);
            sld.setPaintTicks(true);
            sld.setPaintLabels(true);

            // Change listener on the slider
            // When the slider is moved to some integer x, store x in the 'value' variable
            int[] value = {0};
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
                        if (value[0] == 0){
                            JOptionPane.showMessageDialog(frame, "Must add at least one item");
                            return;
                        }
                        else if (value[0] > sm.getQuantity().get(id)) {
                            JOptionPane.showMessageDialog(frame, "This item has insufficient stock");
                            return;
                        }

                        int removedID = sm.getProduct().get(id).getId();
                        sm.removeCartInventory(removedID, value[0], cartID);

                        System.out.println("Inventory: " + sm.getQuantity().get(id));
                        System.out.println("slider value " + value[0]);
                        System.out.println("ShoppingCart: " + sm.getCustomerCart(cartID).get(id));

                        productLabel.setText("<html>" + "Name: " + sm.getProduct().get(id).getName() +
                                "<br>Price: " + sm.getProduct().get(id).getPrice() + "$/unit <br>" + "Quantity: "
                                + sm.getQuantity().get(id) + "</html>");

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            itemInStorePanel.add(btn, c);   // Adding the button to the item panel
            this.storePanelBody.add(itemInStorePanel);

            //For loop ended here
        }

        //Vertical scrollable
        JScrollPane scrollableTextArea = new JScrollPane(this.storePanelBody);
        scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        this.storePanel.add(scrollableTextArea, BorderLayout.CENTER);
    }


    private void cartDisplay() throws IOException {

        for (Integer id : sm.getCustomerCart(cartID).keySet()) {

            JPanel itemInCartPanel = new JPanel(new GridBagLayout());
            itemInCartPanel.setPreferredSize(new Dimension(200, 250));
            itemInCartPanel.setBackground(new Color(0, 223, 0));
            Border blackline = BorderFactory.createLineBorder(Color.black);
            itemInCartPanel.setBorder(blackline);

            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 0;
            itemInCartPanel.add(imageMapping(id), c);

            c.gridx = 1;
            c.gridy = 0;
            JLabel productLabel = displayCartItem(id);
            itemInCartPanel.add(productLabel, c);

            c.gridx = 0;
            c.gridy = 1;
            JSlider sld = new JSlider(JSlider.HORIZONTAL, 0, sm.getCustomerCart(cartID).get(id), 0);

            //Display number of product in the slider
            sld.setMinorTickSpacing(5);
            sld.setMajorTickSpacing(10);
            sld.setPaintTicks(true);
            sld.setPaintLabels(true);

            // Change listener on the slider
            // When the slider is moved to some integer x, store x in the 'value' variable
            //int[] value = {0};
            int[] value = {0};
            sld.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent changeEvent) {
                    JSlider slider = (JSlider) changeEvent.getSource();
                    if (!slider.getValueIsAdjusting()) {    // If the slider is not currently moving carry out the below
                        value[0] = slider.getValue();
                    }
                }
            });
            itemInCartPanel.add(sld, c);   // Adding the slider to the item panel

            c.gridx = 1;
            c.gridy = 1;
            JButton btn = new JButton("Remove from cart");
            // Action listener on the "Add to cart" button
            // When an action (i.e., press) is performed on the button remove the 'value' amount (described in the
            // above ChangeListener) of the current product, from the cart with the corresponding cartID.
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    try {
                        if (value[0] == 0) {
                            JOptionPane.showMessageDialog(frame, "Must remove at least one item");
                            return;
                        }
                        else if (value[0] > sm.getQuantity().get(id)){
                            JOptionPane.showMessageDialog(frame, "You are removing more items than are in your cart");
                            return;
                        }

                        int removedID = sm.getProduct().get(id).getId();
                        sm.addCartInventory(removedID, value[0], cartID);

                        System.out.println("Inventory: " + sm.getQuantity().get(id));
                        System.out.println("slider value " + value[0]);
                        System.out.println("ShoppingCart: " + sm.getCustomerCart(cartID).get(id));

                        productLabel.setText("<html>" + "Name: " + sm.getProduct().get(id).getName() +
                                "<br>Price: " + sm.getProduct().get(id).getPrice() + "$/unit <br>" + "Quantity: "
                                + sm.getCustomerCart(cartID).get(id) + "</html>");


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            itemInCartPanel.add(btn, c);   // Adding the button to the item panel
            this.cartPanelBody.add(itemInCartPanel);

            //For loop ended here
        }
        //Vertical scrollable
        JScrollPane scrollableTextArea = new JScrollPane(this.cartPanelBody);
        scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        this.cartPanel.add(scrollableTextArea, BorderLayout.CENTER);
    }

    /**
     * This method will create the check out button at the footerPanel
     * The checkout button will call the processTransaction method from the StoreManger class which will display the receipt message
     * After the OK button inside the receipt message is clicked, the program will exit
     */
    private void footerDisplay() {

        JButton checkOutBtn = new JButton("Check Out");
        checkOutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (sm.getCustomerCart(cartID).size() == 0) {
                    JOptionPane.showMessageDialog(frame, "Your stock is empty", "Your Receipt", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, "Amount | ID | stores.Product Name | Unit Price\n"
                            + sm.processTransaction(cartID), "Your Receipt", JOptionPane.PLAIN_MESSAGE);
                    System.exit(-1);
                }
            }
        });

        this.footerPanel.add(checkOutBtn);
    }

    /**
     * This method will add all the panels to the frame and display the GUI
     * @throws IOException
     */
    public void displayGUI() throws IOException {
        this.headerPanel.setPreferredSize(new Dimension(600, 100));
        this.bodyPanel.setPreferredSize(new Dimension(800, 400));
        this.footerPanel.setPreferredSize(new Dimension(600, 100));
        this.storePanel.setPreferredSize(new Dimension(600, 600));
        this.cartPanel.setPreferredSize(new Dimension(600, 600));

        productDisplay();
        cartDisplay();
        footerDisplay();

        this.headerPanel.add(this.headerLabel);
        this.storePanelHeader.add(this.storeLabel);
        this.cartPanelHeader.add(this.cartLabel);


        //Add storePanel to the main body panel
        this.storePanel.add(storePanelHeader, BorderLayout.PAGE_START);
        this.bodyPanel.add(storePanel);


        this.cartPanel.add(cartPanelHeader, BorderLayout.PAGE_START);
        this.bodyPanel.add(cartPanel);

        this.mainPanel.add(headerPanel, BorderLayout.PAGE_START);
        this.mainPanel.add(bodyPanel, BorderLayout.CENTER);
        this.mainPanel.add(footerPanel, BorderLayout.PAGE_END);

        this.frame.add(this.mainPanel);                                 // Adding the main panel to the frame
        this.frame.pack();                                              // Packing the frame

        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.frame.addWindowListener(new WindowAdapter() {                                          // Listener attached to the frame, i.e., if close clicked carry out below code
            @Override
            public void windowClosing(WindowEvent we) {                                             // When the action is that of a window closing carry out below code
                if (JOptionPane.showConfirmDialog(frame, "Are you sure you want to quit?")  // If the option selected is okay carry out below code
                        == JOptionPane.OK_OPTION) {
                    // close it down!
                    frame.setVisible(false);                                                        // Setting the frame visibility of the frame to FALSE
                    frame.dispose();                                                                // Disposing of the frame
                    System.exit(-1);
                }
            }
        });

        //Set the visible of the frame
        this.frame.setVisible(true);
    }


    /**
     * The helpDisplay method will give the information about the command lines
     */
    private void helpDisplay() {
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

    /**
     * The removeDisplay will be called when the user want to remove items from their cart. This methods will called the addCartInventory
     * method in the stores.StoreManager class which will remove the items from their cart and add the items back to the stores.Inventory.
     */
    private void removeDisplay() {
        if (sm.getCustomerCart(cartID).size() == 0) {
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
     * YET TO IMPLEMENT
     * The checkOutDisplay will print out the information of the products in the customer's cart with the total price
     */
    private void checkOutDisplay() {
        System.out.println("|----------THE COURSE STORE----------|");
        System.out.println("\\-------------CHECK OUT-------------/\n");
        System.out.println("\\-------------YOUR CART------------/\n");
        if (sm.getCustomerCart(cartID).size() == 0) {
            System.out.println("Your cart is empty, The amount to pay is $0\n");

        } else {
            System.out.println("Amount | ID | stores.Product Name | Unit Price");
            //Display the total
            System.out.println(sm.processTransaction(cartID));
        }
    }

    /**
     * This method will display the user interface, it will display all the functions that the stores.StoreView has such as add items,
     * remove items, help, browse the store, check out and quit.
     *
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

        if (demand.equals("browse")) {
//            browseDisplay();
        }

        if (demand.equals("add")) {
//            addDisplay();
        }

        if (demand.equals("remove")) {
            removeDisplay();
        }

        if (demand.equals("checkout")) {
            checkOutDisplay();
            System.out.println("Good bye");
            return true;
        }

        if (demand.equals("quit")) {             //if quit without checkout the product will be added back to the inventory
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


    public static void main(String args[]) throws IOException {
        //Change StoreView
        StoreManager sm = new StoreManager();
        StoreView sv = new StoreView(sm, sm.assignNewCartID());
        sv.displayGUI();

/*        StoreView sv1 = new StoreView(sm, sm.assignNewCartID());
        StoreView sv2 = new StoreView(sm, sm.assignNewCartID());
        StoreView sv3 = new StoreView(sm, sm.assignNewCartID());
        StoreView[] users = {sv1, sv2, sv3};
        JComboBox storeList = new JComboBox(users);
        storeList.setSelectedIndex(0);
        storeList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });

        //sv1.footerPanel.add(storeList);

        int activeSV = users.length;*/


/*

        Scanner sc = new Scanner(System.in);
        while (activeSV > 0) {
            System.out.print("CHOOSE YOUR STOREVIEW >>> ");

            int choice;

            //Try-catch Exception block, if the user input invalid storeview number, the error will be displayed
            try {
                choice = sc.nextInt();
            } catch (Exception e) {
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
        System.out.println("ALL STOREVIEWS DEACTIVATED");*/

    }
}
