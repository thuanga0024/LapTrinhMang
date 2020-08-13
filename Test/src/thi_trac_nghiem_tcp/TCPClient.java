package thi_trac_nghiem_tcp;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

class TCPClient {

	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {

		Socket clientSocket = new Socket("localhost", 8888);

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		ObjectInputStream inFromServer = new ObjectInputStream(clientSocket.getInputStream());
		Message receiveMess = (Message) inFromServer.readObject();
		List<Exam> listExam = receiveMess.getListExam();

		String choice;
		for (Exam e : listExam) {
			System.out.println("Câu hỏi: " + e.getQuestion());
			System.out.println(e.getAnswer1());
			System.out.println(e.getAnswer2());
			System.out.println(e.getAnswer3());
			System.out.println(e.getAnswer4());
			System.out.print("Chọn câu trả lời: ");
			choice = bf.readLine();
			e.setChoice(choice);
		}

		ObjectOutputStream outFromClient = new ObjectOutputStream(clientSocket.getOutputStream());
		Message sendMess = new Message(listExam);
		outFromClient.writeObject(sendMess);
		outFromClient.flush();

		DataInputStream dataIn = new DataInputStream(clientSocket.getInputStream());
		String resss = dataIn.readUTF();
		System.out.println(resss);

	}
}