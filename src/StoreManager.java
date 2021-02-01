import java.util.ArrayList;

public class StoreManager {

    private ArrayList<Product> product;

    Product product1 = new Product("milk", 01,4.00);
    //Inventory product2 = new Inventory("unknown", 1, id_quantity);


    public void printCourseList() {
        for (Product c : product) {
            System.out.println(c.getName() + " Id:" + c.getId() + " Price:"+c.getPrice());
        }
    }
}
