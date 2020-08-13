package quan_ly_thong_tin_sinh_vien_tcp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class TCPServer {

    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        ServerSocket svSocket = new ServerSocket(9999);
        Socket sk = null;

        ObjectInputStream objIn = null;
        ObjectOutputStream objOut = null;

        Message receiveMess = null;
        Message sendMess = null;

        Student std = null;
        List<Student> listStd = null;

        while (true) {
            sk = svSocket.accept();

            sendMess = new Message();

            objIn = new ObjectInputStream(sk.getInputStream());
            receiveMess = (Message) objIn.readObject();
            int choice = receiveMess.getChoice();

            switch (choice) {
                case 1:
                    listStd = getListStudent();
                    sendMess.setListStudent(listStd);

                    objOut = new ObjectOutputStream(sk.getOutputStream());
                    objOut.writeObject(sendMess);
                    objOut.flush();
                    break;

                case 2:
                    std = receiveMess.getStd();
                    addStudent(std);
                    break;

                case 3:
                    int id = receiveMess.getStd().getId();
                    std = getStudent(id);

                    sendMess.setStd(std);

                    objOut = new ObjectOutputStream(sk.getOutputStream());
                    objOut.writeObject(sendMess);
                    objOut.flush();
                    break;

                case 4:
                    int year = receiveMess.getYear();
                    listStd = getStudentWithYear(year);

                    sendMess.setListStudent(listStd);

                    objOut = new ObjectOutputStream(sk.getOutputStream());
                    objOut.writeObject(sendMess);
                    objOut.flush();
                    break;

                case 5:
                    String country = receiveMess.getCountry();
                    listStd = getStudentWithCountry(country);

                    sendMess.setListStudent(listStd);

                    objOut = new ObjectOutputStream(sk.getOutputStream());
                    objOut.writeObject(sendMess);
                    objOut.flush();
                    break;

                default:
                    break;
            }

            //objIn.close();
            //objOut.close();

        }
    }

    static Student getStudent(int id) throws SQLException {
        Student std = null;
        Connection con = Utils.getConnection();

        String sqlQuery = "select * from SinhVien where id = ?";

        PreparedStatement ppstm = con.prepareStatement(sqlQuery);
        ppstm.setInt(1, id);

        ResultSet rs = ppstm.executeQuery();

        if (rs.next()) {
            std = new Student();
            std.setId(rs.getInt("id"));
            std.setName(rs.getString("ho_ten"));
            std.setCountry(rs.getString("que_quan"));
            std.setBirthday(rs.getDate("ngay_sinh"));
        }

        return std;
    }

    static List<Student> getListStudent() throws SQLException {
        ArrayList<Student> listStd = new ArrayList<Student>();

        Connection con = Utils.getConnection();

        String sqlQuery = "select * from SinhVien";
        PreparedStatement ppstm = con.prepareStatement(sqlQuery);
        ResultSet rs = ppstm.executeQuery();

        while (rs.next()) {
            Student std = new Student();
            std.setId(rs.getInt("id"));
            std.setName(rs.getString("ho_ten"));
            std.setCountry(rs.getString("que_quan"));
            std.setBirthday(rs.getDate("ngay_sinh"));
            listStd.add(std);
        }

        return listStd;

    }

    static List<Student> getStudentWithCountry(String country) throws SQLException {
        ArrayList<Student> listStd = new ArrayList<Student>();

        Connection con = Utils.getConnection();

        String sqlQuery = "select * from SinhVien where que_quan = ?";

        PreparedStatement ppstm = con.prepareStatement(sqlQuery);
        ppstm.setString(1, country);

        ResultSet rs = ppstm.executeQuery();

        while (rs.next()) {
            Student std = new Student();
            std.setId(rs.getInt("id"));
            std.setName(rs.getString("ho_ten"));
            std.setCountry(rs.getString("que_quan"));
            std.setBirthday(rs.getDate("ngay_sinh"));
            listStd.add(std);
        }

        return listStd;
    }

    static List<Student> getStudentWithYear(int year) throws SQLException {
        ArrayList<Student> listStd = new ArrayList<Student>();

        Connection con = Utils.getConnection();

        String sqlQuery = "select * from SinhVien where year(ngay_sinh) = ?";
        PreparedStatement ppstm = con.prepareStatement(sqlQuery);
        ppstm.setInt(1, year);

        ResultSet rs = ppstm.executeQuery();

        while (rs.next()) {
            Student std = new Student();
            std.setId(rs.getInt("id"));
            std.setName(rs.getString("ho_ten"));
            std.setCountry(rs.getString("que_quan"));
            std.setBirthday(rs.getDate("ngay_sinh"));
            listStd.add(std);
        }

        return listStd;
    }

    static void addStudent(Student std) throws SQLException {

        Connection con = Utils.getConnection();

        String getIDQuery = "SELECT TOP 1 id FROM SinhVien ORDER BY id DESC";
        PreparedStatement ppstm = con.prepareStatement(getIDQuery);

        ResultSet rs = ppstm.executeQuery();

        int id = 0;
        if (rs.next())
            id = rs.getInt("id") + 1;

        String addQuery = "INSERT INTO SinhVien VALUES (?,?,?,?)";
        ppstm = con.prepareStatement(addQuery);
        ppstm.setInt(1, id);
        ppstm.setString(2, std.getName());

        Instant instant = std.getBirthday().toInstant();
        LocalDate localDate = ZonedDateTime.ofInstant(instant, ZoneId.of("Asia/Ho_Chi_Minh")).toLocalDate();
        Date sqlDate = Date.valueOf(localDate);
        ppstm.setDate(3, sqlDate);

        ppstm.setString(4, std.getCountry());
        ppstm.execute();
    }

}