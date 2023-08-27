package crypto.accesoadatos;

import crypto.entidadesdenegocio.UserEN;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;

public class UserDAL {
    private Connection connection;
     public UserDAL() throws SQLException {
       Connection connection = ComunDB.obtenerConexion();
     
    }

    public boolean  registrarUsuario(UserEN usuario) {
        try {
            // Obtener una conexión a la base de datos utilizando ComunDB
            Connection connection = ComunDB.obtenerConexion();
            

            String sql = "INSERT INTO usuarios (nameUser, lastName, userPassword, gmail) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = ComunDB.createPreparedStatement(getConnection(), sql);
            preparedStatement.setString(1, usuario.getNameUser());
            preparedStatement.setString(2, usuario.getLastName());
            preparedStatement.setInt(3, usuario.getUserPassword());
            preparedStatement.setString(4, usuario.getGmail());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            
        }
     return false;
    }
     public boolean iniciarSesion(String gmail, int userPassword) {
        try {
            String sql = "SELECT COUNT(*) FROM usuarios WHERE gmail = ? AND userPassword = ?";
            PreparedStatement preparedStatement = ComunDB.createPreparedStatement(getConnection(), sql);
            preparedStatement.setString(1, gmail);
            preparedStatement.setInt(2, userPassword);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0; // Si hay al menos un resultado, el inicio de sesión es válido
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @return the connection
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * @param connection the connection to set
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}





