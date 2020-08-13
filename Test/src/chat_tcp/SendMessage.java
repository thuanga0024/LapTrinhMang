package chat_tcp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class SendMessage implements Runnable {
    
    private Thread t;
    private Socket socket;
    private String userName;

    public SendMessage(Socket socket, String userName) {
        this.socket = socket;
        this.userName = userName;
    }

    @Override
    public void run() {

        String mess;
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        DataOutputStream dataOut;
        
        while(true) {
            try {
                System.out.print(">> ");
                mess = bf.readLine();
                if(mess != null && this.socket != null) {
                    dataOut = new DataOutputStream(this.socket.getOutputStream());
                    dataOut.writeBytes(this.userName + ": " + mess + "\n");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void start () {
        if (t == null) {
           t = new Thread (this);
           t.start ();
        }
    }
}