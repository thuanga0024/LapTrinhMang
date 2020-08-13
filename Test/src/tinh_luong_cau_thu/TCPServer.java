package tinh_luong_cau_thu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TCPServer {
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        ServerSocket svSocket = new ServerSocket(9999);
        Socket sk;
        BufferedReader bf;
        ObjectInputStream inFromClient;
        ObjectOutputStream outFromServer;
        Player player;
        String response;

        while (true) {
            sk = svSocket.accept();
            inFromClient = new ObjectInputStream(sk.getInputStream());

            Message receiveMessage = (Message) inFromClient.readObject();
            Message sendMessage = new Message();

            int choice = receiveMessage.getChoice();

            switch (choice) {
                case Message.ADD_PLAYER:
                    player = receiveMessage.getPlayer();
                    response = addPlayer(player);
                    sendMessage.setResponse(response);

                    outFromServer = new ObjectOutputStream(sk.getOutputStream());
                    outFromServer.writeObject(sendMessage);
                    outFromServer.flush();
                    outFromServer.close();
                    System.out.println("Add player done.");

                    break;

                case Message.ANY_PLAYER_SALARY:
                    player = receiveMessage.getPlayer();
                    int id = player.getId();
                    player = getPlayer(id);

                    sendMessage.setPlayer(player);
                    outFromServer = new ObjectOutputStream(sk.getOutputStream());
                    outFromServer.writeObject(sendMessage);
                    outFromServer.flush();
                    outFromServer.close();
                    System.out.println("Get player done.");

                    break;

                default:
                    break;
            }
        }
    }

    static Connection getConnection() {
        Connection con = null;
        String url = "jdbc:jtds:sqlserver://localhost:1433/PlayerFootball";
        String user = "sa";
        String password = "!@#qwe123";

        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return con;
    }

    static String addPlayer(Player player) throws SQLException {
        String reponse = "";

        Connection conn = getConnection();
        ResultSet rs;

        String get_query = "select id from player where id = ?";
        PreparedStatement ppstm = conn.prepareStatement(get_query);
        ppstm.setInt(1, player.getId());

        rs = ppstm.executeQuery();

        if (rs.next()) {
            reponse = "Duplicate ID player";
        }

        else {
            String insert_query = "insert into player(id, name_player, year_of_birth, position_player, salary) values (?,?,?,?,?)";
            ppstm = conn.prepareStatement(insert_query);

            ppstm.setInt(1, player.getId());
            ppstm.setString(2, player.getName());
            ppstm.setInt(3, player.getYear_of_birth());
            ppstm.setString(4, player.getPosition());
            ppstm.setFloat(5, player.getSalary());
            ppstm.execute();

            reponse = "Add player success";
        }
        return reponse;
    }

    static Player getPlayer(int id) throws SQLException {
        Player player = null;
        Connection conn = getConnection();
        ResultSet rs;

        String get_query = "select * from player where id = ?";
        PreparedStatement ppstm = conn.prepareStatement(get_query);
        ppstm.setInt(1, id);
        rs = ppstm.executeQuery();

        if (rs.next()) {
            player = new Player();
            player.setId(rs.getInt("id"));
            player.setName(rs.getString("name"));
            player.setYear_of_birth(rs.getInt("year_of_birth"));
            player.setPosition(rs.getString("position_player"));
            player.setSalary(rs.getFloat("salary"));
        }

        return player;
    }

}