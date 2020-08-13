
package SoSanhHang;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Random;


public class Sample extends UnicastRemoteObject implements IMyRemote {

    ArrayList<MatHang> listMatHang;

    public Sample(ArrayList<MatHang> listMH) throws RemoteException {
        super();
        listMatHang = listMH;
    }

    @Override
    public String randomMatHang() throws RemoteException {
        Random rd = new Random();
        int i = rd.nextInt(listMatHang.size());
        return listMatHang.get(i).TenHangHoa;
    }

    @Override
    public String kiemTraDuDoan(String TenMH, int n) throws RemoteException {
        for (MatHang mh : listMatHang) {
            if (mh.TenHangHoa.equals(TenMH)) {
                if (n > mh.GiaTien) {
                    return "Gia du doan cao!";
                }
                if (n < mh.GiaTien) {
                    return "Gia du doan thap!";
                }
                return "Gia du doan chinh xac!";
            }
        }
        return "";
    }

}
