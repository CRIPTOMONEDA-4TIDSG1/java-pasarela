
package comunDB;

/**
 *
 * @author themi
 */

import java.sql.*;

public class ComunDB {
    class TipoDB {
        static final int MYSQL = 1; // Propiedad que tendrá valor 1 para saber que es MySQL
    }
    
    static int TIPODB = TipoDB.MYSQL; // Propiedad para el tipo de gestor de base de datos que estamos utilizando
    
    static String connectionUrl = "jdbc:mysql://localhost:3306/PaymentGateway?user=root&password=root_123";
    
    public static Connection obtenerConexion() throws SQLException {
        // Registrar el Driver de la conexión a la base de datos MySQL
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        Connection connection = DriverManager.getConnection(connectionUrl);
        return connection;
    }
    
    public static Statement createStatement(Connection pConn) throws SQLException {
        Statement statement = pConn.createStatement();
        return statement;
    }
    
    public static PreparedStatement createPreparedStatement(Connection pConn, String pSql) throws SQLException {
        PreparedStatement statement = pConn.prepareStatement(pSql);
        return statement;
    }
    
    public static ResultSet obtenerResultSet(Statement pStatement, String pSql) throws SQLException {
        ResultSet resultSet = pStatement.executeQuery(pSql);
        return resultSet;
    }
    
    public static ResultSet obtenerResultSet(PreparedStatement pPreparedStatement) throws SQLException {
        ResultSet resultSet = pPreparedStatement.executeQuery();
        return resultSet;
    }
    
    public static int ejecutarSQL(String pSql) throws SQLException {
        int result;
        try (Connection connection = obtenerConexion();) {
            Statement statement = connection.createStatement();
            result = statement.executeUpdate(pSql);
        } catch (SQLException ex) {
            throw ex;
        }
        return result;
    }
    
    class UtilQuery {
        private String SQL;
        private PreparedStatement statement;
        private int numWhere;
        
        public UtilQuery() {
        }
        
        public UtilQuery(String SQL, PreparedStatement statement, int numWhere) {
            this.SQL = SQL;
            this.statement = statement;
            this.numWhere = numWhere;
        }
        
        public String getSQL() {
            return SQL;
        }
        
        public void setSQL(String SQL) {
            this.SQL = SQL;
        }
        
        public PreparedStatement getStatement() {
            return statement;
        }
        
        public void setStatement(PreparedStatement statement) {
            this.statement = statement;
        }
        
        public int getNumWhere() {
            return numWhere;
        }
        
        public void setNumWhere(int numWhere) {
            this.numWhere = numWhere;
        }
        
        public void AgregarWhereAnd(String pSql) {
            if (this.SQL != null) {
                if (this.numWhere == 0) {
                    this.SQL += " WHERE ";
                } else {
                    this.SQL += " AND ";
                }
                this.SQL += pSql;
            }
            this.numWhere++;
        }
    }
}