
package crypto.DALS;

import java.sql.Connection;
import java.sql.Date;
import crypto.accesoadatos.ComunDB;
import crypto.entidadesdenegocio.OrderEN;
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
    
 public void create(OrderEN order) throws SQLException {
        String sql = "INSERT INTO Orders (dateOrder, email, productId, quantity, total) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ComunDB.obtenerConexion();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setDate(1, (Date) order.getDateOrder());
            statement.setString(2, order.getEmail());
            statement.setInt(3, order.getId());
            statement.setDouble(4, order.getQuantity());
            statement.setDouble(5, order.getTotal());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al crear la orden.", e);
        }
    }
 
    public List<OrderEN> searchAll() throws SQLException {
        String sql = "SELECT dateOrder, email, productId, quantity, total FROM Orders";
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
        } catch (SQLException e) {
            throw new SQLException("Error al buscar todas las órdenes.", e);
        }

        return orders;
    }

    public OrderEN searchById(int id) throws SQLException {
        String sql = "SELECT dateOrder, email, productId, quantity, total FROM Orders WHERE id = ?";
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
            throw new SQLException("Error al buscar la orden por ID.", e);
        }

        return order;
    }
}