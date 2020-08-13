package tinh_luong_cau_thu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPClient {

    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
        Socket clientSocket = new Socket("localhost", 9999);
        ObjectInputStream inFromServer;
        ObjectOutputStream outFromClient;
        Message sendMessage = new Message();
        Message receiveMessage;
        Player player;

        menu();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int choice = Integer.parseInt(bf.readLine());

        switch (choice) {
            case Message.ADD_PLAYER:
                System.out.print("Enter ID player: ");
                int id = Integer.parseInt(bf.readLine().trim());
                System.out.print("Enter name player: ");
                String name = bf.readLine();
                System.out.print("Enter year of birth: ");
                int year_of_birth = Integer.parseInt(bf.readLine().trim());
                System.out.print("Enter position: ");
                String position = bf.readLine();
                System.out.print("Enter salary: ");
                float salary = Float.parseFloat(bf.readLine().trim());

                player = new Player(id, name, year_of_birth, position, salary);
                sendMessage.setPlayer(player);
                sendMessage.setChoice(Message.ADD_PLAYER);

                outFromClient = new ObjectOutputStream(clientSocket.getOutputStream());
                outFromClient.writeObject(sendMessage);

                inFromServer = new ObjectInputStream(clientSocket.getInputStream());
                receiveMessage = (Message) inFromServer.readObject();
                String reponse_add_player = receiveMessage.getResponse();
                System.out.println(reponse_add_player);

                outFromClient.close();
                inFromServer.close();

                break;

            case Message.ANY_PLAYER_SALARY:
                System.out.print("Enter ID player: ");
                int id_player = Integer.parseInt(bf.readLine().trim());

                player = new Player();
                player.setId(id_player);
                sendMessage.setChoice(Message.ANY_PLAYER_SALARY);
                sendMessage.setPlayer(player);

                outFromClient = new ObjectOutputStream(clientSocket.getOutputStream());
                outFromClient.writeObject(sendMessage);

                inFromServer = new ObjectInputStream(clientSocket.getInputStream());
                receiveMessage = (Message) inFromServer.readObject();

                player = receiveMessage.getPlayer();
                if (player != null) {
                    System.out.print("Enter number of matches: ");
                    int num_of_matches = Integer.parseInt(bf.readLine().trim());
                    float salary_cal = cal_salary(player.getSalary(), player.getPosition(), num_of_matches);
                    System.out.println("Salary: " + salary_cal);
                }
                else {
                    System.out.println("Player not found!");
                }

                break;

            default:
                break;
        }
    }

    static void menu() {
        System.out.println("---------Menu----------");
        System.out.println("1. Thêm cầu thủ mới");
        System.out.println("2. Tính tổng lương tháng của cầu thủ");
        System.out.println("Any key: Exit");
        System.out.println("-----------------------");
    }

    static float cal_salary(float default_salary, String position, int num_of_matches) {
        float res = 0;

        if (position.equals("tien dao")) {
            res = (float) (default_salary + num_of_matches + (default_salary / 100 * 2.5));
        }

        else if (position.equals("tien ve") || position.equals("hau ve")) {
            res = (float) (default_salary + num_of_matches + (default_salary / 100 * 2));
        }

        else if (position.equals("thu mon")) {
            res = (float) (default_salary + num_of_matches + (default_salary / 100 * 1.5));
        }

        return res;
    }
}