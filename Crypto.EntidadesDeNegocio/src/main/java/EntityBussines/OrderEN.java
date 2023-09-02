
package EntityBussines;

import java.sql.Date;
/**
 *
 * @author themi
 */
public class OrderEN {
    private int id;
    private Date dateOrder;
    private String email;
    private int productId;
    private double quantity;
    private double total;

    // Constructor
    public OrderEN() {
    }

    public OrderEN( Date dateOrder, String email, int productId, double quantity, double total) {
        this.dateOrder = dateOrder;
        this.email = email;
        this.productId = productId;
        this.quantity = quantity;
        this.total = total;
    }

    // Getter for Id
    public int getId() {
        return id;
    }

    // Setter for Id
    public void setId(int id) {
        this.id = id;
    }

    // Getter for DateOrder
    public Date getDateOrder() {
        return dateOrder;
    }

    // Setter for DateOrder
    public void setDateOrder(Date dateOrder) {
        this.dateOrder = dateOrder;
    }

    // Getter for Email
    public String getEmail() {
        return email;
    }

    // Setter for Email
    public void setEmail(String email) {
        this.email = email;
    }

    // Getter for ProductId
    public int getProductId() {
        return productId;
    }

    // Setter for ProductId
    public void setProductId(int productId) {
        this.productId = productId;
    }

    // Getter for Quantity
    public double getQuantity() {
        return quantity;
    }

    // Setter for Quantity
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    // Getter for Total
    public double getTotal() {
        return total;
    }

    // Setter for Total
    public void setTotal(double total) {
        this.total = total;
    }
}
