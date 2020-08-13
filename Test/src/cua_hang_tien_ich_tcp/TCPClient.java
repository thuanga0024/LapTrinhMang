package cua_hang_tien_ich_tcp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class TCPClient {
    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        menu();
        System.out.print("Chọn: ");
        int choice = Integer.parseInt(bf.readLine());

        switch (choice) {
            case 1:
                System.out.print("Nhap ma san pham: ");
                String id = bf.readLine().trim();
                Product product = getProduct(id);
                System.out.println(product.toString());
                break;

            case 2:
                invoicing();
                break;

            default:
                break;
        }

    }

    static void menu() {
        System.out.println("---------Menu----------");
        System.out.println("1. Tìm kiếm sản phẩm");
        System.out.println("2. Lập hóa đơn");
        System.out.println("Any Key. Exit");
        System.out.println("------------------------");
    }

    static Product getProduct(String id) throws UnknownHostException, IOException, ClassNotFoundException {

        Product product = null;

        Socket sk = new Socket("localhost", 9999);

        DataOutputStream outToServer = new DataOutputStream(sk.getOutputStream());
        outToServer.writeBytes(id + "\n");

        ObjectInputStream inFromServer = new ObjectInputStream(sk.getInputStream());
        product = (Product) inFromServer.readObject();

        sk.close();

        return product;
    }

    static void invoicing() throws NumberFormatException, IOException, ClassNotFoundException {
        ArrayList<LineItem> list_item = new ArrayList<LineItem>();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        float total_price = 0;

        String choice;
        do {
            System.out.print("Nhap ma san pham: ");
            String id = bf.readLine().trim();
            Product product = getProduct(id);

            System.out.print("Nhap so luong mua: ");
            int amount = Integer.parseInt(bf.readLine());

            if (amount > product.getAmount()) {
                System.out.println("San pham " + product.getId() + " chi co " + product.getAmount());
                System.out.println("Vui long chon lai");
            } else {
                LineItem li = new LineItem(product, amount);
                list_item.add(li);
            }

            System.out.print("Tiep tuc (y/n): ");
            choice = bf.readLine();
        } while (choice.equals("y"));

        System.out.println("Mã sản phẩm\tTên sản phẩm\tSố lượng mua\tGiá");
        for (LineItem x : list_item) {
            int id_prd = x.getProduct().getId();
            String name_prd = x.getProduct().getName();
            float price = x.getAmount() * x.getProduct().getPrice();
            total_price += price;

            System.out.println(id_prd + "\t" + name_prd + "\t" + x.getAmount() + "\t" + price);
        }

        System.out.println("Tổng hóa đơn: " + total_price);

    }
}