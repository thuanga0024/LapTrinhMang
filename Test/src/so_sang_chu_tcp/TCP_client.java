package so_sang_chu_tcp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class TCP_client {

	public static void main(String[] args) throws IOException {

		String number, data;
		while (true) {
			BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

			System.out.print("Please, input Number: ");
			number = inFromUser.readLine();

			Socket clientSocket = new Socket("localhost", 8888);
			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
			outToServer.writeBytes(number + "\n");

			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			data = inFromServer.readLine();

			System.out.println("FROM SERVER: " + data);
			clientSocket.close();
		}
	}

}
