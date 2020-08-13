package quan_ly_thong_tin_sinh_vien_tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;
import java.util.List;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TCPClient {

    public static void main(String[] args)
            throws UnknownHostException, IOException, ClassNotFoundException, ParseException {
        Socket clientSK = new Socket("localhost", 9999);

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        ObjectInputStream inFromServer = null;
        ObjectOutputStream outFromClient = null;

        Message receiveMess = null;
        Message sendMess = new Message();

        Student std;
        List<Student> listStd = null;

        menu();

        int choice;
        System.out.print("Vui lòng chọn: ");
        choice = Integer.parseInt(bf.readLine());

        switch (choice) {
            case 1:
                sendMess.setChoice(choice);
                outFromClient = new ObjectOutputStream(clientSK.getOutputStream());
                outFromClient.writeObject(sendMess);
                outFromClient.flush();

                inFromServer = new ObjectInputStream(clientSK.getInputStream());
                receiveMess = (Message) inFromServer.readObject();
                listStd = receiveMess.getListStudent();

                for (Student e : listStd) {
                    System.out.println(e.toString());
                }
                break;

            case 2:
                std = addStudent();

                sendMess.setChoice(choice);
                sendMess.setStd(std);

                outFromClient = new ObjectOutputStream(clientSK.getOutputStream());
                outFromClient.writeObject(sendMess);
                outFromClient.flush();
                break;

            case 3:
                sendMess.setChoice(choice);
                std = new Student();

                System.out.print("Nhập id sinh viên: ");
                int id = Integer.parseInt(bf.readLine().trim());
                std.setId(id);
                sendMess.setStd(std);

                outFromClient = new ObjectOutputStream(clientSK.getOutputStream());
                outFromClient.writeObject(sendMess);
                outFromClient.flush();

                inFromServer = new ObjectInputStream(clientSK.getInputStream());
                receiveMess = (Message) inFromServer.readObject();

                std = receiveMess.getStd();

                System.out.println(std.toString());
                break;

            case 4:
                System.out.print("Nhập năm sinh: ");
                int year = Integer.parseInt(bf.readLine().trim());

                sendMess.setChoice(choice);
                sendMess.setYear(year);

                outFromClient = new ObjectOutputStream(clientSK.getOutputStream());
                outFromClient.writeObject(sendMess);
                outFromClient.flush();

                inFromServer = new ObjectInputStream(clientSK.getInputStream());
                receiveMess = (Message) inFromServer.readObject();
                listStd = receiveMess.getListStudent();

                for (Student e : listStd) {
                    System.out.println(e.toString());
                }
                break;

            case 5:
                System.out.print("Nhập quê quán: ");
                String country = bf.readLine().trim();

                sendMess.setChoice(choice);
                sendMess.setCountry(country);

                outFromClient = new ObjectOutputStream(clientSK.getOutputStream());
                outFromClient.writeObject(sendMess);
                outFromClient.flush();

                inFromServer = new ObjectInputStream(clientSK.getInputStream());
                receiveMess = (Message) inFromServer.readObject();
                listStd = receiveMess.getListStudent();

                for (Student e : listStd) {
                    System.out.println(e.toString());
                }
                break;

            default:
                break;

        }

    }

    static void menu() {
        System.out.println("=====Menu=====");
        System.out.println("1. Xem danh sách sinh viên");
        System.out.println("2. Thêm sinh viên");
        System.out.println("3. Xem thông tin sinh viên");
        System.out.println("4. Tìm nhóm sinh viên theo năm sinh");
        System.out.println("5. Tìm nhóm sinh viên theo quê quán");
        System.out.println("=============");
    }

    static Student addStudent() throws IOException, ParseException {
        Student std = new Student();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Nhập tên sinh viên: ");
        std.setName(bf.readLine());
        System.out.print("Nhập ngày sinh (dd-MM-yyyy): ");
        Date birthday = new SimpleDateFormat("dd-MM-yyyy").parse(bf.readLine());
        std.setBirthday(birthday);
        System.out.print("Nhập quê quán: ");
        std.setCountry(bf.readLine());

        bf.close();
        return std;
    }

}