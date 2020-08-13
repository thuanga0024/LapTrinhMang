package tinh_giai_thua;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

	public static void main(String[] args) throws IOException {

		String clientSoDuong;
		long Ketqua;

		ServerSocket welcomeSocket = new ServerSocket(9998);

		System.out.println("Hello, welcome to Server, Waiting ...");
		while (true) {

			Socket connectionSocket = welcomeSocket.accept();

			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

			clientSoDuong = inFromClient.readLine();

			int SoDuong = Integer.parseInt(clientSoDuong);
			Ketqua = TCPServer.tinhGiaithua(SoDuong);

			DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
			outToClient.writeBytes(Ketqua + "\n");

			System.out.println("Done !!!");
		}
	}

	public static long tinhGiaithua(int n) {
		long giaithua = 1;
		if (n == 0 || n == 1) {
			return giaithua;
		} else {
			for (int i = 2; i <= n; i++) {
				giaithua *= i;
			}
			return giaithua;
		}
	}
}