package so_sang_chu_tcp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCP_Server {

	public static void main(String[] args) throws IOException {
		String clientnumber;
		String data;
		ServerSocket welcomeSocket = new ServerSocket(8888);
		System.out.println("\"Hello, welcome to Server, Waiting ....");
		while (true) {
			Socket connectionSocket = welcomeSocket.accept();

			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			// ĐỌC DỮ LIỆU
			clientnumber = inFromClient.readLine();
			int number = Integer.parseInt(clientnumber);

			System.out.println("Data gui tu client - Server tra ve bang chu doi voi so " + number + "\n");
			data = ReadNumber.xuat(clientnumber);
			// DataOutputStream outToClient = new
			// DataOutputStream(connectionSocket.getOutputStream());
			// outToClient.writeBytes(data+"\n");
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(connectionSocket.getOutputStream(), "UTF-8"));
			bw.write(data + "\n");
			bw.flush();
			bw.close();
		}

	}
}
