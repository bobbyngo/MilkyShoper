import java.util.HashMap;

public class Inventory {
    private HashMap<Integer, Product> infoProduct = new HashMap<>(); //(id, Product)
    private HashMap<Integer, Integer> id_quantity = new HashMap<>(); //(id, quantity)


    /**
     * Constructor of Inventory
     */
    public Inventory(){
        Product p1 = new Product("1% milk", 01, 4.30);
        Product p2 = new Product("2% milk", 02, 3.30);
        Product p3 = new Product("3% milk", 03, 5.00);
        /**
         * Initialize the id-product
         */
        infoProduct.put(01,p1);
        infoProduct.put(02,p2);
        infoProduct.put(03,p3);

        /**
         * Initialize the id-quantity
         */
        id_quantity.put(01,1);
        id_quantity.put(02,2);
        id_quantity.put(03,0);
    }

    /**
     * Get the amount of stock for a given Product ID (Note: it is possible the Product does not exist in
     * the Inventory!)
     */
    public int gettingQuantity (int id) {
        for (Integer i : id_quantity.keySet() ) {
            if (i == id) {
                return getId_quantity().get(i);
            }else {
                return -1;
            }
        }
        return -1;
    }

    /**
     * Add a specified amount of stock for a given Product to the inventory (Note: new Products can be
     * added!).
     */
    public void addingQuantity (Product p,int amount) {
        for (Product i: infoProduct.values()) {
            if (i.equals(p)) {
                id_quantity.put(p.getId(), amount + id_quantity.get(p.getId()));
            }else {
                id_quantity.put(p.getId(),amount);
            }
        }
    }

    /**
     * Remove a specified amount of stock for a given Product ID from the inventory (Note: you cannot
     * have negative stock, and you cannot delete Products from the Inventory; if a Product’s stock
     * reaches 0, leave it.).
     */
    public void removingQuantity (int id,int amount) {
        if (id_quantity.containsKey(id)) {
            if (id_quantity.get(id) == 0) {
                //return 0;
                System.out.println(id_quantity.get(id));
            } else {
                if (id_quantity.get(id) - amount < 0){
                    System.out.println(-1);
                    //return -1;
                    //}else {
                    //id_quantity.put(id, id_quantity.get(id) - amount);
                }
            }
            id_quantity.put(id, id_quantity.get(id) - amount);
        }
        System.out.println("The id is not in Product");
        //return id_quantity.put(id, id_quantity.get(id) - amount);
    }

    /**
     * Get information on a Product given a Product ID
     */
    public void gettingProduct (int id) {
        for (Integer i : infoProduct.keySet()) {
            if (i.equals(id)) ;
            System.out.println("The Product name: "+  infoProduct.get(i).getName()
                    + "ID: "+ infoProduct.get(i).getId() + "price " + infoProduct.get(i).getPrice());
        }
    }

    /**
     * Set and Get methods for the hashmap id and quantity in stock
     */
    public HashMap<Integer, Integer> getId_quantity() {
        return id_quantity;
    }

    public void setId_quantity(HashMap<Integer, Integer> id_quantity) {
        this.id_quantity = id_quantity;
    }

    /**
     * Set and get methods for the hashmap id and product in stock
     */

    public HashMap<Integer, Product> getInfoProduct() {
        return infoProduct;
    }

    public void setInfoProduct(HashMap<Integer, Product> infoProduct) {
        this.infoProduct = infoProduct;
    }
}

