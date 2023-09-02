
package AccessData;

import comunDB.ComunDB;
import EntityBussines.OrderEN;
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
        String sql = "SELECT dateOrder, email, productId, quantity, total FROM CryptoOrder";
        List<OrderEN> orders = new ArrayList<>();

        try (Connection conn = ComunDB.obtenerConexion();
             PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                OrderEN order = new OrderEN();
                order.setDateOrder(resultSet.getDate("dateOrder"));
                order.setEmail(resultSet.getString("email"));
                order.setProductId(resultSet.getInt("productId"));
                order.setQuantity(resultSet.getDouble("quantity"));
                order.setTotal(resultSet.getDouble("total"));
                orders.add(order);
            }
         } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return orders;
    }

    public OrderEN searchById(int id)  {
        String sql = "SELECT dateOrder, email, productId, quantity, total FROM CryptoOrder WHERE id = ?";
        OrderEN order = null;

        try (Connection conn = ComunDB.obtenerConexion();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    order = new OrderEN();
                    order.setDateOrder(resultSet.getDate("dateOrder"));
                    order.setEmail(resultSet.getString("email"));
                    order.setProductId(resultSet.getInt("productId"));
                    order.setQuantity(resultSet.getDouble("quantity"));
                    order.setTotal(resultSet.getDouble("total"));
                }
            }
      } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de excepciones relacionadas con la base de datos
        }

        return order;
    }
}