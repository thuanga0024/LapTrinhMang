package cua_hang_tien_ich_tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TCPServer {

    public static void main(String[] args) throws IOException, SQLException {
        ServerSocket svSocket = new ServerSocket(9999);
        Socket sk;
        BufferedReader bf;
        ObjectOutputStream outToClient;
        int id_product;

        while(true) {
            sk = svSocket.accept();
            bf = new BufferedReader(new InputStreamReader(sk.getInputStream()));
            id_product = Integer.parseInt(bf.readLine());

            Product product = searchProduct(id_product);

            outToClient = new ObjectOutputStream(sk.getOutputStream());
            outToClient.writeObject(product);
            outToClient.flush();
            outToClient.close(); 
            
            System.out.println("Done!");           
        }
    }

    static Connection getConnection() {
        Connection con = null;
        String url = "jdbc:jtds:sqlserver://localhost:1433/QLCH";
        String user = "sa";
        String password = "!@#qwe123";

        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return con;
    }

    static Product searchProduct(int id) throws SQLException {

        Product product = null;

        Connection con = getConnection();
        ResultSet rs;
        
        String sql_query = "select * from product where id = ?";
        PreparedStatement ppstm = con.prepareStatement(sql_query);
        ppstm.setInt(1, id);

        rs = ppstm.executeQuery();

        if(rs.next()) {
            int id_prd = rs.getInt("id");
            String name_prd = rs.getString("name");
            float price_prd = rs.getFloat("price");
            int amount_prd = rs.getInt("amount");
            product = new Product(id_prd, name_prd, price_prd, amount_prd);
        }

        return product;
    }
}