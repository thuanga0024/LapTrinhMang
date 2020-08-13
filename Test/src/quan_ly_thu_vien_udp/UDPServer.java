package quan_ly_thu_vien_udp;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UDPServer {

    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {

        DatagramSocket svSocket = new DatagramSocket(7777);

        DatagramPacket sendPacket;
        DatagramPacket receivePacket;

        byte[] sendData;
        byte[] receiveData;

        ObjectInputStream inFromClient;
        ObjectOutputStream outFromServer;
        ByteArrayOutputStream byteOut;
        ByteArrayInputStream byteIn;

        Message sendMessage;
        Message receiveMessage;

        InetAddress ipAddress;
        int port;

        while (true) {
            receiveData = new byte[1024];
            receivePacket = new DatagramPacket(receiveData, receiveData.length);
            svSocket.receive(receivePacket);

            byteIn = new ByteArrayInputStream(receiveData);
            inFromClient = new ObjectInputStream(byteIn);

            receiveMessage = (Message) inFromClient.readObject();
            sendMessage = new Message();

            ipAddress = receivePacket.getAddress();
            port = receivePacket.getPort();

            int choice = receiveMessage.getChoice();

            switch (choice) {
                case Message.SHOW_INFO:
                    List<Book> listBook = getAllBook();
                    sendMessage.setListBook(listBook);

                    byteOut = new ByteArrayOutputStream(1024);
                    outFromServer = new ObjectOutputStream(byteOut);
                    outFromServer.writeObject(sendMessage);
                    outFromServer.flush();
                    sendData = byteOut.toByteArray();

                    sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, port);
                    svSocket.send(sendPacket);

                    outFromServer.close();
                    System.out.println("Show info done.");
                    break;

                case Message.BORROW_BOOK:
                    String nameBook_borrow = receiveMessage.getNameBook();
                    String reponse = borrowBook(nameBook_borrow);
                    sendMessage.setReponse(reponse);

                    byteOut = new ByteArrayOutputStream(1024);
                    outFromServer = new ObjectOutputStream(byteOut);
                    outFromServer.writeObject(sendMessage);
                    outFromServer.flush();
                    sendData = byteOut.toByteArray();

                    sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, port);
                    svSocket.send(sendPacket);
                    outFromServer.close();

                    System.out.println("borrow book done.");
                    break;

                case Message.RETURN_BOOK:
                    String nameBook_return = receiveMessage.getNameBook();
                    returnBook(nameBook_return);
                    System.out.println("return book done.");
                    break;

                default:
                    break;
            }

        }
    }

    public static Connection getConnection() {
        Connection conn = null;
        String url = "jdbc:jtds:sqlserver://localhost:1433/AT14N01_QLTV";
        String user = "sa";
        String password = "!@#qwe123";

        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

    public static List<Book> getAllBook() throws SQLException {

        ArrayList<Book> listBook = new ArrayList<Book>();//
        ResultSet rs;
        Connection conn = getConnection();

        String sql = "SELECT * FROM book";

        Statement stmt = conn.createStatement();
        rs = stmt.executeQuery(sql);

        while (rs.next()) {
            Book book = new Book();

            book.setId(rs.getInt("id"));
            book.setName(rs.getString("name"));
            book.setPublisher(rs.getString("publisher"));
            book.setTotal(rs.getInt("total"));
            book.setAmount_borrowed(rs.getInt("amount_borrowed"));

            listBook.add(book);
        }
        conn.close();
        rs.close();

        return listBook;
    }

    public static String borrowBook(String nameBook) throws SQLException {

        String reponse = "";

        Connection conn = getConnection();
        ResultSet rs;

        String get_query = "select amount_borrowed, total from book where name = ?";
        PreparedStatement ppstm = conn.prepareStatement(get_query);
        ppstm.setString(1, nameBook);

        rs = ppstm.executeQuery();

        if (rs.next()) {
            int amount_borrorwed = rs.getInt("amount_borrowed");
            int total = rs.getInt("total");
            if (total > amount_borrorwed) {
                String update_query = "update book set amount_borrowed = ? where name = ?";
                ppstm = conn.prepareStatement(update_query);
                ppstm.setInt(1, amount_borrorwed + 1);
                ppstm.setString(2, nameBook);
                ppstm.executeUpdate();

                reponse = "Ok";
            } else if (total > amount_borrorwed) {
                reponse = "Het sach";
            }
        }

        else {
            reponse = "Khong co sach: " + nameBook;
        }

        return reponse;
    }

    public static void returnBook(String nameBook) throws SQLException {

        Connection conn = getConnection();
        ResultSet rs;

        String get_query = "select amount_borrowed from book where name = ?";
        PreparedStatement ppstm = conn.prepareStatement(get_query);
        ppstm.setString(1, nameBook);

        rs = ppstm.executeQuery();
        if (rs.next()) {
            int amount_borrorwed = rs.getInt("amount_borrowed");

            String update_query = "update book set amount_borrowed = ? where name = ?";
            ppstm = conn.prepareStatement(update_query);
            ppstm.setInt(1, amount_borrorwed - 1);
            ppstm.setString(2, nameBook);
            ppstm.executeUpdate();
        }
    }

}