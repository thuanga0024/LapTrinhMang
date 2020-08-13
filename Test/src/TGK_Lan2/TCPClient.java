package TGK_Lan2;

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
            case Message.GET_LIST_ACCOUNT:
                sendMessage.setChoice(Message.GET_LIST_ACCOUNT);

                outFromClient = new ObjectOutputStream(clientSocket.getOutputStream());
                outFromClient.writeObject(sendMessage);
                
                inFromServer = new ObjectInputStream(clientSocket.getInputStream());
                receiveMessage = (Message) inFromServer.readObject();
                List<Account> listAccount = receiveMessage.getList_account();

                for (Account x : listAccount) {
                    System.out.println(x.toString());
                }
                outFromClient.close();
                inFromServer.close();

                break;

            case Message.REGIST_ACCOUNT:
                Account acc_to_regist = new Account();

                sendMessage.setChoice(Message.REGIST_ACCOUNT);
                System.out.print("Nhap ma tai khoan: ");
                int id = Integer.parseInt(bf.readLine().trim());
                System.out.print("Nhap so tien nap vao lan dau: ");
                float balance = Float.parseFloat(bf.readLine().trim());

                acc_to_regist.setId(id);
                acc_to_regist.setBalance(balance);

                sendMessage.setAccount(acc_to_regist);

                outFromClient = new ObjectOutputStream(clientSocket.getOutputStream());
                outFromClient.writeObject(sendMessage);

                inFromServer = new ObjectInputStream(clientSocket.getInputStream());
                receiveMessage = (Message) inFromServer.readObject();
                String reponse_regist = receiveMessage.getReponse();
                System.out.println(reponse_regist);

                outFromClient.close();
                inFromServer.close();

                break;

            case Message.DEPOSIT:
                sendMessage.setChoice(Message.DEPOSIT);

                Account acc_to_deposit = new Account();
                System.out.print("Nhap ma tai khoan: ");
                int id_deposit = Integer.parseInt(bf.readLine().trim());
                System.out.print("Nhap so tien muon nap: ");
                float deposit_amount = Float.parseFloat(bf.readLine().trim());
                
                acc_to_deposit.setId(id_deposit);
                acc_to_deposit.setBalance(deposit_amount);

                sendMessage.setAccount(acc_to_deposit);

                outFromClient = new ObjectOutputStream(clientSocket.getOutputStream());
                outFromClient.writeObject(sendMessage);

                inFromServer = new ObjectInputStream(clientSocket.getInputStream());
                receiveMessage = (Message) inFromServer.readObject();
                String reponse_deposit = receiveMessage.getReponse();
                System.out.println(reponse_deposit);

                outFromClient.close();
                inFromServer.close();

                break;
            
            case Message.WITHDRAW:
                sendMessage.setChoice(Message.WITHDRAW);

                Account acc_to_withdraw = new Account();
                System.out.print("Nhap ma tai khoan: ");
                int id_withdraw = Integer.parseInt(bf.readLine().trim());
                System.out.print("Nhap so tien muon rut: ");
                float withdraw_amount = Float.parseFloat(bf.readLine().trim());
                
                acc_to_withdraw.setId(id_withdraw);
                acc_to_withdraw.setBalance(withdraw_amount);

                sendMessage.setAccount(acc_to_withdraw);

                outFromClient = new ObjectOutputStream(clientSocket.getOutputStream());
                outFromClient.writeObject(sendMessage);

                inFromServer = new ObjectInputStream(clientSocket.getInputStream());
                receiveMessage = (Message) inFromServer.readObject();
                String reponse_withdraw = receiveMessage.getReponse();
                System.out.println(reponse_withdraw);

                outFromClient.close();
                inFromServer.close();

                break;

            default:
                break;
        }

        clientSocket.close();
    }

    static void menu() {
        System.out.println("---------Menu----------");
        System.out.println("1. Lấy danh sách tài khoản");
        System.out.println("2. Tạo tài khoản");
        System.out.println("3. Rút tiền");
        System.out.println("4. Nạp tiền");
        System.out.println("Any key: Exit");
        System.out.println("-----------------------");
    }
}