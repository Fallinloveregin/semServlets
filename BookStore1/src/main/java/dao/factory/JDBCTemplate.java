package dao.factory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCTemplate {
    public static Connection con;
    public static PreparedStatement ptmt;
    public static ResultSet rs;

    public static Connection getConnection() {
        return ConnectionFactory.getInstance().getConnection();
    }

    public static void closeResources() {
        try {
            if (rs != null)
                rs.close();
            if (ptmt != null)
                ptmt.close();
            if (con != null)
                con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
