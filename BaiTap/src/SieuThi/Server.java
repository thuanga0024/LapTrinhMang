
package SieuThi;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    public static void main(String[] args) {
        //doc file
        ArrayList<MatHang> listMatHang = new ArrayList<MatHang>();
        try {
            FileReader fr = new FileReader("mathang.txt");
            BufferedReader br = new BufferedReader(fr);
            String s = "";
            while ((s = br.readLine()) != null) {
                String[] a = s.split("\\$");
                //a[0]: ID
                //a[1]: Ten
                //a[2]: gia tien
                //a[3]: so luong ton kho
                MatHang mh = new MatHang(a[0], a[1], Integer.parseInt(a[2]), Integer.parseInt(a[3]));
                listMatHang.add(mh);
            }
            br.close();
            fr.close();
        } catch (Exception e) {
        }

        //khoi tao ket noi
        try {
            ServerSocket myServer = new ServerSocket(2811);
            System.out.println("Da khoi tao Server TCP, cho doi Client ket noi...");
            while (true) {
                Socket socket = myServer.accept();
                System.out.println("CLient da ket noi!");
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

                //bat dau truyen du lieu
                int k = 0;
                String s = "";
                while (k != 3) {
                    k = dis.readInt();
                    switch (k) {
                        case 1:     //Tim kiem mat hang theo ma hang
                            s = dis.readUTF();
                            int count = 0;
                            for (MatHang mh : listMatHang) {
                                if (mh.ID.equals(s)) {
                                    dos.writeUTF(mh.ThongTinhChiTiet());
                                    count++;
                                    break;
                                }
                            }
                            if (count == 0) {
                                dos.writeUTF("Khong tim thay Mat Hang co ID la: " + s);
                            }
                            break;
                        case 2:     //Lap hoa don thanh toan
                            int check = 0;
                            int sum = 0;      //tinh tong tien cua hoa don
                            String HoaDon = "";
                            String GhiChu = "";
                            while (check != 2) {
                                check = dis.readInt();
                                switch (check) {
                                    case 1:
                                        String ID_canmua = dis.readUTF();
                                        int SoLuong_canmua = dis.readInt();
                                        for (MatHang mh : listMatHang) {
                                            if (mh.ID.equals(ID_canmua)) {
                                                if (mh.SoLuongTonKho >= SoLuong_canmua) {
                                                    mh.SoLuongTonKho -= SoLuong_canmua;
                                                } else {
                                                    GhiChu += "Mat hang " + mh.TenHang + " thieu " + (SoLuong_canmua - mh.SoLuongTonKho) + "\n";
                                                    SoLuong_canmua = mh.SoLuongTonKho;
                                                    mh.SoLuongTonKho = 0;
                                                }
                                                sum = sum + mh.GiaTien * SoLuong_canmua;
                                                HoaDon = HoaDon + "\n"
                                                        + "\tTen Mat Hang: " + mh.TenHang
                                                        + "\tGia tien: " + mh.GiaTien
                                                        + "\tSo luong: " + SoLuong_canmua
                                                        + "\tThanh tien: " + (mh.GiaTien * SoLuong_canmua);
                                                break;
                                            }
                                        }
                                        break;
                                    case 2:
                                        dos.writeInt(sum);
                                        dos.writeUTF(HoaDon);
                                        dos.writeUTF(GhiChu);
                                        break;
                                    default:
                                        System.out.println("Nhap sai!");
                                        break;
                                }

                            }
                            break;
                        default:
                            break;
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
