package tra_cuu_tu_viet_tat_rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws SQLException {
        try {
            IntAcronym intAcro = (IntAcronym) Naming.lookup("rmi://localhost:9999/getAcronym");
            menu();
            Scanner input = new Scanner(System.in);
            System.out.print("Nhập lựa chọn: ");
            int choice = Integer.parseInt(input.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Nhập từ viết tắt: ");
                    String acronym = input.nextLine().trim();
                    String full = intAcro.shortToFull(acronym);
                    System.out.println(acronym + " - " + full);
                    break;

                case 2:
                    System.out.print("Nhập từ đầy đủ: ");
                    String full2 = input.nextLine().trim();
                    String acronym2 = intAcro.fullToShort(full2);
                    System.out.println(acronym2 + " - " + full2);
                    break;

                default:
                    break;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }

    static void menu() {
        System.out.println("=======Menu========");
        System.out.println("1. Chuyển từ viết tắt sang đầy đủ");
        System.out.println("2. Chuyển từ đầy đủ sang viết tắt");
        System.out.println("Nút bất kỳ: thoát");
        System.out.println("===================");
    }
}