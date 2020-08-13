package tra_cuu_tu_dien_rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class Client {
    public static String url = "rmi://localhost:7777/";

    public static void main(String[] args) {
        try {
            IntDictionary lookup = (IntDictionary) Naming.lookup(url + "getDictionary");
            System.out.println("--------------- Hint -------------");
            System.out.println("1:ENGLISH TO VIETNAMESE");
            System.out.println("2:VIETNAMESE TO ENGLISH");
            System.out.println("3:ENGLISH TO MEANING");
            System.out.println("4:Thoat");
            Scanner input = new Scanner(System.in);
            System.out.print("Nhap lua chon cua ban: ");
            int choose = Integer.parseInt(input.nextLine());
            
            switch (choose) {
                case 1:
                    System.out.print("Nhap tu tieng anh ban muon dich: ");
                    String english = input.nextLine();
                    System.out.println("Nghia cua tu " + english + " trong tieng viet la:"
                            + lookup.translate_english_to_vietnamese(english));
                    break;
                case 2:
                    System.out.print("Nhap tu tieng viet ban muon dich: ");
                    String vietnamese = input.nextLine();
                    System.out.println("Nghia cua tu " + vietnamese + " trong tieng anh la: "
                            + lookup.translate_vietnamese_to_english(vietnamese));
                    break;
                case 3:
                    System.out.print("Nhap tu tieng anh ban muon tim hieu nghia: ");
                    String english1 = input.nextLine();
                    System.out.println(
                            "Nghia cua tu " + english1 + " la: " + lookup.translate_english_to_meaning(english1));
                    break;
                case 4:
                    break;
            }

            System.out.println("Da ket thuc phien.");
            input.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }

}
