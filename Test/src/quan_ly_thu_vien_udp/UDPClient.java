package quan_ly_thu_vien_udp;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

public class UDPClient {

    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {

        DatagramSocket clientSocket = new DatagramSocket();
        DatagramPacket sendPacket;
        DatagramPacket receivePacket;

        byte[] sendData;
        byte[] receiveData;

        ObjectInputStream inFromServer;
        ObjectOutputStream outFromClient;
        ByteArrayOutputStream byteOut;
        ByteArrayInputStream byteIn;

        Message sendMessage = new Message();
        Message receiveMessage;

        InetAddress ipAddress = InetAddress.getByName("localhost");
        int port = 7777;

        menu();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int choice = Integer.parseInt(bf.readLine());

        switch (choice) {
            case Message.SHOW_INFO:
                sendMessage.setChoice(Message.SHOW_INFO);

                byteOut = new ByteArrayOutputStream(1024);
                outFromClient = new ObjectOutputStream(byteOut);
                outFromClient.writeObject(sendMessage);
                outFromClient.flush();

                sendData = byteOut.toByteArray();
                sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, port);
                clientSocket.send(sendPacket);

                receiveData = new byte[1024];
                receivePacket = new DatagramPacket(receiveData, receiveData.length);
                clientSocket.receive(receivePacket);
                byteIn = new ByteArrayInputStream(receiveData);
                inFromServer = new ObjectInputStream(byteIn);
                receiveMessage = (Message) inFromServer.readObject();

                List<Book> listBook = receiveMessage.getListBook();

                for (Book x : listBook) {
                    System.out.println(x.toString());
                }
                outFromClient.close();
                inFromServer.close();

                break;

            case Message.BORROW_BOOK:
                sendMessage.setChoice(Message.BORROW_BOOK);
                System.out.print("Nhap ten sach muon muon: ");
                String nameBook = bf.readLine().trim();
                sendMessage.setNameBook(nameBook);

                byteOut = new ByteArrayOutputStream(1024);
                outFromClient = new ObjectOutputStream(byteOut);
                outFromClient.writeObject(sendMessage);
                outFromClient.flush();

                sendData = byteOut.toByteArray();
                sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, port);
                clientSocket.send(sendPacket);

                receiveData = new byte[1024];
                receivePacket = new DatagramPacket(receiveData, receiveData.length);
                clientSocket.receive(receivePacket);
                byteIn = new ByteArrayInputStream(receiveData);
                inFromServer = new ObjectInputStream(byteIn);
                receiveMessage = (Message) inFromServer.readObject();

                String reponse = receiveMessage.getReponse();
                System.out.println(reponse);

                outFromClient.close();
                inFromServer.close();

                break;

            case Message.RETURN_BOOK:
                sendMessage.setChoice(Message.RETURN_BOOK);
                System.out.print("Nhap ten sach muon tra: ");
                String nameBookReturn = bf.readLine().trim();
                sendMessage.setNameBook(nameBookReturn);

                byteOut = new ByteArrayOutputStream(1024);
                outFromClient = new ObjectOutputStream(byteOut);
                outFromClient.writeObject(sendMessage);
                outFromClient.flush();

                sendData = byteOut.toByteArray();
                sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, port);
                clientSocket.send(sendPacket);
                break;

            default:
                break;
        }

        clientSocket.close();
    }

    static void menu() {
        System.out.println("---------Menu----------");
        System.out.println("1. Hiển thị danh sách thông tin");
        System.out.println("2. Mượn sách");
        System.out.println("3. Trả sách");
        System.out.println("Any key: Exit");
        System.out.println("-----------------------");
    }
}