package TGK_Lan2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class TCPServer {

    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {

        ServerSocket svSocket = new ServerSocket(9999);
        Socket sk;
        BufferedReader bf;
        ObjectInputStream inFromClient;
        ObjectOutputStream outFromServer;

        while(true) {
            sk = svSocket.accept();
            inFromClient = new ObjectInputStream(sk.getInputStream());

            Message receiveMessage = (Message) inFromClient.readObject();
            Message sendMessage = new Message();

            int choice = receiveMessage.getChoice();
            
            switch (choice) {
                case Message.GET_LIST_ACCOUNT:
                    List<Account> listAccount = getListAccount();
                    sendMessage.setList_account(listAccount);
                    outFromServer = new ObjectOutputStream(sk.getOutputStream());
                    outFromServer.writeObject(sendMessage);
                    outFromServer.flush();
                    outFromServer.close();
                    System.out.println("Get list account done.");
                    break;
                
                case Message.REGIST_ACCOUNT:
                    Account acc_to_regist = receiveMessage.getAccount();
                    String reponse = registAccount(acc_to_regist);
                    sendMessage.setReponse(reponse);

                    outFromServer = new ObjectOutputStream(sk.getOutputStream());
                    outFromServer.writeObject(sendMessage);
                    outFromServer.flush();
                    outFromServer.close();
                    
                    System.out.println("Regist Account done.");
                    break;

                case Message.WITHDRAW:
                    Account acc_to_withdraw = receiveMessage.getAccount();
                    String reponse_withdraw = withdraw(acc_to_withdraw);
                    sendMessage.setReponse(reponse_withdraw);

                    outFromServer = new ObjectOutputStream(sk.getOutputStream());
                    outFromServer.writeObject(sendMessage);
                    outFromServer.flush();
                    outFromServer.close();
                    System.out.println("Withdraw done.");
                    break;

                case Message.DEPOSIT:
                    Account acc_to_deposit = receiveMessage.getAccount();
                    String reponse_deposit = deposit(acc_to_deposit);
                    sendMessage.setReponse(reponse_deposit);

                    outFromServer = new ObjectOutputStream(sk.getOutputStream());
                    outFromServer.writeObject(sendMessage);
                    outFromServer.flush();
                    outFromServer.close();
                    System.out.println("Withdraw done.");
                    break;

                default:
                    break;
            }
        }
        
    }

    static Connection getConnection() {
        Connection con = null;
        String url = "jdbc:jtds:sqlserver://localhost:1433/AT14N01_Bank";
        String user = "sa";
        String password = "!@#qwe123";

        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return con;
    }

    static List<Account> getListAccount() throws SQLException {

        ArrayList<Account> list_account = new ArrayList<Account>();
        
        ResultSet rs;
		Connection conn = getConnection();
		
		String sql_query = "select * from account";
		
		Statement stmt = conn.createStatement();
		rs = stmt.executeQuery(sql_query);

		while(rs.next()) {
            Account account = new Account();
            
            account.setId(rs.getInt("id"));
            account.setBalance(rs.getFloat("balance"));
            account.setWithdraw_day(rs.getDate("withdraw_day"));
            account.setDeposit_day(rs.getDate("deposit_day"));
			
			list_account.add(account);
		}	
		conn.close();
		rs.close();
	
        return list_account;
    }

    static String registAccount(Account account) throws SQLException {
        String reponse = "";

        Connection conn = getConnection();
        ResultSet rs;

        String get_query = "select id from account where id = ?";
        PreparedStatement ppstm = conn.prepareStatement(get_query);
        ppstm.setInt(1, account.getId());

        rs = ppstm.executeQuery();

        if(rs.next()) {
            reponse = "Trung ma tai khoan";
        }

        else {
            String insert_query = "insert into account(id, balance, deposit_day) values (?,?,?)";
            ppstm = conn.prepareStatement(insert_query);
            
            ppstm.setInt(1, account.getId());
            ppstm.setFloat(2, account.getBalance());
            LocalDate todayLocalDate = LocalDate.now(ZoneId.of("Asia/Ho_Chi_Minh"));
            Date sqlDate = Date.valueOf(todayLocalDate);
            ppstm.setDate(3, sqlDate);

            ppstm.execute();

            reponse = "ok";
        }

        return reponse;
    }

    static String withdraw(Account account) throws SQLException {
        String reponse = "";

        Connection conn = getConnection();
        ResultSet rs;

        String get_query = "select id, balance from account where id = ?";
        PreparedStatement ppstm = conn.prepareStatement(get_query);
        ppstm.setInt(1, account.getId());

        rs = ppstm.executeQuery();

        if(rs.next()) {
            float withdraw_money = account.getBalance();
            float old_balance = rs.getFloat("balance");
            float new_balance = old_balance - withdraw_money;

            String update_query = "update account set balance = ?, withdraw_day = ? where id = ?";
            ppstm = conn.prepareStatement(update_query);

            if(new_balance < 0) {
                ppstm.setFloat(1, 0);
                reponse = "Ban da rut: " + old_balance;
            }
            else {
                ppstm.setFloat(1, new_balance);
                reponse = "Ban da rut: " + withdraw_money;
            }

            LocalDate todayLocalDate = LocalDate.now(ZoneId.of("Asia/Ho_Chi_Minh"));
            Date sqlDate = Date.valueOf(todayLocalDate);
            ppstm.setDate(2, sqlDate);
            ppstm.setInt(3, account.getId());

            ppstm.execute();
        }
        else {
            reponse = "Sai ma tai khoan";
        }

        return reponse;
    }


    static String deposit(Account account) throws SQLException {
        String reponse = "";

        Connection conn = getConnection();
        ResultSet rs;

        String get_query = "select id, balance from account where id = ?";
        PreparedStatement ppstm = conn.prepareStatement(get_query);
        ppstm.setInt(1, account.getId());

        rs = ppstm.executeQuery();

        if(rs.next()) {
            float deposit_money = account.getBalance();
            float old_balance = rs.getFloat("balance");
            float new_balance = old_balance + deposit_money;

            String update_query = "update account set balance = ?, deposit_day = ? where id = ?";
            ppstm = conn.prepareStatement(update_query);

            ppstm.setFloat(1, new_balance);

            LocalDate todayLocalDate = LocalDate.now(ZoneId.of("Asia/Ho_Chi_Minh"));
            Date sqlDate = Date.valueOf(todayLocalDate);
            ppstm.setDate(2, sqlDate);

            ppstm.setInt(3, account.getId());

            ppstm.execute();
            reponse = "tong so tien hien tai: " + new_balance;
        }
        else {
            reponse = "Sai ma tai khoan";
        }

        return reponse;
    }

}
