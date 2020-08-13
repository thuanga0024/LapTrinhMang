package quan_ly_thong_tin_sinh_vien_tcp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Utils {
    public static Connection getConnection() {
        Connection con = null;
        String url = "jdbc:jtds:sqlserver://localhost:1433/QuanLySinhVien";
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