
package AccessData;

import comunDB.ComunDB;
import EntityBussines.ProductEN;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author themi
 */
public class ProductDAL {
      
 public void create(ProductEN product) throws SQLException {
        String sql = "INSERT INTO Cryptocurrencies (cryptoName, descriptionCrypto, price, Amount) VALUES (?, ?, ?, ?)";

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
 
       public List<ProductEN> getAllProducts() {
        String sql = "SELECT * FROM Cryptocurrencies";
        List<ProductEN> products= new ArrayList<>();

        try (Connection connection = ComunDB.obtenerConexion();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                ProductEN product = new ProductEN(
                        resultSet.getInt("Id"),
                        resultSet.getString("cryptoName"),
                        resultSet.getString("descriptionCrypto"),
                        resultSet.getFloat("price"),
                        resultSet.getInt("Amount")
                       
                );
                products.add(product);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return products;
    }
}

//    public ProductEN searchById(int id) throws SQLException {
//        String sql = "SELECT cryptoName, descriptionCrypto, price, amount  FROM Cryptocurrencies WHERE id = ?";
//        ProductEN product = null;
//
//        try (Connection conn = ComunDB.obtenerConexion();
//             PreparedStatement statement = conn.prepareStatement(sql)) {
//            statement.setInt(1, id);
//            try (ResultSet resultSet = statement.executeQuery()) {
//                if (resultSet.next()) {
//                    product = new ProductEN();
//                    product.setCryptoName(resultSet.getString("cryptoName"));
//                    product.setDescriptionCrypto(resultSet.getString("descriptionCrypto"));
//                    product.setPrice(resultSet.getDouble("price"));
//                    product.setAmount(resultSet.getInt("amount"));            
//                }
//            }
//        } catch (SQLException e) {
//            throw new SQLException("Error searching for the product by ID.", e);
//        }
//
//        return product;
//    }
//}