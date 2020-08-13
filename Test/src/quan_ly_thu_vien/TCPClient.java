package quan_ly_thu_vien;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

public class TCPClient {

    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {

        Socket clientSocket = new Socket("localhost", 9999);
        ObjectInputStream inFromServer;
        ObjectOutputStream outFromClient;
        Message sendMessage = new Message();
        Message receiveMessage;

        menu();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int choice = Integer.parseInt(bf.readLine());

        switch (choice) {
            case Message.SHOW_INFO:
                sendMessage.setChoice(Message.SHOW_INFO);

                outFromClient = new ObjectOutputStream(clientSocket.getOutputStream());
                outFromClient.writeObject(sendMessage);
                
                inFromServer = new ObjectInputStream(clientSocket.getInputStream());
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

                outFromClient = new ObjectOutputStream(clientSocket.getOutputStream());
                outFromClient.writeObject(sendMessage);

                inFromServer = new ObjectInputStream(clientSocket.getInputStream());
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

                outFromClient = new ObjectOutputStream(clientSocket.getOutputStream());
                outFromClient.writeObject(sendMessage);

                outFromClient.close();

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