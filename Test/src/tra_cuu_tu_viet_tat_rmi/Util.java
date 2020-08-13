package tra_cuu_tu_viet_tat_rmi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public static Connection getConnection() {
        Connection con = null;
        String url = "jdbc:jtds:sqlserver://localhost:1433/ThuatNgu";
        String user = "sa";
        String password = "!@#qwe123";

        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return con;
    }
}
