package thi_trac_nghiem_tcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class TCPServer {

	public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {

		ServerSocket svSocket = new ServerSocket(8888);

		Socket sk;
		ObjectInputStream inFromClient;
		ObjectOutputStream outFromServer;
		DataOutputStream dataOut;
		Message sendMess;
		Message receiveMess;

		while (true) {
			sk = svSocket.accept();
			List<Exam> listExam = getListExam(3);
			sendMess = new Message(listExam);
			outFromServer = new ObjectOutputStream(sk.getOutputStream());
			outFromServer.writeObject(sendMess);
			outFromServer.flush();

			inFromClient = new ObjectInputStream(sk.getInputStream());
			receiveMess = (Message) inFromClient.readObject();
			listExam = receiveMess.getListExam();

			int count = 0;
			for (Exam e : listExam) {
				if (checkAnswer(e)) {
					count += 1;
				}
			}

			String response = "Trượt";
			if (count >= 2) {
				response = "Đỗ";
			}

			dataOut = new DataOutputStream(sk.getOutputStream());
			// dataOut.writeBytes(response);
			dataOut.writeUTF(response);

			outFromServer.close();
			inFromClient.close();
			dataOut.close();
		}

	}

	static Connection getConnection() {
		Connection con = null;
		String url = "jdbc:jtds:sqlserver://localhost:1433/dethi";
		String user = "sa";
		String password = "!@#qwe123";

		try {
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return con;
	}

	static Exam getExam() throws SQLException {
		Exam exam = new Exam();

		ResultSet rs;
		Connection conn = getConnection();

		String sql_query = "select * from Exam";

		Statement stmt = conn.createStatement();
		rs = stmt.executeQuery(sql_query);

		exam.setId(rs.getInt("id"));
		exam.setQuestion(rs.getString("question"));
		exam.setAnswer1(rs.getString("answer1"));
		exam.setAnswer2(rs.getString("answer2"));
		exam.setAnswer3(rs.getString("answer3"));
		exam.setAnswer4(rs.getString("answer4"));
		conn.close();
		rs.close();

		return exam;
	}

	static List<Exam> getListExam(int amount) throws SQLException {
		ArrayList<Exam> listExam = new ArrayList<Exam>();
		ResultSet rs;
		Connection conn = getConnection();

		String sql_query = "select * from Exam ORDER BY NEWID()";
		Statement stmt = conn.createStatement();
		rs = stmt.executeQuery(sql_query);

		int i = 0;
		while (rs.next() && i < amount) {
			Exam exam = new Exam();

			exam.setId(rs.getInt("id"));
			exam.setQuestion(rs.getString("question"));
			exam.setAnswer1(rs.getString("answer1"));
			exam.setAnswer2(rs.getString("answer2"));
			exam.setAnswer3(rs.getString("answer3"));
			exam.setAnswer4(rs.getString("answer4"));

			listExam.add(exam);
		}
		conn.close();
		rs.close();

		return listExam;
	}

	static Boolean checkAnswer(Exam exam) throws SQLException {

		Boolean res = false;

		ResultSet rs;
		Connection conn = getConnection();

		String sql_query = "select result from Exam where id = ?";
		PreparedStatement ppstm = conn.prepareStatement(sql_query);
		ppstm.setInt(1, exam.getId());

		rs = ppstm.executeQuery();

		String choice = exam.getChoice().toUpperCase().replaceAll("\\,|\\s", "");
		choice = sortString(choice);
		if (rs.next()) {
			String result = rs.getString("result");
			result.replaceAll("\\,|\\s", "");

			if (result.equals(new String(choice))) {
				res = true;
			}
		}

		return res;
	}

	public static String sortString(String inputString) {
		char tempArray[] = inputString.toCharArray();
		Arrays.sort(tempArray);
		return new String(tempArray);
	}

}