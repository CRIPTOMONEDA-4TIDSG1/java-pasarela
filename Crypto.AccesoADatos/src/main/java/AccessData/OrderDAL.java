
package AccessData;

import comunDB.ComunDB;
import EntityBussines.OrderEN;
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
public class OrderDAL {
    
 public void create(OrderEN order) {
        String sql = "INSERT INTO CryptoOrder (DateOrder, email, product_id, Quantity, total) VALUES (?, ?, ?, ?, ?)";
        
         ;
        try (Connection conn = ComunDB.obtenerConexion();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setDate(1, order.getDateOrder());
            statement.setString(2, order.getEmail());
            statement.setInt(3, order.getProductId());
            statement.setDouble(4, order.getQuantity());
            statement.setDouble(5, order.getTotal());
            statement.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
 
    public List<OrderEN> getAllOrders() {
        String sql = "SELECT * FROM CryptoOrder";
        List<OrderEN> orders = new ArrayList<>();

        try (Connection conn = ComunDB.obtenerConexion();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                OrderEN order = new OrderEN(
                resultSet.getInt("Id"),
                resultSet.getDate("DateOrder"),
                resultSet.getString("email"),
                resultSet.getInt("product_id"),
                resultSet.getDouble("Quantity"),
                resultSet.getDouble("total")
                );  orders.add(order);}
         } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return orders;
    }

    public OrderEN searchById(int id)  {
        String sql = "SELECT * FROM CryptoOrder WHERE Id = ?";
        OrderEN order = null;

        try (Connection conn = ComunDB.obtenerConexion();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    order = new OrderEN();
                    order.setId(resultSet.getInt("Id"));
                    order.setDateOrder(resultSet.getDate("dateOrder"));
                    order.setEmail(resultSet.getString("email"));
                    order.setProductId(resultSet.getInt("product_id"));
                    order.setQuantity(resultSet.getDouble("Quantity"));
                    order.setTotal(resultSet.getDouble("total"));
                }
            }
      } catch (SQLException e) {
            e.printStackTrace();
        }

        return order;
    }
}