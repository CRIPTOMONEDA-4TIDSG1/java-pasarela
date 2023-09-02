
package EntityBussines;
/**
 *
 * @author themi
 */
public class ProductEN {
    private int id;
    private String cryptoName;
    private String descriptionCrypto;
    private double  price;
    private int amount;

     // Constructor
    public ProductEN() {
    }
    
    
    public ProductEN(int id, String cryptoName, String descriptionCrypto, float price, int amount) {
        this.id = id;
        this.cryptoName = cryptoName;
        this.descriptionCrypto = descriptionCrypto;
        this.price = price;
        this.amount = amount;
    }
    
    // Getter for Id
    public int getId() {
        return id;
    }

    // Setter for Id
    public void setId(int id) {
        this.id = id;
    }

    // Getter for CryptoName
    public String getCryptoName() {
        return cryptoName;
    }

    // Setter for CryptoName
    public void setCryptoName(String cryptoName) {
        this.cryptoName = cryptoName;
    }

    // Getter for DescriptionCrypto
    public String getDescriptionCrypto() {
        return descriptionCrypto;
    }

    // Setter for DescriptionCrypto
    public void setDescriptionCrypto(String descriptionCrypto) {
        this.descriptionCrypto = descriptionCrypto;
    }

    // Getter for Price
    public double  getPrice() {
        return price;
    }

    // Setter for Price
    public void setPrice(double  price) {
        this.price = price;
    }

    // Getter for Amount
    public int getAmount() {
        return amount;
    }

    // Setter for Amount
    public void setAmount(int amount) {
        this.amount = amount;
    }
}

