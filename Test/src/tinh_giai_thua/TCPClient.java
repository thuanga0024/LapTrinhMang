package tinh_giai_thua;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class TCPClient {

	public static void main(String[] args) throws IOException {

		String SoDuong;
		String Ketqua;

		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

		System.out.print("Please, input So Duong: ");
		SoDuong = inFromUser.readLine();

		Socket clientSocket = new Socket("localhost", 9998);

		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		outToServer.writeBytes(SoDuong + "\n");

		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

		Ketqua = inFromServer.readLine();

		System.out.println("FROM SERVER: " + Ketqua);

		clientSocket.close();
	}

}