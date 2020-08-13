package hay_chon_gia_dung_rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		try {
			Scanner input = new Scanner(System.in);
			Remote remote = Naming.lookup("rmi://localhost:7777/haychongiadung");
			IntHayChonGiaDung hayChonGiaDung = (IntHayChonGiaDung) remote;
			MatHang matHang = hayChonGiaDung.getMatHang();
			System.out.println("Bạn hãy dự đoán giá của sản phẩm " + matHang.getTenHangHoa());
			System.out.println("Bạn có 7 lần dự đoán....Start!!!");
			float giaDuDoan;
			for (int i = 1; i <= 7; i++) {
				System.out.println("Lượt đoán " + i);
				System.out.print("Hãy đưa ra giá dự đoán của bạn: ");
				giaDuDoan = Float.parseFloat(input.nextLine());

				int resFromSV = hayChonGiaDung.checkDuDoan(giaDuDoan, matHang);

				if (resFromSV == -1)
					System.out.println("Giá dự đoán thấp");
				else if (resFromSV == 1)
					System.out.println("Giá dự đoán cao");
				else if (resFromSV == 0) {
					System.out.println("Giá dự đoán chính xác");
					break;
				} else {
					System.out.println("Error From Server.");
					break;
				}
			}
			System.out.println("Đã kết thúc phiên hãy chọn giá đúng");
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
