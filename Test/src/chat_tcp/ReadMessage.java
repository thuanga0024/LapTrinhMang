package chat_tcp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReadMessage implements Runnable {

    private Thread t;
    private Socket socket;

    public ReadMessage(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        String mess;
        while(true) {
            try {
                if(this.socket != null) {
                    BufferedReader bf = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
                    mess = bf.readLine();
                    System.out.println(mess);
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