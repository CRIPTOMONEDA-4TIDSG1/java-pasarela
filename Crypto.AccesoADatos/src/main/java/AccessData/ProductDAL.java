
package AccessData;

import comunDB.ComunDB;
import EntityBussines.ProductEN;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author themi
 */
public class ProductDAL {
      
 public void create(ProductEN product) throws SQLException {
        String sql = "INSERT INTO Cryptocurrencies (cryptoName, descriptionCrypto, price, amount) VALUES (?, ?, ?, ?)";

        try (Connection conn = ComunDB.obtenerConexion();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1,  product.getCryptoName());
            statement.setString(2, product.getDescriptionCrypto());
            statement.setDouble(3, product.getPrice());
            statement.setInt(4, product.getAmount());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error creating the Product.", e);
        }
    }
 
    public List<ProductEN> searchAll() throws SQLException {
        String sql = "SELECT cryptoName, descriptionCrypto, price, amount, FROM Cryptocurrencies";
        List<ProductEN> productList = new ArrayList<>();

        try (Connection conn = ComunDB.obtenerConexion();
             PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                ProductEN product = new ProductEN();
                product.setCryptoName(resultSet.getString("cryptoName"));
                product.setDescriptionCrypto(resultSet.getString("descriptionCrypto"));
                product.setPrice(resultSet.getDouble("price"));
                product.setAmount(resultSet.getInt("amount"));

                productList.add(product);
            }
        } catch (SQLException e) {
            throw new SQLException("Error searching for the products.", e);
        }

        return productList;
    }

    public ProductEN searchById(int id) throws SQLException {
        String sql = "SELECT cryptoName, descriptionCrypto, price, amount  FROM Cryptocurrencies WHERE id = ?";
        ProductEN product = null;

        try (Connection conn = ComunDB.obtenerConexion();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    product = new ProductEN();
                    product.setCryptoName(resultSet.getString("cryptoName"));
                    product.setDescriptionCrypto(resultSet.getString("descriptionCrypto"));
                    product.setPrice(resultSet.getDouble("price"));
                    product.setAmount(resultSet.getInt("amount"));            
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error searching for the product by ID.", e);
        }

        return product;
    }
}