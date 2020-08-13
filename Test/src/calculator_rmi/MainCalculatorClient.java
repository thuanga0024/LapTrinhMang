package calculator_rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class MainCalculatorClient {

    public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {

        IntCalculator intCal = (IntCalculator) Naming.lookup("rmi://localhost:6666/calRemote");
        Scanner sc = new Scanner(System.in);

        menu();
        System.out.print("Nhập x: ");
        float x = Float.parseFloat(sc.nextLine());
        System.out.print("Nhập y: ");
        float y = Float.parseFloat(sc.nextLine());
        System.out.print("Nhập lựa chọn: ");
        int choice = Integer.parseInt(sc.nextLine());

        switch (choice) {
            case 1:
                System.out.println("Kết quả: " + intCal.addition(x, y));
                break;

            case 2:
                System.out.println("Kết quả: " + intCal.subtraction(x, y));
                break;

            case 3:
                System.out.println("Kết quả: " + intCal.multiplication(x, y));
                break;

            case 4:
                System.out.println("Kết quả: " + intCal.division(x, y));
                break;

            case 5:
                System.out.println("Kết quả: " + intCal.greatestCommonDivisor(x, y));
                break;

            default:
                break;
        }

    }

    static void menu() {
        System.out.println("_____________MENU______________");
        System.out.println("1. Cộng");
        System.out.println("2. Trừ");
        System.out.println("3. Nhân");
        System.out.println("4. Chia");
        System.out.println("5. Ước chung lớn nhất");
    }

}